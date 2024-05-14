package com.me.guanpj.kotlinhub.auto;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;

import com.me.guanpj.kotlinhub.R;

import java.util.ArrayList;
import java.util.List;

public class AutoFixLayout extends ViewGroup {

    //每行显示item的数量
    private int mLineItemCount;
    //item水平间距
    private int mItemHorizontalSpacing;
    //item垂直间距
    private int mItemVerticalSpacing;

    //是否显示item分界线（垂直）
    private boolean mShowItemDividerLine;
    //是否适配水平外边距
    private boolean mAdaptHorizontalMargin;
    //是否适配水平内边距
    private boolean mAdaptHorizontalPadding;
    //是否显示外部分割线（空白）
    private boolean mShowOuterDividerLine;

    private boolean isAutoGoneView = false;
    private List<Rect> childrenBounds;

    // 分割线
    private View divider;
    public AutoFixLayout(Context context) {
        this(context, null);
    }

    public AutoFixLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFixLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray =
                getContext().obtainStyledAttributes(attrs, R.styleable.common_uilib_auto_fix_layout);
        mLineItemCount = typedArray.getInt(R.styleable.common_uilib_auto_fix_layout_common_uilib_line_item_count, 2);
        mItemVerticalSpacing = (int) typedArray.getDimension(R.styleable.common_uilib_auto_fix_layout_common_uilib_item_vertical_spacing,
                getResources().getDimension(R.dimen.layout_margin));
        mItemHorizontalSpacing = (int) typedArray.getDimension(R.styleable.common_uilib_auto_fix_layout_common_uilib_item_horizontal_spacing,
                getResources().getDimension(R.dimen.layout_margin));
        mShowItemDividerLine = typedArray.getBoolean(
                R.styleable.common_uilib_auto_fix_layout_common_uilib_item_divider_line, true);
        mAdaptHorizontalMargin = typedArray.getBoolean(
                R.styleable.common_uilib_auto_fix_layout_common_uilib_adapt_horizontal_margin, true);
        mAdaptHorizontalPadding = typedArray.getBoolean(
                R.styleable.common_uilib_auto_fix_layout_common_uilib_adapt_horizontal_padding, true);
        mShowOuterDividerLine = typedArray.getBoolean(
                R.styleable.common_uilib_auto_fix_layout_common_uilib_outer_divider_line, true);
        typedArray.recycle();
        init();
    }

    private void init() {
        childrenBounds = new ArrayList<>();
        // 创建分割线
        divider = new View(getContext());
        divider.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.material_red_500));
        /*if (mShowItemDividerLine) {
            addView(divider, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        }*/

        // 根据mAdaptHorizontalPadding的值来决定是否自动添加AutoFixLayout的内边距
        if (mAdaptHorizontalPadding) {
            int leftPadding = (int) getResources().getDimension(R.dimen.common_uilib_padding_start);
            int rightPadding = (int) getResources().getDimension(R.dimen.common_uilib_padding_start);
            setPadding(getPaddingLeft() + leftPadding, getPaddingTop(), getPaddingRight() + rightPadding, getPaddingBottom());
        }

        /*if (mAdaptHorizontalMargin) {
            int leftMargin = (int) getResources().getDimension(R.dimen.common_uilib_margin_start);
            int rightMargin = (int) getResources().getDimension(R.dimen.common_uilib_margin_end);
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(getLayoutParams());
            layoutParams.setMargins(leftMargin, layoutParams.topMargin, rightMargin, layoutParams.bottomMargin);
            setLayoutParams(layoutParams);
        }*/
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        /*if (mAdaptHorizontalMargin) {
            int leftMargin = (int) getResources().getDimension(R.dimen.common_uilib_margin_start);
            int rightMargin = (int) getResources().getDimension(R.dimen.common_uilib_margin_end);
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(getLayoutParams());
            layoutParams.setMargins(leftMargin, layoutParams.topMargin, rightMargin, layoutParams.bottomMargin);
            setLayoutParams(layoutParams);
        }*/
    }

    private void autoGoneView() {
        if (isAutoGoneView) {
            return;
        }

        boolean isGone = true;
        for (int i = 0; i < getChildCount(); i++) {
            ICommViewMethod view = (ICommViewMethod)getChildAt(i);
            if (view.isFunctionVisible()) {
                isGone = false;
                break;
            }
        }
        setVisibility(isGone ? View.GONE : View.VISIBLE);
        isAutoGoneView = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        autoGoneView();

        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidthUsed = 0;
        int lineMaxHeight = 0;
        int specWidthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int specWidthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int childCountInLine = 0;

        for (int index = 0; index < getChildCount(); index++) {
            View child = getChildAt(index);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            if (specWidthMode != View.MeasureSpec.UNSPECIFIED &&
                    (lineWidthUsed + child.getMeasuredWidth() + mItemHorizontalSpacing > specWidthSize || childCountInLine >= mLineItemCount)) {
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight + mItemVerticalSpacing;
                lineMaxHeight = 0;
                childCountInLine = 0;
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }

            if (index >= childrenBounds.size()) {
                childrenBounds.add(new Rect());
            }
            Rect childBounds = childrenBounds.get(index);
            childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());

            lineWidthUsed += child.getMeasuredWidth() + mItemHorizontalSpacing;
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
            childCountInLine++;
        }

        int selfWidth = widthUsed;
        int selfHeight = heightUsed + lineMaxHeight;
        setMeasuredDimension(selfWidth, selfHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        for (int index = 0; index < getChildCount(); index++) {
            View child = getChildAt(index);
            Rect childBounds = childrenBounds.get(index);
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
            curL += child.getMeasuredWidth() + mItemHorizontalSpacing;
            if ((index + 1) % mLineItemCount == 0) {
                curT += child.getMeasuredHeight() + mItemVerticalSpacing;
                curL = getPaddingLeft();
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}