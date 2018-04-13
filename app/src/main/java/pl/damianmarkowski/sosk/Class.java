package pl.damianmarkowski.sosk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Class {

    @JsonProperty
    private String dateAndTime;

    @JsonProperty
    private String lessonType;

    public Class(){

    }

    public Class(String d, String lt){
        this.dateAndTime = d;
        this.lessonType = lt;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

}
