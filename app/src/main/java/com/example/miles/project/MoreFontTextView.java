package com.example.miles.project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miles.project.widget.FontTextView;

/**
 * Created by Server on 2018/6/13.
 */

public class MoreFontTextView extends Activity {

    Context mContext;

    FontTextView tv_text;
    LinearLayout ll_textview_content;

    String strString = "中文字符ABCDabcd1234abcABC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_font);
        mContext = this;
        tv_text = findViewById(R.id.tv_text);
        ll_textview_content = findViewById(R.id.ll_textview_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_text.setFontPath("fonts/BDZYJT.ttf");

        showText(strString);
    }


    private void showText(String string) {
        char[] chars = string.toCharArray();
        LinearLayout llBackGround = new LinearLayout(mContext);
        LinearLayout.LayoutParams llBGParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llBackGround.setOrientation(LinearLayout.HORIZONTAL);
        llBackGround.setLayoutParams(llBGParams);
        ll_textview_content.addView(llBackGround);
        for (char c : chars){
            FontTextView mFontTextView = new FontTextView(mContext);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            mFontTextView.setLayoutParams(textParams);
            mFontTextView.setTextSize(20);
            if (Character.isDigit(c)){
                mFontTextView.setFontPath("fonts/BrushScriptStd.otf");
            }
            mFontTextView.setText(String.valueOf(c));
            llBackGround.addView(mFontTextView);
        }
    }




}
