package com.komandor.komandor.widgets.binding_adapters;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutBindingAdapter {
  @BindingAdapter({"bind:onRefreshListener", "bind:refreshing"})
  public static void configureSwipeRefreshLayout(SwipeRefreshLayout layout, boolean isLoading, SwipeRefreshLayout.OnRefreshListener listener) {
    layout.setOnRefreshListener(listener);
    layout.post(() -> layout.setRefreshing(isLoading));
  }
}
