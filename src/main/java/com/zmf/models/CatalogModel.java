package com.zmf.models;

import com.zmf.pojo.Chapter;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-15 18:37
 */
public class CatalogModel {
    List<Chapter> chapters;

    public CatalogModel(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public CatalogModel() {
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
