/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gte02
 */

public class Funciones {

	

	public Timestamp retornaTimestampFromDate(Date d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp timestamp = new java.sql.Timestamp(d.getTime());
		return timestamp;
	}

	public String retornaIp() {
		String ip = "";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();
			in.close();
		} catch (MalformedURLException ex) {
			Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			return ip;
		}
	}

	public String sacaid(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return principal.getName();
	}

	

	public String codigoGenerado(int numero, String abrev, int longi) {
		String cod = "0";

		int tamanoAb = abrev.length();
		String codStr = String.valueOf(numero);
		int lengCodStr = codStr.length();
				
		for (int i = 1; i < (longi - lengCodStr); i++) {
			cod = "0" + cod;
		}
		cod = abrev + cod + numero;
		return cod;
	}
	
	
	public String excluirLetrasSerieStr(String cod){
      String sinletras = "";
		for (int i = 0; i < cod.length(); i++) {
		  char el = cod.charAt(i);		  
		  	if(Character.isDigit(el)){
			  sinletras += el;  
		  	}		  
		}		
		return sinletras;
	}

	public Timestamp sumarDiasAFecha(Date fecha, int dias){		
		  Timestamp timestamp = new java.sql.Timestamp(fecha.getTime());
	      if (dias==0) return timestamp;
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(timestamp); 
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  
	      timestamp=new java.sql.Timestamp(calendar.getTimeInMillis());
	      return timestamp; 
	}
	public Timestamp retornaCalendarFromString(String val) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(val);
		Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}

	public Timestamp retornaCalendarFromStringLArge(String val) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(val);
		Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}
	public Timestamp retornaCalendarParaFiltros(String val, int ac) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = formatter.parse(val);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		if (ac == 1) {
			cl.add(Calendar.HOUR, 00);
			cl.add(Calendar.MINUTE, 00);
			cl.add(Calendar.SECOND, 00);
			Timestamp timestamp = new java.sql.Timestamp(cl.getTimeInMillis());
			return timestamp;
		} else if (ac == 2) {
			cl.add(Calendar.HOUR, 23);
			cl.add(Calendar.MINUTE, 59);
			cl.add(Calendar.SECOND, 59);
			Timestamp timestamp = new java.sql.Timestamp(cl.getTimeInMillis());
			return timestamp;
		} else {
			return null;
		}

	}

	public Timestamp sumaDiasTimestam(Timestamp fecha, int ac) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(fecha.getTime());
		cl.add(Calendar.DAY_OF_MONTH, ac);

		Timestamp timestamp = new java.sql.Timestamp(cl.getTimeInMillis());
		return timestamp;

	}

	public java.util.Date retornaDateFromString(String val) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

		Date date = formatter.parse(val);

		return date;

	}

	public Date retornaDateSinhoras(java.util.Date fecha, int t) {
		Calendar cl1 = Calendar.getInstance();
		cl1.setTime(fecha);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (t == 1) {
			cl1.add(Calendar.HOUR, 00);
			cl1.add(Calendar.MINUTE, 00);
			cl1.add(Calendar.SECOND, 00);
		}
		if (t == 2) {
			cl1.add(Calendar.HOUR, 23);
			cl1.add(Calendar.MINUTE, 59);
			cl1.add(Calendar.SECOND, 59);
		}
		String strdate = null;
		strdate = sdf.format(cl1.getTime());
		return cl1.getTime();
	}

	public Date retornaDateCompleto(java.util.Date fecha, int t) {
		Calendar cl1 = Calendar.getInstance();
		cl1.setTime(fecha);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		if (t == 1) {
			cl1.add(Calendar.HOUR, 00);
			cl1.add(Calendar.MINUTE, 00);
			cl1.add(Calendar.SECOND, 00);
		}
		if (t == 2) {
			cl1.add(Calendar.HOUR, 23);
			cl1.add(Calendar.MINUTE, 59);
			cl1.add(Calendar.SECOND, 59);
		}
		String strdate = null;
		strdate = sdf.format(cl1.getTime());
		return cl1.getTime();
	}

	public String getCadenaAlfanumAleatoria(int longitud) {
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public Date retornaDate() {
		java.util.Date fecha = new Date();
		return fecha;
	}

	public int compareTimesTamp(Timestamp t1, Timestamp t2) {

		long l1 = t1.getTime();
		long l2 = t2.getTime();
		if (l2 > l1) {
			return 1;
		} else if (l1 > l2) {
			return -1;
		} else {
			return 0;
		}
	}

	public Long diferenciaFechasSegundos(Calendar cinicio) {

		long milis1, milis2, diff;
		// INSTANCIA DEL CALENDARIO GREGORIANO
		// ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO
		// ANTERIORMENTE
		Calendar fechaact = Calendar.getInstance();
		milis1 = fechaact.getTimeInMillis();
		milis2 = cinicio.getTimeInMillis();
		diff = milis2 - milis1;
		// calcular la diferencia en segundos
		long diffSegundos = Math.abs(diff / 1000);
		// calcular la diferencia en minutos
		long diffMinutos = Math.abs(diff / (60 * 1000));
		long restominutos = diffMinutos % 60;
		// calcular la diferencia en horas
		long diffHoras = (diff / (60 * 60 * 1000));
		// calcular la diferencia en dias
		long diffdias = Math.abs(diff / (24 * 60 * 60 * 1000));
		String devolver = String.valueOf(diffHoras + "H " + restominutos + "m ");
		return diffSegundos;
	}

	

	public Timestamp retornaTimeStamp() {
		Calendar cal = Calendar.getInstance();

		Timestamp later = new Timestamp(cal.getTimeInMillis());
		return later;
	}

	public Timestamp retornaTimeStampAgregado(Timestamp t, Integer cant) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t.getTime());
		cal.add(Calendar.DAY_OF_MONTH, cant);
		Timestamp later = new Timestamp(cal.getTime().getTime());
		return later;
	}

	public static String diferenciaFechas(Calendar cinicio) {
		long milis1, milis2, diff;
		// INSTANCIA DEL CALENDARIO GREGORIANO
		// ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO
		// ANTERIORMENTE
		Calendar fechaact = Calendar.getInstance();
		milis1 = cinicio.getTimeInMillis();
		milis2 = fechaact.getTimeInMillis();
		diff = milis2 - milis1;
		// calcular la diferencia en segundos
		long diffSegundos = Math.abs(diff / 1000);
		// calcular la diferencia en minutos
		long diffMinutos = Math.abs(diff / (60 * 1000));
		long restominutos = diffMinutos % 60;
		// calcular la diferencia en horas
		long diffHoras = (diff / (60 * 60 * 1000));
		// calcular la diferencia en dias
		long diffdias = Math.abs(diff / (24 * 60 * 60 * 1000));

		String devolver = String.valueOf(diffHoras + "H " + restominutos + "m ");
		return devolver;
	}

	public String retornaTimeStamp(String fechaemisionString) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}
	
	public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
	
    public static String acortar(String cadena, int n) {
        Objects.requireNonNull(cadena, "La cadena no puede ser nula.");
        int indice = n > cadena.length() ? 0 : cadena.length() - n;
        return cadena.substring(0, indice);
    }
    
    
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
    StringBuilder builder = new StringBuilder();
    while (count-- != 0) {
    int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
    builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    return builder.toString();
    }
}
