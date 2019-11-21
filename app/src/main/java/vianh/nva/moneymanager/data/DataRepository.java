package vianh.nva.moneymanager.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import vianh.nva.moneymanager.data.entity.Category;
import vianh.nva.moneymanager.data.entity.Money;

public class DataRepository {

    private final AppDatabase db;
    private LiveData<List<Category>> listCategorySpend;

    public DataRepository(Application application) {
        db = AppDatabase.getInstance(application);
        listCategorySpend = db.categoryDao().getCategoriesByType(Money.TYPE_SPEND);
    }


    public LiveData<List<Category>> getListCategorySpend() {
        return listCategorySpend;
    }

    public Completable insertMoney(Money money) {
        Log.d("insert", "inserted");
        return db.moneyDao().insert(money);
    }

}
