package com.example.jerusalemguid.ui.Places;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.Adapter.PlacesAdapter;
import com.example.jerusalemguid.ui.Adapter.gridAdapter;
import com.example.jerusalemguid.ui.DetailsActivity;
import com.example.jerusalemguid.ui.models.Places;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class TouristicPlacesFragment extends Fragment implements PlacesAdapter.ItemClickListener{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Places> items ;
    PlacesAdapter adapter;
    RecyclerView rv;
    FirebaseStorage mStrorage;
    int i = 0;
    int[] animationList = {R.anim.layout_animation_up_to_down,
            R.anim.layout_animation_right_to_left,
            R.anim.layout_animation_down_to_up,
            R.anim.layout_animation_left_to_right};
    gridAdapter adapter2;
    String image ,title , type , desc , history ;
    Double lat1,lat2;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doors, container, false);
        rv = root.findViewById(R.id.rvDoors);

        setHasOptionsMenu(true);
        if (i < animationList.length - 1) {
            i++;
        } else {
            i = 0;


        }
        mStrorage = FirebaseStorage.getInstance();
        items=new ArrayList<Places>();
        adapter=new PlacesAdapter(getContext(),items ,this,this);
        adapter2 =new gridAdapter(getContext(),items ,this);
        GetAllPlaces();
        return root;




    }

    private void GetAllPlaces() {

        db.collection("Places").whereEqualTo("type", "اماكن سياحية").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots){
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                     title = documentSnapshot.getString("title");
                                     image = documentSnapshot.getString("Image");
                                     desc = documentSnapshot.getString("descriptions");
                                     type = documentSnapshot.getString("type");
                                     history = documentSnapshot.getString("history");
                                    lat1 = documentSnapshot.getDouble("lat1");
                                    lat2 = documentSnapshot.getDouble("lat2");
                                    Log.e("name", title.toString());
                                    Log.e("image", image.toString());
                                    Log.e("desc", desc.toString());

                                    Places user = new Places(id, title,image ,desc ,type ,history,lat1,lat2);
                                    items.add(user);
                                    rv.setLayoutManager(
                                            new LinearLayoutManager(getActivity()));
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                    rv.setRotationY(180);

                                    final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getContext(), animationList[i]);
                                    rv.setLayoutAnimation(controller);

                                    adapter.notifyDataSetChanged();
                                    rv.scheduleLayoutAnimation();
                                    Log.e("LogDATA", items.toString());

                                }
                            }
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("LogDATA", "get failed with ");


            }
        });
    }



    @Override
    public void onItemClick(Places data, int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("title",data.getTitle());
        intent.putExtra("image",data.getImage());
        intent.putExtra("desc",data.getDescriptions());
        intent.putExtra("history",data.getHistory());
        intent.putExtra("type",data.getType());
        intent.putExtra("lat1",data.getLat1());
        intent.putExtra("lat2",data.getLat2());
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menue1, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.linear:

                rv.setLayoutManager(
                        new LinearLayoutManager(getActivity()));
                rv.setHasFixedSize(true);
                rv.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                return true;
            case R.id.grid:
                rv.setLayoutManager(
                        new GridLayoutManager(getActivity(),2));
                rv.setHasFixedSize(true);
                rv.setAdapter(adapter2);

                adapter2.notifyDataSetChanged();
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}