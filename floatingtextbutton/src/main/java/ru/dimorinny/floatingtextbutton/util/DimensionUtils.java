package ru.dimorinny.floatingtextbutton.util;

import android.content.Context;
import android.util.TypedValue;

public class DimensionUtils {

    public static int dpToPx(Context context, int dp) {
        return (int) TypedValue
                .applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        dp,
                        context.getResources().getDisplayMetrics()
                );
    }
}
