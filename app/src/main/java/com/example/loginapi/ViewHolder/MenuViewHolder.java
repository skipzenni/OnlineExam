package com.example.loginapi.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginapi.Interface.ItemClickListener;
import com.example.loginapi.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textQuestionName, textQuestionTotal;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        textQuestionName = (TextView)itemView.findViewById(R.id.questionName);
        textQuestionTotal = (TextView)itemView.findViewById(R.id.questionTotal);
        imageView = (ImageView)itemView.findViewById(R.id.questionImage);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(),false);
    }
}
