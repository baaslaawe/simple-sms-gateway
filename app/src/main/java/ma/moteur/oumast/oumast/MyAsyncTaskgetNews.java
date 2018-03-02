package ma.moteur.oumast.oumast;

import android.os.AsyncTask;

/**
 * Created by poste05 on 02/03/2018.
 */

public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
    @Override
    protected void onPreExecute() {

    }
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    protected void onProgressUpdate(String... progress) {
        //textViewAff.setText(progress[0]);
    }
    protected void onPostExecute(String  result2){

    }
}
