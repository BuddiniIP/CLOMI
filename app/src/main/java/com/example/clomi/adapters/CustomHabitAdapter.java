package com.example.clomi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.clomi.R;
import com.example.clomi.model.CustomHabit;

import java.util.List;

public class CustomHabitAdapter extends RecyclerView.Adapter<CustomHabitAdapter.HabitViewHolder> {

    public interface OnHabitClickListener {
        void onHabitClick(CustomHabit habit);
    }

    private final List<CustomHabit> habitList;
    private final OnHabitClickListener listener;

    public CustomHabitAdapter(List<CustomHabit> habitList, OnHabitClickListener listener) {
        this.habitList = habitList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        CustomHabit habit = habitList.get(position);
        holder.tvHabitName.setText(habit.getHabitName());
        holder.tvCategory.setText(habit.getCategory());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onHabitClick(habit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView tvHabitName;
        TextView tvCategory;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHabitName = itemView.findViewById(R.id.tvHabitName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}