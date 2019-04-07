package br.com.aab.jaxws.publisher;

import javax.xml.ws.Endpoint;

import br.com.aab.jaxws.service.RouterServiceImpl;

public class RouterPublisher {
	public static void main(String[] args) {
		String address = "http://localhost:9999/ws/router";
		Endpoint.publish(address, new RouterServiceImpl());
		System.out.println("Endpoint [" + address + "] LISTENING to RouterServiceImpl() ....");
	}

}
