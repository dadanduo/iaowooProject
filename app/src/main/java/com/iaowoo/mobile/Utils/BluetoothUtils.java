package com.iaowoo.mobile.Utils;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by chenda on 2018/4/23.
 */

public class BluetoothUtils {

    //是否支持蓝牙功能
    public static boolean isHaveBluetood() {
        BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();

        if (blueadapter == null)

            return false;
        else
            return true;
    }

    //是否开启蓝牙功能
    public static boolean isOpenBluetooth() {
        BluetoothAdapter blueadapter = BluetoothAdapter.getDefaultAdapter();

        if (isHaveBluetood()) {
            if (blueadapter.isEnabled()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
