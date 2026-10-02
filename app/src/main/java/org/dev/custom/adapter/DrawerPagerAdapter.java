package org.dev.custom.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.io.FileFilter;
import org.dev.custom.fragment.FileFragment;
import org.dev.custom.fragment.TermuxFragment;

public class DrawerPagerAdapter extends FragmentStatePagerAdapter {
    String[] title = new String[] {"项目","分支"};
    FragmentActivity fa;

    public DrawerPagerAdapter(FragmentActivity fa) {
        super(fa.getSupportFragmentManager());
        this.fa = fa;
    }

    @Override
    public String getPageTitle(int pos) {
        return title[pos];
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Fragment getItem(int arg0) {
        if (arg0 == 0) {
            return new FileFragment();
        }
        return new TermuxFragment();
    }
}
