package vianh.nva.moneymanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vianh.nva.moneymanager.data.entity.Money;

@Dao
public interface MoneyDao {
    @Insert
    Completable insert(Money money);

    @Query("SELECT * FROM money where date = :date")
    LiveData<List<Money>> getMoneyByDate(Date date);

    @Query("SELECT * FROM money where CAST(strftime('%m', DATETIME(date/1000, 'unixepoch')) AS INT) = :month ORDER BY date ASC")
    Flowable<List<Money>> getMoneyByMonth(int month);

    @Query("Delete from money")
    Completable deleteAll();
}
