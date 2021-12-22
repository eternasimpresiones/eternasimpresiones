package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Files;
import org.gteperu.erp.everest.mappers.FilesMapper;
import org.gteperu.erp.everest.service.FilesService;
import org.gteperu.erp.everest.utils.Constantes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Resource;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Service("filesService")
public class FilesServiceImpl implements FilesService {

	@Resource(name = "filesMapper")
	FilesMapper filesMapper;

	@Override
	public List<Files> retornaFiles() {
		List<Files> lsFiles = new ArrayList<Files>();
		lsFiles = filesMapper.retornaFiles();
		return lsFiles;
	}

	@Override
	public List<Files> retornaFilesTodas() {
		List<Files> lsFiles = new ArrayList<Files>();
		lsFiles = filesMapper.retornaFilesTodas();
		return lsFiles;
	}

	@Override
	public List<Files> retornaFilesLikePorNombre(Files idfile) {
		List<Files> lsFiles = new ArrayList<Files>();
		lsFiles = filesMapper.retornaFilesLikePorNombre(idfile);
		return lsFiles;
	}

	@Override
	public List<Files> retornaFilesPorEstado(Files idfile) {
		List<Files> lsFiles = new ArrayList<Files>();
		lsFiles = filesMapper.retornaFilesPorEstado(idfile);
		return lsFiles;
	}

	@Override
	public Files retornaObjFiles(Files idfile) {
		return filesMapper.retornaObjFiles(idfile);
	}

	@Override
	public Integer updateFiles(Files idfile) {
		return filesMapper.updateFiles(idfile);
	}

	@Override
	public Integer insertaFiles(Files idfile) {
		return filesMapper.insertaFiles(idfile);
	}

	@Override
	public Integer eliminaFiles(Files idfile) {
		return filesMapper.eliminaFiles(idfile);
	}

	@Override
	public ResponseEntity<byte[]> obtenerFoto(String nombreFoto,String gg) throws IOException {

		String path = gg;

		InputStream in = new FileInputStream(path + nombreFoto);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<byte[]> obtenerLogoEmpresqa(String nombreFoto,String gg) throws IOException {

		String path = gg;

		InputStream in = new FileInputStream(path + nombreFoto);

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	}
}