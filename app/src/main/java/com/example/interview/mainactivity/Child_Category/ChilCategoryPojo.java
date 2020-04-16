package com.example.interview.mainactivity.Child_Category;

public class ChilCategoryPojo {
    String story_id;
    String parent_id;
    String sub_cat_id;
    String child_cat_id;
    String child_cat_name;
    String ChildCat_Image;
    String title;
    String content;

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
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

    public String getChild_cat_name() {
        return child_cat_name;
    }

    public void setChild_cat_name(String child_cat_name) {
        this.child_cat_name = child_cat_name;
    }

    public String getChildCat_Image() {
        return ChildCat_Image;
    }

    public void setChildCat_Image(String childCat_Image) {
        ChildCat_Image = childCat_Image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChilCategoryPojo(String story_id, String parent_id, String sub_cat_id, String child_cat_id, String child_cat_name, String title, String content) {
        this.story_id = story_id;
        this.parent_id = parent_id;
        this.sub_cat_id = sub_cat_id;
        this.child_cat_id = child_cat_id;
        this.child_cat_name = child_cat_name;

        this.title = title;
        this.content = content;
    }
}
