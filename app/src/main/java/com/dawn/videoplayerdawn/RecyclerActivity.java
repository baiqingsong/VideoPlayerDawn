package com.dawn.videoplayerdawn;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 90449 on 2017/7/14.
 */

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
