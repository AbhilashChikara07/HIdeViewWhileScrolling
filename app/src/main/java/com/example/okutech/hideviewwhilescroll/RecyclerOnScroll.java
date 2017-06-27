package com.example.okutech.hideviewwhilescroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

/**
 * Description
 *
 * @author Abhilash Chikara
 * @version 1.0
 * @since 5/12/17
 */

public class RecyclerOnScroll extends RecyclerView.OnScrollListener {

    RecyclerView.LayoutManager mLayoutManager;
    OnLoadMoreListener onLoadMoreListener;
    private int currentScrollState;
    private boolean loading;
    private LinearLayout searchBottomLL;
    private int mLastFirstVisibleItem;

    public RecyclerOnScroll(LinearLayoutManager layoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.mLayoutManager = layoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE)
            recyclerView.invalidate();
        currentScrollState = newState;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        super.onScrolled(view, dx, dy);
        int lastVisibleItemPosition = 0;
        int firstVisiblePosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();
        int visibleItemCount = mLayoutManager.getChildCount();

        if (mLayoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            firstVisiblePosition = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if (mLastFirstVisibleItem < firstVisiblePosition) {
            hideSearchBottomLL();
        }
        if (mLastFirstVisibleItem > firstVisiblePosition) {
            showSearchBottomLL();
        }
        mLastFirstVisibleItem = firstVisiblePosition;

        if (dy > 0) {
            if (!loading && currentScrollState != RecyclerView.SCROLL_STATE_IDLE &&
                    lastVisibleItemPosition + visibleItemCount >= totalItemCount) {
                loading = true;
                onLoadMoreListener.onLoadMore();
            }
        }
    }

    public void setSearchBottomLL(LinearLayout searchBottomLL) {
        this.searchBottomLL = searchBottomLL;
    }

    private void hideSearchBottomLL() {
        if (searchBottomLL != null)
            searchBottomLL.animate()
                    .translationY(searchBottomLL.getHeight())
                    .setInterpolator(new LinearInterpolator())
                    .setDuration(200)
                    .start();
    }

    private void showSearchBottomLL() {
        if (searchBottomLL != null)
            searchBottomLL.animate()
                    .translationY(0)
                    .setInterpolator(new LinearInterpolator())
                    .setDuration(200)
                    .start();
    }


    public void onLoadMoreComplete() {
        loading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}