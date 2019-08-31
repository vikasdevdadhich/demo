package com.webhostapp.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



import java.util.List;

public class Order_List_Adapter extends RecyclerView.Adapter<Order_List_Adapter.ProductViewHolder> {


    private static List<Order_List> productList;
    private Context mCtx;
     public Order_List_Adapter(Context mCtx, List<Order_List> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.order_list_view, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Order_List product = productList.get(position);

        //loading the image
      //  Glide.with(mCtx).load(product.getImage()).into(holder.imageView);

        holder.textViewTitle.setText(product.getId());
        holder.textViewShortDesc.setText(product.getAddress());
        holder.textViewRating.setText(product.getContact());
        holder.textViewRating.setText(product.getDate());
        holder.textViewRating.setText(product.getType());    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
         }
    }



}