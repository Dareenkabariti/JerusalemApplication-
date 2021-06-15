package com.example.jerusalemguid.ui.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.models.Place;
import com.example.jerusalemguid.ui.models.Places;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private List<Places> mData;
    private List<Place> mData2;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ItemClickListener mClickListener2;
    Context context;

    public PlacesAdapter(Context context, List<Places> data, ItemClickListener onClick, ItemClickListener onClick2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.mClickListener = onClick;
        this.mClickListener2 = onClick2;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.title.setText(mData.get(position).getTitle());


        String imageUri = null;
        imageUri = mData.get(position).getImage();
        Picasso.get().load(imageUri).into(holder.image);
        holder.initialize(mData.get(position), mClickListener);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.place_name);
            image = itemView.findViewById(R.id.image);

        }

        void initialize(Places data, ItemClickListener action) {
            //  txt_card_name.text = data.name
            this.title = itemView.findViewById(R.id.place_name);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.onItemClick(data, getAdapterPosition());
                }
            });
            //    itemView.setOnClickListener(this);
        }

        void initialize(Place data, ItemClickListener2 action) {
            //  txt_card_name.text = data.name
            this.title = itemView.findViewById(R.id.place_name);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.onItemClick(data, getAdapterPosition());
                }
            });
            //    itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }

    Places getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(Places data, int position);
    }

    public interface ItemClickListener2 {
        void onItemClick(Place data, int position);
    }


}