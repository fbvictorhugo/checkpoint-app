package com.fbvictorhugo.checkpoint.datasource.internalstorage;

import com.fbvictorhugo.checkpoint.datasource.CheckpointDataSource;
import com.fbvictorhugo.checkpoint.datasource.DataSourceCallback;
import com.fbvictorhugo.checkpoint.model.Checkpoint;

/**
 * By fbvictorhugo on 17/06/16.
 */

public class CheckpointInternalStorage implements CheckpointDataSource {

    @Override
    public void saveCheckpointToday(Checkpoint checkpoint, DataSourceCallback<Checkpoint> callback) {
        callback.onFailure("Not implemented!");
    }

    @Override
    public void getCheckpointDay(String date, DataSourceCallback<Checkpoint> callback) {
        callback.onFailure("Not implemented!");
    }
}
