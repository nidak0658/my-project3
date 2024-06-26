object ApiClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer ${getToken()}")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private fun getToken(): String {
        // Retrieve JWT token from secure storage
        return "your_jwt_token"
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://yourapi.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
