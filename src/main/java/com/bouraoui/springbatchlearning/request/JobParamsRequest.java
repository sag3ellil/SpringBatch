package com.bouraoui.springbatchlearning.request;


public class JobParamsRequest {

    String paramKey;
    String paramValue;

    public String getParamKey() {
        return paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
