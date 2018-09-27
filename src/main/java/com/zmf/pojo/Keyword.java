package com.zmf.pojo;

public class Keyword {
    /**
     * This field corresponds to the database column keyword.keyword_id
     */
    private String keywordId;

    /**
     * This field corresponds to the database column keyword.content
     */
    private String content;

    /**
     * This method returns the value of the database column keyword.keyword_id
     *
     * @return the value of keyword.keyword_id
     */
    public String getKeywordId() {
        return keywordId;
    }

    /**
     * This method sets the value of the database column keyword.keyword_id
     *
     * @param keywordId the value for keyword.keyword_id
     */
    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId == null ? null : keywordId.trim();
    }

    /**
     * This method returns the value of the database column keyword.content
     *
     * @return the value of keyword.content
     */
    public String getContent() {
        return content;
    }

    /**
     * This method sets the value of the database column keyword.content
     *
     * @param content the value for keyword.content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}