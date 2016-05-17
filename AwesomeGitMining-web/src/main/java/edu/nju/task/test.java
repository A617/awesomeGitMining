package edu.nju.task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.GZIPInputStream;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by Dora on 2016/5/17.
 */
public class test {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        String path = "src/main/resources/2015-04-11-15.json.gz";
        String url = "http://data.githubarchive.org/2015-04-11-15.json.gz";



        Map<String,Integer> map = new HashMap<>();
        try {
            downloadFile(path,url);


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
        } catch (IOException e) {
            e.printStackTrace();

        }

        System.out.println(sortMapByValue(map));

    }


    /**
     * 下载文件保存到本地
     *
     * @param path
     *            文件保存位置
     * @param url
     *            文件地址
     * @throws IOException
     */
    public static void downloadFile(String path, String url) throws IOException {
//        log.debug("path:" + path);
//        log.debug("url:" + url);
        HttpClient client = null;
        try {
            // 创建HttpClient对象
            client = new DefaultHttpClient();
            // 获得HttpGet对象
            HttpGet httpGet = new HttpGet(url);
            // 发送请求获得返回结果
            HttpResponse response = client.execute(httpGet);
            // 如果成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                byte[] result = EntityUtils.toByteArray(response.getEntity());
                BufferedOutputStream bw = null;
                try {
                    // 创建文件对象
                    File f = new File(path);
                    // 创建文件路径
                    if (!f.getParentFile().exists())
                        f.getParentFile().mkdirs();
                    // 写入文件
                    bw = new BufferedOutputStream(new FileOutputStream(path));
                    bw.write(result);
                } catch (Exception e) {
//                    log.error("保存文件错误,path=" + path + ",url=" + url, e);
                } finally {
                    try {
                        if (bw != null)
                            bw.close();
                    } catch (Exception e) {
//                        log.error(
//                                "finally BufferedOutputStream shutdown close",
//                                e);
                    }
                }
                System.out.println(111);
            }
            // 如果失败
            else {
                StringBuffer errorMsg = new StringBuffer();
                errorMsg.append("httpStatus:");
                errorMsg.append(response.getStatusLine().getStatusCode());
                errorMsg.append(response.getStatusLine().getReasonPhrase());
                errorMsg.append(", Header: ");
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    errorMsg.append(header.getName());
                    errorMsg.append(":");
                    errorMsg.append(header.getValue());
                }
//                log.error("HttpResonse Error:" + errorMsg);
            }
        } catch (ClientProtocolException e) {
//            log.error("下载文件保存到本地,http连接异常,path=" + path + ",url=" + url, e);
            throw e;
        } catch (IOException e) {
//            log.error("下载文件保存到本地,文件操作异常,path=" + path + ",url=" + url, e);
            throw e;
        } finally {
            try {
                client.getConnectionManager().shutdown();
            } catch (Exception e) {
//                log.error("finally HttpClient shutdown error", e);
            }
        }
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
