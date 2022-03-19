package pt.baie.vatreqservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pt.baie.vatreqservice.model.ContribuinteEspecial;
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
    public ResponseEntity<String> saveXmlFile(@RequestBody ContribuinteEspecial contribuinte, @RequestParam String filepath) {

        try {
            String response = service.contribuinteEspecialXmlFIle(contribuinte, filepath);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
