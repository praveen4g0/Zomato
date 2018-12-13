package qubeCenimas.sharedBox_API.Models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import qubeCenimas.sharedBox_API.Utilities.ConfigMapper;

public class config {
	
	private String tokenOfAccount1;
	private String tokenOfAccount2;
	private String Account1;
	private String Account2;
	private String APIUrl;
	private String ApiKey;


	public config() throws FileNotFoundException, IOException {
		Properties props=new ConfigMapper().readPropertiesFromFile(System.getProperty("user.dir")+"\\resources\\config.properties");
		setTokenOfAccount1(props.getProperty("token1"));
		setTokenOfAccount2(props.getProperty("token2"));
		setAccount1(props.getProperty("account1"));
		setAccount2(props.getProperty("account2"));
		setAPIUrl(props.getProperty("apiURL"));
		setApiKey(props.getProperty("api_key"));
	}
	
	public String getAPIUrl() {
		return APIUrl;
	}

	public void setAPIUrl(String aPIUrl) {
		APIUrl = aPIUrl;
	}


	public String getApiKey() {
		return ApiKey;
	}

	public void setApiKey(String apiKey) {
		ApiKey = apiKey;
	}
	public String getTokenOfAccount1() {
		return tokenOfAccount1;
	}
	public void setTokenOfAccount1(String tokenOfAccount1) {
		this.tokenOfAccount1 = tokenOfAccount1;
	}
	public String getTokenOfAccount2() {
		return tokenOfAccount2;
	}
	public void setTokenOfAccount2(String tokenOfAccount2) {
		this.tokenOfAccount2 = tokenOfAccount2;
	}
	public String getAccount1() {
		return Account1;
	}
	public void setAccount1(String account1) {
		Account1 = account1;
	}
	public String getAccount2() {
		return Account2;
	}
	public void setAccount2(String account2) {
		Account2 = account2;
	}

	

}
