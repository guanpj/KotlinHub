package com.me.guanpj.kotlinhub.widget

import com.me.guanpj.kotlinhub.ext.logger
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.GitHubPaging

abstract class ListPage<D>: DataProvider<D> {

    companion object {
        const val PAGE_SIZE = 10
    }

    var currentPage = 1
        private set

    val data = GitHubPaging<D>()

    fun loadMore() = getData(currentPage + 1)
            .doOnNext {
                currentPage + 1
            }
            .doOnError {
                logger.error("loadMore Error", it)
            }
            .map {
                data.mergeData(it)
                data
            }

    fun loadFromFirst(pageCount: Int = currentPage) =
            Observable.range(1, pageCount)
                    .concatMap {
                        getData(it)
                    }
                    .doOnError {
                        logger.error("loadFromFirst, pageCount=$pageCount", it)
                    }
                    /*.reduce { acc, page ->
                        acc.mergeData(page)
                    }*/
                    .doOnNext {
                        data.clear()
                        data.mergeData(it)
                    }

}