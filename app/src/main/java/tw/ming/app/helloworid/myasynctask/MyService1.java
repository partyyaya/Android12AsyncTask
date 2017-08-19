package tw.ming.app.helloworid.myasynctask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;


public class MyService1 extends Service{
    public MyService1() {
        Log.i("ming","MyService1()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ming", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ming", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ming", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("ming", "onDestroy");
    }
}
