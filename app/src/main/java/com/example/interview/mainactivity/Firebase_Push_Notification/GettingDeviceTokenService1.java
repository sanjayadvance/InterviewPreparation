package com.example.interview.mainactivity.Firebase_Push_Notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GettingDeviceTokenService1 extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String DeviceToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("DEVICE",DeviceToken);
    }

}
