package com.jaffn01.mydriving.uni.mydriving;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * DEPRECATED CLASS 26/12/2017
 * Created by JaffN01 on 23/12/2017.
 */

public class JourneySummary extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.view_history_summary, container, false);
        return  rootView;

    }

}
