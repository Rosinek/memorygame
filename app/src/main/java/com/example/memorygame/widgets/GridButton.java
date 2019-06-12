package com.example.memorygame.widgets;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.memorygame.R;
import com.example.memorygame.model.GameItem;
import com.example.memorygame.utils.ApiCompabilityUtility;


public class GridButton extends ConstraintLayout {

    private FrameLayout cardView;
    private FrameLayout cardviewChecked;
    private TextView textView;
    private ImageView mImage;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private GameItem gameItem = new GameItem();
    private GridButtonListener listener;


    private boolean mIsBackVisible = false;


    public GridButton(@NonNull Context context) {
        super(context);
    }

    public GridButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GridButton, 0, 0);
        Drawable valueIcon = a.getDrawable(R.styleable.GridButton_buttonIcon);
        boolean isRotateEnable = a.getBoolean(R.styleable.GridButton_buttonRotateEnable, false);
        @SuppressLint("ResourceAsColor") int backgroundColor = a.getColor(R.styleable.GridButton_buttonBackgroundColor, ApiCompabilityUtility.getColor(context, R.color.colorPrimary));
        @SuppressLint("ResourceAsColor") int textColor = a.getColor(R.styleable.GridButton_buttonTitleColor, ApiCompabilityUtility.getColor(context, R.color.colorAccent));
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.view_grid_button, this, true);
        }

        if (valueIcon != null) {
            ImageView imageView = findViewById(R.id.image);
            imageView.setImageDrawable(valueIcon);
        }

        textView = findViewById(R.id.tv_value);

        cardView = findViewById(R.id.card_front);
        cardviewChecked = findViewById(R.id.card_back);

        loadAnimations(context);
        if (isRotateEnable) setClick();
        changeCameraDistance();
    }


    public GridButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        cardView.setCameraDistance(scale);
        cardviewChecked.setCameraDistance(scale);
    }

    private void loadAnimations(Context context) {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_in_animation);
    }

    public void setChangeListener(GridButtonListener gridButtonListener) {
        this.listener = gridButtonListener;
    }

    private void setClick() {
        cardView.setOnClickListener(v -> {
            listener.onClickGridButton(this);
            if (!mIsBackVisible) {
                showBackSide();
            } else {
//                showFrontSide();
            }

        });
    }

    public void showFrontSide() {
        mSetRightOut.setTarget(cardviewChecked);
        mSetLeftIn.setTarget(cardView);
        mSetRightOut.start();
        mSetLeftIn.start();
        mIsBackVisible = false;
    }

    public void showBackSide() {
        mSetRightOut.setTarget(cardView);
        mSetLeftIn.setTarget(cardviewChecked);
        mSetRightOut.start();
        mSetLeftIn.start();
        mIsBackVisible = true;
    }

    public GameItem getItemValue() {
        return gameItem;
    }

    public void setItemValue(String value) {
        gameItem.setValue(value);
        textView.setText(value);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public boolean ismIsBackVisible() {
        return mIsBackVisible;
    }

}