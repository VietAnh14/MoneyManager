package vianh.nva.moneymanager.ui.report;


import android.graphics.Color;
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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.Utils;
import vianh.nva.moneymanager.data.entity.TotalMoneyDisplay;
import vianh.nva.moneymanager.databinding.FragmentMonthReportBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthReportFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public final String TAG = this.getClass().getSimpleName();
    private MonthReportViewModel viewModel;
    ArrayAdapter<CharSequence> spinnerMonthAdapter;
    ArrayAdapter<String> spinnerYearAdapter;
    private FragmentMonthReportBinding binding;
    private Calendar calendar = Calendar.getInstance();
    private float totalMoney = 0f;
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
        setupData(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
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

    public void setupChart(List<TotalMoneyDisplay> listMoney) {
        binding.chart.setUsePercentValues(true);
        binding.chart.getDescription().setEnabled(false);
        binding.chart.setCenterText("Bieu do thu chi thang");
        binding.chart.setCenterTextColor(R.color.darkBlueMaterial);
        binding.chart.setDrawHoleEnabled(true);
        binding.chart.setHoleColor(Color.WHITE);
        binding.chart.animateY(1400, Easing.EaseInOutQuad);
        binding.chart.setTransparentCircleAlpha(110);
        binding.chart.getLegend().setEnabled(false);
        binding.chart.setHoleRadius(58f);
        binding.chart.setTransparentCircleRadius(61f);


        List<PieEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        for (TotalMoneyDisplay money : listMoney) {
            colors.add(getResources().getColor(Utils.getResId(money.getColorName(), R.color.class)));
            float percent = Math.round((money.getTotalMoney() / viewModel.getTotal()) * 10000) / 100;
            entries.add(new PieEntry(percent, money.getDescription()));
            Log.d(TAG, "percent " + percent);
            Log.d(TAG, money.getColorName());
        }

        PieDataSet set = new PieDataSet(entries, "Money report");
        set.setSliceSpace(5f);
        set.setValueTextColor(R.color.black);
        set.setSelectionShift(6f);
        set.setColors(colors);
        PieData data = new PieData(set);
        data.setValueTextSize(15f);
        data.setValueFormatter(new PercentFormatter(binding.chart));
        data.setValueTextColor(R.color.black);
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

    public void setupData(int month, int year) {
        viewModel.getCompositeDisposable().add(
                viewModel.getTotalMoney(month, year)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                listMoney -> {
                                    Log.d(TAG, "list money retrieve" + listMoney.size());
                                    setupChart(listMoney);
                                },
                                throwable -> {
                                    Log.e(TAG, "can't get list money", throwable);
                                }
                        )
        );
    }
}
