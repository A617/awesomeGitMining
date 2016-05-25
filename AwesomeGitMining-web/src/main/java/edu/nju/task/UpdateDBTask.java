package edu.nju.task;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.dao.RepositoryMapper;
import edu.nju.dao.UserMapper;
import edu.nju.model.Repository;
import edu.nju.model.User;
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
    RepositoryMapper dao;
    @Resource
    UserMapper userdao;

    @Scheduled(cron = "* * 13 * * ?")
    public void job(){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        try {
            dt = sdf.parse(sdf.format(date.getTime()));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }


        Map<String,Integer> map = new HashMap<>();

        for(int i=0;i<8;i++) {
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

        List<String> list=analyzeTop60Repos(map);

//        ObjectMapper m = new ObjectMapper();
//        String str = "[albaizq/NBAMovements, infx598g-s16/05-23-d3, ga-wdi-boston/ruby-morse-code-challenge, ga-wdi-boston/python, ga-wdi-exercises/gulp-bamsay, LukyVj/family.scss, joshbuchea/HEAD, LarryMad/recipes, barryclark/jekyll-now, jtleek/datasharing, rishabhp/bideo.js, rdpeng/ProgrammingAssignment2, twbs/bootstrap, shockone/black-screen, udacity/frontend-nanodegree-resume, angular/angular.js, mmistakes/minimal-mistakes, BoiseCodeWorks/fullstack-friends, iOS-Dev-Kurs/apiclient, phodal/growth-in-action, spring-projects/spring-boot, puikinsh/gentelella, FreeCodeCamp/FreeCodeCamp, getlantern/lantern, shadowsocks/shadowsocks, udacity/Sunshine-Version-2, scikit-learn/scikit-learn, hakimel/reveal.js, lukas2511/letsencrypt.sh, CocoaPods/Specs, smartninja/basic-gae-boilerplate, jobbole/awesome-python-cn, nightscout/cgm-remote-monitor, tripit/slate, rdpeng/RepData_PeerAssessment1, github/gitignore, jlord/patchwork, BVLC/caffe, taizilongxu/interview_python, facebook/react, alibaba/dubbo, xuejing80/B150303, soimort/you-get, udacity/fullstack-nanodegree-vm, matteocrippa/awesome-swift, stephentuso/welcome-android, electron/electron, ujjwalkarn/DataSciencePython, racaljk/hosts, ParsePlatform/parse-server-example, DevMountain/object-fiddles, FabianTerhorst/FastLayout, balde/balde, arvinquilao/android_kernel_cyanogen_msm8916, ingwinlu/pelican-twitchy, angular/quickstart, joshnewlan/say_what, spring-projects/spring-framework, marmelab/universal.css, citizenly/citizen, wpolitarczyk/DNIF2016, apache/spark, scm-ninja/starter-web, nginx/nginx, SmartThingsCommunity/SmartThingsPublic, sialvsic/thousands_separators, googlesamples/android-architecture, udacity/machine-learning, callemall/material-ui, jikexueyuanwiki/tensorflow-zh, devopsdays/devopsdays-web, fchollet/keras, rambler-ios/team, deadlyvipers/dojo_rules, sahat/hackathon-starter, paircolumbus/Capybara101, Itseez/opencv, philackm/Scrollable-GraphView, trippo/ResponsiveFilemanager, geeeeeeeeek/electronic-wechat, zhufengpeixun/JavaScript201604, sorrycc/awesome-javascript, jjug-ccc/slides-articles-2016spring, rdpeng/ExData_Plotting1, bytecode0101/WarGame2, pallets/flask, BigWdevelopment/YellowGuppyPigeon, learn-co-students/hs-coding-club-bootstrap-philly-springside-chestn-f924, brianray/goto_tutorial, daneden/animate.css, udacity/create-your-own-adventure, DefinitelyTyped/DefinitelyTyped]";
//        List<String> list= null;
//        list = Arrays.asList(str.substring(1,str.length()-1).split(", "));

        System.out.println(list);
        saveRepo(list);

    }


    private void saveRepo(List<String> list){
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory f = new JsonFactory();

        for (String repo : list) {
            String s = null;
            try {
                if(dao.selectByFullName(repo)==null) {
                    s = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo);

                    Repository po = mapper.readValue(s, Repository.class);
                    if(po.getStargazers_count()+po.getForks_count()<30)
                        continue;
                    System.out.println(repo);

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

                                String login = (String) mapper.readValue(jp, Map.class).get("login");

                                dao.insertContribute(repo, login);

                                if (userdao.selectByLogin(login) == null) {
                                    String user = HttpRequest.getGithubContentUsingHttpClient("api.github.com/users/" + login);
                                    User us = mapper.readValue(user, User.class);
                                    userdao.insert(us);
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("cant get contri");
                        }




                        try {
                            String subs = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/subscribers");
                            JsonParser jp3 = f.createJsonParser(subs);
                            jp3.nextToken();
                            while (jp3.nextToken() == JsonToken.START_OBJECT) {
                                String login = (String) mapper.readValue(jp3, Map.class).get("login");
                                dao.insertSubscribe(repo, login);

                                if (userdao.selectByLogin(login) == null) {
                                    String user = HttpRequest.getGithubContentUsingHttpClient("api.github.com/users/" + login);
                                    User us = mapper.readValue(user, User.class);
                                    userdao.insert(us);
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("cant get subscribe");
                        }

                    try {
                        String rps = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/repos");
                        JsonParser jp = f.createJsonParser(rps);
                        jp.nextToken();
                        while (jp.nextToken() == JsonToken.START_OBJECT) {
                            String login = (String) mapper.readValue(jp, Map.class).get("login");

                            if(dao.selectByFullName(repo)==null) {
                                String str = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo);
                                Repository rp = mapper.readValue(str, Repository.class);
                                if (rp.getStargazers_count() + rp.getForks_count() < 30)
                                    continue;

                                dao.insert(rp);
                            }

                            userdao.insertOwn(login,repo);


                        }
                    } catch (Exception e) {
                        System.out.println("cant get own");
                    }
                }

            } catch (Exception e) {
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
                if(map.containsKey(name)) {
                    map.put(name, map.get(name) + 1);
                }else
                    map.put(name, 1);
            }

        }
        return map;
    }


    private List<String> analyzeTop60Repos(Map<String,Integer> map){
        System.out.println(sortMapByValue(map).values());
        return new ArrayList<String>(sortMapByValue(map).keySet()).subList(0,60);
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
