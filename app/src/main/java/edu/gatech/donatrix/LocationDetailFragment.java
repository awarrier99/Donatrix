package edu.gatech.donatrix;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.model.Location;

public class LocationDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Location mItem;

    public LocationDetailFragment() {
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.locationName)).setText("" + mItem.getName());
            ((TextView) rootView.findViewById(R.id.locationAddress)).setText("" + mItem.getAddress());
            ((TextView) rootView.findViewById(R.id.locationLatitude)).setText("" + mItem.getLatitude());
            ((TextView) rootView.findViewById(R.id.locationLongitude)).setText("" + mItem.getLongitude());
        }

        return rootView;
    }
}
