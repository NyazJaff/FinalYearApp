package com.jaffn01.mydriving.uni.mydriving;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JaffN01 on 25/12/2017.
 */

public class JourneyListAdapter  extends ArrayAdapter<String>{

    private  Context context;
    private  FragmentManager fragmentManager;


    public JourneyListAdapter(Context context, String[] journeyDbList, FragmentManager fragmentManager){
        super(context, R.layout.journey_search_item,journeyDbList);
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckyInflater = LayoutInflater.from(getContext());
        View view = buckyInflater.inflate(R.layout.journey_search_item, parent, false);
        TextView searchRedShapeImg = (TextView) view.findViewById(R.id.searchRedShapeImg);
        TextView searchTravelTxt = (TextView) view.findViewById(R.id.searchTravelTxt);
        TextView searchTimeTxt = (TextView) view.findViewById(R.id.searchTimeTxt);
        ImageView searchPerformanceIcon = (ImageView) view.findViewById(R.id.searchPerformanceIcon);
        ImageView searchArrowImg = (ImageView) view.findViewById(R.id.searchArrowImg);


        searchArrowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FrameLayout fl = (FrameLayout) view.findViewById(R.id.fragment_place);
//                fl.removeAllViews();
                Fragment fragment = new MapSummary();
                fragmentManager.beginTransaction().replace(R.id.fragment_place_for_summary, fragment,"testTag").addToBackStack("testTag").commit();
            }
        });

        return view;
    }
}
