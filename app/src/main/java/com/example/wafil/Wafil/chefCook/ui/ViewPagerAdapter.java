package com.example.wafil.Wafil.chefCook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wafil.Wafil.chefCook.fragments.BlackFragment;
import com.example.wafil.Wafil.chefCook.fragments.UserProfilFragment;
import com.example.wafil.Wafil.chefCook.fragments.YellowFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private String title;

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
        //tambah
        fragments = new Fragment[]{
                new BlackFragment(),
                new UserProfilFragment(),
                new YellowFragment()
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        tambahhan
//        switch (position)
//        {
//            case 0:
//                return new BlackFragment();
//            case 1:
//                return new UserProfilFragment();
//            case 2:
//                return new YellowFragment();
//        }
//        return null;
        return fragments[position];
    }



    @Override
    public int getCount() {
//        return 3;
        return fragments.length;
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // ori
        // return super.getPageTitle(position);
        title = getItem(position).getClass().getName();
        return title.subSequence(title.lastIndexOf(".") + 1
                , title.length());
    }
}
