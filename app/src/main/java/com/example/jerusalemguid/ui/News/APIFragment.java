package com.example.jerusalemguid.ui.News;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jerusalemguid.R;
import com.example.jerusalemguid.ui.Adapter.ArticaleAdapter;
import com.example.jerusalemguid.ui.models.Articale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class APIFragment extends Fragment {


    String url = "https://www.json-generator.com/api/json/get/bTSfhiLcwO?indent=2";
    private RequestQueue requestQueue;
    private RecyclerView recyclerView ;
    private List<Articale> articaleList ;
    ArticaleAdapter adapter;
    private com.example.jerusalemguid.ui.models.VolleySingleton VolleySingleton;

    LinearLayoutManager llm = new LinearLayoutManager(getContext());



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_a_p_i, container, false);


        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(llm);

        requestQueue = VolleySingleton.getmInstance(getContext()).getRequestQueue();
        articaleList = new ArrayList<>();
        loadArticlesList();
   return  root;

    }
    private void loadArticlesList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("jj" , "onResponse");

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray articlesArray = obj.getJSONArray("articles");

                            for (int i = 0; i < articlesArray.length(); i++) {

                                try {
                                    JSONObject jsonObject =  articlesArray.getJSONObject(i);
                                    String title = jsonObject.getString("title");
                                    String author = jsonObject.getString("author");
                                    String publishedAt = jsonObject.getString("publishedAt");
                                    String urlToImage = jsonObject.getString("urlToImage");
                                    String description = jsonObject.getString("description");
                                    Articale articale = new Articale(author , title , description , publishedAt , urlToImage);
                                    articaleList.add(articale) ;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adapter = new ArticaleAdapter(getContext() , articaleList);
                                adapter.notifyDataSetChanged();

                                recyclerView.setAdapter(adapter);
                                recyclerView.setRotationY(180);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("errerr" , "e.printStackTrace()");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){

        };


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);
    }
}