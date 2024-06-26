package cn.hq.chatbot.domain.ai.service;

import cn.hq.chatbot.domain.ai.IOpenAI;
import cn.hq.chatbot.domain.ai.model.aggregates.AIAnswer;
import cn.hq.chatbot.domain.ai.model.vo.Choices;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OpenAI implements IOpenAI {

//    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;
    @Override
    public String doChatGPT(String question) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.wlai.vip/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer " + openAiKey);

        String paramJson = "{\"model\":\"gpt-4-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\""+ question +"\"}]}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        //判断返回网页是否错误
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices){
                answers.append(choice.getMessage().getContent());
            }
            return answers.toString();
        }else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
