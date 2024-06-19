package cn.hq.chatbot.domain.ai;

import java.io.IOException;

/**
 * ChatGPT API 接口
 */
public interface IOpenAI {
    String doChatGPT(String question) throws IOException;

}
