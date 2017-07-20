package com.waterfairy.nfcdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.waterfairy.nfcdemo.fragment.ClassroomAssessmentFragment;
import com.waterfairy.nfcdemo.fragment.DataStatisticsFragment;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class MainAdapter extends FragmentPagerAdapter {
    private DataStatisticsFragment dataStatisticsFragment;
    private ClassroomAssessmentFragment classroomAssessmentFragment;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        dataStatisticsFragment = new DataStatisticsFragment();
        classroomAssessmentFragment = new ClassroomAssessmentFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return dataStatisticsFragment;
        else return classroomAssessmentFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
