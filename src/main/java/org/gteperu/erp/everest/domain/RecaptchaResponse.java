package org.gteperu.erp.everest.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecaptchaResponse {

	private boolean success;
    private String errorMessage;
    private Timestamp challenge_ts;
    private String hostname;

//    protected RecaptchaResponse(boolean valid, String errorMessage) {
//        this.valid = valid;
//        this.errorMessage = errorMessage;
//    }

    /**
     * The reCaptcha error message. invalid-site-public-key invalid-site-private-key invalid-request-cookie
     * incorrect-captcha-sol verify-params-incorrect verify-params-incorrect recaptcha-not-reachable
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Timestamp getChallenge_ts() {
		return challenge_ts;
	}

	public void setChallenge_ts(Timestamp challenge_ts) {
		this.challenge_ts = challenge_ts;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

    /**
     * True if captcha is "passed".
     *
     * @return
     */

}