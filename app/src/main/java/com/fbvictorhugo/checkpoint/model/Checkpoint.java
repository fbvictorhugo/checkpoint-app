package com.fbvictorhugo.checkpoint.model;

import com.fbvictorhugo.checkpoint.utils.Utils;

/**
 * By fbvictorhugo on 07/06/16.
 */
public class Checkpoint {

    private String date;
    private String workIn;
    private String lunchIn;
    private String lunchOut;
    private String workOut;

    private String extra;
    private String comments;
    private boolean absent;
    private boolean holiday;

    // region GETs and

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkIn() {
        return workIn;
    }

    public void setWorkIn(String workIn) {
        this.workIn = workIn;
    }

    public String getLunchIn() {
        return lunchIn;
    }

    public void setLunchIn(String lunchIn) {
        this.lunchIn = lunchIn;
    }

    public String getLunchOut() {
        return lunchOut;
    }

    public void setLunchOut(String lunchOut) {
        this.lunchOut = lunchOut;
    }

    public String getWorkOut() {
        return workOut;
    }

    public void setWorkOut(String workOut) {
        this.workOut = workOut;
    }

    // endregion

    @Override
    public String toString() {
        return Utils.getGsonConfiguration().toJson(this);
    }
}