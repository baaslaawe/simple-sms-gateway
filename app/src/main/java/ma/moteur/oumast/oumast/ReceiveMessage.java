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
    public void onReceive(Context context, Intent intent) {
        Bundle mesaj = intent.getExtras();
        SmsMessage[] smsMessage = null;
        String msj = "";

        if(mesaj!= null){
            Object[] pdus = (Object[])mesaj.get("pdus");
            smsMessage = new SmsMessage[pdus.length];
            for(int i = 0; i < pdus.length; i++){
                smsMessage[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                msj = smsMessage[i].getDisplayOriginatingAddress();
            }

            Intent mIntent = new Intent(context, MainActivity.class);
            mIntent.putExtra("sms", msj);
            context.startActivity(mIntent);
        }
    }
}
