package edu.nju.task;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("deprecation")
public class HttpRequest {

    private static final Logger log = Logger.getLogger(HttpRequest.class);

    private static String token = "b6d4d30ba55a5f2166af787f1cacb762c235aaea";

    public static String getGithubContentUsingHttpClient(String url) throws IOException {
        String newUrl = "https://" + token + ":x-oauth-basic@" + url;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(newUrl);
        HttpResponse response = client.execute(request);
        String responseString = new BasicResponseHandler().handleResponse(response);
        return responseString;
    }


    public static void downloadFile(String path, String url) throws IOException {
        log.debug("path:" + path);
        log.debug("url:" + url);
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
                    log.error("保存文件错误,path=" + path + ",url=" + url, e);
                } finally {
                    try {
                        if (bw != null)
                            bw.close();
                    } catch (Exception e) {
                        log.error(
                                "finally BufferedOutputStream shutdown close",
                                e);
                    }
                }
                System.out.println("Download successfully");
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
                log.error("HttpResonse Error:" + errorMsg);
            }
        } catch (ClientProtocolException e) {
            log.error("下载文件保存到本地,http连接异常,path=" + path + ",url=" + url, e);
            throw e;
        } catch (IOException e) {
            log.error("下载文件保存到本地,文件操作异常,path=" + path + ",url=" + url, e);
            throw e;
        } finally {
            try {
                client.getConnectionManager().shutdown();
            } catch (Exception e) {
                log.error("finally HttpClient shutdown error", e);
            }
        }
    }
}
