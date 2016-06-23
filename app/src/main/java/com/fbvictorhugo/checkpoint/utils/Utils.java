package com.fbvictorhugo.checkpoint.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * By fbvictorhugo on 08/06/16.
 */
public class Utils {

    public static final SimpleDateFormat FORMAT_HOUR = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    public static final SimpleDateFormat FORMAT_DAY_WEEK_EXTENSIVE = new SimpleDateFormat("EEEE", Locale.getDefault());

    public static Gson getGsonConfiguration() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }
}
