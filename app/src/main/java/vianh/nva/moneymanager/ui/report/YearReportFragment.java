package vianh.nva.moneymanager.ui.report;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vianh.nva.moneymanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YearReportFragment extends Fragment {


    public YearReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_year_report, container, false);
    }

}
