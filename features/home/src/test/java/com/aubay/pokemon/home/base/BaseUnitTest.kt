package com.aubay.pokemon.home.base

import okhttp3.mockwebserver.MockResponse
import java.io.File

abstract class BaseUnitTest: BaseTest() {

    // PUBLIC API ---
    fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    // UTILS ---
    private fun getJson(path : String) : String {
        val uri = javaClass.classLoader?.getResource(path)
        val file = File(uri!!.path)
        return String(file.readBytes())
    }
}