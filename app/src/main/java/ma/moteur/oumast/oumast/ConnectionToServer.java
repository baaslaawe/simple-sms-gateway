package ma.moteur.oumast.oumast;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by poste05 on 02/03/2018.
 */

public class ConnectionToServer {
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

    public void receveToServer(Context ctx, String urlReceve) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            ctx.getApplicationContext().startForegroundService(new Intent(ctx.getApplicationContext(), MyAsyncTaskgetNews.class));
        } else {
            ctx.getApplicationContext().startService(new Intent(ctx.getApplicationContext(), MyAsyncTaskgetNews.class));
        }
        try {
            URL url = new URL(urlReceve);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //bool=true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
