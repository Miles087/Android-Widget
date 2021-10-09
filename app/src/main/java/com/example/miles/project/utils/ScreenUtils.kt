package com.example.miles.project.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.View
import android.widget.ListView
import android.widget.ScrollView
import java.io.*


/**
 * Description: 各种截图的方法
 * Author:      Created by Miles Wang
 * CreatedDate: 2021-10-09
 * Email:       icy-star@qq.com
 **/
class ScreenUtils {


    /**
     * 截取scrollview的屏幕
     * @param scrollView
     * @return
     */
    fun getBitmapByView(scrollView: ScrollView): Bitmap? {
        var h = 0
        var bitmap: Bitmap? = null
        // 获取scrollview实际高度
        for (i in 0 until scrollView.childCount) {
            h += scrollView.getChildAt(i).height
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"))
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.width, h,
                Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        scrollView.draw(canvas)
        return bitmap
    }

    /**
     * 截图listview
     */
    fun getListViewBitmap(listView: ListView, picpath: String?): Bitmap? {
        var h = 0
        val bitmap: Bitmap
        // 获取listView实际高度
        for (i in 0 until listView.getChildCount()) {
            h += listView.getChildAt(i).getHeight()
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(listView.getWidth(), h,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        listView.draw(canvas)
        return bitmap
    }


    /**
     * 压缩图片
     * @param image
     * @return
     */
    fun compressImage(image: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var options = 100
        // 循环判断如果压缩后图片是否大于250K,大于继续压缩
        while (baos.toByteArray().size / 1024 > 1024 && options > 10) {
            // 重置baos
            baos.reset()
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options, baos)
            // 每次都减少10
            options -= 10
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        val isBm = ByteArrayInputStream(baos.toByteArray())
        // 把ByteArrayInputStream数据生成图片
        return BitmapFactory.decodeStream(isBm, null, null)
    }

    /**
     * 保存到sdcard
     * @param b
     * @return
     */
    fun savePic(context: Context, b: Bitmap): String? {
        val outfile = File("/sdcard/image")
        // 如果文件不存在，则创建一个新文件
        if (!outfile.isDirectory()) {
            try {
                outfile.mkdir()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val fname: String = outfile.toString() + "/" + System.currentTimeMillis() + ".jpg"
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(fname)
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.JPEG, 90, fos)
                fos.flush()
                fos.close()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    outfile.getAbsolutePath(), fname, null)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        // 最后通知图库更新
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$fname")))
        return fname
    }


    /**
     * 截屏
     */
    fun activityShot(activity: Activity): Bitmap? {
        /*获取windows中最顶层的view*/
        val view: View = activity.window.decorView

        //允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true)
        view.buildDrawingCache()

        //获取状态栏高度
        val rect = Rect()
        view.getWindowVisibleDisplayFrame(rect)
        val statusBarHeight: Int = rect.top
        val windowManager = activity.windowManager

        //获取屏幕宽和高
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        val width = outMetrics.widthPixels
        val height = outMetrics.heightPixels

        //去掉状态栏
        val bitmap = Bitmap.createBitmap(
                view.getDrawingCache(), 0, statusBarHeight, width,
                height - statusBarHeight
        )
        //销毁缓存信息
        view.destroyDrawingCache()
        view.setDrawingCacheEnabled(false)
        return bitmap
    }
}