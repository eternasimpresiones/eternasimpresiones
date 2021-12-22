package org.gteperu.erp.everest.mail;

public class ParametrosMail {
	private String mailHost;
	private Integer mailPort;
	private String mailProtocol;
	private String mailUsername;
	private String mailPassword;

	

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public Integer getMailPort() {
		return mailPort;
	}

	public void setMailPort(Integer mailPort) {
		this.mailPort = mailPort;
	}

	public String getMailProtocol() {
		return mailProtocol;
	}

	public void setMailProtocol(String mailProtocol) {
		this.mailProtocol = mailProtocol;
	}

	public String getMailUsername() {
		return mailUsername;
	}

	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

}
