package com.fanyafeng.barcode.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.fanyafeng.barcode.R;
import com.fanyafeng.barcode.BaseActivity;
import com.fanyafeng.barcode.util.QRCode;

//需要搭配baseactivity，这里默认为baseactivity,并且默认Baseactivity为包名的根目录
public class MainActivity extends BaseActivity {
    private ImageView ivBarCode;
    private ImageView ivLogoBarCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.app_name);
        isSetNavigationIcon = false;

        initView();
        initData();
    }


    //初始化UI空间
    private void initView() {
        ivBarCode = (ImageView) findViewById(R.id.ivBarCode);
        ivLogoBarCode = (ImageView) findViewById(R.id.ivLogoBarCode);
    }

    //初始化数据
    private void initData() {
        Bitmap bitmap = QRCode.createQRCode("樊亚风", 500);
        ivBarCode.setImageBitmap(bitmap);

        Bitmap bitmap1 = QRCode.createQRCodeWithLogo("http://news.ifeng.com/a/20161201/50345907_0.shtml", 500, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        ivLogoBarCode.setImageBitmap(bitmap1);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnScanCode:
                startActivity(new Intent(this, ScanCodeActivity.class));
                break;
        }
    }
}
