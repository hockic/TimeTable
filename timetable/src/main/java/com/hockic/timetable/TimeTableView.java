package com.hockic.timetable;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.hockic.timetable.R;

/**
 * TimeTableView
 * https://github.com/shallcheek/TimeTable
 *
 * @author shallcheek (original author)
 * @author Safet Hockic <safet.hockic@gmail.com>
 */
public class TimeTableView extends LinearLayout {

    /**
     * Getters and setters
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        invalidate();
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
        invalidate();
    }

    public TimeTable.OnCellClickListener getOnEventClickListener() {
        return onEventClickListener;
    }

    public void setOnEventClickListener(TimeTable.OnCellClickListener onEventClickListener) {
        this.onEventClickListener = onEventClickListener;
        invalidate();
    }

    public TimeTable.OnEmptyCellListener getOnEmptyCellClickListener() {
        return onEmptyCellClickListener;
    }

    public void setOnEmptyCellClickListener(TimeTable.OnEmptyCellListener onEmptyCellClickListener) {
        this.onEmptyCellClickListener = onEmptyCellClickListener;
    }

    public TimeTable.OrdinalNumberInterpreter getOrdinalNumberInterpreter() {
        return ordinalNumberInterpreter;
    }

    public void setOrdinalNumberInterpreter(TimeTable.OrdinalNumberInterpreter ordinalNumberInterpreter) {
        this.ordinalNumberInterpreter = ordinalNumberInterpreter;
        invalidate();
    }

    public List<TimeTableEvent> getTimeTableEvents() {
        return timeTableEvents;
    }

    public void setTimeTableEvents(List<TimeTableEvent> timeTableEvents) {
        this.timeTableEvents = timeTableEvents;
        invalidate();
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
        invalidate();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        invalidate();
    }

    public int getOrdinalWidth() {
        return ordinalWidth;
    }

    public void setOrdinalWidth(int ordinalWidth) {
        this.ordinalWidth = ordinalWidth;
        invalidate();
    }

    public int getWeekDayCellHeight() {
        return weekDayCellHeight;
    }

    public void setWeekDayCellHeight(int weekDayCellHeight) {
        this.weekDayCellHeight = weekDayCellHeight;
        invalidate();
    }

    public TimeTable.EventTextInterpreter getEventTextInterpreter() {
        return eventTextInterpreter;
    }

    public void setEventTextInterpreter(TimeTable.EventTextInterpreter eventTextInterpreter) {
        this.eventTextInterpreter = eventTextInterpreter;
    }

    public TimeTable.WeekDayNameInterpreter getWeekDayNameInterpreter() {
        return weekDayNameInterpreter;
    }

    public void setWeekDayNameInterpreter(TimeTable.WeekDayNameInterpreter weekDayNameInterpreter) {
        this.weekDayNameInterpreter = weekDayNameInterpreter;
    }

    // Number of rows
    private int numberOfRows = 15;
    // How many days will be displayed
    private int numberOfDays = 7;
    // OnClick listener for events
    private TimeTable.OnCellClickListener onEventClickListener;
    // OnClick listener for empty cells
    private TimeTable.OnEmptyCellListener onEmptyCellClickListener;
    // Ordinal number interpreter (optional)
    private TimeTable.OrdinalNumberInterpreter ordinalNumberInterpreter;
    // Week day name interpreter (optional)
    private TimeTable.WeekDayNameInterpreter weekDayNameInterpreter;
    // Event text interpreter (optional
    private TimeTable.EventTextInterpreter eventTextInterpreter;
    // Data source
    private List<TimeTableEvent> timeTableEvents = new ArrayList<>();

    // Height and width
    private int cellHeight = 30;
    private int weekDayCellHeight = 30;
    private int borderWidth = 2;
    private int ordinalWidth = 30;


    public TimeTableView(Context context) {
        super(context);
    }

    public TimeTableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * Creates a line
     * @return View
     */
    private View getLine(int width, int height) {
        View line = new View(getContext());
        line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lighter_gray));
        line.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        return line;
    }

    public void setup() {
        // Remove all previous views
        removeAllViews();

        // Create new layout
        LinearLayout weekDayRow = new LinearLayout(getContext());
        weekDayRow.setOrientation(HORIZONTAL);

        LinearLayout weekColumns = new LinearLayout(getContext());
        weekColumns.setOrientation(HORIZONTAL);

        ViewGroup.LayoutParams ordinalNumberColumnLayoutParams = new ViewGroup.LayoutParams(dip2px(ordinalWidth), dip2px(numberOfRows * cellHeight) + numberOfRows * 2);
        ViewGroup.LayoutParams currentDayColumnLayoutParams = new ViewGroup.LayoutParams((getViewWidth() - dip2px(ordinalWidth)) / numberOfDays, LayoutParams.FILL_PARENT);

        // Draw the days
        for (int i = 0; i <= numberOfDays; i++) {
            switch (i) {
                case 0:
                    // Create an empty cell at the top left corner
                    TextView ordinalNumberColumnLabel = new TextView(getContext());
                    ordinalNumberColumnLabel.setHeight(dip2px(weekDayCellHeight));
                    ordinalNumberColumnLabel.setWidth(dip2px(ordinalWidth));
                    weekDayRow.addView(ordinalNumberColumnLabel);

                    // Draw ordinal numbers
                    LinearLayout ordinalColumn = new LinearLayout(getContext());
                    ordinalColumn.setLayoutParams(ordinalNumberColumnLayoutParams);
                    ordinalColumn.setOrientation(VERTICAL);

                    for (int j = 1; j <= numberOfRows; j++) {
                        TextView ordinalNumber = new TextView(getContext());
                        ordinalNumber.setGravity(Gravity.CENTER);
                        ordinalNumber.setTextColor(ContextCompat.getColor(getContext(), R.color.darker_gray));
                        ordinalNumber.setHeight(dip2px(cellHeight));
                        ordinalNumber.setWidth(dip2px(ordinalWidth));
                        ordinalNumber.setTextSize(14);

                        // Let the user use a custom interpreter
                        if(getOrdinalNumberInterpreter() != null){
                            ordinalNumber.setText(getOrdinalNumberInterpreter().interpret(j));
                        } else {
                            ordinalNumber.setText(Integer.toString(j));
                        }

                        // Attach view
                        ordinalColumn.addView(ordinalNumber);

                        // Draw line under each cell except the last one
                        ordinalColumn.addView(getLine(LayoutParams.MATCH_PARENT, borderWidth));
                    }

                    // Add column to layout
                    weekColumns.addView(ordinalColumn);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    // Draw day labels
                    LinearLayout weekDayColumn = new LinearLayout(getContext());
                    weekDayColumn.setOrientation(VERTICAL);

                    // Create weekday text view
                    TextView weekDayName = new TextView(getContext());
                    weekDayName.setTextSize(16);
                    weekDayName.setGravity(Gravity.CENTER);

                    // Let the user use a custom interpreter
                    if(weekDayNameInterpreter != null){
                        weekDayName.setText(getWeekDayNameInterpreter().interpret(i));
                    } else {
                        weekDayName.setText(Integer.toString(i));
                    }

                    weekDayName.setHeight(dip2px(weekDayCellHeight));
                    weekDayName.setTextColor(ContextCompat.getColor(getContext(), R.color.darker_gray));
                    weekDayName.setWidth((getViewWidth() - dip2px(ordinalWidth)) / numberOfDays);

                    // Add label to column
                    weekDayColumn.addView(weekDayName);

                    // Add column to layout
                    weekDayRow.addView(weekDayColumn);

                    // Get today's events
                    List<TimeTableEvent> mListMon = new ArrayList<>();

                    for (TimeTableEvent timeTableEvent : timeTableEvents) {
                        if (timeTableEvent.getWeekday() == i) {
                            mListMon.add(timeTableEvent);
                        }
                    }

                    // Draw column and it's events
                    LinearLayout currentDayColumn = getTimeTableView(mListMon, i);
                    currentDayColumn.setOrientation(VERTICAL);
                    currentDayColumn.setLayoutParams(currentDayColumnLayoutParams);
                    currentDayColumn.setWeightSum(1);

                    // Add column to layout
                    weekColumns.addView(currentDayColumn);
                    break;

                default:
                    break;
            }

            // Draw line after each cell
            weekColumns.addView(getLine(borderWidth, dip2px(cellHeight * numberOfRows) + numberOfRows * 2));
            weekDayRow.addView(getLine(borderWidth, LayoutParams.MATCH_PARENT));
        }

        addView(weekDayRow);
        addView(getLine(LayoutParams.MATCH_PARENT, borderWidth));
        addView(weekColumns);
        invalidate();
    }

    private int getViewWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    private View createEmptyCells(int startnum, final int weekDay, final int start) {
        LinearLayout emptyCellLayout = new LinearLayout(getContext());
        emptyCellLayout.setOrientation(VERTICAL);

        for (int i = 1; i < startnum; i++) {
            TextView emptyCell = new TextView(getContext());
            emptyCell.setGravity(Gravity.CENTER);
            emptyCell.setHeight(dip2px(cellHeight));
            emptyCell.setWidth(LayoutParams.MATCH_PARENT);

            // Attach listener to empty cells
            if(onEmptyCellClickListener != null){
                final int num = i;

                emptyCell.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onEmptyCellClickListener.onClick(weekDay, (start + num));
                    }
                });
            }

            // Add cell to layout
            emptyCellLayout.addView(emptyCell);
            emptyCellLayout.addView(getLine(LayoutParams.MATCH_PARENT, borderWidth));
        }

        return emptyCellLayout;
    }

    /**
     * Adding events to the table
     * @return LinearLayout
     */
    private LinearLayout getTimeTableView(List<TimeTableEvent> events, int weekDay) {
        LinearLayout dayLayout = new LinearLayout(getContext());
        dayLayout.setOrientation(VERTICAL);

        if (events.size() == 0) {
            // Create empty cells if there are no events
            dayLayout.addView(createEmptyCells(numberOfRows + 1, weekDay, 0));
        } else {
            for (int i = 0; i < events.size(); i++) {
                if (i == 0) {
                    dayLayout.addView(createEmptyCells(events.get(0).getStart(), weekDay, 0));
                    dayLayout.addView(getViewFromEvent(events.get(0)));
                } else if (events.get(i).getStart() - events.get(i - 1).getStart() > 0) {
                    dayLayout.addView(createEmptyCells(events.get(i).getStart() - events.get(i - 1).getEnd(), weekDay, events.get(i - 1).getEnd()));
                    dayLayout.addView(getViewFromEvent(events.get(i)));
                }
                if (i + 1 == events.size()) {
                    dayLayout.addView(createEmptyCells(numberOfRows - events.get(i).getEnd() + 1, weekDay, events.get(i).getEnd()));
                }
            }
        }

        return dayLayout;
    }

    /**
     * Get single item
     *
     * @return View
     */
    private View getViewFromEvent(final TimeTableEvent event) {
        LinearLayout eventLayout = new LinearLayout(getContext());
        eventLayout.setOrientation(VERTICAL);

        // Create text view
        TextView eventTextView = new TextView(getContext());
        int num = event.getEnd() - event.getStart();
        eventTextView.setHeight(dip2px((num + 1) * cellHeight) + num * 2);
        eventTextView.setTextColor(ContextCompat.getColor(getContext(), event.getTextColor()));
        eventTextView.setWidth(LayoutParams.MATCH_PARENT);
        eventTextView.setTextSize(16);
        eventTextView.setGravity(Gravity.CENTER);

        // Add text view to layout
        eventLayout.addView(eventTextView);

        eventLayout.addView(getLine(LayoutParams.MATCH_PARENT, borderWidth));

        // Attach listener if there is one
        if(getOnEventClickListener() != null) {
            eventLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnEventClickListener().onClick(event);
                }
            });
        }

        if(eventTextInterpreter != null){
            // Interpret text if there is an interpreter
            eventTextView.setText(eventTextInterpreter.interpret(event));
        } else {
            // Use the subject as text
            eventTextView.setText(event.getSubject());
        }

        // Set proper background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            eventLayout.setBackground(getContext().getResources().getDrawable(event.getBackgroundColor(), getContext().getTheme()));
        } else {
            eventLayout.setBackground(getContext().getResources().getDrawable(event.getBackgroundColor()));
        }


        return eventLayout;
    }

    /**
     * Helper method for converting pixels into density-independent pixels
     *
     * @param dpValue dp
     * @return int
     */
    public int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
