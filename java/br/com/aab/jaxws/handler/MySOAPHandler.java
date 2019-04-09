package br.com.aab.jaxws.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MySOAPHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (!isRequest) {

			try {
				String strHeader = "CUSTOM_HEADER";
				String strPrefix = "CUSTOMIZED_PREFIX";
				SOAPMessage soapMessage = context.getMessage();
				SOAPBody soapBody = soapMessage.getSOAPBody();
				SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnvelope.getHeader();

				System.out.println("=================== Before ============================"
						+ "\nMessage Description = " + soapMessage.getContentDescription()
					+ "\nBody = " + soapBody.toString()
					+ "\nEnvelope = " + soapEnvelope.toString()
					+ "\nHeader = " + soapHeader.toString()
				);

				if (soapHeader == null) 
					soapHeader = soapEnvelope.addHeader();
				
				soapEnvelope.removeAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "ns2");
				soapEnvelope.removeAttribute("xmlns:ns2");
				soapBody.removeAttribute("ns2");
				
				soapEnvelope.setPrefix(strPrefix);
				soapHeader.setPrefix(strPrefix);
				soapBody.setPrefix(strPrefix);
				addDesiredBodyNamespaceEntries(soapBody.getChildElements());

				soapMessage.saveChanges();

				System.out.println("=================== After ============================"
						+ "\nMessage Description = " + soapMessage.getContentDescription()
					+ "\nBody = " + soapBody.toString()
					+ "\nEnvelope = " + soapEnvelope.toString()
					+ "\nHeader = " + soapHeader.toString()
					+ "\n======================================================"
				);

				System.out.println("");
				soapMessage.writeTo(System.out);
				System.out.println("\n======================================================");
			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// continue other handler chain
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("Server : handleFault()......");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("Server : close()......");
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("Server : getHeaders()......");
		return null;
	}

	private void addDesiredBodyNamespaceEntries(Iterator childElements) {
		while (childElements.hasNext()) {
			final Object childElementNode = childElements.next();
			if (childElementNode instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) childElementNode;

				// set desired namespace body element prefix
				soapElement.setPrefix("tem");

				// recursively set desired namespace prefix entries in child elements
				addDesiredBodyNamespaceEntries(soapElement.getChildElements());
			}
		}
	}

}
