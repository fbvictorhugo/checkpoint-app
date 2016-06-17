package com.fbvictorhugo.checkpoint.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fbvictorhugo.checkpoint.model.Checkpoint;
import com.fbvictorhugo.checkpoint.utils.Utils;

/**
 * By fbvictorhugo on 08/06/16.
 */
public class SharedPreferencesUtil {

    private static final String PREFS_NAME = "Checkpoint";

    private SharedPreferences mPreferences = null;
    private final SharedPreferences.Editor mEditor;

    public SharedPreferencesUtil(Context context) {
        mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public boolean saveCheckpointToday(Checkpoint checkpoint) {
        mEditor.putString(checkpoint.getDate(), checkpoint.toString());
        return mEditor.commit();
    }

    public Checkpoint getCheckpointDay(String date) {
        String json = mPreferences.getString(date, null);
        return Utils.getGsonConfiguration().fromJson(json, Checkpoint.class);
    }
}
