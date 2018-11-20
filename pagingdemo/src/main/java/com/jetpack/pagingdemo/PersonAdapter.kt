package com.jetpack.pagingdemo

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup

/**
 * @author Create by yc.li09 on 2018/11/20.
 */
class PersonAdapter : PagedListAdapter<Person, PersonViewHolder>(diffCallback) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PersonViewHolder {
        return PersonViewHolder(p0)
    }


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem == newItem
        }
    }
}