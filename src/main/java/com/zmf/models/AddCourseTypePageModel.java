package com.zmf.models;

import com.zmf.pojo.TopType;

import java.util.List;

/**
 * @author zmf
 * @description
 * @timestamp 2017-12-10 00:01
 */
public class AddCourseTypePageModel {
    private List<TopType> topTypes;

    public AddCourseTypePageModel(List<TopType> topTypes) {
        this.topTypes = topTypes;
    }

    public AddCourseTypePageModel() {
    }

    public List<TopType> getTopTypes() {
        return topTypes;
    }

    public void setTopTypes(List<TopType> topTypes) {
        this.topTypes = topTypes;
    }
}
