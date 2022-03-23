package pt.baie.vatreqservice.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pt.baie.vatreqservice.model.ContribuinteEspecial;
import pt.baie.vatreqservice.utils.SFTPFileTrnasferUtils;

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

		log.info("[PedidoService][contribuinteEspecialXmlFIle] PedidoService ====== Getting xml File for "
				.concat(contribuinte.getSingular().getNome()));

		try {

			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(ContribuinteEspecial.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

			// Store XML to tmp File
			String fileName = contribuinte.getSingular().getNome().replace(" ", "").toLowerCase();

			StringBuilder fullFileName = new StringBuilder();
			fullFileName.append(fileName).append(".xml");

			String destPath;

			if (filePath.contains("\\")) {
				destPath = filePath.concat("\\").concat("4. Outros documentos").concat("\\")
						.concat(fullFileName.toString());
			} else {

				destPath = Path.of(filePath).resolve("4. Outros documentos").resolve(fullFileName.toString())
						.toString();
			}

			// temp file
			String tmpdir = System.getProperty("java.io.tmpdir");

			File tempFilePath = new File(tmpdir);

			File file = File.createTempFile(fileName, ".xml", tempFilePath);

			// Writes XML file to file-system
			jaxbMarshaller.marshal(contribuinte, file);
			log.info("[PedidoService][contribuinteEspecialXmlFIle] ====== TEMP FILE CREATED in :"
					.concat(file.getAbsolutePath()));

			// chamada utils sftp
			Boolean result = SFTPFileTrnasferUtils.sendFileSftp(file.getAbsolutePath(), destPath);

			String response;

			if (result) {
				response = fileName.concat(".xml") + " saved in: " + destPath.toString();
				log.info("[PedidoService][contribuinteEspecialXmlFIle] ==== Remote file saved: "
						.concat(file.getAbsolutePath()));

			} else {

				log.error("[PedidoService][contribuinteEspecialXmlFIle][ERROR] ===== ERROR saving Remote file: "
						.concat(file.getAbsolutePath()));
				response = "Some error saving remote file: "
						.concat(fileName.concat(".xml") + " saved in: " + destPath.toString());
			}
			
			file.delete();
			log.info("[PedidoService][contribuinteEspecialXmlFIle] ====== TEMP FILE deleted from: "
					.concat(file.getAbsolutePath()));

			return response;

		} catch (JAXBException e) {
			e.printStackTrace();
			log.error("[PedidoService][contribuinteEspecialXmlFIle][ERROR]====== Error Getting xml File for "
					+ contribuinte.getSingular().getNome());
			return "Error savind file: " + e.getCause();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("[PedidoService][contribuinteEspecialXmlFIle][ERROR]====== ErrorGetting xml File for "
					+ contribuinte.getSingular().getNome());
			return "Error savind file: " + e.getCause();
		}
	}

}
