package cn.hq.chatbot.domain.zsxq.model.aggregates;

import cn.hq.chatbot.domain.zsxq.model.res.RespData;

/**
 * description 未回答问题的聚合信息
 */
public class UnAnsweredQuestionsAggregates {
    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }
}
