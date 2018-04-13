package pl.damianmarkowski.sosk;

public class RestMessage {

    private final long id;
    private final String content;

    public RestMessage(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public RestMessage(){
        this.id = 1;
        this.content = "none";
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}
