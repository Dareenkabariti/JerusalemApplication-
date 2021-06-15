package com.example.jerusalemguid.ui.models;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleySingleton {
    public RequestQueue requestQueue ;
    public static  VolleySingleton mInstance ;
    public VolleySingleton(Context context ){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static  synchronized VolleySingleton getmInstance(Context context ){
        if (mInstance == null){
            mInstance=new VolleySingleton(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue ;
    }
}
