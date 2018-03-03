package com.example.server.touchpull;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Server on 2018/2/27.
 */

public class RollImages extends Activity {

    private Context mContext;
    private ViewPager vp_roller;
    private List<ImageView> imageViewList = new ArrayList<>();
    private List<String> strImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_images);
        mContext = this;
        vp_roller = (ViewPager)findViewById(R.id.vp_roller);
        initImageLoader();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initImageUrls();
        vp_roller.setAdapter(new MyRollAdapter(mContext,strImages));
    }

    private void initImageUrls(){
        strImages.add("http://imglf5.nosdn.127.net/img/NmFFREpXZnNLZmloRmZSbGFZT1NYUlMxdWxUanRCc09IRW5tYmFHNkw2ajRYeHFKemtJNGR3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf5.nosdn.127.net/img/WEJTRnhkaWZlcXFRQ0h4ZGN1U29Lb1VleEpjV1RWcHJIbFE0dGM1Y1BJTkR3SXdoN2g2L0NnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf5.nosdn.127.net/img/aXd3SG4vNURoYWsxZmo2eW9aVnVGWHhNcURVaitzeWtYZlFFc1JXeFNxcWY1WnVPK0V1OS9RPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf3.nosdn.127.net/img/b0VaZ1haaEd4UnJoOXlXUHpOQno2WXhqOU42RkkrWlZ3cVBndERuakxJN25nbUpvSUhweldRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("https://img3.doubanio.com/view/group_topic/l/public/p105215492.webp");
        strImages.add("https://img3.doubanio.com/view/group_topic/large/public/p107341816.jpg");
        strImages.add("https://img3.doubanio.com/view/group_topic/l/public/p92527880.webp");
        strImages.add("https://img3.doubanio.com/view/group_topic/large/public/p107332692.jpg");
        strImages.add("https://img1.doubanio.com/view/group_topic/l/public/p40939567.webp");
        strImages.add("https://img3.doubanio.com/view/group_topic/l/public/p15910380.webp");
        strImages.add("https://img3.doubanio.com/view/group_topic/large/public/p106537730.jpg");
        strImages.add("https://img1.doubanio.com/view/group_topic/l/public/p55554519.webp");
        strImages.add("https://img3.doubanio.com/view/group_topic/l/public/p55554516.webp");
        strImages.add("https://img3.doubanio.com/view/group_topic/l/public/p44503460.webp");
        strImages.add("https://img1.doubanio.com/view/group_topic/l/public/p45926157.webp");
    }

    private void initImageLoader(){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 缓存最大图片大小
                .diskCacheExtraOptions(480, 800, null) // 闪存最大图片大小
                .threadPoolSize(3) // default 最大线程数
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 线程优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default 线程处理队列，先进先出
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) // LruMemory
                .memoryCacheSize(2 * 1024 * 1024) // 缓存
                .memoryCacheSizePercentage(13)    // default 缓存比例？
//                .diskCache(new UnlimitedDiskCache(cacheDir)) // default 闪存缓存
                .diskCacheSize(50 * 1024 * 1024) // 闪存缓存大小
                .diskCacheFileCount(100) // 闪存缓存图片文件数量
                //                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default 文件名
                .imageDownloader(new BaseImageDownloader(this)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs() // LOG
                .build();
        ImageLoader.getInstance().init(config);
    }

    class MyRollAdapter extends PagerAdapter{

        private Context _context;
        private List<String> strImageList;

        public MyRollAdapter(Context c, List<String> list){
            _context = c;
            strImageList = list;
            for (int i = 0; i < strImageList.size(); i++){
                ImageView iv = new ImageView(_context);
                ImageLoader.getInstance().displayImage(strImageList.get(i),iv);
                imageViewList.add(iv);
            }
        }


        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View)object;
        }

        //这个似乎是必须的，没加这个的话就会提示这里没有重写
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }
        //这里也是，不写的话滑动到队列尾就会崩溃
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }
    }
}
