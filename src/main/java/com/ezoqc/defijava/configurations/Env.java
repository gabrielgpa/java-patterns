package com.ezoqc.defijava.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {
	@Value("${com.ezoqc.defijava.security.username}")
	public String securityUsername;
	
	@Value("${com.ezoqc.defijava.security.password}")
	public String securityPassword;
	
	@Value("${com.ezoqc.defijava.cors.allowed.origins}")
	public List<String> allowedOrigins;
}
