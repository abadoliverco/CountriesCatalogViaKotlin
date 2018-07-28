package com.oliver.countriescatalogviakotlin.mvp.base

import android.content.Context
import android.support.annotation.CallSuper
import com.oliver.countriescatalogviakotlin.CountriesCatalogApplication
import com.oliver.countriescatalogviakotlin.data.error.ServiceError
import com.oliver.countriescatalogviakotlin.di.component.ApplicationComponent
import rx.Observable
import rx.SingleSubscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by oliverabad on 23/7/18.
 */
abstract class BasePresenter<T : MvpView> (var context: Context) : Presenter<T> {

    private var mvpView: T? = null
    private var injectContainer: BasePresenterInjection
    private var subscriptions: MutableList<Subscription>? = mutableListOf()

    init {
        this.context = context
        this.injectContainer = BasePresenterInjection()

    }

    fun getInjectionContainer() : BasePresenterInjection {
        return injectContainer
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return CountriesCatalogApplication.applicationComponent
    }

    fun getView() : T? {
        return mvpView
    }

    internal fun getContext(): Context {
        return context
    }

    @CallSuper
    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
        injectComponent(getApplicationComponent())
        getApplicationComponent().inject(injectContainer)
        loadData()
    }

    @CallSuper
    override fun detachView() {
        mvpView = null

        val size = subscriptions!!.size
        var subscription: Subscription?
        for (i in 0 until size) {
            subscription = subscriptions!!.removeAt(0)
            if (!subscription.isUnsubscribed) {
                subscription.unsubscribe()
            }
        }
    }

    protected fun <V> singleSubscribe(observable: Observable<V>, subscriber: SingleSubscriber<V>) {
        subscriptions?.add(observable.subscribeOn(Schedulers.io())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber))
    }

    protected fun <T> baseSubscribe(observable: Observable<T>, subscriber: BaseSubscriber<T>) {

        val singleSubscriber = object : SingleSubscriber<T>() {
            override fun onSuccess(value: T) {
                subscriber.onComplete()
                subscriber.onSuccess(value)
            }

            override fun onError(error: Throwable) {
                val serviceError = ServiceError.error(error, injectContainer.getRetrofit())
                subscriber.onComplete()
                subscriber.onError(serviceError)
            }
        }

        subscriptions!!.add(observable.subscribeOn(Schedulers.io())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleSubscriber))
    }

    interface BaseSubscriber<T> {

        fun onSuccess(t: T)

        fun onError(error: ServiceError)

        fun onComplete()
    }
}