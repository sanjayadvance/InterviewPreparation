package com.example.interview.mainactivity.Main_Category_and_API_Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Controller {


public API_Interface getClient(){
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://apps-bazaar.com/mobileapps/interview/webservice/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    API_Interface service=retrofit.create(API_Interface.class);
    return service;



}

public static API_Controller getInstance(){

    return new API_Controller();
}
}
