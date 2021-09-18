package com.ljp.android_reader.bean;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentView extends androidx.appcompat.widget.AppCompatTextView {
    public ContentView(@NonNull Context context) {
        super(context);
    }

    public ContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        String str = getText().toString().substring(0, getTotalWord());
        setText(str);
    }

    /**
     * 获取fragment总字数
     * */
    public int getTotalWord(){
        return getLayout().getLineEnd(getLineNum());
    }

    /**
     * 获取当前页总行数
     * */
    public int getLineNum(){
        int lineNum = 0;
        Layout layout = getLayout();
        //最后一行头顶纵坐标
        int positionOfLastLine = getHeight() - getPaddingBottom() - getPaddingTop() - getLineHeight();
        lineNum = layout.getLineForVertical(positionOfLastLine);
//        Log.e("getLineNum: ", Integer.toString(lineNum));
        return lineNum;
    }
}
