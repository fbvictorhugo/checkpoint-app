package com.fbvictorhugo.checkpoint.datasource;

import com.fbvictorhugo.checkpoint.model.Checkpoint;

/**
 * By fbvictorhugo on 13/06/16.
 */

public interface CheckpointDataSource {

    void saveCheckpointToday(Checkpoint checkpoint, DataSourceCallback<Checkpoint> callback);

    void getCheckpointDay(String date, DataSourceCallback<Checkpoint> callback);

}
