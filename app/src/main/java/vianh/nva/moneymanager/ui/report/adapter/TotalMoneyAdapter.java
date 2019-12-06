package vianh.nva.moneymanager.ui.report.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.Utils;
import vianh.nva.moneymanager.data.entity.TotalMoneyDisplay;

public class TotalMoneyAdapter extends RecyclerView.Adapter<TotalMoneyAdapter.MyViewHolder> {
    private List<TotalMoneyDisplay> totalMoneyDisplays;
    private Context context;

    public TotalMoneyAdapter(List<TotalMoneyDisplay> totalMoneyDisplays) {
        this.totalMoneyDisplays = totalMoneyDisplays;
    }

    public void setTotalMoneyDisplays(List<TotalMoneyDisplay> totalMoneyDisplays) {
        this.totalMoneyDisplays = totalMoneyDisplays;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_money_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TotalMoneyDisplay money = totalMoneyDisplays.get(position);
        int corlor = Utils.getResId(money.getColorName(), R.color.class);
        int icon = Utils.getResId(money.getIconName(), R.drawable.class);
        holder.categoryIcon.setImageResource(icon);
        holder.categoryIcon.setColorFilter(ContextCompat.getColor(context, corlor), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.totalMoney.setText(String.valueOf(money.getTotalMoney()));
        holder.description.setText(money.getDescription());
    }

    @Override
    public int getItemCount() {
        if (totalMoneyDisplays == null) {
            return 0;
        } else
            return totalMoneyDisplays.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryIcon;
        private TextView description;
        private TextView totalMoney;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.iconCategory);
            description = itemView.findViewById(R.id.categoryName);
            totalMoney = itemView.findViewById(R.id.moneyText);
        }
    }
}
