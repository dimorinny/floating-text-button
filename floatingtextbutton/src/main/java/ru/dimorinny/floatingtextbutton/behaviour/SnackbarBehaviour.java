package ru.dimorinny.floatingtextbutton.behaviour;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class SnackbarBehaviour extends CoordinatorLayout.Behavior<FloatingTextButton> {

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

        child.setTranslationY(
                Math.min(0f, dependency.getTranslationY() - dependency.getHeight())
        );
        return true;
    }
}