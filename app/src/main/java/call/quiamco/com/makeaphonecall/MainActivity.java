package call.quiamco.com.makeaphonecall;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnContacts;
    private Button btnCall;
    private Button btnSms;
    private Button btnAlarm;
//    private EditText etNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();

        btnContacts = (Button) findViewById(R.id.btnContacts);
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent read1 = new Intent();
                read1.setAction(android.content.Intent.ACTION_VIEW);
                read1.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivity(read1);
            }
        });

        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//TODO Auto-generated method stub

                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("tel:"));
                startActivity(callIntent);

            }

        });

////TODO Auto-generated method stub


    }

    private void addListenerOnButton() {
        final Context context = this;



        btnAlarm = (Button) findViewById(R.id.btnAlarm);

        btnAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent intent = new Intent(context, AlarmClock.class);

                startActivity(intent);


            }
        });

        btnSms = (Button) findViewById(R.id.btnSms);

        btnSms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Activity.class);

                startActivity(intent);

            }
        });

    }
}

