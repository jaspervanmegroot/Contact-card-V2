package com.example.jaspe.contact_card;

import android.content.Context;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

public class MyVolleyRequestQueue {

    private static MyVolleyRequestQueue mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private MyVolleyRequestQueue(Context context){
        this.mContext = context;
        this.mRequestQueue = getRequestQueue();
    }

    //
    public static synchronized MyVolleyRequestQueue getInstance(Context context) {
        if(mContext == null) {
            mInstance = new MyVolleyRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if( mRequestQueue == null ) {
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
}
