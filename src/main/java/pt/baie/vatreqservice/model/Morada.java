package pt.baie.vatreqservice.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Morada")
@XmlAccessorType(XmlAccessType.FIELD)
public class Morada implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="Rua")
	private String rua;
	
	@XmlElement(name="Edificio")
	private String edificio;
	
	@XmlElement(name="Suite")
	private String suite;
	
	@XmlElement(name="Piso")
	private String piso;
	
	@XmlElement(name="ProvinciaDistrito")
	private String provinciaDistrito;
	
	@XmlElement(name="Apartado")
	private String apartado;
	
	@XmlElement(name="CodigoPostal")
	private String codPostal;
	
	@XmlElement(name="Localidade")
	private String localidade;

}
