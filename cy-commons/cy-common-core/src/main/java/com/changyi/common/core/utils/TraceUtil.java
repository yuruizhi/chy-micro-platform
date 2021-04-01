package com.changyi.common.core.utils;

import org.slf4j.MDC;


public class TraceUtil {
    /**
     * 追踪id的名称
     */
    public static final String KEY_TRACE_ID = "traceId";


    public static String getTraceId() {
        return MDC.get(KEY_TRACE_ID);
    }
}
