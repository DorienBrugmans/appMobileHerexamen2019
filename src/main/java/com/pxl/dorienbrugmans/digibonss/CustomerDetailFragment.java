package com.pxl.dorienbrugmans.digibonss;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pxl.dorienbrugmans.digibonss.dummy.DummyContent;
import com.pxl.dorienbrugmans.digibonss.utilities.NetworkUtils;

/**
 * A fragment representing a single Customer detail screen.
 * This fragment is either contained in a {@link CustomerListActivity}
 * in two-pane mode (on tablets) or a {@link CustomerDetailActivity}
 * on handsets.
 */
public class CustomerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "Id";
    public static final String IS_LIGHT_THEME = "is_light_theme";
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.Customer mItem;
    private boolean isLightTheme;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CustomerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = NetworkUtils.GetCustomers().get(getArguments().getInt(ARG_ITEM_ID));
            isLightTheme = getArguments().getBoolean(IS_LIGHT_THEME);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mItem.name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customer_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            int id = getResources().getIdentifier(mItem.imageUrl, "drawable", "com.pxl.dorienbrugmans.digibonss");
            ((ImageView) rootView.findViewById(R.id.customer_image)).setImageResource(id);
            ((TextView) rootView.findViewById(R.id.customer_address)).setText("Address: " +mItem.address);
            ((TextView) rootView.findViewById(R.id.customer_phone)).setText("Phone: " +mItem.phone);
        }

        return rootView;
    }
}
