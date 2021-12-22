package org.gteperu.erp.everest.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.gteperu.erp.everest.domain.User;
import org.gteperu.erp.everest.domain.Users;
import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.gteperu.erp.everest.service.UserMService;
import org.gteperu.erp.everest.service.UsersService;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/usuarios")
public class UsuarioController {

    @Resource(name = "userMService")
    private UserMService userMService;

    @Autowired
    private TokenStore tokenStore;

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<User> retornaUsuarioM(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null) {
            String token = authorizationHeader.replace("Bearer", "").trim();         
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            System.out.println(oAuth2AccessToken.getValue());
        }

        return userMService.retorna();
    }
}
