package com.hockic.timetable;

/**
 * TimeTableView
 * https://github.com/shallcheek/TimeTable
 *
 * @author shallcheek (original author)
 * @author Safet Hockic <safet.hockic@gmail.com>
 */
public class TimeTableEvent {
    private int backgroundColor = Color.Bouldergray;
    private int textColor = android.R.color.white;
    private int start;
    private int end;
    private int weekday;
    private String startTime;
    private String endTime;
    private String subject;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeekday() {
        return weekday;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public TimeTableEvent(int backgroundColor, int weekday, int start, int end, String startTime, String endTime, String subject) {
        this.backgroundColor = backgroundColor;
        this.start = start;
        this.end = end;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
    }
}
