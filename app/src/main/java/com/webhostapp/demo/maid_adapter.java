package com.webhostapp.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.List;
import java.util.zip.Inflater;

public class maid_adapter extends RecyclerView.Adapter<maid_adapter.ProductViewHolder> {
    CardView cardView;
    public String name, image, area,gender,religion;
    public int age;
    public double rating;
    private static List<Maid> productList;
    private Context mCtx;

    public maid_adapter(Context mCtx, List<Maid> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View contactView = inflater.inflate(R.layout.maid_recyclerview, null, false);
        cardView = contactView.findViewById(R.id.recyclercard);
        return new ProductViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Maid product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);
        holder.textViewTitle.setText(product.getName());
        holder.textViewShortDesc.setText(product.getArea());
        holder.textViewRating.setText(String.valueOf(product.getAge()));
        holder.textViewPrice.setText(String.valueOf(product.getId()));
holder.textRate.setRating(Float.parseFloat(String.valueOf(product.getRating())));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = product.getName();
                area = product.getArea();
                age = product.getAge();
                gender=product.getGender();
                religion=product.getReligion();
                image = product.getImage();
                rating=product.getRating();
                mCtx.startActivity(new Intent(mCtx, Maid_Description.class).putExtra("name", name).putExtra("area", area).putExtra("age", age).putExtra("image", image).putExtra("gender",gender).putExtra("religion",religion).putExtra("rate",rating));
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
        RatingBar textRate;

        public ProductViewHolder(final View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textRate=itemView.findViewById(R.id.rating);
            imageView = itemView.findViewById(R.id.imageView);
        }

    }


}