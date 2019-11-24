package vianh.nva.moneymanager.ui.calendar.adapter;

import java.util.Date;

public class MoneyApdaterHeader extends AdapterItem {
    private Date date;

    @Override
    public int getType() {
        return TYPE_DATE;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
