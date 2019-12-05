package vianh.nva.moneymanager.ui.report;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import vianh.nva.moneymanager.data.AppRepository;

public class MonthReportViewModel extends AndroidViewModel {
    private AppRepository appRepository;

    public MonthReportViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
    }
}
