package edu.nju.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by fw on 2016/5/16.
 */

@Service("repoStaDao")
public class RepoStaDaoImpl implements IRepoStaDao {


    @Resource
    RepositoryMapper mapper;


    @Override
    public List<LinkedHashMap> getLanguageAndSize() {
        List<LinkedHashMap> list = mapper.getLanguageAndSize();

        return list;

    }

    @Override
    public List<LinkedHashMap> countCreatedYear() {
        List<LinkedHashMap> list = mapper.countCreatedYear();
        return list;
    }

    @Override
    public List<String> getStaLanguages() {
        ObjectMapper mapper2 = new ObjectMapper();
        List<Repository> repo_list = mapper.selectAll();

        Map<String, Integer> languages = null;
        for (Repository repo : repo_list) {
            try {
                languages = mapper2.readValue(repo.getLanguages(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int[][] getLanguageRelation() {
        List<String> names = mapper.countFirst10Languages();
        int[][] result = new int[names.size()][names.size()];
        for (int i = 0; i < result[0].length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = 0;
            }
        }
        List<String> languages = mapper.selectLanguages();
        for (String str : languages) {
            for (int i = 0; i < names.size(); i++) {
                String name1 = names.get(i);
                for (int j = 0; j < names.size(); j++) {
                    String name2 = names.get(j);
                    if (i == j) {
                        result[i][j] = 0;
                    } else {
                        if (str.contains(name1 + "\"") && str.contains(name2 + "\"")) {
                            result[i][j] += 1;
                        }
                    }
                }
            }
        }
        return result;
    }


    @Override
    public List<LinkedHashMap> countFirst20Languages() {
        List<LinkedHashMap> list = mapper.countFirst20Languages();

        return list;
    }

    @Override
    public List<String> countFirst10Languages() {
        List<String> list = mapper.countFirst10Languages();

        return list;
    }

    @Override
    public Map<String, Object> getLanByYear() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        List<String> year = mapper.getYear();
        for (String language : lan) {
            List<Integer> list = new ArrayList<>();
            for (String y : year) {
                list.add(mapper.countLanguagesCreated(y, language));
            }
            map.put(language, list);
        }
        return map;
    }

    @Override
    public List<String> getYearRange() {
        return mapper.getYear();
    }

    @Override
    public List<Integer> countForks() {
        List<Integer> list = mapper.countForks();

        return list;
    }

    @Override
    public List<Integer> countStars() {
        List<Integer> list = mapper.countStars();
        return list;
    }

    @Override
    public List<Integer> countSubscribers() {
        List<Integer> list = mapper.countSubscribers();
        return list;
    }

    @Override
    public List<String> eachYear() {
        List<String> list = mapper.eachYear();
        return list;
    }

    @Override
    public List<Integer> eachSize() {
        List<Integer> list = mapper.eachSize();
        return list;
    }

    @Override
    public List<LinkedHashMap> countAverageSize_year() {
        List<LinkedHashMap> list = mapper.countAverageSize_year();

        return list;
    }

    @Override
    public List<Integer> getLanguageUsageByYear(String year) {
        List<String> lan = mapper.countFirst10Languages();
        List<Integer> list = new ArrayList<>();
        for (String language : lan) {
            list.add(mapper.countLanguagesCreated(year, language));
        }
        return list;
    }

    @Override
    public Map<String, Object> getLanAndSize_EachRepo() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        for (String language : lan) {
            List<Integer> list = mapper.getSizeByLan(language);
            map.put(language, list);
        }
        return map;
    }

    @Override
    public Map<String, Object> getLanAndFork_EachRepo() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        for (String language : lan) {
            List<Integer> list = mapper.countForks();
            map.put(language, list);
        }
        return map;
    }

    @Override
    public Map<String, Object> getLanByYear_forecast() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        List<String> year = mapper.getYear();
        int size=year.size();
        double []x=new double[size];
        for(int i=0;i<size;i++){
            x[i]=i;
        }
        double []y=new double[size];
        for (String language : lan) {
            int start=0;
            List<Integer> list = new ArrayList<>();
            for (String y1 : year) {
                list.add(mapper.countLanguagesCreated(y1, language));
                y[start]=mapper.countLanguagesCreated(y1, language);
                start++;
            }
            list.add((int)estimate(x, y, x.length ));


            map.put(language, list);
        }
        return map;
    }

    public static double estimate( double[] x , double[] y , int i ) {
        double a = getXc( x , y ) ;
        double b = getC( x , y , a ) ;
        return a * i + b ;
    }

    /**
     * 计算 x 的系数
     * @param x
     * @param y
     * @return
     */
    public static double getXc( double[] x , double[] y ){
        int n = x.length ;
        return ( n * pSum( x , y ) - sum( x ) * sum( y ) )
                / ( n * sqSum( x ) - Math.pow(sum(x), 2) ) ;
    }

    /**
     * 计算常量系数
     * @param x
     * @param y
     * @param a
     * @return
     */
    public static double getC( double[] x , double[] y , double a ){
        int n = x.length ;
        return sum( y ) / n - a * sum( x ) / n ;
    }

    /**
     * 计算常量系数
     * @param x
     * @param y
     * @return
     */
    public static double getC( double[] x , double[] y ){
        int n = x.length ;
        double a = getXc( x , y ) ;
        return sum( y ) / n - a * sum( x ) / n ;
    }

    private static double sum(double[] ds) {
        double s = 0 ;
        for( double d : ds ) s = s + d ;
        return s ;
    }

    private static double sqSum(double[] ds) {
        double s = 0 ;
        for( double d : ds ) s = s + Math.pow(d, 2) ;
        return s ;
    }

    private static double pSum( double[] x , double[] y ) {
        double s = 0 ;
        for( int i = 0 ; i < x.length ; i++ ) s = s + x[i] * y[i] ;
        return s ;
    }








}
