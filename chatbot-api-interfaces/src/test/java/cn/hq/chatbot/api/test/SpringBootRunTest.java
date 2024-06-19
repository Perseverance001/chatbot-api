package cn.hq.chatbot.api.test;

import cn.hq.chatbot.domain.ai.IOpenAI;
import cn.hq.chatbot.domain.zsxq.IZsxqApi;
import cn.hq.chatbot.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.hq.chatbot.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootApplication(scanBasePackages = "cn.hq.chatbot.domain")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;


    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId,cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics){
            String topic_id = topic.getTopic_id();
            String text = topic.getQuestion().getText();

            logger.info("topicId:{},text:{}",topic_id,text);
            //
            zsxqApi.answer(groupId,cookie,topic_id,text,false);
        }
    }
    @Test
    public void test_openai() throws IOException {
        String response = openAI.doChatGPT("1+1=？");
        logger.info("测试结果：{}",response);
    }
}
