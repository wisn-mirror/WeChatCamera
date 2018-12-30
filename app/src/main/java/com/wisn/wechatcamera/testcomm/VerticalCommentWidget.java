package com.wisn.wechatcamera.testcomm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camera.DensityUtils;
import com.wisn.wechatcamera.R;

import java.util.List;


/**
 * @author KCrason
 * @date 2018/4/27
 */
public class VerticalCommentWidget extends LinearLayout implements ViewGroup.OnHierarchyChangeListener {

    private List<FeedCommentVO> mCommentBeans;

    private LinearLayout.LayoutParams mLayoutParams;
    private SimpleWeakObjectPool<View> COMMENT_TEXT_POOL;
    private int mCommentVerticalSpace;

    public VerticalCommentWidget(Context context) {
        super(context);
        init();
    }

    public VerticalCommentWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalCommentWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCommentVerticalSpace = DensityUtils.dip2px(this.getContext(), 3f);
        COMMENT_TEXT_POOL = new SimpleWeakObjectPool<>();
        setOnHierarchyChangeListener(this);
    }


    public void addComments(List<FeedCommentVO> commentBeans) {
        this.mCommentBeans = commentBeans;
        if (commentBeans != null) {
            int oldCount = getChildCount();
            int newCount = commentBeans.size();
            if (oldCount > newCount) {
                removeViewsInLayout(0, oldCount);
            }
            int index=0;
            for (int i = 0; i < newCount; i++) {
                FeedCommentVO commentBean = commentBeans.get(i);
                SpannableStringBuilder commentSpan = commentBean.getCommentContentSpan(this.getContext(), i);
                if(commentSpan==null) continue;
                boolean hasChild = i < oldCount;
                View childView = hasChild ? getChildAt(index) : null;
                if (childView == null) {
                    childView = COMMENT_TEXT_POOL.get();
                    if (childView == null) {
                        addViewInLayout(makeContentTextView(commentSpan), index, generateMarginLayoutParams(index), true);
                    } else {
                        if (childView instanceof TextView) {
                            ((TextView) childView).setText(commentSpan);
                            addViewInLayout(childView, index, generateMarginLayoutParams(index), true);
                        }
                    }
                } else {
                    if (childView instanceof TextView) {
                        ((TextView) childView).setText(commentSpan);
                    }
                }
                index++;
            }
            requestLayout();
        }
    }



    private TextView makeContentTextView(SpannableStringBuilder content) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        textView.setTextSize(14f);
        textView.setLineSpacing(mCommentVerticalSpace, 1f);
        textView.setText(content);
        textView.setMovementMethod(new TextMovementMethod());
        return textView;
    }


    private LayoutParams generateMarginLayoutParams(int index) {
        if (mLayoutParams == null) {
            mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (mCommentBeans != null && index > 0) {
            mLayoutParams.bottomMargin = index == mCommentBeans.size() - 1 ? 0 : mCommentVerticalSpace;
        }
        return mLayoutParams;
    }


    @Override
    public void onChildViewAdded(View view, View view1) {

    }

    @Override
    public void onChildViewRemoved(View parent, View child) {
        COMMENT_TEXT_POOL.put(child);
    }

}
