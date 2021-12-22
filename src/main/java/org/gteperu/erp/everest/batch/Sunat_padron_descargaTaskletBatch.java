

package org.gteperu.erp.everest.batch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.gteperu.erp.everest.domain._Parametros;
import org.gteperu.erp.everest.service.ParametrosService;
import org.gteperu.erp.everest.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;


public class Sunat_padron_descargaTaskletBatch implements Tasklet, StepExecutionListener{

	//@Autowired
	ParametrosService parametrosService;
	
	private static final Logger LOG = LoggerFactory.getLogger(Sunat_padron_descargaTaskletBatch.class);
	String separadorOS = System.getProperty("file.separator");
	
	String rutaSunatFiles ="";
	String URLPadronZip ="";
	String nombre_padron_txt ="";
	
	public Sunat_padron_descargaTaskletBatch() {
	}
	
	public Sunat_padron_descargaTaskletBatch(ParametrosService parametrosService) {
		this.parametrosService = parametrosService;
	}



	@Override
	public void beforeStep(StepExecution stepExecution) {
		try {
			List<_Parametros> ls_par = new ArrayList<_Parametros>();
			_Parametros par = new _Parametros();
			par.setGrupo(Constantes.codigoGrupoSunatPadron);
			par.setId_empresa(0);
			ls_par = parametrosService.retornaObjParametrosPorGrupoe(par);
			
			if(ls_par!=null && ls_par.size()>0){
				for(_Parametros p : ls_par){
					if(p.getCodigo().equals(Constantes.codigoRutaSunatPadron)){
						rutaSunatFiles = p.getValor();
					}
					if(p.getCodigo().equals(Constantes.codigoURLDescargaPadron)){
						URLPadronZip = p.getValor();
					}
				}
			}else{
				throw new RuntimeException(Constantes.msgErrorSunatPadronParametros);
			}
			LOG.info("Sunat_padron_descargaTaskletBatch Inicializado");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try{
			String ruta_completa_zip = rutaSunatFiles+ separadorOS + Constantes.nombreSunatPadronZip;
			try{
				// Url sunat
				URL url = new URL(URLPadronZip);
				// establecemos conexion
				URLConnection urlCon = url.openConnection();
				
				// Sacamos por pantalla el tipo de fichero
				System.out.println(urlCon.getContentType());
				
				Files.deleteIfExists(Paths.get(ruta_completa_zip));
				
				// Se obtiene el inputStream de la foto web y se abre el fichero
				// local.
				InputStream is = urlCon.getInputStream();
				FileOutputStream fos = new FileOutputStream(ruta_completa_zip);
				
				// Lectura de la foto de la web y escritura en fichero local
				byte[] array = new byte[1000]; // buffer temporal de lectura.
				int leido = is.read(array);
				while (leido > 0) {
					fos.write(array, 0, leido);
					leido = is.read(array);
				}
				
				// cierre de conexion y fichero.
				is.close();
				fos.close();
				System.out.println("TERMINO LA DESCARGA");
			}catch(Exception e){
				System.err.println("FALLO LA DESCARGA => http://www2.sunat.gob.pe/padron_reducido_ruc.zip ");
				System.err.println(e.getMessage());
				throw new RuntimeException();
			}
			
			
			///////// -----DESCOMPRESION -----////////
			try{
				// ruta donde están los archivos .zip
				java.io.File zipExtraer = new java.io.File(rutaSunatFiles);
				
				// valida si existe el directorio
				if (zipExtraer.exists()) {
					System.out.println("Nombre del fichero: " + Constantes.nombreSunatPadronZip);
					System.out.println("Descomprimiendo.....");
					try {
						
						// crea un buffer temporal para el archivo que se va
						// descomprimir
						ZipInputStream zis = new ZipInputStream(new FileInputStream(ruta_completa_zip));
						ZipEntry salida;
						
						while (null != (salida = zis.getNextEntry())) {
							//borra el archivo si existe
							nombre_padron_txt = salida.getName();
							Files.deleteIfExists(Paths.get(rutaSunatFiles + separadorOS + salida.getName()));
							System.out.println("Nombre del Archivo: " + salida.getName());
							FileOutputStream fos_unzip = new FileOutputStream(rutaSunatFiles + separadorOS + salida.getName());
							int leer;
							byte[] buffer = new byte[1024];
							while (0 < (leer = zis.read(buffer))) {
								fos_unzip.write(buffer, 0, leer);
							}
							fos_unzip.close();
							zis.closeEntry();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Directorio de salida: " + rutaSunatFiles);
				} else {
					System.out.println("No se encontró el directorio.. : " + rutaSunatFiles);
				}

			}catch(Exception e){
				System.err.println("FALLO LA DESCOMPRESION => padron_reducido_ruc.txt");
				System.err.println(e.getMessage());
				throw new RuntimeException();
			}
		
		} catch (Exception e) {
			System.err.println("ERROR: "+ this.getClass().getSimpleName()  + "/execute => " + e.getMessage());
			System.err.println(this.getClass().getSimpleName() + "/execute" + "Linea nro : "+e.getStackTrace()[0].getLineNumber());
			throw new RuntimeException();
		}
		return RepeatStatus.FINISHED;
	}

	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		try {
			if(nombre_padron_txt!=null && nombre_padron_txt!=""){
				stepExecution.getJobExecution().getExecutionContext().putString("ruta_padron_txt",rutaSunatFiles + separadorOS +nombre_padron_txt );				
			}
	        LOG.info("Sunat_padron_descargaTaskletBatch_Step Termino.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return ExitStatus.COMPLETED;
	}
	
	
	
}
