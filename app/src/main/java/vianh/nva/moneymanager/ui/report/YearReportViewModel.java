package vianh.nva.moneymanager.ui.report;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import vianh.nva.moneymanager.data.AppRepository;
import vianh.nva.moneymanager.data.entity.TotalMoneyDisplay;

public class YearReportViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private float totalEarn = 0f;
    private float totalSpend = 0f;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public YearReportViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
    }

    public Flowable<List<TotalMoneyDisplay>> getTotalMoneySpendByYear(int year) {
        return appRepository.getTotalMoneySpendByYear(year).map(
                totalMoneyDisplays -> {
                    for (TotalMoneyDisplay money : totalMoneyDisplays) {
                        totalSpend += money.getTotalMoney();
                    }
                    return totalMoneyDisplays;
                }
        );
    }

    public Flowable<List<TotalMoneyDisplay>> getTotalMoneyEarnByYear(int year) {
        return appRepository.getTotalMoneyEarnByYear(year).map(
                totalMoneyDisplays -> {
                    for (TotalMoneyDisplay money : totalMoneyDisplays) {
                        totalEarn += money.getTotalMoney();
                    }
                    return totalMoneyDisplays;
                }
        );
    }

    public float getTotalEarn() {
        return totalEarn;
    }

    public float getTotalSpend() {
        return totalSpend;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
