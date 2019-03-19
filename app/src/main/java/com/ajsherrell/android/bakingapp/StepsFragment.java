package com.ajsherrell.android.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajsherrell.android.bakingapp.Models.Steps;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StepsFragment extends Fragment {

    public static final String STEPS_KEY = "step_key";
    private static final String POSITION_KEY = "position_key";
    private static final String PLAY_WHEN_READY_KEY = "play_when_ready_key";

    private long currentPosition = 0;
    private boolean playWhenReady = true;
    
    @BindView(R.id.steps_tv)
    TextView stepsTv;

    @BindView(R.id.exo_player)
    SimpleExoPlayerView exoPlayerView;

    @BindView(R.id.step_thumbnail)
    ImageView stepThumbnail;

    private Steps steps;
    private Unbinder unbinder;
    private SimpleExoPlayer exoPlayer;

    public StepsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(STEPS_KEY)) {
            steps = getArguments().getParcelable(STEPS_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        if (savedInstanceState != null && savedInstanceState.containsKey(POSITION_KEY)) {
            currentPosition = savedInstanceState.getLong(POSITION_KEY);
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY_KEY);
        }

        unbinder = ButterKnife.bind(this, view);

        stepsTv.setText(steps.getDescription());

        // check if thumbnail exists
        // used resource: https://guides.codepath.com/android/Displaying-Images-with-the-Glide-Library
        if (!TextUtils.isEmpty(steps.getThumbnailUrl())) {
            GlideApp.with(this)
                    .load(steps.getThumbnailUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new CircleCrop())
                    .placeholder(R.drawable.ic_action_name)
                    .error(R.drawable.ic_action_name)
                    .into(stepThumbnail);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(steps.getVideoUrl())) {
            startPlay(Uri.parse(steps.getVideoUrl()));
        } else {
            stepsTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(POSITION_KEY, currentPosition);
        outState.putBoolean(PLAY_WHEN_READY_KEY, playWhenReady);
    }

    private void startPlay(Uri mediaUri) {
        if (exoPlayer == null) {
            // default track selector
            DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory factory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(factory);

            //player
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

            // bind them
            exoPlayerView.setPlayer(exoPlayer);

            // used resource: https://google.github.io/ExoPlayer/guide.html
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                    Util.getUserAgent(getContext(), getString(R.string.app_name)), bandwidthMeter);
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);
            // Prepare the player with the source.
            exoPlayer.prepare(videoSource);

            // on rotation, etc
            if (currentPosition != 0) {
                exoPlayer.seekTo(currentPosition);
            }
            exoPlayer.setPlayWhenReady(playWhenReady);
            exoPlayerView.setVisibility(View.VISIBLE);
        }
    }

    // release the player
    private void releasePlayer() {
        if (exoPlayer != null) {
            playWhenReady = exoPlayer.getPlayWhenReady();
            currentPosition = exoPlayer.getCurrentPosition();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
