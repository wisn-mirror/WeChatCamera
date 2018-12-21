package com.camera.listener;

/**
 * Created by Wisn on 2018/12/21 下午1:34.
 */
public interface CaptureListener {
    void takePictures();

    void recordShort(long time);

    void recordStart();

    void recordEnd(long time);

    void recordZoom(float zoom);

    void recordError();
}
