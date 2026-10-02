package org.dev.custom.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import org.dev.custom.adapter.BottomPagerAdapter;
import org.dev.custom.adapter.DrawerPagerAdapter;
import org.dev.custom.adapter.OpenPagerAdapter;
import org.dev.custom.databinding.ActivityEditorBinding;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import org.dev.custom.R;

public class EditorActivity extends AppCompatActivity {
    ActivityEditorBinding aeb;
    DrawerPagerAdapter dpa;
    BottomPagerAdapter bpa;
    OpenPagerAdapter opa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem arg0) {
        int id = arg0.getItemId();
        if (id == R.id.settings) startActivity(new Intent(this, SettingsActivity.class));
        return super.onOptionsItemSelected(arg0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aeb = null;
    }

    public void init() {
        aeb = ActivityEditorBinding.inflate(getLayoutInflater());
        setSupportActionBar(aeb.toolbar);
        setContentView(aeb.getRoot());
        ActionBarDrawerToggle abdt =
                new ActionBarDrawerToggle(
                        this,
                        aeb.drawerLayout,
                        aeb.toolbar,
                        R.string.drawer_open,
                        R.string.drawer_close);
        aeb.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();
        aeb.tab.setupWithViewPager(aeb.pager);
        /*    aeb.pager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageSelected(int position) {
                EditorFragment fragment = getCurrentEditor();
                if (fragment != null) fragment.bindInput();
                invalidateOptionsMenu();
            }
        });*/
        aeb.bottomTab.setupWithViewPager(aeb.bottomPager);
        aeb.drawerToolbar.setTitle("Project Files");
        aeb.drawerRail.setOnItemSelectedListener(
                (item) -> {
                    int i = item.getItemId();
                    if (i == R.id.nav_project) {
                        aeb.drawerToolbar.setTitle("Project Files");
                        aeb.drawerPager.setCurrentItem(0);
                        return true;
                    } else if (i == R.id.nav_branch) {
                        aeb.drawerToolbar.setTitle("Project Branch");
                        aeb.drawerPager.setCurrentItem(1);
                        return true;
                    }
                    return false;
                });
        aeb.bottomPager.setAdapter((bpa = new BottomPagerAdapter(this)));
        aeb.drawerPager.setAdapter((dpa = new DrawerPagerAdapter(this)));
        aeb.pager.setAdapter((opa = new OpenPagerAdapter(this)));
        aeb.symbolinputview.addSymbols(
                new String[] {
                    "Tab", "{", "}", "(", ")", "[", "]", ";", ".", ",", "=", "\"", "'", "_", ":",
                    "/", "\\", "<", ">", "->", "+", "-", "*", "%", "!", "?", "==", "!=", ">=", "<=",
                    "&&", "||", "+=", "-=", "++", "--", "::", "?.", "?:", "!!"
                },
                new String[] {
                    "\t", "{", "}", "(", ")", "[", "]", ";", ".", ",", "=", "\"", "'", "_", ":",
                    "/", "\\", "<", ">", "->", "+", "-", "*", "%", "!", "?", "==", "!=", ">=", "<=",
                    "&&", "||", "+=", "-=", "++", "--", "::", "?.", "?:", "!!"
                });
        aeb.symbolinputview.setBackgroundColor(0x000000);
        BottomSheetBehavior<View> bottomSheet = BottomSheetBehavior.from(aeb.bottomSheetLayout);
        bottomSheet.setPeekHeight(137);
        bottomSheet.setMaxHeight(2020);
    }
}
