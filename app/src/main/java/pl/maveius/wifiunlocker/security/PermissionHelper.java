package pl.maveius.wifiunlocker.security;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

public class PermissionHelper {

    private String[] permissions = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    @TargetApi(Build.VERSION_CODES.M)
    public void askPermissions(Activity activity) {
        int requestCode = 200;
        activity.requestPermissions(permissions, requestCode);
    }


}
