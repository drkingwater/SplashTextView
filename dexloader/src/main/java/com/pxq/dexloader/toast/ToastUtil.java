package com.pxq.dexloader.toast;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil implements IToast {
    @Override
    public void showToast(Context context) {
        Toast.makeText(context, "load dex remote", Toast.LENGTH_SHORT).show();
    }
}
