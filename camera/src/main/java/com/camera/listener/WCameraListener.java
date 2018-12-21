package com.camera.listener;

import android.graphics.Bitmap;

/**
 * Created by Wisn on 2018/12/21 下午1:35.
 */
public interface WCameraListener {

    void captureSuccess(Bitmap bitmap);

    void recordSuccess(String url, Bitmap firstFrame);
}
