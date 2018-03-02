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
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;
    private static  String phone=null, message=null, id=null, imeistring=null;
    private static int i=0;
    private static boolean bool = true;
    TextView textViewAff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewAff = (TextView) findViewById(R.id.textView8);
    }

    @Override
    protected void onStart(){
    super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getIdDevicePhone();
        if(bool){
            try {
                Thread.sleep(4000);
                new  MyAsyncTaskgetNews().execute("");
            } catch (InterruptedException e) {

            }
        }
    }
    public static String msg="";
    public void sendSMSMessage() {

        msg="hani baghi njerb l permess";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            msg="chedit l permession";
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                }
                msg="chedit l permession";
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                }
                msg="ma3erftsh ash chedit hna";
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            msg="mashditsh l permession";
        }
    }
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        msg="hani jit bash nsift";
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                    }
                    msg="rani sift";
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                    }
                    i=1;
                } else {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                    }
                    msg="ma9dertsh nsift";
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                    }
                    i=5;
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
            URL url = new URL("http://192.168.1.96/sms/receve.php?id="+id+"&etat"+i);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            msg="tsaft l receve l etat :"+i;
            bool=true;
        }catch(Exception ex){
            msg="matsaft l receve";
        }
    }

    String NewsData="";
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {




        @Override
        protected void onPreExecute() {
            publishProgress("onPreExecute :1" );
            phone=null;
            id=null;
            ////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////
            /////////////////////////////
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }
            message=null;
            i=0;
            bool=false;
            publishProgress("onPreExecute :2" );
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }

        }

        @Override
        protected String  doInBackground(String... params) {
            publishProgress("doInBackground :3" );
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }
            StringBuilder reponseHTTP = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet
                    ("http://192.168.1.96/sms/sms.php?DEVICE_ID="+imeistring);
            try{
                publishProgress("try{ doInBackground :4" );
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {

                }
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if(statusCode == 200){
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while((line = reader.readLine()) != null){
                        reponseHTTP.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(reponseHTTP.toString());
                    id = jsonObject.getString("id");
                    phone = jsonObject.getString("phone");
                    message = jsonObject.getString("message");
                    publishProgress("jabhom men server" );
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {

                    }
                    if(!id.isEmpty()){
                        publishProgress("ghadi isift" );
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {

                        }
                        publishProgress("9bel send "+msg );
                        sendSMSMessage();
                        publishProgress("be3d send "+msg );

                    }else{
                        publishProgress("majebt walo" );
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {

                        }
                        return null;
                    }
                }else{
                    publishProgress("ma9dertsh c:"+statusCode );
                }
            }catch (Exception e){
                publishProgress("catch e:"+e.getMessage() );
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {

                }

            }
            return null;

        }
        protected void onProgressUpdate(String... progress) {
            textViewAff.setText(progress[0]);
        }
        protected void onPostExecute(String  result2){

            publishProgress("vers receve.php" );
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            try{

            receveToServer();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                }
                publishProgress(msg);
                //onResume();
            }catch(Exception e){
                publishProgress("Message howa "+i);
            }
        }
    }
}