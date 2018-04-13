package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EducationalMaterial {

    @JsonProperty
    private String name;

    public String getName() {
        return name;
    }

    public EducationalMaterial(){

    }

    public EducationalMaterial(String n){
        this.name = n;
    }

    public void setName(String name) {
        this.name = name;
    }
}
