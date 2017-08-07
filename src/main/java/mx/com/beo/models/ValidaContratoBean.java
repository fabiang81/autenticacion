package mx.com.beo.models;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author Reynaldo ivan
* 
*  
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/
public class ValidaContratoBean {
	
	
	private String usuario;
	private String contratoAceptado; 
	private String url;
	
	 
	
	
	public ValidaContratoBean(String usuario, String contratoAceptado, String url) {
		super();
		this.usuario = usuario;
		this.contratoAceptado = contratoAceptado;
		this.url = url;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContratoAceptado() {
		return contratoAceptado;
	}
	public void setContratoAceptado(String contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
