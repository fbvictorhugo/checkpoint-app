package com.fbvictorhugo.checkpoint.datasource;

/**
 * By fbvictorhugo on 17/06/16.
 */

public interface DataSourceCallback<T> {

    void onSuccess(T object, String message);

    void onFailure(String message);
}
