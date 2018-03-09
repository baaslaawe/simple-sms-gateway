package ma.moteur.oumast.oumast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NetworkActivity extends AppCompatActivity {
    TextView txtNet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        txtNet = (TextView) findViewById(R.id.textViewNet);
        txtNet.setText("No Internet");
    }
}
