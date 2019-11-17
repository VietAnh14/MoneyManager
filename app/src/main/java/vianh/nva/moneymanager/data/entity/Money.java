package vianh.nva.moneymanager.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;

@Entity(tableName = "money",
        indices = {@Index("date"), @Index("type"), @Index("categoryId")},
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id", childColumns = "categoryId"))
public class Money {
    @Ignore
    public static final int TYPE_SPEND = 0;

    @Ignore
    public static final int TYPE_EARN = 1;

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private String note;
    private Float money;
    private int categoryId;

    // Type of money, earn or spend
    private int type;
}
