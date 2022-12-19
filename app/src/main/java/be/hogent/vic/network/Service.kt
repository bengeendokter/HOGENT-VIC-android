package be.hogent.vic.network

import be.hogent.vic.domain.* // ktlint-disable no-wildcard-imports
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface VicService {
    @GET("virtualmachine")
    suspend fun getVirtualMachines(): List<VirtualMachineNetworkDto>

    @GET("virtualmachine/{id}")
    suspend fun getVirtualMachine(@Path("id") id: Int): VirtualMachineNetworkDto

    @GET("virtualmachinerequest")
    suspend fun getVirtualMachineRequests(): List<VirtualMachineRequestNetworkDto>

    @GET("client")
    suspend fun getClients(): List<ClientNetworkDto>

    @GET("client/{id}")
    suspend fun getClient(@Path("id") id: Int): ClientNetworkDto
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
    .add(createEnumJsonAdapter<Software>())
    .add(createEnumJsonAdapter<Template>())
    .add(createEnumJsonAdapter<Day>())
    .add(createEnumJsonAdapter<BackupFrequency>())
    .add(createEnumJsonAdapter<Status>())
    .build()

object Network {
    private val retrofit = Retrofit.Builder()
        // .baseUrl("http://10.0.2.2:5129/api/")
        .baseUrl("https://devopsg04.westeurope.cloudapp.azure.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val vic = retrofit.create(VicService::class.java)
}
