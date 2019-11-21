package vianh.nva.moneymanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vianh.nva.moneymanager.data.entity.Category;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    // Money type
    @Query("SELECT * FROM category WHERE type = :type")
    LiveData<List<Category>> getCategoriesByType(int type);

    @Query("delete from category")
    void deleteAll();
}
