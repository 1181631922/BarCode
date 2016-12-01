package com.fanyafeng.barcode.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.barcode.R;
import com.fanyafeng.barcode.BaseActivity;
import com.fanyafeng.barcode.util.ImageUtil;
import com.fanyafeng.barcode.zxing.activity.CaptureActivity;
import com.fanyafeng.barcode.zxing.activity.CodeUtils;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class ScanCodeActivity extends BaseActivity {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    private TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_scan_code);

        initView();
        initData();
    }


    //初始化UI空间
    private void initView() {
        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    //初始化数据
    private void initData() {


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnScan:
                Intent intent = new Intent(getApplication(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    tvResult.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ScanCodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(ScanCodeActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                            tvResult.setText(result);
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(ScanCodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(this, "从设置页面返回...", Toast.LENGTH_SHORT).show();
        }
    }
}
