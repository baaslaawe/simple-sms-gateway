package ma.moteur.oumast.oumast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BalanceActivity extends AppCompatActivity {
    TextView txb ;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        txb = (TextView) findViewById(R.id.textViewB);
        txb.setText("Please Reload the balance");
        b = (Button) findViewById(R.id.buttonR);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

    }
    public void refresh(){
        Intent in = getIntent();
        String num = in.getStringExtra("num1");
        if(!num.isEmpty()){
            Intent it = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(it);
        }
    }
}
