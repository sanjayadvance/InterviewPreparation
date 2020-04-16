package com.example.interview.mainactivity.Sub_Category;

public class Sub_Category_PojoClass {

    String id;
    String parent_cat_id;
    String sub_cat_id;
    String child_cat_id;
    String category_name;
    String category_image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_cat_id() {
        return parent_cat_id;
    }

    public void setParent_cat_id(String parent_cat_id) {
        this.parent_cat_id = parent_cat_id;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    public String getChild_cat_id() {
        return child_cat_id;
    }

    public void setChild_cat_id(String child_cat_id) {
        this.child_cat_id = child_cat_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public Sub_Category_PojoClass(String id, String parent_cat_id, String sub_cat_id, String child_cat_id, String category_name, String category_image) {
        this.id = id;
        this.parent_cat_id = parent_cat_id;
        this.sub_cat_id = sub_cat_id;
        this.child_cat_id = child_cat_id;
        this.category_name = category_name;
        this.category_image = category_image;
    }
}
