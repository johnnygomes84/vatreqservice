package pt.baie.vatreqservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
	
	private String filePath;	
	private ContribuinteEspecial contribuinte;

}
