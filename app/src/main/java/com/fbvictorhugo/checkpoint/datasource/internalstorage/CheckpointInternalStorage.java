package com.fbvictorhugo.checkpoint.datasource.internalstorage;

import android.content.Context;

import com.fbvictorhugo.checkpoint.datasource.CheckpointDataSource;
import com.fbvictorhugo.checkpoint.datasource.DataSourceCallback;
import com.fbvictorhugo.checkpoint.model.Checkpoint;
import com.fbvictorhugo.checkpoint.utils.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * By fbvictorhugo on 17/06/16.
 */

public class CheckpointInternalStorage implements CheckpointDataSource {

    private final Context mContext;

    public CheckpointInternalStorage(Context context) {
        mContext = context;
    }

    @Override
    public void saveCheckpointToday(Checkpoint checkpoint, DataSourceCallback<Checkpoint> callback) {
        File file = new File(mContext.getFilesDir(), checkpoint.getDate() + ".json");
        FileOutputStream outputStream;

        try {
            outputStream = mContext.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            outputStream.write(checkpoint.toString().getBytes());
            outputStream.close();
            callback.onSuccess(checkpoint, null);

        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(e.getMessage());
        }
    }

    @Override
    public void getCheckpointDay(String date, DataSourceCallback<Checkpoint> callback) {
        File file = new File(mContext.getFilesDir(), date + ".json");
        FileInputStream fIn;

        try {
            fIn = mContext.openFileInput(file.getName());
            StringBuilder data = new StringBuilder("");
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(fIn);
                BufferedReader buffReader = new BufferedReader(inputStreamReader);

                String readString = buffReader.readLine();
                while (readString != null) {
                    data.append(readString);
                    readString = buffReader.readLine();
                }

                Checkpoint checkpoint = Utils.getGsonConfiguration().fromJson(
                        data.toString(), Checkpoint.class);

                inputStreamReader.close();
                callback.onSuccess(checkpoint, null);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                callback.onFailure(ioe.getMessage());
            }
            fIn.close();
        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(e.getMessage());
        }
    }
}
