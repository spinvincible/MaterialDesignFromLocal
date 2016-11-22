package com.geekyapps.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Nixxmare on 11/16/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<DataItems> data = Collections.emptyList();
    private Context contextThis;
    private int lastPosition = -1;


    public RecyclerAdapter(Context context, List<DataItems> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.contextThis = context;
    }

    public void deleteEntry(View viewtoAnimate , int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            // Zoom From centre Type of Animation
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            anim.setDuration(1000);
            viewToAnimate.startAnimation(anim);
            //Slide from left type of Animation
//            Animation animation = AnimationUtils.loadAnimation(contextThis, android.R.anim.slide_in_left);
//            animation.setDuration(500);
//            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        Log.d("RecyclerAdapter", "onCreateViewHolder Called");

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DataItems value = data.get(position);
        Log.d("RecyclerAdapter", "onBindViewHolder Called " + position);
        holder.text.setText(value.getTitle());
        holder.image.setImageResource(value.getIconId());
        setAnimation(holder.linearLayout , position);
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(contextThis, "The Image  at the Postion " + position + " was Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                deleteEntry( holder.linearLayout , position);
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView image;
         LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layoutCustomRow);
            text = (TextView) itemView.findViewById(R.id.listTitle);
            image = (ImageView) itemView.findViewById(R.id.listIcon);
        }


    }
}
