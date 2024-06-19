package cn.hq.chatbot.domain.ai.model.aggregates;

import cn.hq.chatbot.domain.ai.model.vo.Choices;
import cn.hq.chatbot.domain.ai.model.vo.Message;
import cn.hq.chatbot.domain.ai.model.vo.Usage;

import java.util.List;

public class AIAnswer {
    private List<Choices> choices;

    private int created;

    private String id;

    private String model;

    private String object;

    private Usage usage;

    public void setChoices(List<Choices> choices){
        this.choices = choices;
    }
    public List<Choices> getChoices(){
        return this.choices;
    }
    public void setCreated(int created){
        this.created = created;
    }
    public int getCreated(){
        return this.created;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return this.model;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObject(){
        return this.object;
    }
    public void setUsage(Usage usage){
        this.usage = usage;
    }
    public Usage getUsage(){
        return this.usage;
    }
}
