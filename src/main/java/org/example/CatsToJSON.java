package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatsToJSON {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final int upvote;

    public CatsToJSON(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") int upvote) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvote = upvote;
    }

    public int getUpvote() {
        return upvote;
    }

    @Override
    public String toString() {
        return "Cats {" +
                "\n\t Id = " + id +
                "\n\t text = " + text +
                "\n\t type = " + type +
                "\n\t user = " + user +
                "\n\t upvotes " + upvote +
                "\n}";
    }
}
