package com.hub.olefashopapp.data


//interface AppContainer {
//    val productRepository : ProductRepository
//}
//
//class DefaultAppContainer : AppContainer {
//    private val base_url = "https://fakestoreapi.com/"
//
//    val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()
//
//    private val retrofit =  Retrofit.Builder()
//        .baseUrl(base_url)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .build()
//
//    private val retrofitService: ProductApiService by lazy {
//        retrofit.create(ProductApiService::class.java)
//    }
//
//    override val productRepository: ProductRepository by lazy {
//        NetworkProductRepository(retrofitService)
//    }
//
//}