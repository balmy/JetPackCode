package com.jetpack.pagingdemo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Create by yc.li09 on 2018/11/20.
 */

@Entity
data class Person(@PrimaryKey(autoGenerate = true) val id: Int,
                  val name: String)