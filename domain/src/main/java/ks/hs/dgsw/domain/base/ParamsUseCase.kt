package ks.hs.dgsw.domain.base

abstract class ParamsUseCase<in Params, out T> {
    abstract suspend fun buildParamsUseCase(params: Params): T
}