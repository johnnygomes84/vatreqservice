package pt.baie.vatreqservice.service;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

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

	public String contribuinteEspecialXmlFIle(ContribuinteEspecial contribuinte) {

		try {

			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(ContribuinteEspecial.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

			// Store XML to File
			String filePath = "/opt/vatreqservice/xmlfiles/";
			String fileName = contribuinte.getSingular().getNome().replace(" ", "");
			String reqDay = LocalDate.now().toString().trim().replace("-", "");

			StringBuilder fullFileName = new StringBuilder();

			fullFileName.append(filePath).append(fileName).append(reqDay).append(".xml");

			File file = new File(fullFileName.toString());

			// Writes XML file to file-system
			jaxbMarshaller.marshal(contribuinte, file);

			String response = fileName.concat(reqDay).concat(".xml") + " saved in: " + filePath;

			return response;

		} catch (JAXBException e) {
			e.printStackTrace();
			return "Error savind file: "+e.getCause();
		}
	}

}
