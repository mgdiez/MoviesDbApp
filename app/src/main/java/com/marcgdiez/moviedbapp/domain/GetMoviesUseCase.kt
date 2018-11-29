package com.marcgdiez.moviedbapp.domain

import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class GetMoviesUseCase(
    private val moviesRepository: MoviesRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) {

    private var subscription: Disposable = Disposables.empty()

    fun execute(page: Int, onComplete: (GetMoviesResponse) -> Unit, onError: (Throwable) -> Unit) {
        subscription = moviesRepository.getMovies(page)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }

    fun clear() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }
}