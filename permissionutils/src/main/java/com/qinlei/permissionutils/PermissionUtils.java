package com.qinlei.permissionutils;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by qinlei
 * Created on 2018/1/10
 * Created description :
 */

public class PermissionUtils {
    private static final String TAG = "PermissionUtils";

    private static final class PermissionUtilsHolder {
        private static final PermissionUtils instance = new PermissionUtils();
    }

    private PermissionUtils() {

    }

    public static PermissionUtils getInstance() {
        return PermissionUtilsHolder.instance;
    }

    private String[] requestPermissions;
    private String requestPermissionsTip;
    private PermissionFragment permissionFragment = null;

    private PermissionFragment getRxPermissionsFragment(Activity activity) {
        PermissionFragment rxPermissionsFragment = findRxPermissionsFragment(activity);
        boolean isNewInstance = rxPermissionsFragment == null;
        if (isNewInstance) {
            rxPermissionsFragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(rxPermissionsFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return rxPermissionsFragment;
    }

    private PermissionFragment findRxPermissionsFragment(Activity activity) {
        return (PermissionFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public PermissionUtils with(@NonNull Activity activity) {
        permissionFragment = getRxPermissionsFragment(activity);
        return this;
    }

    public PermissionUtils withPermission(String... args) {
        requestPermissions = args;
        return this;
    }

    public PermissionUtils withDenyTip(String tip) {
        requestPermissionsTip = tip;
        return this;
    }

    public void todo(RequestPermissionSuccess todo) {
        if (requestPermissions == null) {
            return;
        }
        if (TextUtils.isEmpty(requestPermissionsTip)) {
            return;
        }
        if (todo == null) {
            return;
        }
        permissionFragment.setRequestPermissions(requestPermissions);
        permissionFragment.setRequestPermissionsTip(requestPermissionsTip);
        permissionFragment.setRequestPermissionSuccess(todo);
        permissionFragment.requestPermission();
    }
}
