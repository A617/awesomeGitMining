package edu.nju.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.model.Repository;
import edu.nju.task.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)        //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class RepositoryMapperTest {



    @Resource
    private RepositoryMapper dao;

    //    @Test
//    public void selectAll() throws Exception {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 300);
//        map.put("pageOffset", 0);
//        List<Repository> list = dao.selectAllPaged(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getDescription());
//    }
//
//    @Test
//    public void selectByFullName() throws Exception {
//
//    }
//
//    @Test
//    public void testForkSort() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 2);
//        map.put("pageOffset", 20);
//        List<Repository> list = dao.selectReposSortedByFork(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getForksCount());
//    }
//
    @Test
    public void testSearch() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", 3000);
        map.put("pageOffset", 0);
        map.put("full_name", "tj");
        List<Repository> list = dao.searchRepository(map);
        for (Repository u : list)
            System.out.println(u.getCreated_at());

    }


    @Test
    public void selectRankOfFork() throws Exception {
        System.out.println(dao.selectRankOfFork("jquery/jquery"));
    }

    @Test
    public void selectRankOfSize() throws Exception {
        System.out.println(dao.selectRankOfSize("jquery/jquery"));
    }

//

//    @Test
//    public void testCount() {
//        System.out.println(dao.countSearch("tj"));
//    }
//    @Test
//    public void testYear(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 3000);
//        map.put("pageOffset", 0);
//        map.put("year",2010);
//        List<Repository> list = dao.selectReposByYear(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getCreatedAt());
//    }
//    @Test
//    public void testLanguage(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 3000);
//        map.put("pageOffset", 0);
//        map.put("language","java");
//        List<Repository> list = dao.selectReposByLanguage(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getLanguage());
//    }
//    @Test
//    public void testKey(){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("pageSize", 3000);
//        map.put("pageOffset", 0);
//        map.put("keyword","java");
//        List<Repository> list = dao.selectReposByKey(map);
//        for (Repository u : list)
////            System.out.println(u.getFullName() + ":" + u.getDescription());
//    }
//
//    @Test
//    public void testLan_Key_Year(){
//        String lan="Ruby";
//        String key="Ruby";
//        String year="2008";
////            System.out.println(dao.countLan_Key_Year(lan,key,year));
//    }
//
    @Test
    public void selectReposByLan_Key_Year() {

        List<Repository> list = dao.selectReposByLan_Key_Year("", "", "", "forks_count", 10, 0);
        for (Repository u : list)
            System.out.println(u.getFull_name() + ":" + u.getDescription());
        System.out.println(list.size());
    }


    @Test
    public void selectFullNameTest() throws Exception {
        List<String> list = dao.selectFullName();
        for (String u : list)
            System.out.println(u);
    }

    @Test
    public void getContributorsTest() throws Exception {
        String name = "technomancy/leiningen";
        List<String> list = dao.getContributors(name);
        for (String u : list)
            System.out.println(u);

    }


    @Test
    public void getCollaTest() throws Exception {
        String name = "technomancy/leiningen";
        List<String> list = dao.getCollaborators(name);
        for (String u : list)
            System.out.println(u);


    }

    @Test
    public void getYearTest() throws Exception {
        List<String> list = dao.getYear();
        for (String u : list)
            System.out.println(u);


    }

    @Test
    public void countLanguagesCreatedTest() throws Exception {
        System.out.println(dao.countLanguagesCreated("2008", "Ruby"));
    }


    @Test
    public void eachYearTest() throws Exception {
        List<String> list = dao.eachYear();
        for (String u : list)
            System.out.println(u);

    }

    @Test
    public void eachSizeTest() throws Exception {
        List<Integer> list = dao.eachSize();
        for (Integer u : list)
            System.out.println(u);

    }

    @Test
    public void countAverageSize_year() throws Exception {
        System.out.println(dao.countAverageSize_year());

    }

    @Test
    public void testSelectLanguage() {
        List<String> list = dao.selectLanguages();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    @Test
    public void getSizeByLanTest() throws Exception {
        String lan="Ruby";
        List<Integer> list = dao.getSizeByLan(lan);
        for (Integer u : list)
            System.out.println(u);

    }

    public void insert() throws Exception {
//        String s = "{full_name='arthurbailao/gama-demo', name='gama-demo', owner_name='arthurbailao', language='JavaScript', open_issues_count=33, watchers_count=0, clone_url='https://github.com/arthurbailao/gama-demo.git', html_url='https://github.com/arthurbailao/gama-demo', homepage='null', created_at=Fri May 20 12:39:32 CST 2016, pushed_at=Sat May 21 14:50:22 CST 2016, updated_at=Fri May 20 23:52:23 CST 2016, size=889, stargazers_count=0, forks_count=17, subscribers_count=3, languages='{\"JavaScript\":202347,\"CSS\":10224,\"HTML\":4928}', size_score=null, scale_score=null, promising_score=null, participation_score=null, hot_score=null, description=''}";
        ObjectMapper m = new ObjectMapper();
//        Repository po = m.readValue(s,Repository.class);
        String str = "[arthurbailao/gama-demo, DevMountain/JavaScript-Review, jtleek/datasharing, tensorflow/tensorflow, joomla-extensions/weblinks, yourtion/30dayMakeOS, LarryMad/recipes, ericmjl/Network-Analysis-Made-Simple, octocat/Spoon-Knife, rdpeng/ProgrammingAssignment2, twbs/bootstrap, mxstbr/react-boilerplate, fhc02sk/ALD-Uebung, barryclark/jekyll-now, rdpeng/ExData_Plotting1, leachim6/hello-world, joomla-projects/gsoc16_browser-automated-tests, rmotr-group-projects/pyp-w1-gw-tic-tac-toe, jlord/patchwork, googlesamples/android-architecture, angular/angular.js, zhangzibin/char-rnn-chinese, PythonWorkshop/intro-to-tensorflow, github/gitignore, DejanL/eZdravje, udacity/frontend-nanodegree-resume, firebase/quickstart-android, almasaeed2010/AdminLTE, 3lvis/Networking, deeplook/pydata_berlin2016_materials, Selz/plyr, ujjwalkarn/DataSciencePython, udacity/create-your-own-adventure, d3/d3, torvalds/linux, nightscout/cgm-remote-monitor, XX-net/XX-Net, yangyangwithgnu/use_vim_as_ide, HubPress/hubpress.io, inferjay/AndroidDevTools, JacksonTian/fks, Itseez/opencv, diegonogare/DataScience, rmotr-group-projects/pyp-w1-gw-extensible-calculator, NARKOZ/hacker-scripts, BYVoid/Batsh, sindresorhus/awesome, moozer/git-demo, AngularClass/angular2-webpack-starter, cutestrap/cutestrap, jobbole/awesome-python-cn, contiki-os/contiki, dypsilon/frontend-dev-bookmarks, LibreVR/Revive, mrdoob/three.js, google/flexbox-layout, driftyco/ionic, rdpeng/RepData_PeerAssessment1, awesome-br/awesome-br.github.io, django/django, twitter/distributedlog, avelino/awesome-go, sullo/nikto, Hopopgit/HopopApp, karan/Projects, shockone/black-screen, andlabs/libui, angular/quickstart, amix/vimrc, joshnewlan/say_what, vhf/free-programming-books, poole/hyde, geekcomputers/Python, Dogfalo/materialize, zzyyppqq/DrawingBoard, akveo/blur-admin, ujjwalkarn/Machine-Learning-Tutorials, wesm/pydata-book, rails/rails, reddit/reddit, donnemartin/data-science-ipython-notebooks, makersacademy/ruby-refresher, alibaba/dubbo, linnovate/mean, puikinsh/gentelella, ariya/phantomjs, FreeCodeCamp/FreeCodeCamp, syl20bnr/spacemacs, jaeho93/cal_project, kriasoft/react-starter-kit, getlantern/lantern, udacity/fullstack-nanodegree-vm, rhinstaller/anaconda, hyperledger/fabric, montassarelbehi/eGovFx, roots/sage, esthercrawford/EstherBot, jquery/jquery, BillSchofield/RefactoringToPatterns, andkulikov/Transitions-Everywhere]";
        List<String> list= Arrays.asList(str.substring(1,str.length()-1).split(", "));

        for(String repo:list) {
            String s = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo);
            System.out.println(repo);

            Repository po = m.readValue(s, Repository.class);
            po.setOwner_name(po.getFull_name().split("/")[0]);
            String lan = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repo + "/languages");
            po.setLanguages(lan);

            if(dao.selectByFullName(repo)==null)
                 dao.insert(po);
        }
    }

    private static String token = "b6d4d30ba55a5f2166af787f1cacb762c235aaea";

    public static String getGithubContentUsingHttpClient(String url) throws IOException {
        String newUrl = "https://" + token + ":x-oauth-basic@" + url;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(newUrl);
        HttpResponse response = client.execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);
        return responseString;
    }
}