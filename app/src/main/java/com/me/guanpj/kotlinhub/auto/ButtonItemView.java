package com.me.guanpj.kotlinhub.auto;

import android.content.Context;
import android.util.AttributeSet;

public class ButtonItemView extends androidx.appcompat.widget.AppCompatButton implements ICommViewMethod {
    public ButtonItemView(Context context) {
        this(context, null);
    }

    public ButtonItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        setPadding(20, 20, 20, 20);
    }

    @Override
    public boolean isFunctionVisible() {
        return true;
    }
}
