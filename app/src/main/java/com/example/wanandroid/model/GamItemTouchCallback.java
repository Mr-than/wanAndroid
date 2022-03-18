package com.example.wanandroid.model;


import android.graphics.Canvas;
import com.example.wanandroid.model.*;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class GamItemTouchCallback extends ItemTouchHelper.Callback {

    private final int mDefaultScrollX;
    private int mCurrentScrollX;
    private int mCurrentScrollXWhenInactive;
    private float mInitXWhenInactive;
    private boolean mFirstInactive;
    private Test test;


    public GamItemTouchCallback(int defaultScrollX,Test test) {
        mDefaultScrollX = defaultScrollX;
        //this.test=test;
    }
    
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }



    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        // 上下拖动
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 向左滑动
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return Integer.MAX_VALUE;
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            test.onItemMove(viewHolder.getAbsoluteAdapterPosition(),target.getAbsoluteAdapterPosition());
        return true;
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (dX == 0) {
                mCurrentScrollX = viewHolder.itemView.getScrollX();
                mFirstInactive = true;
            }
            if (isCurrentlyActive) {
                viewHolder.itemView.scrollTo(mCurrentScrollX - (int) dX, 0);
            } else {

                if (mFirstInactive) {
                    mFirstInactive = false;
                    mCurrentScrollXWhenInactive = viewHolder.itemView.getScrollX();
                    mInitXWhenInactive = dX;
                }

                if (viewHolder.itemView.getScrollX() >= mDefaultScrollX) {
                    viewHolder.itemView.scrollTo(mDefaultScrollX, 0);
                } else {

                    // 这里只能做距离的比例缩放，因为回到最初位置必须得从当前位置开始，dx不一定与ItemView的滑动距离相等
                    viewHolder.itemView.scrollTo((int) (mCurrentScrollXWhenInactive * dX / mInitXWhenInactive), 0);

                }

            }
        }else {
            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
        }


    }


    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (viewHolder.itemView.getScrollX() >= mDefaultScrollX) {
            viewHolder.itemView.scrollTo(mDefaultScrollX, 0);

        } else if (viewHolder.itemView.getScrollX() < 0) {

            viewHolder.itemView.scrollTo(0, 0);
        }

    }
}
