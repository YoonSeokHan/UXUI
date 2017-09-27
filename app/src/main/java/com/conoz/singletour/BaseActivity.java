package com.conoz.singletour;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.conoz.singletour.config.Common;
import com.conoz.singletour.fragment.AccompaniedFragment;
import com.conoz.singletour.fragment.AmusedFragment;
import com.conoz.singletour.fragment.BaseFragment;
import com.conoz.singletour.fragment.FullFragment;
import com.conoz.singletour.fragment.FullMapFragment;
import com.conoz.singletour.fragment.FullMapNearFragment;
import com.conoz.singletour.fragment.LoginFragment;
import com.conoz.singletour.fragment.MainFragment;
import com.conoz.singletour.fragment.MemberFragment;
import com.conoz.singletour.fragment.MissionFragment;
import com.conoz.singletour.fragment.MissionReadFragment;
import com.conoz.singletour.fragment.MissionWriteFragment;
import com.conoz.singletour.fragment.RecommendationFragment;
import com.conoz.singletour.fragment.SafeFragment;
import com.conoz.singletour.fragment.SafeReadFragment;
import com.conoz.singletour.interfaces.IFragmentManager;

/**
 * Created by Yongkyu on 2017. 8. 24..
 */

public abstract class BaseActivity extends Activity implements IFragmentManager{
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mLeftMenuTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Left Menu: 0 홈, 1 미션, 2 배부른 혼행, 3 즐기는 혼행, 4 안전한 혼행, 5 동행같은 혼행, 6 추천사이트, 7 회원정보 변경/회원 가입, 8 로그아웃/로그인
     */
    protected void initLeft(){
        mTitle = mDrawerTitle = getTitle();
        mLeftMenuTitles = getResources().getStringArray(R.array.left_menu_array);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // 클릭 방지를 위해 메뉴 오픈시 내용 위에 가려지는 오버레이
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // 아이템들과 클릭 리스너 어댑터
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mLeftMenuTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // 액션바 상단 설정
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* 아이콘 */
                R.string.drawer_open,  /* 접근성: 열기 */
                R.string.drawer_close  /* 접근성: 닫기 */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        if (savedInstanceState == null) {
            selectItem(Common.FRAGMENT_MAIN, null);
//        }
    }

    public void changeLeftMenu(boolean isLogin){
        if(isLogin){    //로그인
            mLeftMenuTitles = getResources().getStringArray(R.array.left_menu_login_array);
        }else{          //로그아웃
            mLeftMenuTitles = getResources().getStringArray(R.array.left_menu_array);
        }
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mLeftMenuTitles));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position, null);
        }
    }

    /**
     *
     * @param position 0 홈, 1 미션, 2 배부른 혼행, 3 즐기는 혼행, 4 안전한 혼행, 5 동행같은 혼행, 6 추천사이트, 7 회원정보 변경/회원 가입, 8 로그아웃/로그인
     */
    public void selectItem(int position, Bundle bundle) {
        //페이지 이동
        changeFragment(position, bundle);
        if(position<mLeftMenuTitles.length){
            // 선택된 메뉴의 타이틀을 변경 후 메뉴 창 닫기
            mDrawerList.setItemChecked(position, true);
            setTitle(mLeftMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    @Override
    public void changeFragment(int fragmentId, Bundle bundle) {
        BaseFragment newFragment=null;
        if(fragmentId== Common.FRAGMENT_MAIN){
            newFragment = MainFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_MISSION){
            newFragment = MissionFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_FULL){
            newFragment = FullFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_AMUSED){
            newFragment = AmusedFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_SAFE){
            newFragment = SafeFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_ACCOMPANY){
            newFragment = AccompaniedFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_RECOMMEND){
            newFragment = RecommendationFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_MEMBER){
            newFragment = MemberFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_LOGIN){
            newFragment = LoginFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_FULL_MAP){
            newFragment = FullMapFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_FULL_MAP_NEAR){
            newFragment = FullMapNearFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_MISSION_WRITE){
            newFragment = MissionWriteFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_MISSION_READ){
            newFragment = MissionReadFragment.newInstance(bundle);
        }else if(fragmentId== Common.FRAGMENT_SAFE_READ){
            newFragment = SafeReadFragment.newInstance(bundle);
        }

        if(newFragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, newFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * ActionBarDrawerToggle 에서 반드시 onPostCreate(), onConfigurationChanged() 를 구현해야 한다
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
