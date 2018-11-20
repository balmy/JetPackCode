package com.jetpack.pagingdemo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

/**
 * @author Create by yc.li09 on 2018/11/20.
 */
class RoomViewModel(app: Application) : AndroidViewModel(app) {

    companion object {

        private const val PAGE_SIZE = 15

        private const val ENABLE_PLACEHOLDERS = false
    }

    private val dao = PersonDb.get(app).personDao()

    val allPerson = LivePagedListBuilder(dao.getAllPerson(), PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE).setEnablePlaceholders(ENABLE_PLACEHOLDERS)
        .setInitialLoadSizeHint(PAGE_SIZE).build()).build()


    fun insertStudent(name: String) {
        dao.insert(Person(id = 0, name = name))
    }

}