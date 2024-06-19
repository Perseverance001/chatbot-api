package cn.hq.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

//单元测试
public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/88888285112252/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie","zsxq_access_token=4EE5EA9A-BA93-5F92-8B35-2DEBD97F6230_E71A79F0C6D6236C; abtest_env=product; zsxqsessionid=c5ce6d31e09dae043744dcdef0a81929; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212251412151881%22%2C%22first_id%22%3A%22190240aedc310be-0bbd8f349acb41-4c657b58-1327104-190240aedc41c3b%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkwMjQwYWVkYzMxMGJlLTBiYmQ4ZjM0OWFjYjQxLTRjNjU3YjU4LTEzMjcxMDQtMTkwMjQwYWVkYzQxYzNiIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjEyMjUxNDEyMTUxODgxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212251412151881%22%7D%7D");
        get.addHeader("Content-Type","application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(get);
        //判断返回网页是否错误
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/2855482288854221/answer");
        post.addHeader("cookie","zsxq_access_token=4EE5EA9A-BA93-5F92-8B35-2DEBD97F6230_E71A79F0C6D6236C; abtest_env=product; zsxqsessionid=c5ce6d31e09dae043744dcdef0a81929; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212251412151881%22%2C%22first_id%22%3A%22190240aedc310be-0bbd8f349acb41-4c657b58-1327104-190240aedc41c3b%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkwMjQwYWVkYzMxMGJlLTBiYmQ4ZjM0OWFjYjQxLTRjNjU3YjU4LTEzMjcxMDQtMTkwMjQwYWVkYzQxYzNiIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiMjEyMjUxNDEyMTUxODgxIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212251412151881%22%7D%7D");
        post.addHeader("Content-Type","application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"哀牢山\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        //判断返回网页是否错误
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String ques = "1+1等于几";
        HttpPost post = new HttpPost("https://api.wlai.vip/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer sk-vnFRCSaq8GlCZIAm885eBc73F38c4023A363274794Fc7275");

        String paramJson = "{\"model\":\"gpt-4-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\""+ ques +"\"}]}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        //判断返回网页是否错误
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}
