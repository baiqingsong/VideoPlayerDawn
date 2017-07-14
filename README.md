# 视频播放使用

* [引用](#引用)
* [使用](#使用)
* [参考地址](#参考地址)


## 引用
build.gradle中添加
```
dependencies {
    compile 'fm.jiecao:jiecaovideoplayer:5.6'
    compile 'com.squareup.picasso:picasso:2.5.1'
}
```

## 使用
xml调用控件：
```
<fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
    android:id="@+id/jc_video_player"
    android:layout_width="match_parent"
    android:layout_height="200dp" />
```

Java代码中设置：
```
mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "假人");
Picasso.with(this)
        .load("https://www.baidu.com/img/bd_logo1.png")
        .into(mJcVideoPlayerStandard.thumbImageView);
```
JCVideoPlayerStandard的setUp方法设置视频地址，展示样式，和名字
通过JCVideoPlayerStandard的thumbImageView控件展示视频初始图片（Picasso展示）

如果调用全屏播放：
```
JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, 
    "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "full screen");
```
直接调用startFullscreen方法即可

如果在recycler中调用：
```
public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private String[] videoUrls = new String[]{
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4",
            "http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
    };
    private String[] videoThumbs = new String[]{
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png",
            "https://www.baidu.com/img/bd_logo1.png"
    };
    private String[] videoTitles = new String[]{
            "假人1",
            "假人2",
            "假人3",
            "假人4",
            "假人5",
            "假人6",
            "假人7",
            "假人8",
            "假人9",
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new VideoRecyclerAdapter(RecyclerActivity.this));
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder>  {
        private Context mContext;

        public VideoRecyclerAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            VideoViewHolder holder = new VideoViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.item_video_recycler, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(VideoViewHolder holder, int position) {
            holder.jcVideoPlayer.setUp(videoUrls[position]
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoTitles[position]);
            Picasso.with(mContext)
                    .load(videoThumbs[position])
                    .into(holder.jcVideoPlayer.thumbImageView);
        }

        @Override
        public int getItemCount() {
            return videoUrls.length;
        }

        class VideoViewHolder extends RecyclerView.ViewHolder {
            JCVideoPlayerStandard jcVideoPlayer;

            public VideoViewHolder(View itemView) {
                super(itemView);
                jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.jc_video_player);
            }
        }
    }
}

```

**注：在返回时需要做相应处理**
```
@Override
public void onBackPressed() {
    if (JCVideoPlayer.backPress()) {
        return;
    }
    super.onBackPressed();
}
@Override
protected void onPause() {
    super.onPause();
    JCVideoPlayer.releaseAllVideos();
}
```


## 参考地址

[https://github.com/lipangit/JieCaoVideoPlayer](https://github.com/lipangit/JieCaoVideoPlayer "参考地址")
