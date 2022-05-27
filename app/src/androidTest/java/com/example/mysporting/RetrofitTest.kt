package com.example.mysporting

import com.example.mysporting.retrofit.ApiInterface
import com.google.android.gms.common.api.Api
import junit.framework.TestCase.assertEquals
import okhttp3.MultipartBody.Part.create
import org.junit.Test
import java.net.URI.create

class RetrofitTest {
    @Test
    fun `sportplaces data comes` () {
        // call the api
        val api = ApiInterface.create()
        val response = api.getSportPlaces().execute()
        // verify the response is OK
        assertEquals(response.code(), 200)
    }
}