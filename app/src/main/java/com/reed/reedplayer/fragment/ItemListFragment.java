package com.reed.reedplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reed.reedplayer.R;
import com.reed.reedplayer.adapter.ItemListAdapter;
import com.reed.reedplayer.component.ModelSourceFactory;
import com.reed.reedplayer.model.BaseModelSource;
import com.reed.reedplayer.utils.Consts;
import com.reed.reedplayer.utils.SourceType;

/**
 * Created by thinkreed on 16/5/23.
 */
public class ItemListFragment extends BaseFragment {

  public static ItemListFragment getInstance(Bundle bundle) {
    ItemListFragment fragment = new ItemListFragment();
    fragment.setArguments(bundle);
    return fragment;
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
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    ItemListAdapter adapter = new ItemListAdapter();
    BaseModelSource modelSource = getModelSource();
    modelSource.registerObserver(adapter);
    recyclerView.setAdapter(adapter);
    modelSource.retriveData();
  }

  private BaseModelSource getModelSource() {
    Bundle bundle = getArguments();
    return ModelSourceFactory
        .createModelSource(SourceType.values()[bundle.getInt(Consts.INSTANCE.getKEY_TYPE())]);
  }

}
