package com.example.admin.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SchoolWorkActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_work2);

        calendarView = (CalendarView) findViewById(R.id.calendarView_remind);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                int d = dayOfMonth;
                int m = month;
                m = m + 1;
                int y = year;
                String curDate = String.valueOf(d);
                String curMonth = String.valueOf(m);
                String curyear = String.valueOf(y);

                System.out.println("SELECTED DATE: " + curDate + "-" + curMonth + "-" + curyear);

            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // TODO Auto-generated method stub
              //  initScheduleEvent();
            }
        });

        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        //LinearLayout ll= (LinearLayout)inflater.inflate(R.layout.myLayout, null, false);
       // CalendarView cv = (CalendarView) ll.getChildAt(0);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                // create the AlertDialog

                new AlertDialog.Builder(SchoolWorkActivity.this)
                        .setTitle("My School Work Calendar")
                        .setMessage("Click to schedule or view School work.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //do nothing...yet
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Do nothing.
                            }
                        }
                ).show();
            }
        });

    }
}


//        /** Adds Events and Reminders in Calendar. */
//        private void addReminderInCalendar() {
//
//            Uri EVENTS_URI = Uri.parse(getCalendarUriBase(true) + "events");
//            ContentResolver cr = getContentResolver();
//
//
//            /** Inserting an event in calendar. */
//            ContentValues values = new ContentValues();
//            values.put(CalendarContract.Events.CALENDAR_ID, 1);
//            values.put(CalendarContract.Events.TITLE, "Sanjeev Reminder 01");
//            values.put(CalendarContract.Events.DESCRIPTION, "A test Reminder.");
//            values.put(CalendarContract.Events.ALL_DAY, 0);
//            // event starts at 11 minutes from now
//
//            // ends 60 minutes from now
//
//            values.put(CalendarContract.Events.HAS_ALARM, 1);
//            Uri event = cr.insert(EVENTS_URI, values);
//
//            // Display event id.
//            Toast.makeText(getApplicationContext(), "Event added :: ID :: " + event.getLastPathSegment(), Toast.LENGTH_SHORT).show();
//
//            /** Adding reminder for event added. */
//            Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(true) + "reminders");
//            values = new ContentValues();
//            values.put(CalendarContract.Reminders.EVENT_ID, Long.parseLong(event.getLastPathSegment()));
//            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
//            values.put(CalendarContract.Reminders.MINUTES, 10);
//            cr.insert(REMINDERS_URI, values);
//        }
//
///** Returns Calendar Base URI, supports both new and old OS. */
//        private String getCalendarUriBase(boolean eventUri) {
//            Uri calendarURI = null;
//            try {
//                if (android.os.Build.VERSION.SDK_INT <= 7) {
//                    calendarURI = (eventUri) ? Uri.parse("content://calendar/") : Uri.parse("content://calendar/calendars");
//                } else {
//                    calendarURI = (eventUri) ? Uri.parse("content://com.android.calendar/") : Uri
//                            .parse("content://com.android.calendar/calendars");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return calendarURI.toString();
//        }
//
//    }

