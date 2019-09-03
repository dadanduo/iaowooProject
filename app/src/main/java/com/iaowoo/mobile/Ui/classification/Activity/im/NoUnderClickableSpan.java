package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class NoUnderClickableSpan extends ClickableSpan {
    public NoUnderClickableSpan() {
    }

    public void onClick(View var1) {
    }

    public void updateDrawState(TextPaint paint) {
        super.updateDrawState(paint);
        paint.setUnderlineText(false);
        paint.setColor(-65536);
    }
}
