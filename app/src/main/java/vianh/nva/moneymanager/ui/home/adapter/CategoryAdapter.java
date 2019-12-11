package vianh.nva.moneymanager.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
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
import vianh.nva.moneymanager.Utils.Utils;
import vianh.nva.moneymanager.data.entity.Category;
import vianh.nva.moneymanager.ui.category.ListCategoryActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>{
    public static final int TYPE_SETTING = -1;

    private List<Category> list;
    private int selectedPosition = 0;
    private Context context;

    public CategoryAdapter(List<Category> data, int selectedPosition) {
        this.selectedPosition = selectedPosition;
        this.list = data;
    }

    public CategoryAdapter() { }

    public void setList(List<Category> list) {

        // Add a category just to start new activity
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        int iconId = Utils.getResId(list.get(position).getIconName(), R.drawable.class);
        int colorId = Utils.getResId(list.get(position).getColorName(), R.color.class);
        String desc = list.get(position).getDescription();
        holder.itemView.setSelected(selectedPosition == position);
        holder.categoryIcon.setColorFilter(ContextCompat.getColor(context, colorId), android.graphics.PorterDuff.Mode.SRC_IN);
        holder.categoryIcon.setImageResource(iconId);
        holder.categoryDesc.setText(desc);

        if (list.get(position).getType() != TYPE_SETTING) {
            holder.itemView.setOnClickListener(view -> {
                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
            });
        } else {
            holder.itemView.setOnClickListener( view -> {
                Intent intent = new Intent(context, ListCategoryActivity.class);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
       if (list != null) {
           return list.size();
       } else
           return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryDesc;
        private ImageView categoryIcon;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryDesc = itemView.findViewById(R.id.categorySpendDesc);
            categoryIcon = itemView.findViewById(R.id.categorySpendIcon);
        }
    }

    public int getSelectedId() {
        return list.get(selectedPosition).getId();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
    public void setSelectedPosition(int selectedPosition) {
        notifyItemChanged(this.selectedPosition);
        this.selectedPosition = selectedPosition;
        notifyItemChanged(selectedPosition);
    }
}
