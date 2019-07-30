package com.acronsh.entity;

import java.util.List;


public class Table {
    private String name;
    private String comment;
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "com.acronsh.entity.Table{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", columns=" + columns +
                '}';
    }
}