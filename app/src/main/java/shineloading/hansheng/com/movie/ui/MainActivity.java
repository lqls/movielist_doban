package shineloading.hansheng.com.movie.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shineloading.hansheng.com.movie.R;
import shineloading.hansheng.com.movie.ui.fragment.MovieListFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar         toolbar;
    private FragmentManager fragmentManager;
    private FrameLayout     mFlDrawerContainer;
    private ListView        mlistViewDrawerMenu;
    private DrawerLayout    mDlHome;

    private String[] citys={"北京","上海","广州","深圳"};
    private List<String>          datas;
    private ActionBarDrawerToggle mdrawerToggle;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initView();

        datas=new ArrayList<String>();
        datas.addAll(Arrays.asList(citys));

        mdrawerToggle = new ActionBarDrawerToggle(this,mDlHome,toolbar,R.string.xxx,R.string.zzz){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle("请选择城市");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getActionBar().setTitle(title);
                invalidateOptionsMenu();
            }
        };

        mDlHome.setDrawerListener(mdrawerToggle);
        /*getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);*/

        mlistViewDrawerMenu.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datas));

        mlistViewDrawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //加载不同的内容fragment
                Toast.makeText(MainActivity.this, datas.get(i), Toast.LENGTH_SHORT).show();
                mDlHome.closeDrawer(mlistViewDrawerMenu);
            }
        });

    }


    private void initView() {
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fl_drawer_container,
                MovieListFragment.newInstance(getResources().getString(R.string.in_theaters_english)),
                getResources().getString(R.string.in_theaters_chinese)).commit();
        mFlDrawerContainer = (FrameLayout) findViewById(R.id.fl_drawer_container);
        mlistViewDrawerMenu = (ListView) findViewById(R.id.listview_drawer_menu);
        mDlHome = (DrawerLayout) findViewById(R.id.dl_home);

        title=(String) getTitle();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       mdrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mdrawerToggle.onConfigurationChanged(newConfig);
    }
}
