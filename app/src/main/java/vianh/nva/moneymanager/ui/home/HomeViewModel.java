package vianh.nva.moneymanager.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Completable;
import vianh.nva.moneymanager.data.AppRepository;
import vianh.nva.moneymanager.data.entity.Category;
import vianh.nva.moneymanager.data.entity.Money;

public class HomeViewModel extends AndroidViewModel {

    private AppRepository appRepository;
    private LiveData<List<Category>> listCategorySpend;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
        this.listCategorySpend = appRepository.getListCategorySpend();
    }

    public LiveData<List<Category>> getListCategorySpend() {
        return listCategorySpend;
    }

    public LiveData<List<Category>> getListCategoryEarn() {
        return appRepository.getListCategoryEarn();
    }

    public Completable insertMoney(Money money) {
        if (!isValid(money)) {
            return Completable.error(new Throwable("Invalid argument"));
        }        return appRepository.insertMoney(money);
    }

    private boolean isValid(Money money) {
        return money.getMoney() > 0;
    }
}