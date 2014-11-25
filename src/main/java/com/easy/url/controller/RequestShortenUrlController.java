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
 * Main controller that handle requests for generate easy url and fetch original urls
 * support REST API
 * support Graphics User Interface.
 * <p/>
 * states returns @see BaseResponse.ResponseState
 *
 * @author Ahmad Mahagna
 */

@Controller
public class RequestShortenUrlController {

    // PREFIX EASY URL
    private static final String PREFIX_EASY_URL = "http://easyUrl.me/";


    // ------------------------------------------------------------------------------------------------
    //                   requests for UI
    // ------------------------------------------------------------------------------------------------


    /**
     * request method GET for generate easy URL.
     *
     * @return make page for generate easy URL
     */
    @RequestMapping(value = "/make", method = RequestMethod.GET)
    public ModelAndView makeUrl() {
        //TODO add constants rather than string for pages and commands
        return new ModelAndView("make", "command", new MakeEasyUrlRequest());
    }

    /**
     * request method GET for fetch original URL
     *
     * @return fetch page for fetch original URL
     */
    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public ModelAndView fetchUrl() {
        return new ModelAndView("fetch", "command", new FetchUrlRequest());
    }

    /**
     * @param makeRequest make easy url request
     * @param model       spring model ui
     * @return generateUrl page for get results
     * @See MakeEasyUrlRequest if success otherwise return failed page
     */
    @RequestMapping(value = "/tinyUrlMaker", method = RequestMethod.POST)
    public String generateEasyUrl(@ModelAttribute("makeEasyUrl") MakeEasyUrlRequest makeRequest,
                                  ModelMap model) {
        // adding attributes to model
        model.addAttribute("originalUrl", makeRequest.getOriginalUrl());

        // generate url
        MakeEasyUrlResponse makeEasyUrlResponse = generateEasyUrlAndSave(makeRequest.getOriginalUrl());
        // check state
        if (checkError(model, makeEasyUrlResponse)) {
            return "failed";
        }

        // adding the result
        model.addAttribute("easyURL", makeEasyUrlResponse.getEasyUrl());

        return "generateUrl";
    }

    /**
     * fetch url request get back original url
     *
     * @param fetchRequest fetch url request
     * @param model        model ui
     * @return fetch original page with results otherwise return failed with error message
     * @see com.easy.url.model.response.FetchUrlResponse
     */
    @RequestMapping(value = "/fetchUrl", method = RequestMethod.POST)
    public String fetchOriginalUrl(@ModelAttribute("fetchEasyUrl") FetchUrlRequest fetchRequest,
                                   ModelMap model) {
        // update model
        model.addAttribute("easyUrl", fetchRequest.getEasyUrl());
        // fetch from DB
        FetchUrlResponse fetchUrlResponse = fetchUrl(fetchRequest.getEasyUrl());
        // check state
        if (checkError(model, fetchUrlResponse)) {
            return "failed";
        }
        // update result
        model.addAttribute("originalUrl", fetchUrlResponse.getOriginUrl());

        return "fetchOriginalUrl";
    }


    // ------------------------------------------------------------------------------------------------
    //                   requests for REST API
    // ------------------------------------------------------------------------------------------------


    /**
     * generate easy URL request
     *
     * @param url input represent url
     * @return json object represent response for make easy url with generate result
     * @see com.easy.url.model.response.MakeEasyUrlResponse
     * README.md  file for more information
     */
    @RequestMapping(value = "/make/json", method = RequestMethod.POST)
    public
    @ResponseBody
    MakeEasyUrlResponse makeEasyUrlJson(

            @RequestBody(required = true) String url) {

        //TODO validate  url   REGEX start with http / https ...
        // validate if url is blank then reject request
        if (StringUtils.isBlank(url)) {
            return new MakeEasyUrlResponse(BaseResponse.ResponseState.INVALID_URL.getState(), "invalid url [ " + url + "] ");
        }
        // return generated easy URL.
        return generateEasyUrlAndSave(url);

    }

    /**
     * fetch original URL request
     *
     * @param easyUrl given easy URL
     * @return json object represent response for fetch original url with results
     * @see com.easy.url.model.response.FetchUrlResponse
     */
    @RequestMapping(value = "/fetch/json", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    FetchUrlResponse fetchUrlJson(

            @RequestParam(value = "easyUrl", required = true) String easyUrl) {

        //TODO validate
        // fetch from DB corresponding original url
        return fetchUrl(easyUrl);

    }


    /**
     * check if response state isn't OK and update the mode
     *
     * @param model        get model for update it with error state/message
     * @param baseResponse given response
     * @return true if any error occurred otherwise false
     */
    private boolean checkError(ModelMap model, BaseResponse baseResponse) {
        // check state if isn't ok update model
        if (baseResponse.getRequestState() != FetchUrlResponse.ResponseState.OK.getState()) {
            model.addAttribute("message", baseResponse.getErrorMessage());
            model.addAttribute("httpStatus", baseResponse.getRequestState());
            return true;
        }
        return false;
    }


    /**
     * generate easy URL then save it to DB
     *
     * @param url string represent URL
     * @return response object
     * @See MakeEasyUrlResponse
     */
    private MakeEasyUrlResponse generateEasyUrlAndSave(String url) {

        // generate ID
        long generateId = Util.getRandomLong();
        // generate easy url
        String easyUrl = EasyUrlAlgorithm.getEasyUrl(generateId);
        try {
            // adding to DB
            DB.addUrl(url, easyUrl);
        } catch (DuplicateShortenUrl e) {
            //TODO add logs
            return new MakeEasyUrlResponse(MakeEasyUrlResponse.ResponseState.ERROR.getState(), "failed to add url , please try again");
        }
        // adding prefix and create response
        String easyUrlWithPrefix = PREFIX_EASY_URL + easyUrl;
        return new MakeEasyUrlResponse(easyUrlWithPrefix);

    }

    /**
     * fetch original url by given easy url
     *
     * @param easyUrl represent easy url shorten url
     * @return FetchUrlResponse represent response for fetch url
     * @See FetchUrlResponse
     */
    private FetchUrlResponse fetchUrl(String easyUrl) {

        // validate should start with prefix
        if (!StringUtils.startsWith(easyUrl, PREFIX_EASY_URL)) {
            return new FetchUrlResponse(FetchUrlResponse.ResponseState.INVALID_URL.getState(), "easyUrl should start with  [" + PREFIX_EASY_URL + "]");
        }
        // remove prefix
        String easyUrlWithoutPrefix = StringUtils.removeStart(easyUrl, PREFIX_EASY_URL);
        // fetch from DB
        String originUrl = DB.fetchUrl(easyUrlWithoutPrefix);
        if (originUrl == null) {
            return new FetchUrlResponse(FetchUrlResponse.ResponseState.NOT_FOUND.getState(), "no original url found match easy url [" + easyUrl + "]");
        }

        return new FetchUrlResponse(originUrl);
    }


}
