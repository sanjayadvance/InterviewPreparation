package com.example.interview.mainactivity.Main_Category_and_API_Client;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.interview.R;
import com.example.interview.mainactivity.Feedback.Feedback_Activity;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    Custom_Adapter custom_adapter;
    ProgressDialog progressDialog;

    //Mob Ads
    private AdView adView;
    AdRequest adRequest;


    public List<PojoClass> MainCategoryList = new ArrayList<PojoClass>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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





            //ProgressBar Dialog
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();



        SharedPreferences.Editor editor = getSharedPreferences("my_prf", MODE_PRIVATE).edit();
        editor.putString("name", "sanjay");

        editor.apply();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();

        main_category_data();

    }
    private void init() {

        recyclerView=(RecyclerView)findViewById(R.id.main_categor_recycle_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        custom_adapter = new Custom_Adapter(MainCategoryList);

        recyclerView.setAdapter(custom_adapter);
    }

    private void main_category_data() {
        API_Interface interfacess=API_Controller.getInstance().getClient();

        final Call<JsonObject>mainCatergory=interfacess.category("category");
        mainCatergory.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.isSuccessful())
                {

                    try {
                        JSONObject jsonObject1=new JSONObject(response.body().toString());
                        if (jsonObject1.optString("status").equals("1")){


                            JSONArray jsonArray=jsonObject1.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                                MainCategoryList.add(new PojoClass(

                                        jsonObject2.optString("id"),
                                        jsonObject2.optString("parent_cat_id"),
                                        jsonObject2.optString("category_name"),
                                        jsonObject2.optString("category_image")));

                            }

                            custom_adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        }
                        else {
                            Toast.makeText(MainActivity.this, "else", Toast.LENGTH_SHORT).show();
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }






            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {


                Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();

            }
        });


    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
//            super.onBackPressed();


            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
        //Share Intent
                    Intent sendIntent=new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,"www.instagram.com/sanjaywell");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);

        } else if (id == R.id.insta) {

            showCustomDialog();
        }
        else if (id == R.id.feedback) {


            Intent intent=new Intent(MainActivity.this, Feedback_Activity.class);
            startActivity(intent);



        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
// Show Dialog Box
    private void showCustomDialog() {

        ViewGroup viewGroup=findViewById(android.R.id.content);

        View dialogView= LayoutInflater.from(this).inflate(R.layout.activity_custom__dialog_box,viewGroup,false);
        Button btnOK=(Button)dialogView.findViewById(R.id.okbutton);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(dialogView);

        final AlertDialog alertDialog= builder.create();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


}




