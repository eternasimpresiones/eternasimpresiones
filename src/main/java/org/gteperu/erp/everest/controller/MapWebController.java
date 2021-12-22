package org.gteperu.erp.everest.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapWebController {

    @RequestMapping(value = "/home.do")
    public String getMapIni(Model model, HttpServletRequest request) throws Exception {

        return "home/index";

    }

    @RequestMapping(value = "/public.do")
    public String getPublic(Model model, HttpServletRequest request) throws Exception {
        return "public/index";

    }

    @RequestMapping("*")
    public String hello(HttpServletRequest request) {
        return "public/index";
    }
}
