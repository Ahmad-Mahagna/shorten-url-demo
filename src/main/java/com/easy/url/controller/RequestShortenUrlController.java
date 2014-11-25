package com.easy.url.controller;

import com.easy.url.DB;
import com.easy.url.Exception.DuplicateShortenUrl;
import com.easy.url.algorithm.EasyUrlAlgorithm;
import com.easy.url.algorithm.Util;
import com.easy.url.model.request.FetchUrlRequest;
import com.easy.url.model.request.MakeEasyUrlRequest;
import com.easy.url.model.response.BaseResponse;
import com.easy.url.model.response.FetchUrlResponse;
import com.easy.url.model.response.MakeEasyUrlResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by amahagna on 11/23/14.
 */

@Controller
public class RequestShortenUrlController {


    private static final String PREFIX_EASY_URL = "http://easyUrl.me/";

    @RequestMapping(value = "/make", method = RequestMethod.GET)
    public ModelAndView makeUrl() {
        return new ModelAndView("make", "command", new MakeEasyUrlRequest());
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ModelAndView fetchUrl() {
        return new ModelAndView("fetch", "command", new FetchUrlRequest());
    }


    @RequestMapping(value = "/tinyUrlMaker", method = RequestMethod.POST)
    public String generateEasyUrl(@ModelAttribute("makeEasyUrl") MakeEasyUrlRequest makeRequest,
                                  ModelMap model) {
        model.addAttribute("fullUrl", makeRequest.getFullUrl());
        MakeEasyUrlResponse makeEasyUrlResponse = generateEasyUrlAndSave(makeRequest.getFullUrl());
        if (checkError(model, makeEasyUrlResponse)) {
            return "failed";
        }

        model.addAttribute("easyURL", makeEasyUrlResponse.getEasyUrl());

        return "generateUrl";
    }


    @RequestMapping(value = "/fetchUrl", method = RequestMethod.POST)
    public String fetchOriginalUrl(@ModelAttribute("fetchEasyUrl") FetchUrlRequest fetchRequest,
                                   ModelMap model) {
        model.addAttribute("easyUrl", fetchRequest.getEasyUrl());
        FetchUrlResponse fetchUrlResponse = fetchUrl(fetchRequest.getEasyUrl());
        if (checkError(model, fetchUrlResponse)) {
            return "failed";
        }

        model.addAttribute("originalUrl", fetchUrlResponse.getOriginUrl());

        return "fetchOriginalUrl";
    }

    private boolean checkError(ModelMap model, BaseResponse baseResponse) {
        if (baseResponse.getHttpState() != FetchUrlResponse.ResponseState.OK.getState()) {
            model.addAttribute("message", baseResponse.getErrorMessage());
            model.addAttribute("httpStatus", baseResponse.getHttpState());
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/make/json", method = RequestMethod.POST)
    public
    @ResponseBody
    MakeEasyUrlResponse makeEasyUrlJson(

            @RequestBody(required = true) String url) {

        //TODO validate  url   REGEX start with http / https ...
        if (StringUtils.isBlank(url)) {
            return new MakeEasyUrlResponse(BaseResponse.ResponseState.INVALID_URL.getState(), "invalid url [ " + url + "] ");
        }
        return generateEasyUrlAndSave(url);

    }


    @RequestMapping(value = "/fetch/json", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    FetchUrlResponse fetchUrlJson(

            @RequestParam(value = "easyUrl", required = true) String easyUrl) {

        //TODO validate
        return fetchUrl(easyUrl);

    }


    private MakeEasyUrlResponse generateEasyUrlAndSave(String url) {

        long generateId = Util.getRandomLong();
        String easyUrl = EasyUrlAlgorithm.getEasyUrl(generateId);
        try {
            DB.addUrl(url, easyUrl);
        } catch (DuplicateShortenUrl e) {
            //TODO add logs
            return new MakeEasyUrlResponse(MakeEasyUrlResponse.ResponseState.ERROR.getState(), "failed to add url , please try again");
        }
        String easyUrlWithPrefix = PREFIX_EASY_URL + easyUrl;
        return new MakeEasyUrlResponse(easyUrlWithPrefix);

    }

    private FetchUrlResponse fetchUrl(String easyUrl) {

        if (!StringUtils.startsWith(easyUrl, PREFIX_EASY_URL)) {
            return new FetchUrlResponse(FetchUrlResponse.ResponseState.INVALID_URL.getState(), "easyUrl should start with  [" + PREFIX_EASY_URL + "]");
        }
        String easyUrlWithoutPrefix = StringUtils.removeStart(easyUrl, PREFIX_EASY_URL);
        String originUrl = DB.fetchUrl(easyUrlWithoutPrefix);
        if (originUrl == null) {
            return new FetchUrlResponse(FetchUrlResponse.ResponseState.NOT_FOUND.getState(), "no original url found match easy url [" + easyUrl + "]");
        }

        return new FetchUrlResponse(originUrl);
    }


}
