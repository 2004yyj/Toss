package ks.hs.dgsw.domain.base

abstract class BaseUseCase<out T> {
    abstract suspend fun buildUseCase(): T
}