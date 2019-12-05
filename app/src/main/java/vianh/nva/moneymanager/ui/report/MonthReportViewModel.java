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
    private float total = 0f;

    public MonthReportViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
    }

    public Flowable<List<TotalMoneyDisplay>> getTotalMoney(int month, int year) {
        return appRepository.getTotalMoneyByMonthYear(month, year).map(
                totalMoneyDisplays -> {
                    float totalMoney = 0f;
                    for (TotalMoneyDisplay money : totalMoneyDisplays) {
                        if (money.getType() == Money.TYPE_SPEND) {
                            money.setTotalMoney(-1 * money.getTotalMoney());
                        }
                        totalMoney += money.getTotalMoney();
                    }
                    total = totalMoney;
                    return  totalMoneyDisplays;
                }
        );
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public float getTotal() {
        return total;
    }
}
