package com.fbvictorhugo.checkpoint.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.fbvictorhugo.checkpoint.R;
import com.fbvictorhugo.checkpoint.datasource.CheckpointDataSource;
import com.fbvictorhugo.checkpoint.datasource.DataSourceCallback;
import com.fbvictorhugo.checkpoint.datasource.DataSourceFactory;
import com.fbvictorhugo.checkpoint.model.Checkpoint;
import com.fbvictorhugo.checkpoint.utils.Utils;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * By fbvictorhugo on 07/06/16.
 */
public class CalendarFragment extends Fragment {

    static final String FRAGMENT_TAG = CalendarFragment.class.getName();
    private CheckpointDataSource mCheckpointDataSource;
    private CalendarView mCalendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        DataSourceFactory mDataSource = DataSourceFactory.getInstance(getContext());
        mCheckpointDataSource = (CheckpointDataSource) mDataSource.getDataSource(CheckpointDataSource.class);
        findViews(view);
        configureCalendar();

        return view;
    }

    private void findViews(View view) {
        mCalendarView = (CalendarView) view.findViewById(R.id.calendar_calendarview);
    }

    private void configureCalendar() {
        mCalendarView.setDate(new Date().getTime());
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                GregorianCalendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                String date = Utils.FORMAT_DATE.format(calendar.getTime());

                mCheckpointDataSource.getCheckpointDay(date, new DataSourceCallback<Checkpoint>() {
                    @Override
                    public void onSuccess(Checkpoint checkpoint, String message) {
                        if (checkpoint != null) {
                            Toast.makeText(getContext(), checkpoint.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });

            }
        });

    }

}
