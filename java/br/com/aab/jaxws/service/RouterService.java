package br.com.aab.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL)
public interface RouterService {

	@WebMethod
	String getRouter();
}