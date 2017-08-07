package mx.com.beo.models;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author Reynaldo ivan 
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/
public class TicketBean {
	
	private String idUser;
	private String idCreds;
	
	public TicketBean() { 
	}
	
	
	public TicketBean(String idUser, String idCreds) {
		super();
		this.idUser = idUser;
		this.idCreds = idCreds;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdCreds() {
		return idCreds;
	}
	public void setIdCreds(String idCreds) {
		this.idCreds = idCreds;
	}
	
	

}
