package com.dotengine.linsir.lin_webrtc_demo;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 *  Created by linSir 
 *  date at 2017/5/26.
 *  describe:      
 */

public class MyApplication extends Application {

    private static Socket client;
    //please add your
    private String host = "";

    @Override public void onCreate() {
        super.onCreate();

        try{
            client = IO.socket(host);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //client.on("id",)


    }
}
