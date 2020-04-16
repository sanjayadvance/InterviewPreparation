package com.example.interview.mainactivity.Child_Category;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.example.interview.R;
import com.example.interview.mainactivity.Main_Category_and_API_Client.API_Controller;
import com.example.interview.mainactivity.Main_Category_and_API_Client.API_Interface;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildCategoryMain_Activity extends AppCompatActivity {
    RecyclerView ChildCategory_RecycleView;
    ChildCategoryAdapter childCategoryAdapter;
    String parent_cat_id;
    String sub_cat_id;
    String child_cat_id;

    ProgressDialog progressDialog;



    public List<ChilCategoryPojo>ChildCatAPI=new ArrayList<ChilCategoryPojo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_category_main_);

        //Progressbar Dialog
        progressDialog=new ProgressDialog(ChildCategoryMain_Activity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        ChildCategory_RecycleView=(RecyclerView)findViewById(R.id.ChildCategoryRecycleViewId);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        ChildCategory_RecycleView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        ChildCategory_RecycleView.setLayoutManager(layoutManager);

        Intent intent=getIntent();
        parent_cat_id=intent.getStringExtra("parent_cat_id");
        sub_cat_id=intent.getStringExtra("sub_cat_id");
        child_cat_id=intent.getStringExtra("child_cat_id");







        ChildCategoryDataLoad(parent_cat_id,sub_cat_id,child_cat_id);



    }

    private void ChildCategoryDataLoad(String parent_cat_id,String sub_cat_id,String child_cat_id) {
        API_Interface ChildLoad= API_Controller.getInstance().getClient();

        Call<JsonObject>ChildCatData=ChildLoad.getallcontents("getallcontents",parent_cat_id,sub_cat_id,child_cat_id);
        ChildCatData.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){

                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        String msg=jsonObject.optString("msg");
                        String status=jsonObject.getString("status");
                        if (status.equals("1")){

                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                ChildCatAPI.add(new ChilCategoryPojo(
                                              "","","","",""
                                        ,jsonObject1.optString("title"),jsonObject1.optString("content")

                                        ));



                            }

                            Toast.makeText(ChildCategoryMain_Activity.this,msg, Toast.LENGTH_SHORT).show();
                            childCategoryAdapter=new ChildCategoryAdapter(ChildCatAPI);
                            ChildCategory_RecycleView.setAdapter(childCategoryAdapter);
                            progressDialog.dismiss();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }
}
