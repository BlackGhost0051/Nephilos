package com.blackghost.nephilos.Interfaces;

public interface RequestInterface {
    void GET_request(String response);
    void POST_request(String response);
    void PUT_request(String response);
    void DELETE_request(String response);
    void PATCH_request(String response);
    void HEAD_request(String response);
    void OPTIONS_request(String response);
    void TRACE_request(String response);
    void CONNECT_request(String response);
}
