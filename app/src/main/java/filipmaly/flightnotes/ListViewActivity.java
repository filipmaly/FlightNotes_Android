package filipmaly.flightnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Calendar;


public class ListViewActivity extends AppCompatActivity {


    private SimpleCursorAdapter dataadap;
    DatabaseHelper myDb;
    public static String Idkliknuti="0";

    public void drawerClick(MenuItem item){        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myDb = new DatabaseHelper(this);
        myDb.getWritableDatabase();

        displayListView();
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


    private void displayListView() {


        Cursor res = myDb.getListData();

        String[] columns = new String[]
                {
                        DatabaseHelper.COL_1,
                        DatabaseHelper.COL_2,
                        DatabaseHelper.COL_8,
                        DatabaseHelper.COL_6
                };

        int[] itemfrom_xml = new int[]{
                R.id.Battery_ID,
                R.id.Battery_Name,
                R.id.TextModeSpinner,
                R.id.Battery_LastUse,
        };


        dataadap = new SimpleCursorAdapter
                (
                this,
                        R.layout.activity_listview__item,
                res,
                columns,
                itemfrom_xml, 0);


         ListView listView = (ListView) findViewById(R.id.listViewbattery);
         listView.setAdapter(dataadap);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);


                Idkliknuti = cursor.getString( cursor.getColumnIndex("_id") );



                Intent intent1 = new Intent(getApplicationContext(), get_vallue_button.class);
                startActivity(intent1);



            }
        });
    }


    public void onClickListwiev (View button)
    {
        switch (button.getId())
        {
            case R.id.buttonADDbattListWiev:
                AddData();
                displayListView();
                break;


        }
    }

    public  void AddData() {

        Calendar cal = Calendar.getInstance();

        //String date = cal.
        int inhour = cal.get(Calendar.HOUR_OF_DAY);
        int intmin = cal.get(Calendar.MINUTE);
        String _inhour = Integer.toString(inhour);
        String _intmin = Integer.toString(intmin);
        String pomlkka= ":";
        int intdate = cal.get(Calendar.DATE);
        String time = _inhour + pomlkka + _intmin;

        String date;
        date= "dodelat ";



        boolean isInserted = myDb.insertData("Zadej jmeno", "Zadej typ", "Zadej články", "Zadej kapacitu", date , time , "0");


    }

}

