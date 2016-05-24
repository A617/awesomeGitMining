package edu.nju.task;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dora on 2016/5/24.
 */
public class HttpRequestTest {
    @Test
    public void getGithubContentUsingHttpClient() throws Exception {

        String url = "api.github.com/repos/"+"andlabs/libui"+"/stats/code_frequency";
        System.out.println(HttpRequest.getGithubContentUsingHttpClient(url));
    }

}