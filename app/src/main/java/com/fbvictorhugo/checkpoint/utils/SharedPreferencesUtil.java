package com.fbvictorhugo.checkpoint.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fbvictorhugo.checkpoint.model.Checkpoint;

/**
 * By fbvictorhugo on 08/06/16.
 */
public class SharedPreferencesUtil {

    private static final String PREFS_NAME = "Checkpoint";
    private SharedPreferences mPreferences = null;

    public SharedPreferencesUtil(Context context) {
        mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean saveCheckpointToday(Checkpoint checkpoint) {
        return mPreferences.edit().putString(checkpoint.getDate(), checkpoint.toString()).commit();
    }

    public Checkpoint getCheckpointDay(String date) {
        String json = mPreferences.getString(date, null);
        return Utils.getGsonConfiguration().fromJson(json, Checkpoint.class);
    }
}
