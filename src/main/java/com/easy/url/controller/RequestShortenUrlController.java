package com.easy.url.controller;

import com.easy.url.model.request.MakeEasyUrlRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by amahagna on 11/23/14.
 */

@Controller
public class RequestShortenUrlController {


    @RequestMapping(value = "/make", method = RequestMethod.GET)
    public ModelAndView makeUrl() {
        return new ModelAndView("make", "command", new MakeEasyUrlRequest());
    }

    @RequestMapping(value = "/tinyUrlMaker", method = RequestMethod.POST)
    public String generateEasyUrl(@ModelAttribute("easyUrl")MakeEasyUrlRequest makeRequest,
                             ModelMap model) {
        model.addAttribute("fullUrl", makeRequest.getFullUrl());

        return "generateUrl";
    }


}
