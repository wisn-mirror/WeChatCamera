package com.wisn.wechatcamera;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wisn.wechatcamera.testcomm.FeedCommentVO;
import com.wisn.wechatcamera.testcomm.VerticalCommentWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    private ImageView result;
    private VerticalCommentWidget verticalCommentWidget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTextView();
        verticalCommentWidget = this.findViewById(R.id.verticalCommentWidget);

        List<FeedCommentVO> feedCommentVOlist=new ArrayList<>();
        feedCommentVOlist.add(new FeedCommentVO("没有toUser  人名加回复内容" +
                "有toUser  人名回复toUser人名回复内容","名称1","名称2"));
        feedCommentVOlist.add(new FeedCommentVO("简介: 确认订单页面,重新选择优惠劵会调用,接口会重新计算订单金额,重新拉去最新的优惠劵(邮费劵)信息,然后绑定用户新选择的优惠劵","名称2","名称2"));
        feedCommentVOlist.add(new FeedCommentVO("5.商城页自定义栏位事件埋点:" +
                "   轮播,icon栏位,单图栏位,魔方栏位,商品瀑布流","名称1",""));
        verticalCommentWidget.addComments(feedCommentVOlist);

        Button start2=  findViewById(R.id.start2);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FeedCommentVO> update2=new ArrayList<>();
                update2.add(new FeedCommentVO("没222222222有toUser  人名加回复内容" +
                        "有toUser  人名回复toUser人名回复内容","名称1","名称2"));
                update2.add(new FeedCommentVO("5.商城2222页自定义栏位事件埋点:" +
                        "   轮播,icon栏位,单图栏位,魔方栏2位,商品瀑布流","名称1",""));
                verticalCommentWidget.addComments(update2);
            }
        });
        Button start3=  findViewById(R.id.start3);
        start3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FeedCommentVO> update3=new ArrayList<>();
                update3.add(new FeedCommentVO("没有to33333333User  人名加回复内容" +
                        "有toUser  人名回复toUser人名回复内容","名称1","名称2"));
                update3.add(new FeedCommentVO("简介: 确认333333订单页面,重新选择优惠劵会调用,接口会重新计算订单金额,重新拉去最新的优惠劵(邮费劵)信息,然后绑定用户新选择的优惠劵","名称2","名称2"));
                update3.add(new FeedCommentVO("5.商城3333333页自定义栏位事件埋点:" +
                        "   轮播,icon栏位,单图栏位,魔方栏位,商品瀑布流","名称1",""));
                update3.add(new FeedCommentVO("简介: 确333333认订单页面,重新选择优惠劵会调用,接口会重新计算订单金额,重新拉去最新的优惠劵(邮费劵)信息,然后绑定用户新选择的优惠劵","名称2","名称2"));

                verticalCommentWidget.addComments(update3);
            }
        });

    }









































    private void testTextView() {
        result = findViewById(R.id.result);
        ((TextView) findViewById(R.id.tv_text)).setText("哈哈哈http://www.baidu.com，你好");
        setContentHttp((TextView) findViewById(R.id.tv_text1), "哈哈哈http://www.baidu.com，你好");
        setContentHttpPattern((TextView) findViewById(R.id.tv_text2), "哈哈哈http://www.baidu.com，你好");
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWithPermissions();
            }
        });
    }

    private void setContentHttp(TextView tv, String str) {
        SpannableString sp = new SpannableString(str);
        Linkify.addLinks(sp, Linkify.WEB_URLS);
        URLSpan[] spans = sp.getSpans(0, str.length(), URLSpan.class);
        if (spans != null && spans.length > 0) {
            for (int i = 0; i < spans.length; i++) {
                final String url = spans[i].getURL();
                sp.setSpan(new ForegroundColorSpan(Color.BLUE), sp.getSpanStart(spans[i]), sp.getSpanEnd(spans[i]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                sp.setSpan(new UnderlineSpan(), sp.getSpanStart(spans[i]), sp.getSpanEnd(spans[i]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                sp.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(MainActivity.this, "url:" + url, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                }, sp.getSpanStart(spans[i]), sp.getSpanEnd(spans[i]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
               /* sp.setSpan(new ViewUtils.Clickable(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "url:"+url, Toast.LENGTH_SHORT).show();
                    }
                }),
                        sp.getSpanStart(spans[i]), sp.getSpanEnd(spans[i]),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
            }
        }
        tv.setText(sp);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setContentHttpPattern(TextView tv, String string) {
        SpannableString sp = new SpannableString(string);
        String urlPattern =
                "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";
        Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(string);
        int startPoint = 0;
        while (m.find(startPoint)) {
            int endPoint = m.end();
            String hit = m.group();
            ClickableSpan clickSpan = new NoLineClickSpan(hit);
            sp.setSpan(clickSpan, endPoint - hit.length(), endPoint, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//用Span替换对应长度的url
            sp.setSpan(new BackgroundColorSpan(Color.WHITE), endPoint - hit.length(), endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            startPoint = endPoint;
        }
        tv.setText(sp);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }


    class NoLineClickSpan extends ClickableSpan {
        String text;

        public NoLineClickSpan(String text) {
            super();
            this.text = text;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#1791F8"));//指定颜色值
            ds.setUnderlineText(false); // 去掉下划线
        }

        @Override
        public void onClick(View widget) {
            // 点击超链接时调用
            final Bundle bundle = new Bundle();
            bundle.putString("URL", text);
            Toast.makeText(MainActivity.this, "url:" + text, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 获取权限并启动
     */
    private void startWithPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                    .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager
                            .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                            .PERMISSION_GRANTED) {
                startActivityForResult(new Intent(MainActivity.this, CameraActivity.class), 100);
            } else {
                //不具有获取权限，需要进行权限申请
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        } else {
            startActivityForResult(new Intent(MainActivity.this, CameraActivity.class), 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            Log.i("CJT", "picture");
            String path = data.getStringExtra("path");
            result.setImageBitmap(BitmapFactory.decodeFile(path));
        }
        if (resultCode == 102) {
            Log.i("CJT", "video");
            String path = data.getStringExtra("path");
        }
        if (resultCode == 103) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    startActivityForResult(new Intent(MainActivity.this, CameraActivity.class), 100);
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
