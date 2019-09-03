package com.iaowoo.mobile.interfaceCallback;

import com.iaowoo.mobile.ScottMap.PositionEntity;

/**
 * Created by chenda on 2018/4/25.
 */

public interface ApplicationCallBack {
    void address(PositionEntity entity);
    void latcha(double la, double lo);
}
