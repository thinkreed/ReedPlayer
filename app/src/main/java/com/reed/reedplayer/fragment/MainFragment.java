package com.reed.reedplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reed.reedplayer.R;
import com.reed.reedplayer.activity.SongListActivity;

/**
 * Created by thinkreed on 16/5/23.
 */
public class MainFragment extends BaseFragment {

  public static MainFragment getInstance() {
    return new MainFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.findViewById(R.id.btn_local_songs).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SongListActivity.class);
        startActivity(intent);
      }
    });
  }
}
