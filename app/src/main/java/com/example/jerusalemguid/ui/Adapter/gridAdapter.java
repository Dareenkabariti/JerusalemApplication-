package com.example.jerusalemguid.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.models.Places;
import com.squareup.picasso.Picasso;

import java.util.List;

public class gridAdapter extends RecyclerView.Adapter<gridAdapter.ViewHolder> {

    private List<Places> mData;
    private LayoutInflater mInflater;
    private PlacesAdapter.ItemClickListener mClickListener;
    Context context ;

    public gridAdapter(Context context, List<Places> data , PlacesAdapter.ItemClickListener onClick) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context ;
        this.mClickListener = onClick;


    }

    @Override
    public gridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_card, parent, false);
        return new gridAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final gridAdapter.ViewHolder holder, final int position) {

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
            this.title = itemView.findViewById(R.id.place_name1);
            image = itemView.findViewById(R.id.image1);

        }
        void initialize(Places data, PlacesAdapter.ItemClickListener action) {
            //  txt_card_name.text = data.name
            this.title = itemView.findViewById(R.id.place_name1);
            image = itemView.findViewById(R.id.image1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.onItemClick(data, getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {

        }

    }

    Places getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(PlacesAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(Places data , int position);
    }
}