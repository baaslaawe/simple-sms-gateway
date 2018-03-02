package ma.moteur.oumast.oumast;

import org.json.JSONObject;

/**
 * Created by poste05 on 02/03/2018.
 */

public class StringToObject {
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
