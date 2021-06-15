package com.example.jerusalemguid.ui.Favorite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.databinding.FragmentNotificationsBinding;
import com.example.jerusalemguid.ui.Adapter.FavoriteAdapter;
import com.example.jerusalemguid.ui.DHelper.DBHelper;
import com.example.jerusalemguid.ui.DetailsActivity;
import com.example.jerusalemguid.ui.models.Place;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteAdapter.ItemClickListener{
    private RecyclerView recyclerView ;
    private List<Place> placesList ;
    FavoriteAdapter adapter;
    DBHelper DB ;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_dashboard, container, false);

        DB = new DBHelper(getContext()) ;

        recyclerView = root.findViewById(R.id.rvFavorite);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        placesList = new ArrayList<>();
        getData();



        return root;
    }
    private void getData() {
        Cursor res = DB.getData();
        if(res.getCount()==0){
                Toast.makeText(getContext(),"No entry Exists",Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            Place place = new Place(res.getString(0),res.getString(1),res.getString(2),res.getString(3));
            placesList.add(place) ;
            buffer.append("Name : " + res.getString(0)+"\n");
            buffer.append("desc : " + res.getString(1)+"\n");
            buffer.append("Image Url  : " + res.getString(2)+"\n");
            buffer.append("history  : " + res.getString(3)+"\n\n");
        }

        adapter = new FavoriteAdapter(getContext() , placesList ,this);

        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onItemClick(Place data, int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("title",data.getName());
        intent.putExtra("image",data.getImageUrl());
        intent.putExtra("desc",data.getDescription());
        intent.putExtra("history",data.gethistory());
        startActivity(intent);
    }
}