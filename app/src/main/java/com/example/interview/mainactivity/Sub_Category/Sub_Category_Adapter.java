package com.example.interview.mainactivity.Sub_Category;

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
import com.example.interview.mainactivity.Sub_Category_2.Sub_Category2_MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Sub_Category_Adapter extends RecyclerView.Adapter<Sub_Category_Adapter.MyViewHolder> {
    List<Sub_Category_PojoClass>Sub_Cat_Data;
    private int lastPosition=-1;

    public Sub_Category_Adapter(List<Sub_Category_PojoClass> sub_Cat_Data) {
        Sub_Cat_Data = sub_Cat_Data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sub_category_item_design,viewGroup,false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Sub_Category_PojoClass sub_category_pojoClass=Sub_Cat_Data.get(i);

        myViewHolder.Sub_Cat_text.setText(sub_category_pojoClass.getCategory_name());

        String url="http://apps-bazaar.com/mobileapps/interview/category_image/";
        Picasso.get().load(url+sub_category_pojoClass.getCategory_image()).into(myViewHolder.Sub_Cate_imageView);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), Sub_Category2_MainActivity.class);
                intent.putExtra("parent_cat_id",sub_category_pojoClass.getId());
                intent.putExtra("mainparent_cat_id",sub_category_pojoClass.getParent_cat_id());
                v.getContext().startActivity(intent);
            }
        });

//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(v.getContext(), Sub_Category_Activity.class);
//                intent.putExtra("parent_cat_id",sub_category_pojoClass.getId());
//                v.getContext().startActivity(intent);
//            }
//        });

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
        return Sub_Cat_Data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Sub_Cate_imageView;
        TextView Sub_Cat_text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Sub_Cate_imageView=(ImageView)itemView.findViewById(R.id.sub_category_image_id);
            Sub_Cat_text=(TextView)itemView.findViewById(R.id.sub_category_text_id);
        }
    }
}
