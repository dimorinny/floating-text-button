package ru.dimorinny.floatingtextbutton.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class ScrollBehavior extends CoordinatorLayout.Behavior<FloatingTextButton> {


    private static final Long HIDE_DURATION = 250L;
    private static final float TRANSLATION_HIDE = 500f;
    private static final float TRANSLATION_SHOW = 0f;
    private ViewPropertyAnimatorCompat animation = null;
    private static final Interpolator HIDE_INTERPOLATOR = new FastOutSlowInInterpolator();

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingTextButton child, View target, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onNestedScroll(coordinatorLayout, child, target, scrollX, scrollY, oldScrollX, oldScrollY);

        if (scrollY > 0) {

            hide(child);
        } else if (scrollY < 0) {
            show(child);
        }
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingTextButton child, View directTargetChild, View target, int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    private void hide(FloatingTextButton floatingTextButton) {

        animation = ViewCompat.animate(floatingTextButton)
                .translationY(TRANSLATION_HIDE)
                .setDuration(HIDE_DURATION);
        animation.start();
    }

    private void show(FloatingTextButton floatingTextButton) {

        animation = ViewCompat.animate(floatingTextButton)
                .translationY(TRANSLATION_SHOW)
                .setDuration(HIDE_DURATION);
        animation.start();
    }

    @Override
    public boolean layoutDependsOn(
            CoordinatorLayout parent,
            FloatingTextButton child,
            View dependency
    ) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(
            CoordinatorLayout parent,
            FloatingTextButton child,
            View dependency
    ) {
        if (child.getTranslationY() > 0) {
            return true;
        }
        if (animation != null) {
            animation.cancel();
            animation = null;
        }

        child.setTranslationY(
                Math.min(60f, dependency.getTranslationY() - dependency.getHeight())
        );
        return true;
    }

    @Override
    public void onDependentViewRemoved(
            CoordinatorLayout parent,
            FloatingTextButton child,
            View dependency
    ) {
        if (dependency instanceof Snackbar.SnackbarLayout) {

            animation = ViewCompat.animate(child)
                    .translationY(0f)
                    .setInterpolator(HIDE_INTERPOLATOR)
                    .setDuration(HIDE_DURATION);

            animation.start();
        }
        super.onDependentViewRemoved(parent, child, dependency);
    }
}