package edu.nju.task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by Dora on 2016/5/17.
 */
public class test {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        String path = "src/main/resources/2015-01-01-15.json.gz";
        Map<String,Integer> map = new HashMap<>();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(path))));
            String json;
            while((json=br.readLine())!=null){
                JsonNode node = mapper.readTree(json);// 这里的JsonNode和XML里面的Node很像
                if(node.get("type").toString().equals("\"ForkEvent\"")){
                    String name = node.get("repo").get("name").toString().replace("\"","");
                    if(map.containsKey(name))
                     map.put(name,map.get(name)+1);
                    else
                        map.put(name,1);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        System.out.println(sortMapByValue(map));

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


}

class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

        return me2.getValue().compareTo(me1.getValue());
    }
}
