package com.sk.broadcastreceiver;

/*
 * Created by Sambhaji Karad on 18-Jan-18
 * Mobile 9423476192
 * Email sambhaji2134@gmail.com/
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("API123",""+intent.getAction());

        if(intent.getAction().equals("com.sk.broadcastreceiver.SOME_ACTION"))
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();
        else {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = null;
            if (cm != null) {
                activeNetwork = cm.getActiveNetworkInfo();
            }
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if (isConnected) {
                try {
                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG).show();
            }
        }
    }
}
