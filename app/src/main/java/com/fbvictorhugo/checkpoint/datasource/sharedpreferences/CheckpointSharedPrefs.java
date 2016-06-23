package com.fbvictorhugo.checkpoint.datasource.sharedpreferences;

import android.content.Context;

import com.fbvictorhugo.checkpoint.datasource.CheckpointDataSource;
import com.fbvictorhugo.checkpoint.datasource.DataSourceCallback;
import com.fbvictorhugo.checkpoint.model.Checkpoint;
import com.fbvictorhugo.checkpoint.utils.SharedPreferencesUtil;

/**
 * By fbvictorhugo on 17/06/16.
 */

public class CheckpointSharedPrefs implements CheckpointDataSource {

    private final SharedPreferencesUtil mPrefsUtil;

    public CheckpointSharedPrefs(Context context) {
        mPrefsUtil = new SharedPreferencesUtil(context);
    }

    @Override
    public void saveCheckpointToday(Checkpoint checkpoint, DataSourceCallback<Checkpoint> callback) {
        boolean result = mPrefsUtil.saveCheckpointToday(checkpoint);
        if (result) {
            callback.onSuccess(checkpoint, null);
        } else {
            callback.onFailure("On Failure!");
        }
    }

    @Override
    public void getCheckpointDay(String date, DataSourceCallback<Checkpoint> callback) {
        try {
            Checkpoint checkpoint = mPrefsUtil.getCheckpointDay(date);
            callback.onSuccess(checkpoint, null);
        } catch (Exception e) {
            callback.onFailure(e.getMessage());
        }
    }
}
