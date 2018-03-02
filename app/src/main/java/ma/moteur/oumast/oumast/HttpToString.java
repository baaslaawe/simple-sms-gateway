package ma.moteur.oumast.oumast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by poste05 on 02/03/2018.
 */

public class HttpToString {
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
}
