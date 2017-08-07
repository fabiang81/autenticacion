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
public class EnvioNotificacioneBean {
	
	private String idPersona;
	private TicketBean ticketBean;
	private String tipoMensaje;
	private String tipoNotificacion;
	private String from;
	private String to;
	private MapParameterBean key;
	private String canal;
	
	private String url;
	
	
	
	
	
	
	public EnvioNotificacioneBean(String idPersona, TicketBean ticketBean, String tipoMensaje, String tipoNotificacion,
			String from, String to, MapParameterBean key, String canal,String url) {
		super();
		this.idPersona = idPersona;
		this.ticketBean = ticketBean;
		this.tipoMensaje = tipoMensaje;
		this.tipoNotificacion = tipoNotificacion;
		this.from = from;
		this.to = to;
		this.key = key;
		this.canal = canal;
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public TicketBean getTicketBean() {
		return ticketBean;
	}
	public void setTicketBean(TicketBean ticketBean) {
		this.ticketBean = ticketBean;
	}
	public String getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public MapParameterBean getKey() {
		return key;
	}
	public void setKey(MapParameterBean key) {
		this.key = key;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	
	
	

}
