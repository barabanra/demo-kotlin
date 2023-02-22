package ru.barabanra.demokotlin.web.filters

import org.apache.commons.collections4.IteratorUtils
import org.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.time.Duration
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingFilter : OncePerRequestFilter() {
    
    private val incomingRequestTime = "INCOMING_REQUEST_TIME"
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.setAttribute(incomingRequestTime, LocalDateTime.now())
        val cachingRequestWrapper = ContentCachingRequestWrapper(request)
        val cachingResponseWrapper = ContentCachingResponseWrapper(response)


        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper)
        logMessage(cachingRequestWrapper, cachingResponseWrapper)
    }

    private fun logMessage(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
        try {
            val requestTime = request.getAttribute(incomingRequestTime) as LocalDateTime
            val took = Duration.between(requestTime, LocalDateTime.now())
            val metaInfoObject = JSONObject()

            metaInfoObject.put("uri", request.requestURI)
            metaInfoObject.put("httpMethod", request.method)
            metaInfoObject.put("requestTime", requestTime)
            metaInfoObject.put("took", took.toMillis().toString() + " millis")
            metaInfoObject.put("requestHeaders", getHeaders(request))
            metaInfoObject.put("responseHeaders", getHeaders(response))
            metaInfoObject.put("responseStatus", response.status)

            val bodyObject = JSONObject()
            bodyObject.put("request", this.formatBody(String(request.contentAsByteArray)))
            bodyObject.put("response", this.formatBody(String(response.contentAsByteArray)))

            val logObject = JSONObject()
            logObject.put("metaInfo", metaInfoObject)
            logObject.put("bodies", bodyObject)

            log.debug("{}", logObject)
        } finally {
            response.copyBodyToResponse()
        }
    }

    private fun formatBody(body: String): String? {
        return body
            .replace("  ".toRegex(), "")
            .replace("\n".toRegex(), "")
    }

    private fun getHeaders(response: ContentCachingResponseWrapper): String? {
        return response.headerNames
            .stream()
            .map { headerName: String ->
                "$headerName=" + response.getHeader(
                    headerName
                )
            }
            .collect(Collectors.joining(","))
    }

    private fun getHeaders(request: ContentCachingRequestWrapper): String? {
        return IteratorUtils.toList(request.headerNames.asIterator())
            .stream()
            .map { headerName -> headerName.toString() + "=" + request.getHeader(headerName) }
            .collect(Collectors.joining(","))
    }
}