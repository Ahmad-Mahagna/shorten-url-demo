package com.easy.url.model.response;

/**
 * Created by amahagna on 11/24/14.
 */
public class FetchUrlResponse extends BaseResponse {

    private String originUrl;

    public FetchUrlResponse(String originalUrl) {
        super(BaseResponse.ResponseState.OK.getState(), null);
        this.originUrl = originalUrl;
    }

    public FetchUrlResponse(int state, String errMsg) {
        super(state, errMsg);
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }
}
