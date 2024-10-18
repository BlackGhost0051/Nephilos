package com.blackghost.nephilos.Interfaces;

public interface RequestInterface {
    void GET_request(String request);
    void POST_request(String request);
    void PUT_request(String request);
    void DELETE_request(String request);
    void PATCH_request(String request);
    void HEAD_request(String request);
    void OPTIONS_request(String request);
    void TRACE_request(String request);
    void CONNECT_request(String request);
}
