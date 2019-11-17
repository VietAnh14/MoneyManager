package vianh.nva.moneymanager.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.data.entity.Category;
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

        initData(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    public void initData(View view) {


        mViewModel = ViewModelProviders.of(this).get(OutcomeViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.listCategory);
        final CategoryAdapter adapter = new CategoryAdapter();
        final LiveData<List<Category>> listLiveData = mViewModel.getListCategorySpend();


        mViewModel.getListCategorySpend().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapter.setList(categories);
                Log.d("Outcome", "Changed");
            }
        });
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
    }
}
