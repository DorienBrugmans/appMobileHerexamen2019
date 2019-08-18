package com.pxl.dorienbrugmans.digibonss;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pxl.dorienbrugmans.digibonss.dummy.DummyContent;

import java.util.List;

public class CustomersListAdapter extends RecyclerView.Adapter<CustomersListAdapter.ViewHolder> {
    private List<DummyContent.Customer> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomersListAdapter(Context context, List<DummyContent.Customer> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_customers_list_rcv_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DummyContent.Customer customer = mData.get(position);
        holder.myTextView.setText(customer.name);
        String image = String.valueOf(customer.imageUrl);

        switch (image) {
            case "badkamer":
                holder.image.setImageResource(R.drawable.badkamer);
                break;
            case "logo":
                holder.image.setImageResource(R.drawable.logo);
                break;
                default:
                    holder.image.setImageResource(R.drawable.badkamer);
                    break;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.myTextView);
            image = itemView.findViewById(R.id.myImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    DummyContent.Customer getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
