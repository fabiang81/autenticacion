package mx.com.beo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilerias {

	private static final Logger LOGGER = LoggerFactory.getLogger(Utilerias.class);

	/**
	 * Establece un formato de fecha.
	 * 
	 * @param fecha a la que se desea dar formato.
	 * @return fecha con formato establecido dd-MM-yyyy HH:mm:ss
	 */
	public static String formatoFecha(Date fecha, String formatoFecha) throws NullPointerException {
		SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
		String fechaFormato = formato.format(fecha);

		return fechaFormato;
	}

	/**
	 * Establece un formato de fecha.
	 * 
	 * @param fecha a la que se desea dar formato.
	 * @return fecha con formato establecido yyyy-MM-dd'T'HH:mm:ss
	 */
	public static String fechaFormatoServicio(String fecha, String formatoFecha) {

		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		String fechaFormato = "";
		Date date = null;

		try {
			date = sdf.parse(fecha);
			fechaFormato = formatoFecha(date, Constantes.FORMATO_FECHA_SERVICIO);
		} catch (Exception e) {
			LOGGER.error(Constantes.ERROR_FORMATO_FECHA_SERVICIO, e);
		}

		return fechaFormato;
	}

}
