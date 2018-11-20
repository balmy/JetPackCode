package com.jetpack.pagingdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author Create by yc.li09 on 2018/11/20.
 */
class PersonViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        android.R.layout.simple_list_item_1, parent, false
    )) {

    private val nameView = itemView.findViewById<TextView>(android.R.id.text1)
    private var person: Person? = null

    fun bindTo(person: Person?) {
        this.person = person
        nameView.text = person?.name
    }

}