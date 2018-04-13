package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PracticalTip {

    @JsonProperty
    private String tip;

    @JsonProperty
    private String date;

    public PracticalTip(){

    }

    public PracticalTip(String t,String d){
        this.tip = t;
        this.date = d;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
