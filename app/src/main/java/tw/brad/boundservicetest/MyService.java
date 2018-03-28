package tw.brad.boundservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MyService extends Service {
    private final LocalBinder binder = new LocalBinder();
    private MainActivity.MyHandler handler;

    // Step 2: 一個繼承 Binder 的 public 類別
    public class LocalBinder extends Binder {
        MyService getMyService(){
            Log.v("brad", "getMyService()");
            return MyService.this;
        }
    }

    public MyService() {
        Log.v("brad", "MyService");
    }

    // 開始 Bound 綁定服務
    @Override
    public IBinder onBind(Intent intent) {
        Log.v("brad", "onBind");

        //return null; => Step 1
        return binder;
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

    void doSomething(String name){
        Log.v("brad", "doSomething: " + name);
    }

    void showTextView(){
        Message mesg = new Message();
        mesg.arg1 = (int)(Math.random()*49+1);
        handler.sendMessage(mesg);
    }

    void setHandler(MainActivity.MyHandler handler){
        this.handler = handler;
    }

}
