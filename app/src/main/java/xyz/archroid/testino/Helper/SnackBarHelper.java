package xyz.archroid.testino.Helper;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import xyz.archroid.testino.R;

public class SnackBarHelper {

    public SnackBarHelper() {
    }
    public static void info(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.white));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.blue));
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }
    public static void warning(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.white));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.yellow));
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }
    public static void alert(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.white));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.red));
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }
    public static void message(Context context , CoordinatorLayout coordinatorLayout , String text ){
        Snackbar snackbar = Snackbar.make(coordinatorLayout , text , BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setTextColor(context.getResources().getColor(R.color.white));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.green));
        ViewCompat.setLayoutDirection(snackbar.getView(),ViewCompat.LAYOUT_DIRECTION_RTL);
        snackbar.show();
    }
}