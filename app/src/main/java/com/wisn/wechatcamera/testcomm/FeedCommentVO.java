package com.wisn.wechatcamera.testcomm;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;

/**
 * Created by Wisn on 2018/12/28 上午11:43.
 */
public class FeedCommentVO {
    String content;
    String fromName;
    String toName;

    public FeedCommentVO(String content, String fromName, String toName) {
        this.content = content;
        this.fromName = fromName;
        this.toName = toName;
    }

    public SpannableStringBuilder commentContentSpan;

    public SpannableStringBuilder getCommentContentSpan(Context context, int position) {
        if (commentContentSpan == null) {
            if (TextUtils.isEmpty(toName)) {
                commentContentSpan = SpanUtils.makeSingleCommentSpan(context, fromName, content);
            } else {
                commentContentSpan = SpanUtils.makeReplyCommentSpan(context, fromName, toName, content);
            }
        }
        return commentContentSpan;
    }
}
