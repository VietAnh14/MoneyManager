package vianh.nva.moneymanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vianh.nva.moneymanager.data.entity.Money;

@Dao
public interface MoneyDao {
    @Insert
    Completable insert(Money money);

    @Query("SELECT * FROM money where year = :year and month = :month and year = :year and day = :day")
    LiveData<List<Money>> getMoneyByDate(int day, int month, int year);

    @Query("SELECT * FROM money WHERE month = :month AND year = :year ORDER BY day ASC")
    Flowable<List<Money>> getMoneyByMonthAndYear(int month, int year);

    @Query("Delete from money")
    Completable deleteAll();
}
