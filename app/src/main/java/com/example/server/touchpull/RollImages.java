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
        strImages.add("http://imglf.nosdn.127.net/img/MlhYMG4rQ0pYNGg3b3JrdDR4TWVlWjRRR3NoV1Z0UU9GSS9OZ29YcjFKa1JrQXhQMGpQVW1RPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg0JXQutCw0YLQtdGA0LjQvdCwIC8gY2F0aGVyaW5lMjMzMy5sb2Z0ZXIuY29t&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=340&dx=16&dy=20&stripmeta=0");
        strImages.add("http://imglf.nosdn.127.net/img/MlhYMG4rQ0pYNGg3b3JrdDR4TWVlY0IxTFhZQk8zS3dTeGpVRk1pWmlUekNia3UvakpDZGZnPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg0JXQutCw0YLQtdGA0LjQvdCwIC8gY2F0aGVyaW5lMjMzMy5sb2Z0ZXIuY29t&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=340&dx=16&dy=20&stripmeta=0");
        strImages.add("http://imglf2.nosdn.127.net/img/MlhYMG4rQ0pYNGg3b3JrdDR4TWVlUXBCUDluYkk2VnBLM3Z4d1VWZVBHUTMwWitRR2NuN3FRPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg0JXQutCw0YLQtdGA0LjQvdCwIC8gY2F0aGVyaW5lMjMzMy5sb2Z0ZXIuY29t&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=340&dx=16&dy=20&stripmeta=0");
        strImages.add("http://imglf1.ph.126.net/w9FN4IEljzoseH-NrvYU-Q==/6630209047118035414.jpg");
        strImages.add("http://imglf5.nosdn.127.net/img/WEJTRnhkaWZlcW9zVGFNa0hMZzB1enhwWVhoZlp2SzF1VG9zTmhuaVpKSU5jekxKTHNZNGt3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXIzNFZRYVFYaXVpSG1MVjFqcCt2aXo0QWdiZCtQcmI1SVJlR0xwZU5XVHFnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXFHZDBWcUJQeTgrNXBjTmhFbVM0RlF4ZTArSUdsSXpBYlNOcUdWS0Jqcm13PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXFQUlNzdUVkVEFvQ3lBTVVONTNzU3VvZ1YxcUpQY0o0RU9TUVJKQWN2VExRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXFDNmpBdnBTL3Y3TE9mQUNoLzh3QVQvaUFvVk1uRTJLcXltVlhRZ1ZrLzh3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXEzMm1WVEhqUjE5cUdIUC9aZ3Vac3VmMXpYN2tFTnpleDE4R3kvUUJ2ZzdRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcXJLYUY4NTRVeXhncnZMWXpGbXFtRWFOZTdQWVBiMGtjSGZlNURBdy9id2pnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXAvdkhBZ05QYUcyOTNhSjRKUmN4RWRMRldPTFVXcStLazVYYUgyQUJpWjN3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcXJDbUo0Y2FVUkozR21MZXhYT0VOSHNTWXVZVVRGYkxJMzVRVFRqK29UYldBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXJ1TkZTTkZYdFZISy8zMzFSQnQ0eTg4bitEbjFUdDZxNUZsTzVrcjE4YnlRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcXBaOXpGUVNxN2poSUl5MXN6UGNWUi9VZ09YdElhRHJBWkwzSTY0eDNaa2lnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXJoekpEemdKZ1I1NjVmT0hTaXpaTjdpcmwrcEt5eFk0dzR5RkNXODA5bURBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcW9wRGgzeFppOWtDNldPMUh0NGhGVy9EdHRLRFRUK2xyc09GbWpYRHRqRDdnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcW9EbVdwVmV5ZkxINStGRVZKeldocGF1V0IwamNBZkFlb1hFM0lSVlloWlFnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcXBCR05zT1NzSVJNNEJMQUthVXpqMDRjNnI4Y2xwVVE4T0VGZWdqK1laclZ3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcXJ3eXVTQTFoeDFWU2xUNThSVUJ2aWdWd3Y2MkhrOHFMeVdUdWdIL1lwNVNBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcXJkanUxTGdNdnREZmh2MmRJZzNtWXFCeVZ6eDdPbzJMVHNiOEIxZjMzNVRRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXBIVjVKck9CNENtMTk0QTdRV0NpSGs1UjFPRGpqTk8rMUJOTnJHUWVCWGdRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXBOK1lFemYyTDUyTnIwdTkxNkxiVlViOFd0czBaN1dnb0xlOWJVK29VZ1lBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXFac3I3VXI3b0RpRTRPU04xNlJ3ZFNTNjB4cWRSL2FyOWYrY2xPdDk3UmFnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXBKd1NUczJsSTBGSkFpZmR2L3RkekNaZ1dybzhDVGowdHRGSkJmWE9PUUd3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXJ2ay8zcnZqNE5WTWhOZ1htZ0VkQlBlMlE4TW1lYUpnN00rc3ZtcHZVc0RRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcXA0cW5jRVU1R2ZBTjRHd25SbmNaZHd4eEUra3hNZVE5SHJTeG9seGo3YU53PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXJ2MVZndjJaV3B6UURweVJtbTBldmtqVThmeTRvNkZnYmh1S2ZzVENuZUV3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcW9kL09WWjBMbXl5OTdLOUdhMjdwRmNFMGFpaDNEb3NUMXR0bnlOclN2aVZ3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXF2ZXhEY0Z0ZU5kejRuOUhzQzJQUlVCSWdWQUJEVFNIV3VJMmZBZHNHTzVBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcW9BM254NUtKcEw5RGxQOFo5aHhsQVc3YnZsUGp1V1pSblRFTzYxd1ZibW9RPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcXJ3TWUwK2FibmE2MDBWbHJydTVjLzQ4bElHdFZseCtYVkYwWGF1b3AwUmhnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXFTSHhsYnF3UFpiV3NzUEIxdG9YNTMzNmQyT1BTaEJMOVlPQ1VYbXFMM0hBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcXJtTjFFMXk4SUphUUFxUWlMeHlSUnYzeUxab0tSRnlqL2dBRnAxTTZUaHVnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf2.nosdn.127.net/img/WEJTRnhkaWZlcW9sR29uUGEvcUJpQndYUUNDcWhrZy9CS0ZpcHBRRksvTlRIQTVnVU9pVytBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf1.nosdn.127.net/img/WEJTRnhkaWZlcXFEbG5YQ3dlNzI1MzI4bXIxUzJpaUNFUW9iSnF4elVCd1dLODYvUGhnL1VBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcW83djdDb0NmaDdSU2t2NFlVd0VSL0RBdFd4VmxTMjNybUkwR2dkaTNhOWZnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcXJVSlRWQVlZYmJLN2F0ektMVnF1ZExmM1E5cS9LdUFLcUFvME5NTlhqdWlRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXBOSitaaDdVYm9RRUNnQ0Fja3FnNE1CSEZ1WWdNUGNKWW05NnFpN2MwVGtRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf.nosdn.127.net/img/WEJTRnhkaWZlcXB0MHpDTDI4ZDJ5TzVXQU5IOFhRaVJ2ZW1kVHkvRVNDS05oVk5tdllmRjZRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        strImages.add("http://imglf0.nosdn.127.net/img/WEJTRnhkaWZlcW9aQVJ5SHhCMlZUb3hTVlRQb3BPYWVHYkFQWUpNYTdxYjhCQW1CcjRXeDFBPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
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
