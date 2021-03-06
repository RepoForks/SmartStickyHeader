package com.salman.zach.stickyheader.headerView.animator;

import android.view.View;

import com.salman.zach.stickyheader.headerView.SmartHeaderUtility;

/**
 * Created by salmanZach on 21/4/16.
 */

public abstract class SmartHeaderAnimator {

    protected View mHeader;
    private int mMinHeightHeader;
    private int mHeightHeader;
    private int mMaxHeaderTranslation;

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    public abstract void onScroll(final int scrolledY);


    public void setupAnimator(final View header) {
        this.mHeader = header;

        onAnimatorAttached();

        SmartHeaderUtility.executeOnGlobalLayout(mHeader, new Runnable() {
            @Override
            public void run() {
                onAnimatorReady();
            }
        });
    }

    /**
     * Called after that the animator is attached to the header
     */
    protected abstract void onAnimatorAttached();

    /**
     * Called after that the animator is attached and the header measured
     */
    protected abstract void onAnimatorReady();

    public void setHeightHeader(int heightHeader, int minHeightHeader) {
        mHeightHeader = heightHeader;
        mMinHeightHeader = minHeightHeader;
        calculateMaxTranslation();
    }

    private void calculateMaxTranslation() {
        mMaxHeaderTranslation = mMinHeightHeader - mHeightHeader;
    }

    public View getHeader() {
        return mHeader;
    }

    public int getMinHeightHeader() {
        return mMinHeightHeader;
    }

    public int getHeightHeader() {
        return mHeightHeader;
    }

    public int getMaxTranslation() {
        return mMaxHeaderTranslation;
    }
}
