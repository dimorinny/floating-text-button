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
    private ImageView iconView;
    private TextView titleView;

    private String title;
    private int titleColor;
    private Drawable icon;
    private int background;
    private int horizontalPadding;
    private int verticalPadding;

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

    public void setTitleColor(@ColorInt int color) {
        titleView.setTextColor(color);
    }

    public void setBackgroundColor(@ColorInt int color) {
        container.setCardBackgroundColor(color);
    }

    public void setIconDrawable(Drawable drawable) {
        if (drawable != null) {
            iconView.setVisibility(VISIBLE);
            iconView.setImageDrawable(drawable);
        } else {
            iconView.setVisibility(GONE);
        }
    }

    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
        container.setContentPadding(
                getHorizontalPaddingValue(horizontalPadding),
                getVerticalPaddingValue(this.verticalPadding),
                getHorizontalPaddingValue(horizontalPadding),
                getVerticalPaddingValue(this.verticalPadding)
        );
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
        container.setContentPadding(
                getHorizontalPaddingValue(this.horizontalPadding),
                getVerticalPaddingValue(verticalPadding),
                getHorizontalPaddingValue(this.horizontalPadding),
                getVerticalPaddingValue(verticalPadding)
        );
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

        container = (CardView) view.findViewById(R.id.layout_button_container);
        iconView = (ImageView) view.findViewById(R.id.layout_button_image);
        titleView = (TextView) view.findViewById(R.id.layout_button_text);
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
        icon = styleable.getDrawable(R.styleable.FloatingTextButton_floating_icon);
        background = styleable.getColor(R.styleable.FloatingTextButton_floating_background_color, Color.WHITE);
        horizontalPadding = styleable.getInteger(R.styleable.FloatingTextButton_floating_vertical_padding, 8);
        verticalPadding = styleable.getInteger(R.styleable.FloatingTextButton_floating_horizontal_padding, 16);

        styleable.recycle();
    }

    private void initView() {
        setTitle(title);
        setTitleColor(titleColor);
        setIconDrawable(icon);
        setBackgroundColor(background);

        container.setContentPadding(
                getHorizontalPaddingValue(horizontalPadding),
                getVerticalPaddingValue(verticalPadding),
                getHorizontalPaddingValue(horizontalPadding),
                getVerticalPaddingValue(verticalPadding)
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
