package ma.moteur.oumast.oumast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by poste05 on 06/03/2018.
 */

public class CheckInternetConnection extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String res="0";
        int[] type = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
        if(isNetworkAvailable(context, type)){
            res="1";
        }
        Intent mIntent = new Intent(context, MainActivity.class);
        mIntent.putExtra("net", res);
        context.startActivity(mIntent);
    }
    private boolean isNetworkAvailable(Context context, int[] typeNetworks){
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int typeNetwork : typeNetworks){
                NetworkInfo networkInfo = cm.getNetworkInfo(typeNetwork);
                if(networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
}
