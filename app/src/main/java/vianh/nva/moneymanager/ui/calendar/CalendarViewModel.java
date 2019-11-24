package vianh.nva.moneymanager.ui.calendar;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import io.reactivex.Flowable;
import vianh.nva.moneymanager.data.AppRepository;
import vianh.nva.moneymanager.data.entity.Money;

public class CalendarViewModel extends AndroidViewModel {
    AppRepository repository;

    public CalendarViewModel(Application application) {
        super(application);
        repository = AppRepository.getInstance(application);
    }

    public Flowable<List<Money>> getListMoneyByMonth(int month) {
        return repository.getListMoneyByMonth(month);
    }
}