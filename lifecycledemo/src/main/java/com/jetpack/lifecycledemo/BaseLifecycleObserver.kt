package com.jetpack.lifecycledemo



import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log


/**
 * @author Create by yc.li09 on 2018/11/13.
 */
class BaseLifecycleObserver : LifecycleObserver {

    companion object {
        private val TAG = "MyListener"
    }

    private var lifecycle: Lifecycle? = null

    fun setLifecycle(lifecycle: Lifecycle) {
        this.lifecycle = lifecycle
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        Log.d(TAG, "onCreate current state: " + lifecycle?.currentState?.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.d(TAG, "onStart current state: " + lifecycle?.currentState?.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.d(TAG, "onResume current state: " + lifecycle?.currentState?.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.d(TAG, "onPause current state: " + lifecycle?.currentState?.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.d(TAG, "onStop current state: " + lifecycle?.currentState?.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        Log.d(TAG, "onDestroy current state: " + lifecycle?.currentState?.name)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun any() {
        Log.d(TAG, "any current state: " + lifecycle?.currentState?.name)
    }
}
/**
 * java 8 android 7.0 +
 */
//class BaseLifecycleObserver : DefaultLifecycleObserver {
//
//    companion object {
//        private val TAG = "MyListener"
//    }
//
//    fun setLifecycle(lifecycle: Lifecycle) {
//        lifecycle.addObserver(this)
//    }
//
//    override fun onCreate(owner: LifecycleOwner) {
//        Log.d(TAG, "onCreate current state: " + owner.lifecycle.currentState.name)
//    }
//
//    override fun onResume(owner: LifecycleOwner) {
//        Log.d(TAG, "onResume current state: " + owner.lifecycle.currentState.name)
//    }
//
//    override fun onPause(owner: LifecycleOwner) {
//        Log.d(TAG, "onPause current state: " + owner.lifecycle.currentState.name)
//    }
//
//    override fun onStart(owner: LifecycleOwner) {
//        Log.d(TAG, "onStart current state: " + owner.lifecycle.currentState.name)
//    }
//
//    override fun onStop(owner: LifecycleOwner) {
//        Log.d(TAG, "onStop current state: " + owner.lifecycle.currentState.name)
//    }
//
//    override fun onDestroy(owner: LifecycleOwner) {
//        Log.d(TAG, "onDestroy current state: " + owner.lifecycle.currentState.name)
//    }
//}
