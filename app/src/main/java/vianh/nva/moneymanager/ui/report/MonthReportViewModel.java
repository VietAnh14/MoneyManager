package vianh.nva.moneymanager.ui.report;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import vianh.nva.moneymanager.data.AppRepository;
import vianh.nva.moneymanager.data.entity.Money;
import vianh.nva.moneymanager.data.entity.TotalMoneyDisplay;

public class MonthReportViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private float totalEarn = 0f;
    private float totalSpend = 0f;

    public MonthReportViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
    }

    public Flowable<List<TotalMoneyDisplay>> getTotalMoneyEarn(int month, int year) {
        return appRepository.getTotalMoneyEarnByMonthYear(month, year).map(
                totalMoneyDisplays -> {
                    float totalMoney = 0f;
                    for (TotalMoneyDisplay money : totalMoneyDisplays) {
                        totalMoney += money.getTotalMoney();
                    }
                    totalEarn = totalMoney;
                    return totalMoneyDisplays;
                }
        );
    }

    public Flowable<List<TotalMoneyDisplay>> getTotalMoneySpend(int month, int year) {
        return appRepository.getTotalMoneySpendByMonthYear(month, year).map(
                totalMoneyDisplays -> {
                    float totalMoney = 0f;
                    for (TotalMoneyDisplay money : totalMoneyDisplays) {
                        money.setTotalMoney(-1 * money.getTotalMoney());
                        totalMoney += money.getTotalMoney();
                    }
                    totalSpend = totalMoney;
                    return totalMoneyDisplays;
                }
        );
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public float getTotalEarn() {
        return totalEarn;
    }

    public float getTotalSpend() {
        return totalSpend;
    }
}
