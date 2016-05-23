package edu.nju.task;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.dao.RepoDaoImpl;
import edu.nju.model.Repository;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by Dora on 2016/5/17.
 */
@Component("taskJob")
public class UpdateDBTask {
    private static final Logger LOG = Logger.getLogger(UpdateDBTask.class);

    @Resource
    RepoDaoImpl dao;

    //每分钟的10秒执行
    @Scheduled(cron = "01 02 00 * * ?")
    public void job(){
//        LOG.info(dao.getAllFullname().get(1));
       // LOG.info("hello。。。。");




        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        try {
            dt = sdf.parse(sdf.format(date.getTime()));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }


        Map<String,Integer> map = new HashMap<>();

        for(int i=0;i<5;i++) {
            String url = "http://data.githubarchive.org/"+sdf.format(dt)+"-"+(12+i)+".json.gz";
            System.out.println(url);
            String path = "src/main/resources/data.json.gz";
            try {
                HttpRequest.downloadFile(path, url);

                System.out.println("download "+sdf.format(dt)+"-"+(12+i)+" successfully!");
                getRepoForkMap(path,map);



            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        System.out.println(map);

        List<String> list=analyzeTop100Repos(map);

        System.out.println(list);
        saveRepo(list);

    }


    private void saveRepo(List<String> list){
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory f = new JsonFactory();

        for (String repo : list) {
            String s = null;
            try {
                s = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo);


                Repository po = mapper.readValue(s, Repository.class);
                po.setOwner_name(po.getFull_name().split("/")[0]);
                String lan = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/languages");
                po.setLanguages(lan);
                dao.insert(po);

                try {
                    String contri = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/contributors");
                    JsonParser jp = f.createJsonParser(contri);
                    jp.nextToken();
                    while (jp.nextToken() == JsonToken.START_OBJECT) {
                        dao.insertContribute(repo,(String)mapper.readValue(jp, Map.class).get("login"));
                    }
                } catch (IOException e) {
                    continue;
                }


                try {
                    String subs = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/subscribers");
                    JsonParser jp3 = f.createJsonParser(subs);
                    jp3.nextToken();
                    while (jp3.nextToken() == JsonToken.START_OBJECT) {
                        dao.insertSubscribe(repo,(String)mapper.readValue(jp3, Map.class).get("login"));
                    }
                } catch (IOException e) {
                    continue;
                }

            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }




        }
    }

    private Map<String,Integer> getRepoForkMap(String path,Map<String,Integer> map) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br=new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(path))));
        String json;
        while((json=br.readLine())!=null){
            JsonNode node = mapper.readTree(json);
            if(node.get("type").toString().equals("\"ForkEvent\"")){
                String name = node.get("repo").get("name").toString().replace("\"","");
                if(map.containsKey(name))
                    map.put(name, map.get(name)+1);
                else
                    map.put(name, 1);
            }

        }
        return map;
    }


    private List<String> analyzeTop100Repos(Map<String,Integer> map){
        return new ArrayList<String>(sortMapByValue(map).keySet()).subList(0,100);
    }




    /**
     * 使用 Map按value进行排序
     * @param
     * @return
     */
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;




    }

    static class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            return me2.getValue().compareTo(me1.getValue());
        }
    }

}
