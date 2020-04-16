package com.example.interview.mainactivity.Main_Category_and_API_Client;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API_Interface {

    @FormUrlEncoded
    @POST("service.php")
    Call<JsonObject>category(
            @Field("method")String method);


    @FormUrlEncoded
    @POST("service.php")
    Call<JsonObject>get_sub_category(
            @Field("method")String method,
            @Field("parent_cat_id")String parent_cat_id);

    @FormUrlEncoded
    @POST("service.php")
    Call<JsonObject>getallcontents(
            @Field("method")String method,
            @Field("parent_id")String parent_cat_id,
            @Field("sub_cat_id")String sub_cat_id,
            @Field("child_cat_id")String child_cat_id);



}
