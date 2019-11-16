package vianh.nva.moneymanager.ui.dashboard;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.ui.dashboard.adapter.CategoryAdapter;

public class OutcomeFragment extends Fragment {

    private OutcomeViewModel mViewModel;

    public static OutcomeFragment newInstance() {
        return new OutcomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outcome, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.listCategory);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            data.add("a");
        }
        CategoryAdapter adapter = new CategoryAdapter(data);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OutcomeViewModel.class);
        // TODO: Use the ViewModel
    }

}
