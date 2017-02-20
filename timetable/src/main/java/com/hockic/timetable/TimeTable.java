package com.hockic.timetable;

public class TimeTable {

    /**
     * Interfaces and helper classes
     */
    public interface OrdinalNumberInterpreter {
        String interpret(int number);
    }

    public interface WeekDayNameInterpreter {
        String interpret(int day);
    }

    public interface EventTextInterpreter {
        String interpret(TimeTableEvent event);
    }

    public interface OnCellClickListener {
        void onClick(TimeTableEvent item);
    }

    public interface OnEmptyCellListener {
        void onClick(int day, int row);
    }
}
