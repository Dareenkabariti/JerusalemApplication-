package com.example.jerusalemguid.ui.Places;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.jerusalemguid.R;
import com.example.jerusalemguid.databinding.FragmentHomeBinding;
import com.example.jerusalemguid.ui.Adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class PlacesFragment extends Fragment {

    private FragmentHomeBinding binding;
    ViewPager viewPager;
    TabLayout tabLayout;
    View myFragment;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_home, container, false);


        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        return myFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
        adapter.addFragment(new AllFragment(), "الكل");
        adapter.addFragment(new ReligiousPlacesFragment(),"اماكن دينية") ;
        adapter.addFragment(new TouristicPlacesFragment(), "اماكن سياحية");
        adapter.addFragment(new HistoricalPlacesFragment(), "اماكن اثرية");

        viewPager.setAdapter(adapter);
          viewPager.setRotationY(180);

    }
}
