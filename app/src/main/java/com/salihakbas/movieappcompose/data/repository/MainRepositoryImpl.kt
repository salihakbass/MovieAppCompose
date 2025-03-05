package com.salihakbas.movieappcompose.data.repository

import com.salihakbas.movieappcompose.data.source.local.MainDao
import com.salihakbas.movieappcompose.data.source.remote.MainService
import com.salihakbas.movieappcompose.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainDao: MainDao,
) : MainRepository