package com.example.interview.mainactivity.Child_Category;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.interview.R;
import com.example.interview.mainactivity.DetailsActivity.DetailActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.MyViewHolder> {
    List<ChilCategoryPojo>ChildDataList;
    private int lastPosition=-1;

    public ChildCategoryAdapter(List<ChilCategoryPojo> childDataList) {
        ChildDataList = childDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View Itemview= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_category_mainactivity_design,viewGroup,false);
        return new MyViewHolder(Itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final ChilCategoryPojo chilCategoryPojo=ChildDataList.get(i);
          myViewHolder.ContentTitle.setText(chilCategoryPojo.getTitle());
          myViewHolder.DesContent.setText(chilCategoryPojo.getContent());

          myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(v.getContext(), DetailActivity.class);

                  intent.putExtra("content",chilCategoryPojo.getContent());

                  v.getContext().startActivities(new Intent[]{intent});
              }
          });



    final Document document= Jsoup.parse(chilCategoryPojo.content);
        myViewHolder.DesContent.setText(document.text());

        setAnimation(myViewHolder.itemView,i);

    }

    public void setAnimation(View viewToAnimate, int position){

        if (position>lastPosition){

            ScaleAnimation animation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(900);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }
    }

    @Override
    public int getItemCount() {
        return ChildDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ContentTitle;
        TextView DesContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ContentTitle=(TextView) itemView.findViewById(R.id.titleofcontentID);
            DesContent=(TextView)itemView.findViewById(R.id.DescriptionofContentID);
        }
    }
}
