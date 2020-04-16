package com.example.interview.mainactivity.Sub_Category_2;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interview.R;
import com.example.interview.mainactivity.Child_Category.ChildCategoryMain_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Sub_Category2_Adapter extends RecyclerView.Adapter<Sub_Category2_Adapter.MyViewHolder> {

    List<Sub_Category2_PojoClass>SubCatData;

    public Sub_Category2_Adapter(List<Sub_Category2_PojoClass> subCatData) {
        SubCatData = subCatData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sub_category2_main_design,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Sub_Category2_PojoClass sub_category2_pojoClass=SubCatData.get(i);

        myViewHolder.sub_cat2_text.setText(sub_category2_pojoClass.getCategory_name());

        String url="http://apps-bazaar.com/mobileapps/interview/category_image/";
        Picasso.get().load(url+sub_category2_pojoClass.getCategory_image()).into(myViewHolder.sub_cat2_image);





        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ChildCategoryMain_Activity.class);
                intent.putExtra("parent_cat_id",sub_category2_pojoClass.getMainparent_cat_id());
                intent.putExtra("sub_cat_id",sub_category2_pojoClass.getParent_cat_id());
                intent.putExtra("child_cat_id",sub_category2_pojoClass.getId());



                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return SubCatData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sub_cat2_image;
        TextView sub_cat2_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sub_cat2_image=(ImageView)itemView.findViewById(R.id.sub_category2_image_id);
            sub_cat2_text=(TextView)itemView.findViewById(R.id.sub_category2_text_id);

        }
    }
}
