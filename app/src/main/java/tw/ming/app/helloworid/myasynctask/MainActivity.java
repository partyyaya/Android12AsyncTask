package tw.ming.app.helloworid.myasynctask;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;
    private TextView tv, tv2;
    private MyServiceConnection myServiceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        tv2 = (TextView)findViewById(R.id.tv2);

        Intent it = new Intent(this, MyService1.class);
        myServiceConnection = new MyServiceConnection();
        bindService(it,myServiceConnection, Context.BIND_AUTO_CREATE);

    }

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
    public void test1(View view){
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("Ming", "III", "OK", "Game", "IOS");
    }
    public void test2(View view){//部會干擾test1執行
        tv.setText("Lottery: " + (int)(Math.random()*49+1));
    }
    public void test3(View view){
        if (myAsyncTask!=null && !myAsyncTask.isCancelled()){
            myAsyncTask.cancel(true);//強制中斷
        }
    }

    private class MyAsyncTask extends AsyncTask<String,Integer,String>{
        int i;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("ming", "onPreExecute");
        }

        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);
            tv.setText(ret);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv2.setText(values[0] + " : " + values[1] + " : " + values[2]);
        }

        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);
            tv.setText(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("ming", "onCancelled");
        }

        @Override
        protected String doInBackground(String... names) {
            String result = "OK";
            for (String name : names){
                i++;
                Log.i("ming", name);
                publishProgress(i, i*10, i*100);//執行時一起加入
                if (isCancelled()){
                    result = "NOT OK";
                    break;
                }
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }
            return result;
        }
    }

}