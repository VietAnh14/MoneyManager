package vianh.nva.moneymanager.ui.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import vianh.nva.moneymanager.data.AppRepository;
import vianh.nva.moneymanager.data.entity.Category;

public class CategoryViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        appRepository = AppRepository.getInstance(application);
    }
    // TODO: Implement the ViewModel

    public LiveData<List<Category>> getListCategorySpend() {
        return appRepository.getListCategorySpend();
    }

    public LiveData<List<Category>> getListCategoryEarn() {
        return appRepository.getListCategoryEarn();
    }

    public Completable insertCategory(Category category) {
        return appRepository.insertCategory(category);
    }

    public Completable updateCategory(Category category) {
        return appRepository.updateCategory(category);
    }
}
