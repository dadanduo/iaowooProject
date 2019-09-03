package com.iaowoo.mobile.Ui.classification.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iaowoo.mobile.Ui.classification.Fragment.EearthFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.EearthFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class ShareFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    public ShareFragmentAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
            return new EearthFragment();

    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
