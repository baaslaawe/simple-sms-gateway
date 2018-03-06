package ma.moteur.oumast.oumast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Created by poste05 on 06/03/2018.
 */

public class ReceiveMessage extends BroadcastReceiver {
    final SmsManager mysms = SmsManager.getDefault();
    public void onReceive(Context context, Intent intent) {
        Bundle mybundle =intent.getExtras();
        try {
            if(mybundle != null){
                final Object[] messageContent =(Object[]) mybundle.get("pdus");
                for (int i = 0; i <messageContent.length; i++){
                    SmsMessage mynewsms = SmsMessage.createFromPdu((byte[]) messageContent[i]);
                   //
                    String s = mynewsms.getDisplayOriginatingAddress();
                    String s1 = mynewsms.getDisplayMessageBody();
                    intent.putExtra("num1", s);
                    intent.putExtra("msg1", s1);
                    context.startActivity(intent);
                }
            }
        }catch (Exception e){

        }
    }
}
