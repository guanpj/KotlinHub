package retrofit2.adapter.rxjava2

abstract class PagingWrapper<T> {

    abstract fun getElements(): List<T>

    val paging by lazy {
        GitHubPaging<T>().also { it.addAll(getElements()) }
    }
}