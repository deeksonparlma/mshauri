package com.epicodus.mshauri.model;

public class AwarenessModel {
    String foundationName;
    String articleTittle;
    String articleContent;
    String datePosted;
    public AwarenessModel(){}
    public AwarenessModel(String foundationName, String articleTitile, String articleContent, String datePosted) {
        this.foundationName = foundationName;
        this.articleTittle = articleTitile;
        this.articleContent = articleContent;
        this.datePosted = datePosted;
    }

    public String getFoundationName() {
        return foundationName;
    }

    public String getArticleTitile() {
        return articleTittle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public String getDatePosted() {
        return datePosted;
    }
}
