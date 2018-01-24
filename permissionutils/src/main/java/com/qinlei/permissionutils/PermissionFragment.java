package com.qinlei.permissionutils;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by qinlei
 * Created on 2018/1/4
 * Created description :
 */
public class PermissionFragment extends Fragment implements EasyPermissions.PermissionCallbacks {
    private final static int REQUEST_CODE = 9999999;

    private String[] requestPermissions = new String[]{};
    private String requestPermissionsTip = "";
    private RequestPermissionSuccess requestPermissionSuccess = null;

    public void setRequestPermissions(String[] requestPermissions) {
        this.requestPermissions = requestPermissions;
    }

    public void setRequestPermissionsTip(String requestPermissionsTip) {
        this.requestPermissionsTip = requestPermissionsTip;
    }

    public void setRequestPermissionSuccess(RequestPermissionSuccess requestPermissionSuccess) {
        this.requestPermissionSuccess = requestPermissionSuccess;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterPermissionGranted(REQUEST_CODE)
    public void requestPermission() {
        if (EasyPermissions.hasPermissions(getActivity(), requestPermissions)) {
            if (requestPermissionSuccess != null) {
                requestPermissionSuccess.todo();
            }
        } else {
            EasyPermissions.requestPermissions(this, requestPermissionsTip, REQUEST_CODE, requestPermissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle(R.string.permission_utils_title)
                    .setRationale(R.string.permission_utils_message)
                    .build()
                    .show();
        }
    }
}
