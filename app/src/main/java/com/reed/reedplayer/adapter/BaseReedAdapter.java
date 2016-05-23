package com.reed.reedplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.presenter.ViewGroupPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkreed on 16/5/4.
 */
public abstract class BaseReedAdapter extends RecyclerView.Adapter<BaseReedAdapter.ViewHolder> {

  private List<Model> mDataList = new ArrayList<>();

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(createViewGroupPresenter(parent, parent.getContext(), viewType));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Model model = mDataList.get(position);
    holder.presenter.holder = holder;
    holder.presenter.bind(model);
  }

  @Override
  public int getItemCount() {
    return mDataList.size();
  }

  public void setDataList(List<Model> dataList) {
    mDataList = dataList;
    notifyDataSetChanged();
  }

  public void appendData(Model item) {
    mDataList.add(item);
    notifyItemInserted(getItemCount());
  }

  public List<Model> getDataList() {
    return mDataList;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public final ViewGroupPresenter presenter;

    public ViewHolder(ViewGroupPresenter viewGroupPresenter) {
      super(viewGroupPresenter.rootView);
      presenter = viewGroupPresenter;
    }
  }

  protected abstract ViewGroupPresenter createViewGroupPresenter(ViewGroup parent, Context context,
      int viewType);
}
