package com.easy.url.model.response;

/**
 * POJO Make easy url response
 *
 * @author Ahmad Mahagna
 */
public class MakeEasyUrlResponse extends BaseResponse {


    private String easyUrl;

    // success response constructor
    public MakeEasyUrlResponse(String easyUrl) {
        super(ResponseState.OK.getState(), null);
        this.easyUrl = easyUrl;
    }

    public MakeEasyUrlResponse(int state, String errMsg) {
        super(state, errMsg);

    }


    public String getEasyUrl() {
        return easyUrl;
    }

    public void setEasyUrl(String easyUrl) {
        this.easyUrl = easyUrl;
    }


}
