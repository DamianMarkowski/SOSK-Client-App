package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String status;

    public Course(){

    }

    public Course(int i,String n, String s){
        this.id = i;
        this.name = n;
        this.status = s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
