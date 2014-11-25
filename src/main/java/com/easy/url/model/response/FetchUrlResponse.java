package com.easy.url.model.response;

/**
 * POJO  Response for fetch original url
 *
 * @author Ahmad Mahagna
 */
public class FetchUrlResponse extends BaseResponse {

    private String originUrl;

    // success response constructor
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
