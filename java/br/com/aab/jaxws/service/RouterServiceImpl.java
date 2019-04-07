package br.com.aab.jaxws.service;


import java.time.LocalDateTime;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@HandlerChain(file = "handler-chain.xml")
@WebService(endpointInterface = "br.com.aab.jaxws.service.RouterService")
public class RouterServiceImpl implements RouterService {

	@Override
	public String getRouter() {
		return "Customized namespace at " + LocalDateTime.now() + " !";
	}
	
}

