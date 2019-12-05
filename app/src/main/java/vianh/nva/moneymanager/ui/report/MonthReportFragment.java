package vianh.nva.moneymanager.ui.report;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.databinding.FragmentMonthReportBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthReportFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private MonthReportViewModel viewModel;
    ArrayAdapter<CharSequence> spinnerMonthAdapter;
    ArrayAdapter<String> spinnerYearAdapter;
    private FragmentMonthReportBinding binding;
    private Calendar calendar = Calendar.getInstance();
    public MonthReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View root = inflater.inflate(R.layout.fragment_month_report, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_month_report, container, false);
        viewModel = ViewModelProviders.of(this).get(MonthReportViewModel.class);
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(binding);
        setupChart();
    }

    public void initView(FragmentMonthReportBinding binding) {
        spinnerMonthAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.monthArray, android.R.layout.simple_spinner_item);
        spinnerMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> years = new ArrayList<>();
        for(int i = 2000; i <= 2050; i++) {
            years.add("Nam" + i);
        }
        spinnerYearAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, years);
        spinnerYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerMonth.setAdapter(spinnerMonthAdapter);
        binding.spinnerYear.setAdapter(spinnerYearAdapter);
        binding.spinnerYear.setOnItemSelectedListener(this);
        binding.spinnerMonth.setOnItemSelectedListener(this);
        binding.spinnerMonth.setSelection(calendar.get(Calendar.MONTH));
        binding.spinnerYear.setSelection(calendar.get(Calendar.YEAR) - 2000);
        Log.d("adapter", "set adapter");
    }

    public void setupChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(new int[] {R.color.greenMaterial, R.color.blueMaterial,
                R.color.pinkMaterial, R.color.yellowMaterial}, getContext());
        PieData data = new PieData(set);
        binding.chart.setData(data);
        binding.chart.invalidate();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        String text = String.valueOf(binding.spinnerMonth.getSelectedItemPosition()) + binding.spinnerYear.getSelectedItemPosition();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
