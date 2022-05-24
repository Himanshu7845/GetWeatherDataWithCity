package com.liveline.assignmentweather.Model;

public class MainModel
{
    String official,common,lat,lang;

    public MainModel(String official, String common, String lat, String lang) {
        this.official = official;
        this.common = common;
        this.lat = lat;
        this.lang = lang;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
