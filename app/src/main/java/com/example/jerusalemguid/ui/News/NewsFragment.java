package com.example.jerusalemguid.ui.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.jerusalemguid.R;

import com.example.jerusalemguid.ui.Adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class NewsFragment extends Fragment {


    ViewPager viewPager;
    TabLayout tabLayout;
    View myFragment;
    NewsFragment dashboardFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_notifications, container, false);
        viewPager = myFragment.findViewById(R.id.viewPager1);
        tabLayout = myFragment.findViewById(R.id.tabLayout1);

        return myFragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new APIFragment(), " اهم الاخبار");
        adapter.addFragment(new ImportantNewsFragment(), "تفصيل الخبر");

        viewPager.setAdapter(adapter);
        viewPager.setRotationY(180);

    }

}