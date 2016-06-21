package com.fbvictorhugo.checkpoint.datasource;

import android.content.Context;

import com.fbvictorhugo.checkpoint.datasource.internalstorage.CheckpointInternalStorage;
import com.fbvictorhugo.checkpoint.datasource.sharedpreferences.CheckpointSharedPrefs;

/**
 * By fbvictorhugo on 13/06/16.
 */

public class DataSourceFactory {

    private enum DatabaseType {
        SHARED_PREFERENCES, INTERNAL_STORAGE, SQLITE;
    }

    private static DataSourceFactory sFactory;
    private final Context mContext;
    private final DatabaseType type = DatabaseType.SHARED_PREFERENCES;

    public static DataSourceFactory getInstance(Context context) {
        if (sFactory == null) {
            sFactory = new DataSourceFactory(context);
        }
        return sFactory;
    }

    private DataSourceFactory(Context context) {
        mContext = context;
    }

    public Object getDataSource(Class dataSourceInterface) {
        switch (type) {
            case SHARED_PREFERENCES:
                return instaceForSharedPreference(dataSourceInterface);
            case INTERNAL_STORAGE:
                return instaceForInternalStorage(dataSourceInterface);
        }
        return null;
    }

    // region Instances for Shared Preferences

    private Object instaceForSharedPreference(Class dataSourceInterface) {
        if (isSameClass(dataSourceInterface, CheckpointDataSource.class)) {
            return new CheckpointSharedPrefs(mContext);
        }
        return null;
    }

    // endregion

    // region Instances for Internal Storage

    private Object instaceForInternalStorage(Class dataSourceInterface) {
        if (isSameClass(dataSourceInterface, CheckpointDataSource.class)) {
            return new CheckpointInternalStorage(mContext);
        }
        return null;
    }

    // endregion

    private boolean isSameClass(Class verifyClass, Class targetClass) {
        if (verifyClass != null && targetClass != null) {
            if (verifyClass.toString().equals(targetClass.toString())) {
                return true;
            }
        }
        return false;
    }
}
