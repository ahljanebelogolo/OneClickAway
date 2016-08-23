package call.quiamco.com.makeaphonecall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity extends AppCompatActivity {

    EditText txtnumber, txtMsg;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        txtnumber = (EditText) findViewById(R.id.txtnumber);
        txtMsg = (EditText) findViewById(R.id.txtMsg);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String No = txtnumber.getText().toString();
                String Message = txtMsg.getText().toString();

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(No, null, Message, null, null);


                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}