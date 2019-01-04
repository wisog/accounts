package com.stokkur.exam.demoAccounts.rest.util;

/**
 * Util for Url Composition
 */
public final class ApiConstant {

    public static final String API_PATH = "/api/v1";
    public static final String MANAGEMENT_AUDITS_RESOURCE = "/management/audits";
    public static final String MANAGEMENT = "/management";
    public static final String AUTHENTICATE = "/authenticate";

    private ApiConstant() {
        throw new AssertionError();
    }

}