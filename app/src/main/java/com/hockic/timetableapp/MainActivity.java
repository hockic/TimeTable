package com.hockic.timetableapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hockic.timetable.Color;
import com.hockic.timetable.Day;
import com.hockic.timetable.TimeTable;
import com.hockic.timetable.TimeTableEvent;
import com.hockic.timetable.TimeTableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeTableView timeTable = (TimeTableView) findViewById(R.id.timeTable);

        // Set values
        timeTable.setOrdinalNumberInterpreter(ordinalInterpreter);
        timeTable.setWeekDayNameInterpreter(weekDayNameInterpreter);
        timeTable.setEventTextInterpreter(eventTextInterpreter);
        timeTable.setOnEventClickListener(onItemClickListener);
        timeTable.setOrdinalWidth(40);
        timeTable.setNumberOfRows(18);
        timeTable.setNumberOfDays(5);


        // Set schedule events
        timeTable.setTimeTableEvents(getEvents());

        // Setup table
        timeTable.setup();
    }

    TimeTable.OrdinalNumberInterpreter ordinalInterpreter = new TimeTable.OrdinalNumberInterpreter(){
        @Override
        public String interpret(int num){
            return (num <= 9) ? Integer.toString(num + 7) + ":00" : "";
        }
    };

    TimeTable.WeekDayNameInterpreter weekDayNameInterpreter = new TimeTable.WeekDayNameInterpreter() {
        @Override
        public String interpret(int day) {
            return getDayName(day);
        }
    };

    TimeTable.OnCellClickListener onItemClickListener = new TimeTable.OnCellClickListener() {
        @Override
        public void onClick(TimeTableEvent _item) {
            try {
                TimeTableEvent item = (TimeTableEvent) _item;

                Toast.makeText(
                        getApplicationContext(),
                        item.getSubject() + " @" + item.getSubject() + " / " + item.getStartTime() + " - " + item.getEndTime(),
                        Toast.LENGTH_LONG).show();

            } catch(NullPointerException e) {
                // do nothing
            }
        }
    };

    TimeTable.EventTextInterpreter eventTextInterpreter = new TimeTable.EventTextInterpreter() {
        @Override
        public String interpret(TimeTableEvent _item) {
            TimeTableEvent item = (TimeTableEvent) _item;
            return item.getSubject();
        }
    };

    private static int getRowFromTime(String time) {
        int row = 0;

        switch (time){
            case "08:15": row = 1; break;
            case "09:00": row = 1; break;
            case "09:15": row = 2; break;
            case "10:00": row = 2; break;
            case "10:15": row = 3; break;
            case "11:00": row = 3; break;
            case "11:15": row = 4; break;
            case "12:00": row = 4; break;
            case "12:15": row = 5; break;
            case "13:00": row = 5; break;
            case "13:15": row = 6; break;
            case "14:00": row = 6; break;
            case "14:15": row = 7; break;
            case "15:00": row = 7; break;
            case "15:15": row = 8; break;
            case "16:00": row = 8; break;
            case "16:15": row = 9; break;
            case "17:00": row = 9; break;
        }

        return row;
    }

    private static String getDayName(int d) {
        String day = "";

        switch (d){
            case Day.MONDAY:    day = "Mon"; break;
            case Day.TUESDAY:   day = "Tue"; break;
            case Day.WEDNESDAY: day = "Wed"; break;
            case Day.THURSDAY:  day = "Thu"; break;
            case Day.FRIDAY:    day = "Fri"; break;
            case Day.SATURDAY:  day = "Sat"; break;
            case Day.SUNDAY:    day = "Sun"; break;
        }

        return day;
    }



    private static List<TimeTableEvent> getEvents() {
        List<TimeTableEvent> events = new ArrayList<>();
        events.add(new TimeTableEvent(Color.Bouldergray,     Day.MONDAY,    getRowFromTime("09:15"), getRowFromTime("11:00"), "09:15", "11:00", "E1:AMF3"));
        events.add(new TimeTableEvent(Color.Hitpink,         Day.MONDAY,    getRowFromTime("11:15"), getRowFromTime("14:00"), "11:15", "14:00", "AMF3"));
        events.add(new TimeTableEvent(Color.Rajahorange,     Day.MONDAY,    getRowFromTime("14:15"), getRowFromTime("16:00"), "14:15", "16:00", "E2:AMF1"));
        events.add(new TimeTableEvent(Color.Oldrose,         Day.TUESDAY,   getRowFromTime("08:15"), getRowFromTime("11:00"), "08:15", "11:00", "AMF1"));
        events.add(new TimeTableEvent(Color.Vikingblue,      Day.TUESDAY,   getRowFromTime("11:15"), getRowFromTime("14:00"), "11:15", "14:00", "AMF1"));
        events.add(new TimeTableEvent(Color.Celerygreen,     Day.WEDNESDAY, getRowFromTime("08:15"), getRowFromTime("11:00"), "08:15", "11:00", "AMF3"));
        events.add(new TimeTableEvent(Color.Rockblue,        Day.WEDNESDAY, getRowFromTime("11:15"), getRowFromTime("14:00"), "11:15", "14:00", "UC1"));
        events.add(new TimeTableEvent(Color.Eastsidemagenta, Day.WEDNESDAY, getRowFromTime("14:15"), getRowFromTime("17:00"), "14:15", "17:00", "AMF3"));
        events.add(new TimeTableEvent(Color.Sandorange,      Day.THURSDAY,  getRowFromTime("08:15"), getRowFromTime("11:00"), "08:15", "11:00", "UC7"));
        events.add(new TimeTableEvent(Color.Calicobeige,     Day.THURSDAY,  getRowFromTime("11:15"), getRowFromTime("13:00"), "11:15", "13:00", "E1:AMF3"));
        events.add(new TimeTableEvent(Color.Vistagreen,      Day.FRIDAY,    getRowFromTime("09:15"), getRowFromTime("13:00"), "09:15", "13:00", "AMF2"));
        events.add(new TimeTableEvent(Color.Bouldergray,     Day.FRIDAY,    getRowFromTime("13:15"), getRowFromTime("15:00"), "13:15", "15:00", "E2:AMF3"));
        return events;
    }
}
