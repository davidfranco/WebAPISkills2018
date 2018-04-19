package es.skills2018.api.enums;

import javax.ws.rs.core.MediaType;

import es.skills2018.api.utils.ServerConstants;


public class CustomMediaType {
	public static final String APPLICATION_JSON = MediaType.APPLICATION_JSON + ServerConstants.REST_RESPONSE_CHARSET;
}
