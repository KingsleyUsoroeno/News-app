package techgroup.com.news24.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import techgroup.com.news24.Fragments.GeneralNewsFragment;
import techgroup.com.news24.Fragments.SportFragment;
import techgroup.com.news24.Fragments.TechNewsFragment;
import techgroup.com.news24.R;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new GeneralNewsFragment();
        } else if (position == 1) {
            return new SportFragment();
        } else
            return new TechNewsFragment();
    }

    // This determines the Number of tabs to Return
    @Override
    public int getCount() {
        return 3;
    }
    // This determines the title for each tab

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_general_news);

            case 1:
                return mContext.getString(R.string.fragment_sport);

            case 2:
                return mContext.getString(R.string.fragment_tech_news);

            default:
                return null;
        }


    }
}
