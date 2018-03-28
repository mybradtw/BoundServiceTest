package tw.brad.boundservicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean isBound = false;    // 預設尚未連接
    private MyService myService;        // 將會在連接之後取得

    // 開發一個類別實作 ServiceConnection
    private class MyServiceConnection implements ServiceConnection {
        // 完成連接
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder)iBinder;
            myService = binder.getMyService();
            Log.v("brad", "onServiceConnected");
            isBound = true;
            myService.setHandler(handler);
        }
        // 結束連接
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("brad", "onServiceDisconnected");
            isBound = false;
        }
    }
    private MyServiceConnection connection = new MyServiceConnection();
    private MyHandler handler;
    private TextView mesg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new MyHandler();
        mesg = findViewById(R.id.mesg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isBound){
            unbindService(connection);
        }
    }

    public void test1(View view) {
        myService.doSomething("Brad");
    }

    public void test2(View view) {
        myService.showTextView();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("brad", "MainActivity:MyHandler:handlerMessage()");
            mesg.setText("OK:" + msg.arg1);
        }
    }
}
