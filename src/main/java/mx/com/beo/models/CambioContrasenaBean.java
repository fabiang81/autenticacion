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
public class CambioContrasenaBean {
	
	private String 	otp;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	private String url;
	private TicketBean ticketbean;
	private String idPersona;
	private String canal;
	
	
	 
	
	public CambioContrasenaBean(String otp, String oldPassword, String newPassword, String confirmNewPassword,
			String url, TicketBean ticketbean, String idPersona, String canal) {
		super();
		this.otp = otp;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
		this.url = url;
		this.ticketbean = ticketbean;
		this.idPersona = idPersona;
		this.canal = canal;
	}
	
	
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public TicketBean getTicketbean() {
		return ticketbean;
	}
	public void setTicketbean(TicketBean ticketbean) {
		this.ticketbean = ticketbean;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	

}
