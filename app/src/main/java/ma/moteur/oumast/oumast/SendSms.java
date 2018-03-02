package ma.moteur.oumast.oumast;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

/**
 * Created by poste05 on 02/03/2018.
 */
// 222 is error in second function
public class SendSms {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    public static String num, msg;
    public static int etat=222;

    public static void send(Context ctx, SmsObject obj) {

        if (ContextCompat.checkSelfPermission(ctx.getApplicationContext(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) ctx.getApplicationContext(),
                    Manifest.permission.SEND_SMS)){
            } else {
                ActivityCompat.requestPermissions((Activity) ctx.getApplicationContext(),
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        num=obj.getNumber();msg=obj.getMessage();
    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(!num.isEmpty()) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(num, null, msg, null, null);
                        etat=1;
                    }
                } else {
                    etat=0;
                }
            }
        }

    }
}
