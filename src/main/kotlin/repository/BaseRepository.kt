package repository

import entitiy.FullParameters
import entitiy.HttpResponse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

abstract class BaseRepository {
    /**
     * this function returns the result of external API
     */
    protected fun execute(fullParameters: FullParameters) : HttpResponse {
        val connection: HttpURLConnection
        var response: HttpResponse
        val url: URL = URL(fullParameters.url + getQuery(fullParameters.parameters))

        connection = url.openConnection() as HttpURLConnection
        with(connection) {
            readTimeout = 1200
            connectTimeout = 1200
            requestMethod = fullParameters.method.toString()
            setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            setRequestProperty("charset", "utf-8")
            useCaches = false
            response = if (responseCode == 404) {
                HttpResponse(responseCode, "")
            } else {
                HttpResponse(responseCode, getResultFromInputStream(inputStream))
            }
            connect()
            return response
        }
    }

    private fun getResultFromInputStream(inputStream: InputStream): String {
        return try {
            val sb: StringBuilder = StringBuilder()
            val br: BufferedReader = BufferedReader(InputStreamReader(inputStream))

            for (line in br.readLines()) {
                sb.append(line)
            }

            sb.toString()

        } catch (e: Exception) {
            ""
        }

    }

    private fun getQuery(parameters: Map<String, String>): String {
        if(parameters.isEmpty()) return ""

        val result: StringBuilder = StringBuilder()
        var first: Boolean = true

        for (param in parameters) {
            if (first) {
                result.append("?")
                first = false
            } else {
                result.append("&")

            }

            result.append(URLEncoder.encode(param.key), "UTF-8")
            result.append("=")
            result.append(URLEncoder.encode(param.value), "UTF-8")
        }

        return result.toString()
    }
}