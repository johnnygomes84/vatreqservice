package pt.baie.vatreqservice.model;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Singular")
@XmlAccessorType(XmlAccessType.FIELD)
public class Singular implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="Nome")
	private String nome;

	@XmlElement(name="Morada")
	private Morada morada;
	
	@XmlElement(name="PaisResidencia")
	private Integer paisResidencia;
	
	@XmlElement(name="NIFPaisResidencia")
	private String nifPaisResidencia;
	
	@XmlElement(name="DataNasc")
	private String dataNasc;
	
	@XmlElement(name="PaisNascimento")
	private Integer paisNascimento;
	
	@XmlElement(name="ConcelhoNascimento")
	private String concelhoNascimento;
	
	@XmlElement(name="FreguesiaNascimento")
	private String freguesiaNascimento;
	
	@XmlElement(name="LocalidadeNascimento")
	private String localidadeNascimento;
	
	@XmlElement(name="Sexo")
	private String sexo;
	
	@XmlElement(name="Nacionalidade")
	private Integer nacionalidade;

}
