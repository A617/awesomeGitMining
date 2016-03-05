package main.dao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
 
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
 
/**
 * @author Crunchify.com
 *
 */
 
@SuppressWarnings("deprecation")
public class CrunchifyReadGithubWithBasicAuthentication {
 
	static String token  = "b6d4d30ba55a5f2166af787f1cacb762c235aaea";
	public static void main(String[] args) {
 
		// Replace this token with your actual token
 
		String url = "api.github.com/search/repositories?q=stars:%3E=0&per_page=100";
 /*
		// HttpClient Method to get Private Github content with Basic OAuth token
		getGithubContentUsingHttpClient(url,"&page=1");
		getGithubContentUsingHttpClient(url,"&page=2");
		getGithubContentUsingHttpClient(url,"&page=3");
		getGithubContentUsingHttpClient(url,"&page=4");*/
 
		// URLConnection Method to get Private Github content with Basic OAuth token
		getGithubContentUsingURLConnection( url,"&page=1");
 
		getGithubContentUsingURLConnection( url,"&page=2");
		
		getGithubContentUsingURLConnection(url,"&page=3");
		
		getGithubContentUsingURLConnection(url,"&page=4");
	}
 
	@SuppressWarnings("resource")
	private static void getGithubContentUsingHttpClient(String url,String param) {
		String newUrl = "https://" + token + ":x-oauth-basic@" + url +param;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(newUrl);
		System.out.println(newUrl);
		try {
			HttpResponse response = client.execute(request);
			String responseString = new BasicResponseHandler().handleResponse(response);
			System.out.println(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public static void getGithubContentUsingURLConnection( String url,String param) {
		String newUrl = "https://" + url + param;
		System.out.println(newUrl);
		System.out.println(token);
		
		String result = "";
		BufferedReader in = null;
		try {
			URL myURL = new URL(newUrl);
			URLConnection connection = myURL.openConnection();
			token = token + ":x-oauth-basic";
			String authString = "Basic " + Base64.encodeBase64String(token.getBytes());
			connection.setRequestProperty("Authorization", authString);


			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	// ConvertStreamToString() Utility - we name it as crunchifyGetStringFromStream()
	private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
		if (crunchifyStream != null) {
			Writer crunchifyWriter = new StringWriter();
 
			char[] crunchifyBuffer = new char[2048];
			try {
				Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
				int counter;
				while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
					crunchifyWriter.write(crunchifyBuffer, 0, counter);
				}
			} finally {
				crunchifyStream.close();
			}
			return crunchifyWriter.toString();
		} else {
			return "No Contents";
		}
	}
}