package com.coronavirus.android.activity;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class MyThread implements Runnable {

    @Override
    public void run() {
        saveTempBitmap(MainActivity.shotWeb);
    }

    public void saveTempBitmap(Bitmap bitmap) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap);
        }else{
            Toast.makeText(null,"存储空间不能写入",Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImage(Bitmap finalBitmap) {
        String dir= Environment.getExternalStorageDirectory().getAbsolutePath()+"/webshot";
        String fileName= UUID.randomUUID().toString();
        try {
            File file=new File(dir);
            if (!file.exists()){
                boolean mkdir=file.mkdir();
                if (mkdir)
                    Log.d(TAG, "saveImage: 创建成功");
                else Log.d(TAG, "saveImage: 创建失败");
            }
            FileOutputStream out = new FileOutputStream(dir+"/"+fileName+".jpg");
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
            out.close();
//            Uri uri=Uri.fromFile(file1);
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE), String.valueOf(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Toast.makeText(this,"saveOK",Toast.LENGTH_SHORT).show();
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
//
//        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, new String[]{file.getName()}, null);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
