package edu.gatech.donatrix.controllers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.model.Location;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 */
public class LocationDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ITEM_ID = "item_id";

    // TODO: Rename and change types of parameters
    private Location mItem;


    public LocationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            int item_id = getArguments().getInt(ARG_ITEM_ID);
            mItem = LocationDao.getLocationByID(item_id, this.getContext());

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.locationName)).setText("" + mItem.getName());
            ((TextView) rootView.findViewById(R.id.locationAddress)).setText("" + mItem.getAddress());
            ((TextView) rootView.findViewById(R.id.locationLatitude)).setText("" + mItem.getLatitude());
            ((TextView) rootView.findViewById(R.id.locationLongitude)).setText("" + mItem.getLongitude());
        }
        // Inflate the layout for this fragment
        return rootView;
    }

}
