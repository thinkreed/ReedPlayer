package com.reed.reedplayer.adapter

import android.content.Context
import android.view.ViewGroup

import com.reed.reedplayer.R
import com.reed.reedplayer.ReedApplication
import com.reed.reedplayer.model.Model
import com.reed.reedplayer.presenter.DisplayPresenter
import com.reed.reedplayer.presenter.MotionPresenter
import com.reed.reedplayer.presenter.ViewGroupPresenter

/**
 * Created by thinkreed on 16/5/23.
 */
class ItemListAdapter : BaseReedAdapter() {
    override fun createViewGroupPresenter(parent: ViewGroup, context: Context,
                                          viewType: Int): ViewGroupPresenter? {
        when (Model.Templete.values()[viewType]) {
            Model.Templete.ITEM_SONG -> return ViewGroupPresenter(parent, context, R.layout.item_song_info)
                    .add(DisplayPresenter(), R.id.title)
                    .add(DisplayPresenter(), R.id.cover)
                    .add(MotionPresenter(), 0)
                    .add(DisplayPresenter(), R.id.artist)
            else -> return null
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].templete.ordinal
    }

    override fun onDataRetrived(models: List<Model>) {
        super.onDataRetrived(models)
        ReedApplication.getInstance().queueManager.setPlaylist(models)
    }
}
