package com.reed.reedplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reed.reedplayer.R;
import com.reed.reedplayer.adapter.SongListAdapter;
import com.reed.reedplayer.model.LocalSongsModelSource;

/**
 * Created by thinkreed on 16/5/23.
 */
public class SongListFragment extends BaseFragment {

  private SongListAdapter mAdapter;
  private RecyclerView mRecyclerView;

  public static SongListFragment getInstance() {
    return new SongListFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_list, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mAdapter = new SongListAdapter();
    LocalSongsModelSource localSongsModelSource = new LocalSongsModelSource();
    localSongsModelSource.registerObserver(mAdapter);
    mRecyclerView.setAdapter(mAdapter);
    localSongsModelSource.retriveData();
  }
}
