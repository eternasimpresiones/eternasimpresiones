package org.gteperu.erp.everest.controller;





import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** Handles logout. Removes access token from token store. **/
@Controller
public class OAuth2Controller {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.POST)
    public ResponseEntity revokeToken(HttpServletRequest httpServletRequest) {
    	String tokens = httpServletRequest.getHeader("Authorization");
    	 String value = tokens.substring(tokens.indexOf(" ")).trim();
    	  OAuth2AccessToken token = tokenStore.readAccessToken(value.split(" ")[0]);
    	  tokenStore.removeAccessToken(token);    	
        return new ResponseEntity(HttpStatus.OK);
    }
    
   

}
