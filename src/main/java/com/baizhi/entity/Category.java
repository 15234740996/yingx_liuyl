package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String id;
    private String cate_name;
    private String levels;
    private String parent_id;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", cate_name='" + cate_name + '\'' +
                ", levels='" + levels + '\'' +
                ", parent_id='" + parent_id + '\'' +
                '}';
    }

    public Category() {
        super();
    }

    public Category(String id, String cate_name, String levels, String parent_id) {
        this.id = id;
        this.cate_name = cate_name;
        this.levels = levels;
        this.parent_id = parent_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
