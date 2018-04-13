package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EducationalMaterialsList {

    @JsonProperty("list")
    private List<EducationalMaterial> list;

    public EducationalMaterialsList(){

    }

    public EducationalMaterialsList(List<EducationalMaterial> l){
        this.list = l;
    }

    public List<EducationalMaterial> getList() {
        return list;
    }

    public void setList(List<EducationalMaterial> list) {
        this.list = list;
    }
}
