package ma.moteur.oumast.oumast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by poste05 on 09/03/2018.
 */

public class ConnectionDetector {
    Context context;

    public ConnectionDetector(Context context) {
        this.context = context;
    }
    public boolean isConnected(){
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if(info != null){
                if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
