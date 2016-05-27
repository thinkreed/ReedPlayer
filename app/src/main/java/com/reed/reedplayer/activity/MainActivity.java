package com.reed.reedplayer.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.MainFragment;

public class MainActivity extends BaseActivity {

  private static final int REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    checkApiVersionAndPermission();
  }

  private void checkApiVersionAndPermission() {
    int currentVersion = android.os.Build.VERSION.SDK_INT;
    if (currentVersion >= Build.VERSION_CODES.M) {
      if (ActivityCompat.checkSelfPermission(this,
          Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
            REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE);
      }
    }
  }

  @Override
  protected BaseFragment getFragment() {
    return MainFragment.getInstance();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case REQUEST_CODE_FOR_READ_EXTERNAL_STORAGE:
        if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
          // TODO: 16/5/26 not decide yet....request again or close the application
        }
        break;
      default:
        break;
    }
  }
}
