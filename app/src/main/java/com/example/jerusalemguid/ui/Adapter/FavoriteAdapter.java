package com.example.jerusalemguid.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.models.Place;
import com.example.jerusalemguid.ui.models.Places;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    private Context context ;
    private List<Place> placesList;
    private ItemClickListener mClickListener;

    public FavoriteAdapter(Context context , List<Place> places  , ItemClickListener onClick){
        this.context = context ;
        this.placesList = places;
        this.mClickListener = onClick;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item,parent,false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        Place place = placesList.get(position);
        holder.name.setText(place.getName());
        Glide.with(context).load(place.getImageUrl()).into(holder.imageView);
        holder.initialize(placesList.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return placesList == null ? 0 : placesList.size();
    }


    public class FavoriteHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView name  ;
        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);


        }
        void initialize(Place data, ItemClickListener action) {
            //  txt_card_name.text = data.name
            this.name = itemView.findViewById(R.id.place_name);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.onItemClick(data, getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener {
        void onItemClick(Place data , int position);
    }
}
