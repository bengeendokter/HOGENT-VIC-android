package be.hogent.vic.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

interface VicService {
    @GET("virtualmachine")
    suspend fun getVirtualMachines(): VirtualMachineNetworkDtoContainer
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    .build()

object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:7257/api/") // TODO: change to hosted API
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val vic = retrofit.create(VicService::class.java)
}
