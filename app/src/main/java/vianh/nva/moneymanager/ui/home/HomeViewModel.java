package vianh.nva.moneymanager.ui.home;

import android.app.Application;
import android.util.Log;

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
//        Log.d("Home viewmodel", String.valueOf(listCategorySpend.getValue().size()));
        return listCategorySpend;
    }

    public LiveData<List<Category>> getListCategoryEarn() {
        return appRepository.getListCategoryEarn();
    }

    public Completable insertMoney(Money money) {
        if (!isValid(money)) {
            return Completable.error(new Throwable("Invalid argument"));
        }
        if (money.getType() == Money.TYPE_SPEND) {
            float value = money.getMoney() * -1;
            money.setMoney(value);
        }
        return appRepository.insertMoney(money);
    }

    public Completable updateMoney(Money money) {
        if (!isValid(money)) {
            return Completable.error(new Throwable("Invalid argument"));
        }
        if (money.getType() == Money.TYPE_SPEND) {
            float value = money.getMoney() * -1;
            money.setMoney(value);
        }
        return appRepository.updateMoney(money);
    }

    public Completable deleteMoney(Money money) {
        return appRepository.deleteMoney(money);
    }

    private boolean isValid(Money money) {
        return money.getMoney() > 0;
    }
}