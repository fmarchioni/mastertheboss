package com.example.keycloak.web;

import java.util.Map;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyResource {

	@GetMapping(path = "users")
	public String user(KeycloakAuthenticationToken keycloakAuthenticationToken) {
		@SuppressWarnings("unchecked")
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) keycloakAuthenticationToken
		.getPrincipal();

		StringBuffer html = new StringBuffer();
		html.append("Principal: "+principal.getName());
		html.append("<br />");   

		Map<String, Object> customClaims = (principal.getKeycloakSecurityContext().getToken().getOtherClaims());
		for (Map.Entry<String, Object> entry : customClaims.entrySet()) {
			html.append(entry.getKey() + "/" + entry.getValue());
			html.append("<br />");
		}

		html.append(principal.getKeycloakSecurityContext().getToken().getEmail());
		html.append("<br />");   


		return html.toString();
	}



}
