package ks.hs.dgsw.data.base

abstract class BaseDataSource<RE> {
    protected abstract val remote: RE
}