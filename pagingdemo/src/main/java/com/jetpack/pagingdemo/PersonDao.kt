package com.jetpack.pagingdemo

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * @author Create by yc.li09 on 2018/11/20.
 */
@Dao
interface PersonDao {
    @Query("SELECT * FROM Person ORDER BY name COLLATE NOCASE ASC")
    fun getAllPerson(): DataSource.Factory<Int, Person>

    @Insert
    fun insert(students: List<Person>)

    @Insert
    fun insert(student: Person)
}