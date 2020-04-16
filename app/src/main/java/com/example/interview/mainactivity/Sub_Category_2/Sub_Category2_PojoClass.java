package com.example.interview.mainactivity.Sub_Category_2;

public class Sub_Category2_PojoClass {
    String id;

    String parent_cat_id;
    String category_name;
    String category_image;
    String mainparent_cat_id;

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

    public Sub_Category2_PojoClass(String id, String parent_cat_id, String category_name, String category_image, String mainparent_cat_id) {
        this.id = id;

        this.parent_cat_id = parent_cat_id;
        this.category_name = category_name;
        this.category_image = category_image;
        this.mainparent_cat_id = mainparent_cat_id;


    }

    public String getMainparent_cat_id() {
        return mainparent_cat_id;
    }

    public void setMainparent_cat_id(String mainparent_cat_id) {
        this.mainparent_cat_id = mainparent_cat_id;
    }
}
