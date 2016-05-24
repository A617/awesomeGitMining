package edu.nju.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.nju.dao.RepoDaoImpl;
import edu.nju.model.Repository;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by Dora on 2016/5/17.
 */
public class test {

    static RepoDaoImpl dao;

    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//        dao =(RepoDaoImpl) context.getBean("repoDao");


        String path = "src/main/resources/2016-05-16-15.json.gz";
        String url = "http://data.githubarchive.org/2016-05-20-15.json.gz";




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
            url = "http://data.githubarchive.org/"+sdf.format(dt)+"-"+(12+i)+".json.gz";
            System.out.println(url);
            path = "src/main/resources/data.json.gz";
            try {
                HttpRequest.downloadFile(path, url);

                System.out.println("download "+sdf.format(dt)+"-"+(12+i)+".json.gz successfully!");
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

    private static void saveRepo(List<String> list){
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory f = new JsonFactory();

        for (String repo : list) {
            String s = null;
            try {
                s = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo);
                System.out.println(repo);

                Repository po = mapper.readValue(s, Repository.class);
                po.setOwner_name(po.getFull_name().split("/")[0]);
                String lan = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/languages");
                po.setLanguages(lan);
                System.out.println(po);

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

    private static Map<String,Integer> getRepoForkMap(String path,Map<String,Integer> map) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br=new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(path))));
        String json;
        while((json=br.readLine())!=null){
            JsonNode node = mapper.readTree(json);
            if(node.get("type").toString().equals("\"ForkEvent\"")){
                String name = node.get("repo").get("name").toString().replace("\"","");
                if(map.containsKey(name))
                    map.put(name,map.get(name)+1);
                else
                    map.put(name,1);
            }

        }
        return map;
    }


    private static List<String> analyzeTop100Repos(Map<String,Integer> map){
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



