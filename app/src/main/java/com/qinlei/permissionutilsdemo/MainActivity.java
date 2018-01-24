package com.qinlei.permissionutilsdemo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qinlei.permissionutils.PermissionUtils;
import com.qinlei.permissionutils.RequestPermissionSuccess;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void requestOnePermission(View view) {
        PermissionUtils.getInstance()
                .with(this)
                .withPermission(Manifest.permission.CAMERA)
                .withDenyTip("我们需要该权限")//第一次拒绝的提示文字
                .todo(new RequestPermissionSuccess() {
                    @Override
                    public void todo() {
                        Toast.makeText(MainActivity.this, "我获取到了这个权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void requestMutiPermission(View view) {
        String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_CALENDAR};
        PermissionUtils.getInstance()
                .with(this)
                .withPermission(perms)
                .withDenyTip("我们需要这两个权限")//第一次拒绝的提示文字
                .todo(new RequestPermissionSuccess() {
                    @Override
                    public void todo() {
                        Toast.makeText(MainActivity.this, "我已经获取到这两权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
