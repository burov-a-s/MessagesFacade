package ru.iflex.burov.rcp;

import ru.iflex.burov.interceptors.LoggerInterceptor;

import javax.interceptor.Interceptors;

@Interceptors(LoggerInterceptor.class)
public class RPCRequest {
    private String jsonrpc = "2.0";
    private String method;
    private String params;
    private int id = 1;

    public RPCRequest() {
        id++;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RPCRequest{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", id=" + id +
                '}';
    }
}