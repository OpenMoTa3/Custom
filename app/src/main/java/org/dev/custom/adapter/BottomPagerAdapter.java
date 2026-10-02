package org.dev.custom.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import org.dev.custom.fragment.TermuxFragment;

public class BottomPagerAdapter extends FragmentStatePagerAdapter {
    private static final String[] TITLES = {"构建输出", "应用日志", "XIDE 日志", "其他"};

    public BottomPagerAdapter(FragmentActivity fa) {
        super(fa.getSupportFragmentManager());
    }

    @Override
    public String getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return new TermuxFragment();
    }
}
