package com.wisn.wechatcamera;

import android.graphics.Color;
 import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
 import android.text.style.BackgroundColorSpan;
 import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wisn.wechatcamera.textview.JustifyTextView;
import com.wisn.wechatcamera.textview.qqface.QDQQFaceView;
import com.wisn.wechatcamera.textview.qqface.QMUIDisplayHelper;
import com.wisn.wechatcamera.textview.qqface.span.QMUITouchableSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {

    private QDQQFaceView qmuiqqFaceView;
    private JustifyTextView justifyTextView;
    private TextView textView;
    public static final String urlPattern =  "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";

    private String text="新版活码的\uD83D\uDE03  \uD83D\uDE03 \uD83D\uDE03 \uD83D\uDE03 [嘘][困][流汗][晕]e[晕][抠鼻][擦汗][抠鼻][抠鼻]记求，针对行业深度应用，草料二维码w提供解决求，\uD83D\uDE03 \uD83D\uDE03 \uD83D\uDE03针对行业深度应用，草料二太阳穴那里却又是一阵钻心的疼，宿醉后的反应让她有些懵了，低头看着自己一、丝、不、挂、吻痕遍及的身子，再掀开被子看了看自己身下的那抹刺眼的血红。\n" +
            "‘轰’——爆炸的声音在脑海里响起，然后她的大脑一片空白，几乎是如机械似的穿上已经整齐叠放在身边的衣服，然后拉开门想要立刻离开这里。\n" +
            "刚打开门却看到一个人站在门口，带着惊讶的表情看着她，“小小小、小姐，你要的醒酒汤。”\n" +
            "服务员端着一碗刚刚熬制出来的醒酒汤，手间颤了一下，似乎是被她过激的动作吓到。\n" +
            "叶一涵根本来不及顾及那些，猛地上前揪住那个服务员的衣领，脸色阴鸷的问他，“我问你，开这间房的人是谁？”\n" +
            "服务员没有想到她直接过来揪住他，手中的醒酒汤也来不及。维码提供https://www.baidu.com/解决方案版（16800元/年），含高级记录功能和顾方案版（16800元/年），含高w级记录功https://cli.im/record能和顾咨询请致电 134 5476 1701";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        qmuiqqFaceView = findViewById(R.id.t1);
        justifyTextView = findViewById(R.id.t2);
        textView = findViewById(R.id.t3);
        qmuiqqFaceView.setText( getSSSS());
        qmuiqqFaceView.setTextSize(QMUIDisplayHelper.sp2px(this,12));
        qmuiqqFaceView.setTextColor(getResources().getColor(R.color.base_3FB838));
        justifyTextView.setText( getSSSS());
        textView.setText( getSSSS());
        textView.setTextSize(12);

    }
    public  CharSequence getSSSS(){
        SpannableString sp = new SpannableString(text);
        Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(text);
        int startPoint = 0;
        while (m.find(startPoint)) {
            int endPoint = m.end();
            final String hit = m.group();
            sp.setSpan(new BackgroundColorSpan(Color.WHITE), endPoint - hit.length(), endPoint, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sp.setSpan(new QMUITouchableSpan(Color.BLUE, Color.BLACK, Color.GRAY, Color.GREEN) {
                @Override
                public void onSpanClick(View widget) {
                    Toast.makeText(widget.getContext(), "点击了"+hit, Toast.LENGTH_SHORT).show();
                }
            }, endPoint - hit.length(), endPoint, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            startPoint = endPoint;
        }
        return sp;
    }
}
