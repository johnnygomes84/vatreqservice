package pt.baie.vatreqservice.service;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.Path;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pt.baie.vatreqservice.model.ContribuinteEspecial;

@Slf4j
@Service
public class PedidoService {

	public String contribuinteEspecialString(ContribuinteEspecial contribuinte) {

		log.info("[VATREQSERVICE] PedidoService ====== Getting String xml for "
				.concat(contribuinte.getSingular().getNome()));

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

			return xmlContent;

		} catch (JAXBException e) {
			e.printStackTrace();
			log.error("[ERROR][VATREQSERVICE] PedidoService ====== Getting String xml for "
					+ contribuinte.getSingular().getNome());
			return e.getMessage();
		}
	}

	public String contribuinteEspecialXmlFIle(ContribuinteEspecial contribuinte, String filePath) {

		log.info("[VATREQSERVICE] PedidoService ====== Getting xml File for "
				.concat(contribuinte.getSingular().getNome()));

		try {

			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(ContribuinteEspecial.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

			// Store XML to File
			String fileName = contribuinte.getSingular().getNome().replace(" ", "").toLowerCase();
			String reqDay = LocalDate.now().toString().trim().replace("-", "");

			StringBuilder fullFileName = new StringBuilder();
			fullFileName.append(fileName).append(reqDay).append(".xml");

			String fullPath;

			if (filePath.contains("\\")) {
				fullPath = filePath.concat("\\").concat("4. Outros documentos").concat("\\")
						.concat(fullFileName.toString());
			} else {

				fullPath = Path.of(filePath).resolve("4. Outros documentos").resolve(fullFileName.toString())
						.toString();
			}

			File file = new File(fullPath.toString());

			// Writes XML file to file-system
			jaxbMarshaller.marshal(contribuinte, file);

			String response = fileName.concat(reqDay).concat(".xml") + " saved in: " + fullPath.toString();

			return response;

		} catch (JAXBException e) {
			e.printStackTrace();
			log.error("[ERROR][VATREQSERVICE] PedidoService ====== Getting xml File for "
					+ contribuinte.getSingular().getNome());
			return "Error savind file: " + e.getCause();
		}
	}

}
