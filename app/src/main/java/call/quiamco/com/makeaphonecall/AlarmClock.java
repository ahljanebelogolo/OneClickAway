package call.quiamco.com.makeaphonecall;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmClock extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    int choose_alarm_sound;

    @TargetApi(Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_clock);

        this.context = this;

        // initialize our alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //initialize our timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        //initialize our text update box
        update_text = (TextView) findViewById(R.id.updateText);

        final Calendar calendar = Calendar.getInstance();


        //create an intents to alarm receiver class
        final Intent my_Intent = new Intent(this.context, Receiver.class);



        // create the spinner in the main UI
        Spinner spinner = (Spinner) findViewById(R.id.musicSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.music_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Set an onclick listener to the onItemSelected method
        spinner.setOnItemSelectedListener(this);




        // initialize start button
        Button alarm_on = (Button) findViewById(R.id.alarmOn);



        // create an onClick listener to start the alarm
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


                // setting calendar instance with the hour and minute that we picked
                // on the time picker
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getCurrentMinute());

                // get the int values of the hour and minute
                int hour = alarm_timepicker.getCurrentHour();
                int minute = alarm_timepicker.getCurrentMinute();

                // convert the int values to strings
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                // convert 24-hour time to 12-hour time
                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {
                    //10:7 --> 10:07
                    minute_string = "0" + String.valueOf(minute);
                }

                // method that changes the update text Textbox
                set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);


                // put in extra string into my_intent
                // tells the clock that you pressed the "alarm on" button
                my_Intent.putExtra("extra", "alarm on");


                // put in an extra int into my_intent
                // tells the clock that you want a certain value from the drop-down menu/spinner
                my_Intent.putExtra("sound_choice", choose_alarm_sound);
                Log.e("The sound id is" , String.valueOf(choose_alarm_sound));









                // create a pending intent that delays the intent
                // until the specified calendar time
                pending_intent = PendingIntent.getBroadcast(AlarmClock.this, 0,
                        my_Intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);
            }

        });



        // initialize the stop button
        Button alarm_off = (Button) findViewById(R.id.alarmOff);
        // create an onClick listener to stop the alarm or undo an alarm set

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // method that changes the update text Textbox
                set_alarm_text("Alarm off!");

                // cancel the alarm
                alarm_manager.cancel(pending_intent);

                // put extra string into my_intent
                // tells the clock that you pressed the "alarm off" button
                my_Intent.putExtra("extra", "alarm off");


                // also put an extra int into the alarm off section
                // to prevent crashes in a Null Pointer Exception
                my_Intent.putExtra("sound_choice", choose_alarm_sound);

                // stop the ringtone
                sendBroadcast(my_Intent);

               Intent my_Intent=new Intent(AlarmClock.this,MainActivity.class);
               startActivityForResult(my_Intent, 1);

            }
        });



    }

    private void set_alarm_text(String output) {

        update_text.setText(output);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        // outputting whatever id the user has selected

        choose_alarm_sound = (int) id;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }
}


