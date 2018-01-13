package com.jaffn01.mydriving.uni.mydriving;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

import routing.OSRMRoadManager;
import routing.Road;
import routing.RoadManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapSummary.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapSummary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapSummary extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MapSummary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapSummary.
     */
    // TODO: Rename and change types and number of parameters
    public static MapSummary newInstance(String param1, String param2) {
        MapSummary fragment = new MapSummary();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Fragment f = (Fragment) getFragmentManager()
//                .findFragmentById(R.id.fragment_place_for_summary);
//        if (f != null)
//            getFragmentManager().beginTransaction().remove(f).commit();
//    }



//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_place_for_summary);
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.remove(fragment);
//        fragmentTransaction.commit();
//
//    }

    static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_map_summary, container, false);



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MapView mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.setTileSource(TileSoruce.MAPNIK);
        mMapView.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(50.790670, -1.090783);
        IMapController mapController = mMapView.getController();
        mapController.setZoom(19);
        mapController.setCenter(startPoint);

        RoadManager roadManager = new OSRMRoadManager(getActivity());
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(startPoint);
        GeoPoint endPoint = new GeoPoint(50.789031, -1.093670);
        waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
        roadOverlay.setColor(Color.GREEN);

        RoadManager roadManagerRed = new OSRMRoadManager(getActivity());
        ArrayList<GeoPoint> waypointsRed = new ArrayList<GeoPoint>();
        waypointsRed.add(startPoint);
        GeoPoint endPointRed = new GeoPoint(50.789074, -1.094110);
        waypointsRed.add(endPointRed);

        Road roadRed = roadManagerRed.getRoad(waypointsRed);
        Polyline roadOverlayRed = RoadManager.buildRoadOverlay(roadRed);
        roadOverlayRed.setColor(Color.RED);

        mMapView.getOverlays().add(roadOverlayRed);
        mMapView.getOverlays().add(roadOverlay);
        mMapView.invalidate();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
