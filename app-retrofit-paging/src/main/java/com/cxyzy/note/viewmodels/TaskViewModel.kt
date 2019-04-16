package com.cxyzy.note.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cxyzy.note.network.HttpRepository
import com.cxyzy.note.network.bean.Task

class TaskViewModel : BaseViewModel() {
    private val tag = TaskViewModel::class.java.simpleName
    lateinit var taskList: LiveData<PagedList<Task>>

    fun delTask(id: Int, start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
//                    taskRepository.delTask(id)
                },
                {
                    Log.e(tag, "${it.message}")
                },
                { finally() },
                true)
    }

    /**
     * @param start 这个方法中可以显示加载进度条等
     * @param finally 可以隐藏进度条等
     */
    fun getTaskFromNetwork(start: () -> Unit, finally: () -> Unit) {
        launchOnUITryCatch(
                {
                    start()
                    taskList = HttpRepository.getTask()
                },
                {
                    Log.e(tag, "${it.message}")
                },
                { finally() },
                true)
    }
}