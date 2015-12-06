package filipmaly.flightnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class calendar_activity extends AppCompatActivity {

    public void drawerClick(MenuItem item){        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }

    public static String selectedDate = "";
    public static boolean addDate= false;

    CalendarView calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                int d = dayOfMonth;
                int m = month;
                int y = year;

                selectedDate =String.valueOf(d) + "/" + String.valueOf(m) + "/"  + String.valueOf(y);
                addDate =false;



                //selectedDate = "datum";
                Intent intent1 = new Intent(getApplicationContext(), get_calendar_data.class);
                startActivity(intent1);


                //Toast.makeText(getApplicationContext(),dayOfMonth + "/" + month +"/" + year, Toast.LENGTH_LONG).show();
            }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_design, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_back) {


            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void adddate (View button)
    {
        switch (button.getId())
        {
            case R.id.addcalendardate:
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();

                addDate =true;

                //System.out.println(dateFormat.format(cal.getTime()));

                selectedDate = (dateFormat.format(cal.getTime()));

            Intent intent1 = new Intent(getApplicationContext(), get_calendar_data.class);
            startActivity(intent1);
            break;


        }

    }



}
