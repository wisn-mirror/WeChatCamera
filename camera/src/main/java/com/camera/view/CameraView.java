package com.camera.view;

import android.graphics.Bitmap;

/**
 * Created by Wisn on 2018/12/21
 */
public interface CameraView {

    void resetState(int type);

    void confirmState(int type);

    void showPicture(Bitmap bitmap, boolean isVertical);

    void playVideo(Bitmap firstFrame, String url);

    void stopVideo();

    void setTip(String tip);

    void startPreviewCallback();

    boolean handlerFoucs(float x, float y);
}
