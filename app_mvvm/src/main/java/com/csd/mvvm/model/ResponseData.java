package com.csd.mvvm.model;

import org.json.JSONObject;

public class ResponseData {

    /**
     * ret : 1
     * msg : 登录成功
     */

    private String ret;
    private String msg;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "ret='" + ret + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }


    public static ResponseData paseFromJson(JSONObject jsonObject) {
        ResponseData responseData = new ResponseData();
        responseData.setRet(jsonObject.optString("ret"));
        responseData.setMsg(jsonObject.optString("msg"));
        return responseData;
    }
}
