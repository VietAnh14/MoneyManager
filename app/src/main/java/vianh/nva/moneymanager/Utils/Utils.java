package vianh.nva.moneymanager.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Field;

public class Utils {
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
