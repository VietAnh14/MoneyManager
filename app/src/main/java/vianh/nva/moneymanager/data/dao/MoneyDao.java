package vianh.nva.moneymanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import vianh.nva.moneymanager.data.entity.Money;

@Dao
public interface MoneyDao {
    @Insert
    void insert(Money money);

    @Query("SELECT * FROM money where date = :date")
    LiveData<List<Money>> getMoneyByDate(Date date);

    @Query("SELECT * FROM money where CAST(strftime('%m', DATETIME(date, 'unixepoch')) AS INT) = :month")
    LiveData<List<Money>> getMoneyByMonth(int month);

    @Query("Delete from money")
    void deleteAll();
}
