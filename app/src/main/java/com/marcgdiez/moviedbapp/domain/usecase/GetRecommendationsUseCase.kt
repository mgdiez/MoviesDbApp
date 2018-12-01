package com.marcgdiez.moviedbapp.domain.usecase

import com.marcgdiez.moviedbapp.domain.MoviesRepository
import com.marcgdiez.moviedbapp.domain.bo.GetMoviesResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class GetRecommendationsUseCase(
    private val moviesRepository: MoviesRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) {

    private var subscription: Disposable = Disposables.empty()

    fun execute(id: Int, onComplete: (GetMoviesResponse) -> Unit, onError: (Throwable) -> Unit) {
        subscription = moviesRepository.getRecommendations(id)
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