package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.view.View;

public abstract class RollingTextAdapter {
    public abstract int getCount();
    public abstract View getView(Context context, View contentView, int position);
}
