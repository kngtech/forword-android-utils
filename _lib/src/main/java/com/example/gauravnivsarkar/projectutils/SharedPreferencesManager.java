package com.example.gauravnivsarkar.projectutils;


/**
 * Created by workwellness on 8/29/15.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


public final class SharedPreferencesManager {

    private static final String SHARED_PREFS_NAME = "workwellness";
    private static SharedPreferences sharedPreferences;

    private SharedPreferencesManager() {
    }

    /**
     * Write string to shared preferences.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     * @return true if value was written successfully
     */
    public static boolean writeString(@NonNull Context context, String key, String value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putString(key, value).apply();
        return true;
    }

    /**
     * Write int to shared preferences.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     * @return true if value was written successfully
     */
    public static boolean writeInt(@NonNull Context context, String key, int value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putInt(key, value).apply();
        return true;
    }

    /**
     * Write boolean to shared preferences.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     * @return true if value was written successfully
     */
    public static boolean writeBoolean(@NonNull Context context, String key, boolean value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putBoolean(key, value).apply();
        return true;
    }

    /**
     * Write long to shared preferences.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     * @return true if value was written successfully
     */
    public static boolean writeLong(@NonNull Context context, String key, long value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putLong(key, value).apply();
        return true;
    }

    /**
     * Write float to shared preferences.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     * @return true if value was written successfully
     */
    public static boolean writeFloat(@NonNull Context context, String key, float value) {
        if (context == null) {
            return false;
        }
        getSharedPreferencesEditor(context).putFloat(key, value).apply();
        return true;
    }

    /**
     * Get string to shared preferences.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return string for this key, or defaultValue if not found
     */
    public static String getString(@NonNull Context context, String key, String defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    /**
     * Get int to shared preferences.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return int for this key, or defaultValue if not found
     */
    public static int getInt(@NonNull Context context, String key, int defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    /**
     * Get long to shared preferences.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return long for this key, or defaultValue if not found
     */
    public static long getLong(@NonNull Context context, String key, long defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getLong(key, defaultValue);
    }

    /**
     * Get float to shared preferences.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return float for this key, or defaultValue if not found
     */
    public static float getFloat(@NonNull Context context, String key, float defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getFloat(key, defaultValue);
    }

    /**
     * Get boolean to shared preferences.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return boolean for this key, or defaultValue if not found
     */
    public static boolean getBoolean(@NonNull Context context, String key, boolean defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * Gets shared preferences for name defined in resources
     * Preferences are encrypted for release builds using
     * https://github.com/scottyab/secure-preferences
     * and plain text for debug builds
     *
     * @param context the context
     * @return {@link SharedPreferences}
     */
    private static SharedPreferences getSharedPreferences(@NonNull Context context) {

        if (context == null) {
            return null;
        }

        if (sharedPreferences == null) {
            {
                String name=context.getResources().getString(R.string.prefs_name);
                sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            }
        }
        return sharedPreferences;
    }

    /**
     * Gets shared preferences editor.
     *
     * @param context the context
     * @return the shared preferences editor
     */
    public static SharedPreferences.Editor getSharedPreferencesEditor(@NonNull Context context) {

        if (context == null) {
            return null;
        }
        return getSharedPreferences(context).edit();
    }

}
