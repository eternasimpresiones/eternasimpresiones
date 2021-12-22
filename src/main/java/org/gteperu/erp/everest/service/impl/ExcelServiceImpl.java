package org.gteperu.erp.everest.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gteperu.erp.everest.domain.Documentos;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.service.ExcelService;
import org.springframework.stereotype.Service;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

	 

	@Override
	public XSSFWorkbook excelReporteVenta(String appPath, List<_DocumentoCpe> lista, _Company company) throws IOException {
		_Company datos_empresa = company;
		FileInputStream fsIP = new FileInputStream(appPath);
		// HSSFWorkbook -> para file excel 97 al 2007
		// obj que tendra el file excel "wb"
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		// Indice de la hoja Excel
		XSSFSheet worksheet = wb.getSheetAt(0);
		Integer ind_aux = 1;

		/***** Inicio Estilos *****/
		// Estilo -- 01
		CellStyle cab_right_sty = wb.createCellStyle();
		Font cab_font = wb.createFont();
		cab_font.setFontHeightInPoints((short) 15); // tamaño
		cab_font.setFontName("Arial"); // fuente
		cab_font.setBold(true); // negrita
		cab_font.setColor(IndexedColors.BLUE1.getIndex()); // color
		cab_right_sty.setFont(cab_font);

		// Estilo -- 02
		CellStyle cab_right_sty2 = wb.createCellStyle();
		Font cab_font2 = wb.createFont();
		cab_font2.setFontHeightInPoints((short) 15); // tamaño
		cab_font2.setFontName("Arial"); // fuente
		cab_font2.setBold(true); // negrita
		cab_right_sty2.setFont(cab_font2);

		/***** Fin Estilos *****/

		XSSFRow fila2 = worksheet.createRow(ind_aux);
		XSSFCell A2 = fila2.createCell(0);
		A2.setCellValue(datos_empresa.getRazon_social_empresa());
		A2.setCellStyle(cab_right_sty);

		ind_aux++;
		XSSFRow fila3 = worksheet.createRow(ind_aux);
		XSSFCell A3 = fila3.createCell(0);
		A3.setCellValue("RUC: " + datos_empresa.getNro_documento_empresa());
		A3.setCellStyle(cab_right_sty);

		ind_aux = 4;
		Date fechaactual = new Date();
		String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechaactual);
		XSSFRow fila5 = worksheet.createRow(ind_aux);
		XSSFCell A4 = fila5.createCell(0);
		A4.setCellValue(fecha);
		A4.setCellStyle(cab_right_sty2);

		ind_aux = 7;
		
		CellStyle border = wb.createCellStyle();
		border.setBorderTop(BorderStyle.THIN);
		border.setBorderBottom(BorderStyle.THIN);
		border.setBorderLeft(BorderStyle.THIN);
		border.setBorderRight(BorderStyle.THIN);

		for (_DocumentoCpe orden : lista) {
			XSSFRow fil_dg = worksheet.createRow(ind_aux);
			XSSFCell A_dgd = fil_dg.createCell(0);
			XSSFCell B_dgd = fil_dg.createCell(1);
			XSSFCell C_dgd = fil_dg.createCell(2);
			XSSFCell D_dgd = fil_dg.createCell(3);
			XSSFCell E_dgd = fil_dg.createCell(4);
			XSSFCell F_dgd = fil_dg.createCell(5);
			XSSFCell G_dgd = fil_dg.createCell(6);
			XSSFCell H_dgd = fil_dg.createCell(7);
			XSSFCell I_dgd = fil_dg.createCell(8);
			XSSFCell J_dgd = fil_dg.createCell(9);
			XSSFCell K_dgd = fil_dg.createCell(10);
			XSSFCell L_dgd = fil_dg.createCell(11);
			XSSFCell M_dgd = fil_dg.createCell(12);
			A_dgd.setCellStyle(border);
			B_dgd.setCellStyle(border);
			C_dgd.setCellStyle(border);
			D_dgd.setCellStyle(border);
			E_dgd.setCellStyle(border);
			F_dgd.setCellStyle(border);
			G_dgd.setCellStyle(border);
			H_dgd.setCellStyle(border);
			I_dgd.setCellStyle(border);
			J_dgd.setCellStyle(border);
			K_dgd.setCellStyle(border);
			L_dgd.setCellStyle(border);
			M_dgd.setCellStyle(border);

			/* XSSFCell N_dgd = fil_dg.createCell(13);
			XSSFCell O_dgd = fil_dg.createCell(14);
			XSSFCell P_dgd = fil_dg.createCell(15);
			XSSFCell Q_dgd = fil_dg.createCell(16);
			XSSFCell R_dgd = fil_dg.createCell(17);
			XSSFCell S_dgd = fil_dg.createCell(18);
*/
			Timestamp foc = orden.getFecha_documento();
			Date date_foc = new Date();
			date_foc.setTime(foc.getTime());
			String fechaordencompra = new SimpleDateFormat("dd/MM/yyyy").format(date_foc);
		
			
			
			Timestamp focvct = orden.getFecha_vto();
			Date date_focvc5 = new Date();
			date_focvc5.setTime(date_focvc5.getTime());
			String fechaovct = new SimpleDateFormat("dd/MM/yyyy").format(date_focvc5);
			
			Timestamp focreg = orden.getTfecha_registro();
			Date date_focreg = new Date();
			date_focreg.setTime(focreg.getTime());
			String fechareg = new SimpleDateFormat("dd/MM/yyyy").format(date_focreg);
			/*String fechaentrega = "";
		 	if (orden.getFecha_registro() != null) {
				Timestamp fe = orden.get.getFecha_registro();
				Date date_fe = new Date();
				date_fe.setTime(fe.getTime());
				fechaentrega = new SimpleDateFormat("dd/MM/yyyy").format(date_fe);
			}
*/
		 	A_dgd.setCellValue(orden.getNombreTipoComprobante());
			B_dgd.setCellValue(fechaordencompra); //fecha de emision
		 	C_dgd.setCellValue(fechareg); // fecha de registro
			D_dgd.setCellValue(orden.getSerie_comprobante());
			E_dgd.setCellValue(orden.getNro_comprobante());
			F_dgd.setCellValue(orden.getClientes().getNro_doc());
			G_dgd.setCellValue(orden.getClientes().getRazon_social());
			H_dgd.setCellValue(orden.getSub_total());
			I_dgd.setCellValue(orden.getTotal_igv());
			J_dgd.setCellValue(orden.getTotal());
 			K_dgd.setCellValue(orden.getTipo_cambio() != null ? ""+orden.getTipo_cambio() : "0.0");
 			L_dgd.setCellValue(orden.getObservacion());
	//		M_dgd.setCellValue(orden.getCondicionespago().getDescripcion());
	//		if (orden.getEntregadias() != null) {
	//			N_dgd.setCellValue(orden.getEntregadias());
	//		}
 			M_dgd.setCellValue(fechaovct);
//			P_dgd.setCellValue(orden.get);
//			Q_dgd.setCellValue(orden.get);
//			R_dgd.setCellValue(orden.get);
//			S_dgd.setCellValue(orden.get);

			ind_aux++;
		}
		return wb;
	}

}