package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Files;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface FilesService {

    public List<Files> retornaFiles();

    public List<Files> retornaFilesTodas();

    public List<Files> retornaFilesPorEstado(Files idfile);

    public List<Files> retornaFilesLikePorNombre(Files idfile);

    public Files retornaObjFiles(Files idfile);

    public Integer updateFiles(Files idfile);

    public Integer insertaFiles(Files idfile);

    public Integer eliminaFiles(Files idfile);
 public ResponseEntity<byte[]> obtenerFoto(String nombreFoto,String ruta)
            throws IOException;
    public ResponseEntity<byte[]> obtenerLogoEmpresqa(String nombreFoto,String ruta)
            throws IOException;
}