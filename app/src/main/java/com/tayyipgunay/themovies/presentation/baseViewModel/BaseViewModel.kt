package com.tayyipgunay.themovies.presentation.baseViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel() :ViewModel(), CoroutineScope {

    // job: Coroutine'leri kontrol etmek ve yönetmek için kullanılan bir Job nesnesi.
    // Bu Job, coroutine'lerin iptal edilmesini ve temizlenmesini sağlar.
    private val job = Job()
    protected open val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        println("Global Exception Caught: ${throwable.message}")
    }

    // coroutineContext: CoroutineScope'un bir özelliği.
    // Coroutine'lerin hangi bağlamda çalışacağını belirler.
    // Bu bağlam, job ve Dispatchers.Main'in birleşimidir.
    // job: Coroutine'lerin iptalini kontrol eder.
    // Dispatchers.Main: Coroutine'lerin ana iş parçacığında (UI thread) çalışmasını sağlar.
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    // job: Arka planda çalışan coroutine'lerin yönetimi için.
    // Dispatchers.Main: Coroutine'lerin ana iş parçacığında çalışması için, örneğin UI güncellemeleri için.

    // onCleared: ViewModel yok edildiğinde (örneğin, Activity veya Fragment sona erdiğinde) çağrılır.
    // Bu metod, başlatılan tüm coroutine'leri iptal ederek bellek sızıntılarını önler.
    override fun onCleared() {
        super.onCleared()
        job.cancel() // Tüm coroutine'leri iptal eder ve kaynakları temizler.
    }
}