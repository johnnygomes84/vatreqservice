package pt.baie.vatreqservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.baie.vatreqservice.model.ContribuinteEspecial;
import pt.baie.vatreqservice.model.RequestDTO;
import pt.baie.vatreqservice.service.PedidoService;

@RestController
@RequestMapping("/vatrequestservice")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping("/getstringfile")
	public ResponseEntity<String> getNifXml(@RequestBody ContribuinteEspecial contribuinte) {

		try {
			String response = service.contribuinteEspecialString(contribuinte);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PostMapping("/savexmlfile")
	public ResponseEntity<String> saveXmlFile(@RequestBody RequestDTO requestDto) {

		try {
			String response = service.contribuinteEspecialXmlFIle(requestDto.getContribuinte(),
					requestDto.getFilePath());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
