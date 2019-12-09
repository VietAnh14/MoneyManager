package vianh.nva.moneymanager.ui.category.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vianh.nva.moneymanager.R;
import vianh.nva.moneymanager.Utils;
import vianh.nva.moneymanager.data.entity.Category;
import vianh.nva.moneymanager.data.entity.Money;
import vianh.nva.moneymanager.ui.category.CategoryActivity;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.CategoryViewHolder> {
    private List<Category> listCategory;
    private Context context;
    private boolean isSpend = true;
    public static final int MODE_INSERT = 5;
    public static final int MODE_UPDATE = 6;

    public ListCategoryAdapter(boolean isSpend) {
        this.isSpend = isSpend;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.money_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        int iconId = Utils.getResId(listCategory.get(position).getIconName(), R.drawable.class);
        int colorId = Utils.getResId(listCategory.get(position).getColorName(), R.color.class);
        String desc = listCategory.get(position).getDescription();
        holder.categoryIcon.setColorFilter(ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.categoryIcon.setImageResource(iconId);
        holder.categoryDescription.setText(desc);
        int type = listCategory.get(position).getType();
        holder.itemView.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Intent intent = new Intent(activity, CategoryActivity.class);
            intent.putExtra("category", listCategory.get(position));
            if (position == 0) {
                intent.putExtra("mode", MODE_INSERT);
            } else {
                intent.putExtra("mode", MODE_UPDATE);
            }
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listCategory == null ? 0 : listCategory.size();
    }

    public void setListCategory(List<Category> listCategory) {
        List<Category> temp = new ArrayList<>();
        int type = isSpend ? Money.TYPE_SPEND : Money.TYPE_EARN;
        temp.add(new Category("ic_calendar", "black", "Them moi category", type));
        temp.addAll(listCategory);
        this.listCategory = temp;
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryIcon;
        private TextView categoryDescription;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.iconCategory);
            categoryDescription = itemView.findViewById(R.id.categoryName);
            TextView moneyTextView = itemView.findViewById(R.id.moneyText);
            moneyTextView.setVisibility(View.INVISIBLE);
        }
    }
}
