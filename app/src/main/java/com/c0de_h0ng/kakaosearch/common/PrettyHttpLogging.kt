package com.c0de_h0ng.kakaosearch.common

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by c0de_h0ng on 2021/10/03.
 */
class PrettyHttpLogging : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        val logName = "OkHttp"
        if (!message.startsWith("{")) {
            Log.d(logName, message)
            return
        }
        try {
            val prettyPrintJson = GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(JsonParser.parseString(message))
            Log.d(logName, prettyPrintJson)
        } catch (e: JsonSyntaxException) {
            Log.d(logName, message)
        }
    }

}