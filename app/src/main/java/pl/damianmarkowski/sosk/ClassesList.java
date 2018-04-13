package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassesList {

    @JsonProperty("list")
    private List<Class> list;

    public ClassesList(){

    }

    public ClassesList(List<Class> l){
        this.list = l;
    }

    public List<Class> getList() {
        return list;
    }

    public void setList(List<Class> list) {
        this.list = list;
    }

}
