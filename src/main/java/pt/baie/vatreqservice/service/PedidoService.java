package pt.baie.vatreqservice.service;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

import lombok.Singular;
import pt.baie.vatreqservice.model.ContribuinteEspecial;

@Service
public class PedidoService {

	public String contribuinteEspecialString(ContribuinteEspecial contribuinte) {

		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(ContribuinteEspecial.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

			// Print XML String to Console
			StringWriter sw = new StringWriter();

			// Write XML to StringWriter
			jaxbMarshaller.marshal(contribuinte, sw);

			// Verify XML Content
			String xmlContent = sw.toString();
			System.out.println(xmlContent);

			return xmlContent;

		} catch (JAXBException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
