package tw.ming.app.helloworid.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
    }

    public void test1(View view){
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("ming","iii");
    }

    public void test2(View view){
        tv.setText("Lottery:"+(int)(Math.random()*49+1));
    }
    private  class MyAsyncTask extends AsyncTask<String,Void,Void>{

        public MyAsyncTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("ming","onPreExecute");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("ming","onPostExecute");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            for(Integer j:values){
                tv.setText(values[0]+":"+values[1]+":"+values[2]);
            }
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(String... names) {
            for(String name:names){
                Log.i("ming",name);
                publishProgress(i,i*10,i*100);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }
    }
}
