package com.fbvictorhugo.checkpoint.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fbvictorhugo.checkpoint.R;
import com.fbvictorhugo.checkpoint.datasource.CheckpointDataSource;
import com.fbvictorhugo.checkpoint.datasource.DataSourceCallback;
import com.fbvictorhugo.checkpoint.datasource.DataSourceFactory;
import com.fbvictorhugo.checkpoint.model.Checkpoint;
import com.fbvictorhugo.checkpoint.ui.components.HourTextView;
import com.fbvictorhugo.checkpoint.utils.CheckpointEstate;
import com.fbvictorhugo.checkpoint.utils.Utils;

import java.util.Date;

/**
 * By fbvictorhugo on 07/06/16.
 */
public class CheckpointTodayFragment extends Fragment {

    static final String FRAGMENT_TAG = CheckpointTodayFragment.class.getName();

    private AppCompatTextView mTextDateToday;
    private AppCompatTextView mTextDateOfWeek;
    private HourTextView mTextWorkIn;
    private HourTextView mTextLunchIn;
    private HourTextView mTextLunchOut;
    private HourTextView mTextWorkOut;

    private FloatingActionButton mFab;

    private Checkpoint mCheckpoint;
    private CheckpointDataSource mCheckpointDataSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkpoint_today, container, false);
        findViews(view);
        setHasOptionsMenu(true);

        DataSourceFactory mDataSource = DataSourceFactory.getInstance(getContext());
        mCheckpointDataSource = (CheckpointDataSource) mDataSource.getDataSource(CheckpointDataSource.class);

        mCheckpointDataSource.getCheckpointDay(todayFormated(),
                new DataSourceCallback<Checkpoint>() {

                    @Override
                    public void onSuccess(Checkpoint checkpoint, String message) {
                        mCheckpoint = checkpoint;
                        setupUI();
                    }

                    @Override
                    public void onFailure(String message) {
                        mCheckpoint = new Checkpoint();
                        mCheckpoint.setDate(todayFormated());
                        Log.d(FRAGMENT_TAG, message);
                    }
                });

        mTextDateToday.setText(todayFormated());
        mTextDateOfWeek.setText(Utils.FORMAT_DAY_WEEK_EXTENSIVE.format(new Date()));

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHour(getNextCheckpointHour());
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.checkpont_today, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Toast.makeText(getContext(), R.string.not_implemented_yet,
                        Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViews(View view) {
        mTextDateToday = (AppCompatTextView) view.findViewById(R.id.checkpoint_today_date);
        mTextDateOfWeek = (AppCompatTextView) view.findViewById(R.id.checkpoint_today_day_of_week);
        mFab = (FloatingActionButton) view.findViewById(R.id.checkpoint_fab);

        mTextWorkIn = (HourTextView) view.findViewById(R.id.checkpoint_work_in);
        mTextLunchIn = (HourTextView) view.findViewById(R.id.checkpoint_lunch_in);
        mTextLunchOut = (HourTextView) view.findViewById(R.id.checkpoint_lunch_out);
        mTextWorkOut = (HourTextView) view.findViewById(R.id.checkpoint_work_out);
    }

    private void setupUI() {
        mTextWorkIn.setHour(mCheckpoint.getWorkIn());
        mTextLunchIn.setHour(mCheckpoint.getLunchIn());
        mTextLunchOut.setHour(mCheckpoint.getLunchOut());
        mTextWorkOut.setHour(mCheckpoint.getWorkOut());
    }

    private void checkHour(CheckpointEstate checkpointEstate) {
        if (checkpointEstate == null) {
            return;
        }

        String hour = (nowFormated());
        switch (checkpointEstate) {
            case WORK_IN:
                mCheckpoint.setWorkIn(hour);
                mTextWorkIn.setHour(hour);
                break;

            case LUNCH_IN:
                mCheckpoint.setLunchIn(hour);
                mTextLunchIn.setHour(hour);
                break;

            case LUNCH_OUT:
                mCheckpoint.setLunchOut(hour);
                mTextLunchOut.setHour(hour);
                break;

            case WORK_OUT:
                mCheckpoint.setWorkOut(hour);
                mTextWorkOut.setHour(hour);
                break;
        }

        mCheckpointDataSource.saveCheckpointToday(mCheckpoint,
                new DataSourceCallback<Checkpoint>() {
                    @Override
                    public void onSuccess(Checkpoint checkpoint, String message) {

                    }

                    @Override
                    public void onFailure(String message) {
                        Log.d(FRAGMENT_TAG, message);
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Nullable
    private CheckpointEstate getNextCheckpointHour() {
        if (mTextWorkIn.isHourEmpty()) {
            return CheckpointEstate.WORK_IN;
        } else if (mTextLunchIn.isHourEmpty()) {
            return CheckpointEstate.LUNCH_IN;
        } else if (mTextLunchOut.isHourEmpty()) {
            return CheckpointEstate.LUNCH_OUT;
        } else if (mTextWorkOut.isHourEmpty()) {
            return CheckpointEstate.WORK_OUT;
        } else {
            return null;
        }
    }

    private String todayFormated() {
        return Utils.FORMAT_DATE.format(new Date());
    }

    private String nowFormated() {
        return Utils.FORMAT_HOUR.format(new Date());
    }

}
