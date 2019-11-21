package vianh.nva.moneymanager.ui.dashboard;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import vianh.nva.moneymanager.data.DataRepository;
import vianh.nva.moneymanager.data.entity.Category;
import vianh.nva.moneymanager.data.entity.Money;

public class OutcomeViewModel extends AndroidViewModel {
    private DataRepository dataRepository;
    private LiveData<List<Category>> listCategorySpend;

    public OutcomeViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        this.listCategorySpend = dataRepository.getListCategorySpend();
    }

    public LiveData<List<Category>> getListCategorySpend() {
        return listCategorySpend;
    }

    public Completable insertSpentMoney(Money money) {
        money.setType(Money.TYPE_SPEND);
        if (!isValid(money)) {
             return Completable.error(new Throwable("Invalid argument"));
        }
        return dataRepository.insertMoney(money);
    }

    private boolean isValid(Money money) {
        return money.getDate().getTime() >= 0 && money.getType() == Money.TYPE_SPEND && money.getMoney() > 0;
    }
}
