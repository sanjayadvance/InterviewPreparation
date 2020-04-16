package com.example.interview.mainactivity.Sub_Category;

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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_Category_Activity extends AppCompatActivity {


    RecyclerView Sub_Cat_Recycle_Ciew;
    Sub_Category_Adapter sub_category_adapter;
    String parent_cat_id;
    ProgressDialog progressDialog;

    private AdView adView;
    AdRequest adRequest;


    public List<Sub_Category_PojoClass>Sub_Category_DataList=new ArrayList<Sub_Category_PojoClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category_);

        //Mob Ads
        adView = (AdView) findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().addTestDevice("F132D2D134A1717D83FB4A7D46730CE4").build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });

        //ProgressBAr Dialog
        progressDialog=new ProgressDialog(Sub_Category_Activity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        Sub_Cat_Recycle_Ciew=(RecyclerView)findViewById(R.id.Sub_Category_RecycleViewId);

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this);
        Sub_Cat_Recycle_Ciew.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Sub_Cat_Recycle_Ciew.setLayoutManager(mLayoutManager);


        Intent intent=getIntent();

        parent_cat_id=intent.getStringExtra("parent_cat_id");

        SuCategoryDataLoad(parent_cat_id);



    }

    private void SuCategoryDataLoad(String parent_cat_id) {

        API_Interface Sub_Cat_API_Interface= API_Controller.getInstance().getClient();

        final Call<JsonObject>SubCategory=Sub_Cat_API_Interface.get_sub_category("get_sub_category",parent_cat_id);

        SubCategory.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){

                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        if (jsonObject.optString("status").equals("1")){

                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i=0; i<=jsonArray.length();i++){

                                JSONObject jsonObject1=jsonArray.getJSONObject(i);

                                Sub_Category_DataList.add(new Sub_Category_PojoClass(jsonObject1.optString("id"),
                                        jsonObject1.optString("parent_cat_id"),
                                        jsonObject1.optString("sub_cat_id"),
                                     "",
                                        jsonObject1.optString("category_name"),
                                        jsonObject1.optString("category_image")));



                                Toast.makeText(Sub_Category_Activity.this, "Successful", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                sub_category_adapter= new Sub_Category_Adapter(Sub_Category_DataList);
                                Sub_Cat_Recycle_Ciew.setAdapter(sub_category_adapter);

                            }





                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }




            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(Sub_Category_Activity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
