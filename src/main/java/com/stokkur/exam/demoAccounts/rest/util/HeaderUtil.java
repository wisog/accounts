package com.stokkur.exam.demoAccounts.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public class HeaderUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "Accounts";

    private HeaderUtil() {}

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-accounts-alert", message);
        headers.add("X-accounts-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        LOG.debug("Entity created, " + entityName, param);
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        LOG.debug("Entity updated, " + entityName, param);
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        LOG.debug("Entity deleted, " + entityName, param);
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        LOG.error("Error on entity "+entityName, defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-accounts-error", "error." + errorKey);
        headers.add("X-accounts-params", entityName);
        return headers;
    }
}
