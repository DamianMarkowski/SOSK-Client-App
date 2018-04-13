package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PracticalLessonsTipsList {

    @JsonProperty("list")
    private List<PracticalTip> list;

    public PracticalLessonsTipsList(){

    }

    public PracticalLessonsTipsList(List<PracticalTip> l){
        this.list = l;
    }

    public List<PracticalTip> getList() {
        return list;
    }

    public void setList(List<PracticalTip> list) {
        this.list = list;
    }
}
