package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentsList {

    @JsonProperty("list")
    private List<Student> list;

    public StudentsList(){

    }

    public StudentsList(List<Student> l){
        this.list = l;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }
}
