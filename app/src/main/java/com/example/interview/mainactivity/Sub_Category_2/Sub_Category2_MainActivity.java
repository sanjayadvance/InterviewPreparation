package com.example.interview.mainactivity.Sub_Category_2;

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

public class Sub_Category2_MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewSubCat2;
    Sub_Category2_Adapter sub_category2_adapter;
    String parent_cat_id;
    String mainparent_cat_id;

    ProgressDialog progressDialog;

    public List<Sub_Category2_PojoClass>Sub_Cat2_Array=new ArrayList<Sub_Category2_PojoClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category2__main);

        progressDialog=new ProgressDialog(Sub_Category2_MainActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        recyclerViewSubCat2=(RecyclerView)findViewById(R.id.sub_category2_recycleViewid);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerViewSubCat2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewSubCat2.setLayoutManager(layoutManager);

        Intent intent=getIntent();

        parent_cat_id=intent.getStringExtra("parent_cat_id");
        mainparent_cat_id=intent.getStringExtra("mainparent_cat_id");


        sub_cat2_DataLoad(parent_cat_id);

    }

    private void sub_cat2_DataLoad(final String parent_cat_id) {

        API_Interface Sub_Cat_API= API_Controller.getInstance().getClient();

        Call<JsonObject>Sub_Cat_Enqu=Sub_Cat_API.get_sub_category("get_sub_category",parent_cat_id);
        Sub_Cat_Enqu.enqueue(new Callback<JsonObject>() {
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
                                Sub_Cat2_Array.add(new Sub_Category2_PojoClass(
                                        jsonObject1.optString("id"),
                                        jsonObject1.optString("parent_cat_id"),
                                        jsonObject1.optString("category_name"),
                                        jsonObject1.optString("category_image"),mainparent_cat_id));





                            }

                            Toast.makeText(Sub_Category2_MainActivity.this,msg, Toast.LENGTH_SHORT).show();
                            sub_category2_adapter=new Sub_Category2_Adapter(Sub_Cat2_Array);
                            recyclerViewSubCat2.setAdapter(sub_category2_adapter);
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
