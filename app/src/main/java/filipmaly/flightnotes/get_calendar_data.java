package filipmaly.flightnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class get_calendar_data extends AppCompatActivity {

    private SimpleCursorAdapter dataadapModel;
    DatabaseHelper myDb;

    Spinner spinnermodel1, spinnermodel2, spinnermodel3, spinnermodel4, spinnerhour;
    EditText editTextTime, editTextHour,editTextWeather,editTextWind,editTextTemp,editTextPlace;
    TextView textViewDate,textViewModel1,textViewModel2,textViewModel3,textViewModel4, getID_calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_calendar_data);


        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        editTextTime = (EditText) findViewById((R.id.editTextTime));
        editTextHour = (EditText) findViewById((R.id.editTextHour));
        editTextWeather =(EditText) findViewById((R.id.editTextWeather));
        editTextWind= (EditText) findViewById((R.id.editTextWind));
        editTextTemp= (EditText) findViewById((R.id.editTextTemp));
        editTextPlace= (EditText) findViewById((R.id.editTextPlace));

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewModel1= (TextView) findViewById(R.id.textViewModel1);
        textViewModel2= (TextView) findViewById(R.id.textViewModel2);
        textViewModel3= (TextView) findViewById(R.id.textViewModel3);
        textViewModel4= (TextView) findViewById(R.id.textViewModel4);
        getID_calendar= (TextView) findViewById(R.id.getID_calendar);

        spinnermodel1 = (Spinner) findViewById(R.id.spinnermodel1);
        spinnermodel2 = (Spinner) findViewById(R.id.spinnermodel2);
        spinnermodel3 = (Spinner) findViewById(R.id.spinnermodel3);
        spinnermodel4 = (Spinner) findViewById(R.id.spinnermodel4);
        spinnerhour = (Spinner) findViewById(R.id.spinnerhour);


        setspine();
        setHourDate();
        stringminute();
        if(calendar_activity.addDate == false)
        {
            setTextsCalendar();
        }
    }

    public void SaveCalendar (View button)
    {
        switch (button.getId())
        {
            case R.id.calendarsave:
                savedataAirport();
                Intent intent1 = new Intent(getApplicationContext(), calendar_activity.class);
                startActivity(intent1);

                break;
        }

    }


    public void setspine() {



        Cursor res = myDb.getModelData();
        String[] columnsModel = new String[]
                {
                        DatabaseHelper.COL_2P,
                };

        int[] Model_item = new int[]{
                R.id.TextModeSpinner,

        };


        dataadapModel = new SimpleCursorAdapter
                (
                        this,
                        R.layout.content_spinner_mode,
                        res,
                        columnsModel,
                        Model_item, 0);


        spinnermodel1.setAdapter(dataadapModel);
        spinnermodel2.setAdapter(dataadapModel);
        spinnermodel3.setAdapter(dataadapModel);
        spinnermodel4.setAdapter(dataadapModel);

    }

    public void stringminute()
   {
           ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                   R.array.minuteItem, android.R.layout.simple_spinner_item);
           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           spinnerhour.setAdapter(adapter);
       spinnerhour.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
       spinnerhour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               editTextTime.setText(spinnerhour.getSelectedItem().toString());
               editTextTime.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
           }

           public void onNothingSelected(AdapterView<?> adapterView) {
               return;
           }
       });

   }

    public void setHourDate()
    {
        Time now = new Time();
        now.setToNow();
        editTextHour.setText(now.format("%k:%M"));
        editTextHour.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        textViewDate.setText(calendar_activity.selectedDate);
        textViewDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void setTextsCalendar ()
    {
        Cursor res = myDb.getCurrentDate();

        editTextWeather.setText(res.getString(5));
        editTextWind.setText(res.getString(12));
        editTextTemp.setText(res.getString(6));
        editTextPlace.setText(res.getString(4));
        textViewModel1.setText(res.getString(7));
        textViewModel2.setText(res.getString(8));
        textViewModel3.setText(res.getString(9));
        textViewModel4.setText(res.getString(10));

    }



    public void savedataAirport()
    {
        boolean isUpdate = myDb.insertDataAirport
                (
                        textViewDate.getText().toString(),
                        editTextHour.getText().toString(),
                        editTextPlace.getText().toString(),
                        editTextWeather.getText().toString(),
                        editTextTemp.getText().toString(),
                        textViewModel1.getText().toString(),
                        textViewModel2.getText().toString(),
                        textViewModel3.getText().toString(),
                        textViewModel4.getText().toString(),
                        textViewModel4.getText().toString(),
                        editTextHour.getText().toString(),
                        editTextWind.getText().toString()


                );


    }




}

