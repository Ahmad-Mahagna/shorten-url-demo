package com.easy.url.model.response;

/**
 * Created by amahagna on 11/24/14.
 */
public class MakeEasyUrlResponse extends BaseResponse{


    private String easyUrl;


    public MakeEasyUrlResponse(String easyUrl) {
        super(ResponseState.OK.getState(),null);
        this.easyUrl = easyUrl;
    }

    public MakeEasyUrlResponse(int state, String errMsg) {
        super(state,errMsg);

    }





    public String getEasyUrl() {
        return easyUrl;
    }

    public void setEasyUrl(String easyUrl) {
        this.easyUrl = easyUrl;
    }


}
