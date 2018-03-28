package tw.brad.boundservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    // 開始 Bound 綁定服務
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("brad", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("brad", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("brad", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v("brad", "onDestroy");
        super.onDestroy();
    }
}
