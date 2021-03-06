package ma.moteur.oumast.oumast;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    private TelephonyManager mTelephonyManager;
    private static  String phone=null, message=null, id=null, imeistring=null;
    private static int i=0;
   // private static boolean bool = true;
    TextView textViewAff, textViewA;
    public boolean good=false, syncs=true;
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewAff = (TextView) findViewById(R.id.textView8);
        textViewA = (TextView) findViewById(R.id.textViewId);
        cd = new ConnectionDetector(this);
        /*Thread t = new Thread(new Runnable() {
            public void run() {
                int numb=0;
                try{

                }catch (Exception ex){
                    numb = Integer.parseInt(getIntent().getStringExtra("sms"));
                }

                if(numb == 555){
                    Intent o = new Intent(getApplicationContext(), BalanceActivity.class);
                    startActivity(o);
                }else {
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();*/

        /*Thread net = new Thread(new Runnable() {
            public void run() {
                /*
                // Seba7 lkhir si mohamed ini bessmi allah,
                // ghadi ikhessni n dir syncronisation bin onInBackground o had thred bash ila kan problem t connection yegfo o isifto l la page d error
                if(syncs == false) {
                    bool=false;
                    Intent oms = new Intent(getApplicationContext(), NetworkActivity.class);
                    startActivity(oms);
                }else {
                    bool=true;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        net.start();
        */
    }
    public void to(){
        Intent oms = new Intent(getApplicationContext(), NetworkActivity.class);
        startActivity(oms);
    }
    public void CheckConnection(){
        if(!cd.isConnected()){

            syncs = false;
        }else {
            syncs = true;
        }
    }
    @Override
    protected void onStart(){
        super.onStart();

    }
    @Override
    protected void onResume() {
        super.onResume();
        getIdDevicePhone();
        newThread();
    }
    public void newThread(){
            new MyAsyncTaskgetNews().execute("");
    }
    public static String msg="";
    public void sendSMSMessage() {

        msg="hani baghi njerb l permess";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(syncs = true){
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phone, null, message, null, null);
                        i=1;
                    }else {
                        i=0;
                    }

                } else {
                    i=0;
                }
            }
        }

    }

    protected void getIdDevicePhone(){
        imeistring="80";
    }

    public void receveToServer() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            this.startForegroundService(new Intent(this, MyAsyncTaskgetNews.class));
        } else {
            this.startService(new Intent(this, MyAsyncTaskgetNews.class));
        }
        try {
            if((i ==1 || i == 0) && syncs == true ){
                URL url = new URL("http://192.168.1.69/sms/receve.php?id="+id+"&etat"+i);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                msg="tsaft l receve l etat :"+i;
            }
        }catch(Exception ex){
            msg="matsaft l receve";
        }
    }

    String NewsData="";
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            phone=null;
            id=null;
            ////////////////////////////////////////////////////////////////////////////////////////
            message=null;
            i=0;
        }

        @Override
        protected String  doInBackground(String... params) {
            publishProgress("doInBackground :3" );
            StringBuilder reponseHTTP = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet
                    ("http://192.168.1.69/sms/sms.php?DEVICE_ID="+imeistring);
            try{
                publishProgress("try{ doInBackground :4" );
                if(httpGet != null) {
                    HttpResponse response = client.execute(httpGet);
                    publishProgress("try{ mashi ana :4" );
                    if (response != null) {
                        StatusLine statusLine = response.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        if (statusCode == 200) {
                            HttpEntity entity = response.getEntity();
                            InputStream content = entity.getContent();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                reponseHTTP.append(line);
                            }
                            JSONObject jsonObject = new JSONObject(reponseHTTP.toString());
                            id = jsonObject.getString("id");
                            phone = jsonObject.getString("phone");
                            message = jsonObject.getString("message");
                            publishProgress("jabhom men server");
                            if (!id.isEmpty()) {
                                publishProgress("ghadi isift");
                                publishProgress("9bel send " + msg);
                                CheckConnection();
                                sendSMSMessage();
                                publishProgress("be3d send " + msg);
                                good=true;
                                return "send";
                            } else {
                                syncs=false;
                                publishProgress("majebt walo");
                                return null;
                            }
                        } else {
                            syncs=false;
                            publishProgress("ma9dertsh c:" + statusCode);
                            return null;
                        }
                    }else{
                        syncs=false;
                        publishProgress("response is null");
                        return null;
                    }
                }else {
                    syncs=false;
                    publishProgress("httpGet is null" );
                    return null;
                }
            }catch (Exception e){
                syncs=false;
                publishProgress("catch e:"+e.getMessage());
                to();
                return null;
            }
        }
        protected void onProgressUpdate(String... progress) {

            textViewAff.setText(progress[0]);
            textViewA.setText(msg);

        }
        protected void onPostExecute(String  result2){

            publishProgress("vers receve.php" );
            if(good){
                try{
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    receveToServer();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }catch(Exception e){
                    publishProgress("Message howa "+i);
                }
            }else{
                publishProgress("Majebtech send");
            }
            try {
                Thread.sleep(1000);
                newThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}