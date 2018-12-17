package kamal.agooz.ahmed.barberboy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookinActivity extends AppCompatActivity
{
    EditText name_field;
    CheckBox hair,beard,pigment,musk;
    Button set_time,set_date,book_now;
    static TextView selected_time,selected_date;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String name,hair2 = "",beard2 = "",pigment2 = "",musk2 = "",time_txt,date_txt;
    double price = 0;
    String allprice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookin);
        name_field = findViewById(R.id.name_field);

        hair = findViewById(R.id.shave_hair_chk);
        beard = findViewById(R.id.shave_beard_chk);
        pigment = findViewById(R.id.hair_pigment_chk);
        musk = findViewById(R.id.face_musk_chk);

        set_time = findViewById(R.id.set_time_btn);
        set_date= findViewById(R.id.set_date_btn);
        book_now = findViewById(R.id.book_now_btn);

        selected_time = findViewById(R.id.time_txt);
        selected_date = findViewById(R.id.date_txt);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        set_time.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        book_now.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name=name_field.getText().toString();
                if(hair.isChecked())
                {
                    price=price+25;
                    hair2="Shave Hair";
                }
                if(beard.isChecked())
                {
                    price=price+15;
                    beard2="Shave Beard";
                }
                if (pigment.isChecked())
                {
                    price = price + 35;
                    pigment2 = "Hair Pigment";
                }

                if (musk.isChecked())
                {
                    price = price + 20;
                    musk2 = "Face Musk";
                }

                time_txt = selected_time.getText().toString();
                date_txt = selected_date.getText().toString();

                if (name.length() == 0
                        || !hair.isChecked()
                        && !beard.isChecked()
                        && !pigment.isChecked()
                        && !musk.isChecked()
                        || time_txt.length() == 0 ||
                        date_txt.length() == 0)
                {
                    Toast.makeText(BookinActivity.this, "Please Enter Correct Data", Toast.LENGTH_SHORT).show();
                }else
                {
                    allprice = Double.toString(price);
                    WriteNewBook(name, hair2, beard2, pigment2, musk2, time_txt, date_txt, allprice);
                    Intent intent=new Intent(getApplicationContext(),startActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void WriteNewBook(String name, String hair,String beard, String pigment, String musk, String time, String date, String price)
    {
       BookList bookList=new BookList(name,hair,beard,pigment,musk,time,date,price);
       String key=databaseReference.child("allbooks").push().getKey();
       databaseReference.child("allbooks").child(key).setValue(bookList);
    }
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), R.style.dialoge, this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {

            // Do something with the time chosen by the user
            int hour = hourOfDay;
            int minutes = minute;

            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "PM";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "AM";
            } else if (hour == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minutes < 10)
                min = "0" + minutes ;
            else
                min = String.valueOf(minutes);

            // Append in a StringBuilder
            String aTime = new StringBuilder().append(hour).append(':')
                    .append(min ).append(" ").append(timeSet).toString();

            selected_time.setText(aTime);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), R.style.dialoge,this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            // Do something with the date chosen by the user
            int month2 = month + 1;
            selected_date.setText(day + "/" + month2  + "/" + year);
        }
    }
}
