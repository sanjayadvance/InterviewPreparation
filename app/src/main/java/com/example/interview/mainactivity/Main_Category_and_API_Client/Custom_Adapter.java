package com.example.interview.mainactivity.Main_Category_and_API_Client;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interview.R;
import com.example.interview.mainactivity.Sub_Category.Sub_Category_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Custom_Adapter extends RecyclerView.Adapter<Custom_Adapter.MyViewHolder> {

    List<PojoClass>pojoClassList;
    private int lastPosition=-1;


    public Custom_Adapter(List<PojoClass> dataList) {
        pojoClassList = dataList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.maincategory_item,viewGroup,false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {



        final PojoClass pojoClass=pojoClassList.get(i);

        myViewHolder.maincat_title.setText(pojoClass.getCategory_name());

        String url="http://apps-bazaar.com/mobileapps/interview/category_image/";

        Picasso.get().load(url+pojoClass.getCategory_image()).into(myViewHolder.maincat_image);


        // Intent implementation to subcategory activity
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Sub_Category_Activity.class);
                intent.putExtra("parent_cat_id",pojoClass.getId());
                v.getContext().startActivity(intent);
            }
        });
        setAnimation(myViewHolder.itemView,i);

    }

    public void setAnimation(View viewToAnimate, int position){

        if (position>lastPosition){

            ScaleAnimation animation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(800);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }
    }

    @Override
    public int getItemCount() {
        return pojoClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView maincat_image;
        TextView maincat_title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            maincat_image=(ImageView)itemView.findViewById(R.id.main_category_image_id);
            maincat_title=(TextView) itemView.findViewById(R.id.main_category_text_id);
        }
    }
}
