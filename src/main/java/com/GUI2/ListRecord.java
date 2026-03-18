package com.GUI2;
//描述一条表格中的记录
public class ListRecord {
    private String id;
    private String title;

    public ListRecord(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public ListRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ListRecord{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
