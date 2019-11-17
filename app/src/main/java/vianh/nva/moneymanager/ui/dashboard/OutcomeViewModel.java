package vianh.nva.moneymanager.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import vianh.nva.moneymanager.data.DataRepository;
import vianh.nva.moneymanager.data.entity.Category;

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
}
