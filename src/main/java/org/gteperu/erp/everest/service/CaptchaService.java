package org.gteperu.erp.everest.service;


 import org.gteperu.erp.everest.domain.RecaptchaResponse;
import org.gteperu.erp.everest.domain.ResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:application.properties")
@Service
public class CaptchaService {

    private final RestTemplate restTemplate;

    public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Value("${google.recaptcha.secret.key}")
    public String recaptchaSecret;
    @Value("${google.recaptcha.verify.url}")
    public String recaptchaVerifyUrl;

    public boolean verify(String recaptchaCliente) {
        MultiValueMap param= new LinkedMultiValueMap<>();
        param.add("secret", recaptchaSecret);
        param.add("response", recaptchaCliente);

        RecaptchaResponse recaptchaResponse = null;
        try {
            recaptchaResponse = this.restTemplate.postForObject(recaptchaVerifyUrl, param, RecaptchaResponse.class);
//            recaptchaResponse = this.restTemplate.exchange(recaptchaVerifyUrl+"?secret=6LeDuGMaAAAAAESB0DSRAtf48rVwcdC31RiKTNB4&response="+recaptchaCliente, HttpMethod.POST, null, RecaptchaResponse.class).getBody();

        }catch(RestClientException e){
            System.out.print(e.getMessage());
        }
       if(recaptchaResponse.isSuccess()){
            return true;
        }else {
            return false;
        }
    }
}