package com.example.brunocolombini.poc_mind;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private HashMap<Integer, Boolean> appCompatCheckBoxListChecked = new HashMap<>();
    private List<String> strings;

    public RecycleViewAdapter(List<String> strings) {
        this.strings = strings;
        organizeList(false);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public void selectAll() {
        organizeList(true);
        notifyDataSetChanged();
    }

    private void organizeList(boolean b){
        appCompatCheckBoxListChecked.clear();
        for (int i = 0; i < strings.size(); i++) {
            appCompatCheckBoxListChecked.put(i, b);
        }
    }

    public void deleteSelected() {
        for (int i = strings.size() - 1; i >= 0; i--) {
            if (appCompatCheckBoxListChecked.get(i)) {
                appCompatCheckBoxListChecked.remove(i);
                strings.remove(i);
            };
        }
        organizeList(false);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.adapter_recycleview)
        ConstraintLayout constraintLayout;

        @BindView(R.id.textView)
        TextView name;

        @BindView(R.id.checkBox)
        AppCompatCheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final int position) {
            constraintLayout.setBackgroundColor(itemView.getResources().getColor(position % 2 == 0 ? R.color.brown_light_1 : R.color.brown_light_2));
            name.setText(position + " - " + strings.get(position));

            if (appCompatCheckBoxListChecked.get(position)) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!checkBox.isChecked()) {
                        checkBox.setChecked(true);
                        appCompatCheckBoxListChecked.put(position, true);
                    } else {
                        checkBox.setChecked(false);
                        appCompatCheckBoxListChecked.put(position, false);
                    }
                }
            });

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(true);
                        appCompatCheckBoxListChecked.put(position, true);
                    } else {
                        checkBox.setChecked(false);
                        appCompatCheckBoxListChecked.put(position, false);
                    }
                }
            });

        }
    }
}
