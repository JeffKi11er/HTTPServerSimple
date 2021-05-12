package com.company.http;

public enum HttpStatusCode {
    /*Client errors*/
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Method not allowed"),
    CLIENT_ERROR_414_URI_TOO_LONG(414, "Uri too long"),
    /*Server errors*/
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500,"Internal server error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501,"Not Implemented");
    public final int STATUS_CODE;
    public final String STATUS_MESSAGE;


    HttpStatusCode(int STATUS_CODE, String STATUS_MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.STATUS_MESSAGE = STATUS_MESSAGE;
    }
}
