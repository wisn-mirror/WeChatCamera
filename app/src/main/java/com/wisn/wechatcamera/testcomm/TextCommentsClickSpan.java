package com.wisn.wechatcamera.testcomm;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.camera.DensityUtils;
import com.wisn.wechatcamera.R;

/**
 * @author KCrason
 * @date 2018/4/28
 */
public class TextCommentsClickSpan extends ClickableSpan {

    public Context mContext;

    public String userId;
    public boolean mPressed;

    public TextCommentsClickSpan(Context context, String userId) {
        this.mContext = context;
        this.userId = userId;
    }

    public void setPressed(boolean isPressed) {
        this.mPressed = isPressed;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.bgColor = mPressed ? ContextCompat.getColor(mContext, R.color.base_3FB838) : Color.TRANSPARENT;
        ds.setColor(ContextCompat.getColor(mContext, R.color.base_EF3C11));
        ds.setTextSize(DensityUtils.dip2px(mContext, 17f));
        ds.setFakeBoldText(true);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
        if (!TextUtils.isEmpty(userId)) {
            Toast.makeText(mContext, "You Click " + userId, Toast.LENGTH_SHORT).show();

         /*   ARouter.getInstance().build(ARoutePath.im.UserProfileActivity)
                    .withString(Constants.im.Key_Account, userId)
                    .withString(Constants.im.Key_Way, "")
                    .navigation();*/
        }
    }
}
