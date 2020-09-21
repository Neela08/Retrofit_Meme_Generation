package com.bitwisey.a10gag;

;

import com.google.gson.annotations.SerializedName;

public class RetroInfo {


    @SerializedName("author")
    private String author;
    @SerializedName("url")
    private String url;
   

    public RetroInfo( String author, String url) {

        this.author = author;
        this.url = url;

    }


    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
