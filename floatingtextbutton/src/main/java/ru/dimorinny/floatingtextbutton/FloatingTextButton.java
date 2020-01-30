package ru.dimorinny.floatingtextbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ru.dimorinny.floatingtextbutton.util.DimensionUtils;

public class FloatingTextButton extends FrameLayout {

    private CardView container;
    private ImageView leftIconView;
    private ImageView rightIconView;
    private TextView titleView;

    private String title;
    private int titleColor;
    private Drawable leftIcon;
    private Drawable rightIcon;
    private int background;

    public FloatingTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
        initAttributes(attrs);
        initView();
    }

    public void setTitle(String newTitle) {
        title = newTitle;

        if (newTitle == null || newTitle.isEmpty()) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
        }

        titleView.setText(newTitle);
    }

    public String getTitle() {
        return title;
    }

    public void setTitleColor(@ColorInt int color) {
        titleColor = color;
        titleView.setTextColor(color);
    }

    public @ColorInt int getTitleColor() {
        return titleColor;
    }

    public void setBackgroundColor(@ColorInt int color) {
        background = color;
        container.setCardBackgroundColor(color);
    }

    public @ColorInt int getBackgroundColor() {
        return background;
    }

    public void setLeftIconDrawable(Drawable drawable) {
        leftIcon = drawable;
        if (drawable != null) {
            leftIconView.setVisibility(VISIBLE);
            leftIconView.setImageDrawable(drawable);
        } else {
            leftIconView.setVisibility(GONE);
        }
    }

    public void setRightIconDrawable(Drawable drawable) {
        rightIcon = drawable;
        if (drawable != null) {
            rightIconView.setVisibility(VISIBLE);
            rightIconView.setImageDrawable(drawable);
        } else {
            rightIconView.setVisibility(GONE);
        }
    }

    public Drawable getLeftIconDrawable() {
        return leftIcon;
    }

    public Drawable getRightIconDrawable() {
        return rightIcon;
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        container.setOnClickListener(listener);
    }

    @Override
    public boolean hasOnClickListeners() {
        return container.hasOnClickListeners();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener listener) {
        container.setOnLongClickListener(listener);
    }

    private void inflateLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_floating_text_button, this, true);

        container = view.findViewById(R.id.layout_button_container);

        leftIconView = view.findViewById(R.id.layout_button_image_left);
        rightIconView = view.findViewById(R.id.layout_button_image_right);

        titleView = view.findViewById(R.id.layout_button_text);
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray styleable = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.FloatingTextButton,
                0,
                0
        );

        title = styleable.getString(R.styleable.FloatingTextButton_floating_title);
        titleColor = styleable.getColor(R.styleable.FloatingTextButton_floating_title_color, Color.BLACK);
        leftIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_left_icon);
        rightIcon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_right_icon);
        background = styleable.getColor(R.styleable.FloatingTextButton_floating_background_color, Color.WHITE);

        styleable.recycle();
    }

    private void initView() {
        setTitle(title);
        setTitleColor(titleColor);
        setLeftIconDrawable(leftIcon);
        setRightIconDrawable(rightIcon);
        setBackgroundColor(background);

        container.setContentPadding(
                getHorizontalPaddingValue(16),
                getVerticalPaddingValue(8),
                getHorizontalPaddingValue(16),
                getVerticalPaddingValue(8)
        );
        initViewRadius();
    }

    private void initViewRadius() {
        container.post(new Runnable() {
            @Override
            public void run() {
                container.setRadius(container.getHeight() / 2);
            }
        });
    }

    private int getVerticalPaddingValue(int dp) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return DimensionUtils.dpToPx(getContext(), dp) / 4;
        } else {
            return DimensionUtils.dpToPx(getContext(), dp);
        }
    }

    private int getHorizontalPaddingValue(int dp) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return DimensionUtils.dpToPx(getContext(), dp) / 2;
        } else {
            return DimensionUtils.dpToPx(getContext(), dp);
        }
    }
}
