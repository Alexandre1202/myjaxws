package br.com.aab.jaxws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.aab.jaxws.service.RouterService;

public class RouterClient {

	public static void main(String[] args) {
		String address = "http://localhost:9999/ws/router?wsdl";
		String namespaceURI = "http://service.jaxws.aab.com.br/";
		String localPart = "RouterServiceImplService";
		URL url = null;
		try {
			url = new URL(address);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qname = new QName(namespaceURI, localPart);
		Service service = Service.create(url, qname);
		RouterService router = service.getPort(RouterService.class);
		System.out.println(router.getRouter());
	}

}
