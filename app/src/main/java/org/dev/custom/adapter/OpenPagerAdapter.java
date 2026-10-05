package org.dev.custom.adapter;

import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.io.File;
import java.util.ArrayList;
import org.dev.custom.activity.EditorActivity;
import org.dev.custom.fragment.OpenFragment;
import org.dev.custom.fragment.TermuxFragment;

public class OpenPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<File> openFile = new ArrayList();
    EditorActivity ea;

    public OpenPagerAdapter(EditorActivity ea) {
        super(ea.getSupportFragmentManager());
        this.ea = ea;
    }

    @Override
    public String getPageTitle(int position) {
        return openFile.get(position).getName();
    }

    @Override
    public int getCount() {
        return openFile.size();
    }

    @Override
    public Fragment getItem(int position) {
        return new OpenFragment(openFile.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        OpenFragment op = (OpenFragment) object;
        if (!hasOpen(op.getOpenFile())) {
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;
    }

    public void openFile(File p) {
        ea.getActivityEditorBinding().tab.setVisibility(View.VISIBLE);
        if (!hasOpen(p)) {
            openFile.add(p);
            notifyDataSetChanged();
        }
    }

    public void closeAllFile() {
        ea.getActivityEditorBinding().tab.setVisibility(View.GONE);
        openFile.clear();
        notifyDataSetChanged();
    }

    public void closeOtherFile(File p) {
        for (File f : openFile) {
            if (f.compareTo(p) != 0) {
                openFile.remove(f);
            }
        }
        notifyDataSetChanged();
    }

    public void closeCurrentFile(File p) {
        for (File f : openFile) {
            if (f.compareTo(p) == 0) {
                if(openFile.size()==1)
                ea.getActivityEditorBinding().tab.setVisibility(View.GONE);
                openFile.remove(f);
            }
        }
        notifyDataSetChanged();
    }

    public boolean hasOpen(File p) {
        for (File f : openFile) {
            if (f.compareTo(p) == 0) {
                return true;
            }
        }
        return false;
    }
}
