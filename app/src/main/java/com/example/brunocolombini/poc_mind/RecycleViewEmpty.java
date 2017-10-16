package com.example.brunocolombini.poc_mind;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by brunocolombini on 16/10/17.
 */

public class RecycleViewEmpty extends RecyclerView {

    private View recycleViewEmpty;
    private View emptyState;
    private View sameStateView;

    public RecycleViewEmpty(Context context) {
        super(context);
        recycleViewEmpty = this;
    }

    public RecycleViewEmpty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        recycleViewEmpty = this;
    }

    public RecycleViewEmpty(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        recycleViewEmpty = this;
    }

    public void setEmptyStateView(View emptyState) {
        this.emptyState = emptyState;
    }

    public void setSameStateView(View sameStateView) {
        this.sameStateView = sameStateView;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        EmptyObserver emptyObserver = new EmptyObserver();

        adapter.registerAdapterDataObserver(emptyObserver);

        emptyObserver.onChanged();
    }

    class EmptyObserver extends AdapterDataObserver {

        @Override
        public void onChanged() {
            super.onChanged();
            if (getAdapter() != null) {
                if (getAdapter().getItemCount() == 0) {
                    if (emptyState != null)
                        emptyState.setVisibility(VISIBLE);
                    if (sameStateView != null)
                        sameStateView.setVisibility(GONE);

                    recycleViewEmpty.setVisibility(GONE);

                } else {
                    if (emptyState != null)
                        emptyState.setVisibility(GONE);
                    if (sameStateView != null)
                        sameStateView.setVisibility(VISIBLE);

                    recycleViewEmpty.setVisibility(VISIBLE);
                }
            }
        }
    }
}