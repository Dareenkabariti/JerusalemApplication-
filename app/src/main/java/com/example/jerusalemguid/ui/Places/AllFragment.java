package com.example.jerusalemguid.ui.Places;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
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
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.Adapter.PlacesAdapter;
import com.example.jerusalemguid.ui.Adapter.gridAdapter;
import com.example.jerusalemguid.ui.DetailsActivity;
import com.example.jerusalemguid.ui.models.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import top.defaults.colorpicker.ColorPickerPopup;

public class AllFragment extends Fragment implements PlacesAdapter.ItemClickListener{
    int i = 0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Places> items ;
    PlacesAdapter adapter;
    String token;
    gridAdapter adapter2;
    RecyclerView rv;
    int[] animationList = {R.anim.layout_animation_up_to_down,
            R.anim.layout_animation_right_to_left,
            R.anim.layout_animation_down_to_up,
            R.anim.layout_animation_left_to_right};
    FirebaseStorage mStrorage;
    String image ,title , type , description , history ;
    Double lat1,lat2;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.w("TAG" , "Fail to get token"+task.getException());
                    return;
                }
                    token = task.getResult();

                Log.e("token1" , token.toString());

            }
        });

        if (i < animationList.length - 1) {
            i++;
        } else {
            i = 0;


    }
        setHasOptionsMenu(true);
        View myFragment= inflater.inflate(R.layout.fragment_all, container, false);
        rv = myFragment.findViewById(R.id.rvAll);
        mStrorage = FirebaseStorage.getInstance();
        items=new ArrayList<Places>();
        adapter=new PlacesAdapter(getContext(),items ,this,this);
        adapter2=new gridAdapter(getContext(),items ,this);
        GetAllPlaces();

        return myFragment;
    }

    private void GetAllPlaces() {

        db.collection("Places").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                  title = documentSnapshot.getString("title");
                                  image = documentSnapshot.getString("Image");
                                  description = documentSnapshot.getString("descriptions");
                                  type = documentSnapshot.getString("type");
                                  history = documentSnapshot.getString("history");
                                  lat1 = documentSnapshot.getDouble("lat1");
                                  lat2 = documentSnapshot.getDouble("lat2");
                                    Log.e("name", title.toString());
                                 Log.e("image", image.toString());
                                 Log.e("desc", description.toString());

                                    Places user = new Places(id, title,image ,description ,type ,history ,lat1,lat2);
                                    items.add(user);
                                    rv.setLayoutManager(
                                            new LinearLayoutManager(getActivity()));
                                 //   rv.setLayoutManager(layoutManager);
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                    final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getContext(), animationList[i]);
                                    rv.setLayoutAnimation(controller);

                                    adapter.notifyDataSetChanged();
                                    rv.scheduleLayoutAnimation();
                                   rv.setRotationY(180);

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
              return  true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }
}



