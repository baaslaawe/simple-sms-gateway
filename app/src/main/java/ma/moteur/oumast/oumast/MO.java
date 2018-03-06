package ma.moteur.oumast.oumast;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mohamed oumast on 05/03/2018.
 */

public class MO implements Serializable {
    public MO(){}


    public static String error="default error";


    public int getIdDevicePhone(){
        return 80;
    }

    public InputStream getHttpResponse(String urlSend){
        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(urlSend);
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                return  content;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean receveToServer(Context ctx, String urlReceve, Class c) {
        try {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            ctx.getApplicationContext().startForegroundService(new Intent(ctx.getApplicationContext(), c));
        } else {
            ctx.getApplicationContext().startService(new Intent(ctx.getApplicationContext(), c));
        }
            URL url = new URL(urlReceve);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (!urlConnection.equals(null)){
                return  true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            error = ex.getMessage();
        }
        return false;
    }
    public StringBuilder getStringBuilder(InputStream content){
        if(content != null){
            try{
                StringBuilder HttpResponse = new StringBuilder();;
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while((line = reader.readLine()) != null){
                    HttpResponse.append(line);
                }
                return HttpResponse;
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }



    public SmsObject getSmsObjectInfos(StringBuilder sb){
        if(sb != null)
        {
            SmsObject obj = new SmsObject();
            try {
                JSONObject jsonObject = new JSONObject(sb.toString());
                obj.setId(jsonObject.getString("id"));
                obj.setNumber(jsonObject.getString("phone"));
                obj.setMessage(jsonObject.getString("message"));
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
