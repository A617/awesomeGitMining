package edu.nju.task;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.nju.dao.RepoDaoImpl;
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

    @Scheduled(cron = "00 47 10 * * ?")
    public void job(){
//        LOG.info(dao.getAllFullname().get(1));
       // LOG.info("hello。。。。");
//
//
//
//
//        Calendar date = Calendar.getInstance();
//        date.setTime(new Date());
//        date.set(Calendar.DATE, date.get(Calendar.DATE) - 2);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dt = new Date();
//        try {
//            dt = sdf.parse(sdf.format(date.getTime()));
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        Map<String,Integer> map = new HashMap<>();
//
//        for(int i=0;i<5;i++) {
//            String url = "http://data.githubarchive.org/"+sdf.format(dt)+"-"+(12+i)+".json.gz";
//            System.out.println(url);
//            String path = "src/main/resources/data.json.gz";
//            try {
//                HttpRequest.downloadFile(path, url);
//
//                System.out.println("download "+sdf.format(dt)+"-"+(12+i)+" successfully!");
//                getRepoForkMap(path,map);
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        }
//
//        System.out.println(map);
//
//        List<String> list=analyzeTop100Repos(map);

        ObjectMapper m = new ObjectMapper();
        String str = "[arthurbailao/gama-demo, DevMountain/JavaScript-Review, jtleek/datasharing, tensorflow/tensorflow, joomla-extensions/weblinks, yourtion/30dayMakeOS, LarryMad/recipes, ericmjl/Network-Analysis-Made-Simple, octocat/Spoon-Knife, rdpeng/ProgrammingAssignment2, twbs/bootstrap, mxstbr/react-boilerplate, fhc02sk/ALD-Uebung, barryclark/jekyll-now, rdpeng/ExData_Plotting1, leachim6/hello-world, joomla-projects/gsoc16_browser-automated-tests, rmotr-group-projects/pyp-w1-gw-tic-tac-toe, jlord/patchwork, googlesamples/android-architecture, angular/angular.js, zhangzibin/char-rnn-chinese, PythonWorkshop/intro-to-tensorflow, github/gitignore, DejanL/eZdravje, udacity/frontend-nanodegree-resume, firebase/quickstart-android, almasaeed2010/AdminLTE, 3lvis/Networking, deeplook/pydata_berlin2016_materials, Selz/plyr, ujjwalkarn/DataSciencePython, udacity/create-your-own-adventure, d3/d3, torvalds/linux, nightscout/cgm-remote-monitor, XX-net/XX-Net, yangyangwithgnu/use_vim_as_ide, HubPress/hubpress.io, inferjay/AndroidDevTools, JacksonTian/fks, Itseez/opencv, diegonogare/DataScience, rmotr-group-projects/pyp-w1-gw-extensible-calculator, NARKOZ/hacker-scripts, BYVoid/Batsh, sindresorhus/awesome, moozer/git-demo, AngularClass/angular2-webpack-starter, cutestrap/cutestrap, jobbole/awesome-python-cn, contiki-os/contiki, dypsilon/frontend-dev-bookmarks, LibreVR/Revive, mrdoob/three.js, google/flexbox-layout, driftyco/ionic, rdpeng/RepData_PeerAssessment1, awesome-br/awesome-br.github.io, django/django, twitter/distributedlog, avelino/awesome-go, sullo/nikto, Hopopgit/HopopApp, karan/Projects, shockone/black-screen, andlabs/libui, angular/quickstart, amix/vimrc, joshnewlan/say_what, vhf/free-programming-books, poole/hyde, geekcomputers/Python, Dogfalo/materialize, zzyyppqq/DrawingBoard, akveo/blur-admin, ujjwalkarn/Machine-Learning-Tutorials, wesm/pydata-book, rails/rails, reddit/reddit, donnemartin/data-science-ipython-notebooks, makersacademy/ruby-refresher, alibaba/dubbo, linnovate/mean, puikinsh/gentelella, ariya/phantomjs, FreeCodeCamp/FreeCodeCamp, syl20bnr/spacemacs, jaeho93/cal_project, kriasoft/react-starter-kit, getlantern/lantern, udacity/fullstack-nanodegree-vm, rhinstaller/anaconda, hyperledger/fabric, montassarelbehi/eGovFx, roots/sage, esthercrawford/EstherBot, jquery/jquery, BillSchofield/RefactoringToPatterns, andkulikov/Transitions-Everywhere]";
        List<String> list= null;
        list = Arrays.asList(str.substring(1,str.length()-1).split(", "));

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
                    System.out.println(repo);

                    Repository po = mapper.readValue(s, Repository.class);
                    po.setOwner_name(po.getFull_name().split("/")[0]);
                    String lan = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/languages");
                    po.setLanguages(lan);
                    System.out.println(po);

                    dao.insert(po);
                }

                if(dao.getContributors(repo)==null||dao.getContributors(repo).isEmpty()) {
                    try {
                        String contri = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/contributors");
                        JsonParser jp = f.createJsonParser(contri);
                        jp.nextToken();
                        while (jp.nextToken() == JsonToken.START_OBJECT) {

                            String login = (String) mapper.readValue(jp, Map.class).get("login");
                            dao.insertContribute(repo, login);

                            if(userdao.selectByLogin(login)==null) {
                                System.out.println(login);
                                String user = HttpRequest.getGithubContentUsingHttpClient("api.github.com/users/" + login);
                                User us = mapper.readValue(user, User.class);
                                userdao.insert(us);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("cant get contri");
                    }
                }

                if(dao.getSubscribers(repo)==null||dao.getSubscribers(repo).isEmpty()) {
                    try {
                        String subs = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/subscribers");
                        JsonParser jp3 = f.createJsonParser(subs);
                        jp3.nextToken();
                        while (jp3.nextToken() == JsonToken.START_OBJECT) {
                            String login = (String) mapper.readValue(jp3, Map.class).get("login");
                            dao.insertSubscribe(repo, login);

                            if(userdao.selectByLogin(login)==null) {
                                System.out.println(login);
                                String user = HttpRequest.getGithubContentUsingHttpClient("api.github.com/users/" + login);
                                User us = mapper.readValue(user, User.class);
                                userdao.insert(us);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("cant get subscribe");
                    }
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
