package org.gteperu.erp.everest.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.gteperu.erp.everest.domain.Det_doc;
import org.gteperu.erp.everest.domain.Documento;

@Mapper
public interface Det_docMapper {
	
	@Insert("INSERT INTO det_doc(id_doc,id_producto,costo_unitario_me,costo_unitario_mn,cuenta_contable,cant_regist_docu) "
			+ "VALUES (#{id_doc},#{id_producto},#{costo_unitario_me},#{costo_unitario_mn},#{cuenta_contable},#{cant_regist_docu})")
	public Integer insertaDet_doc_ingreso(Det_doc det_doc);
    @Update("delete from det_doc  WHERE id_det_doc=#{id_det_doc}")
    public Integer eliminarDetdoc(Det_doc det_doc);
}
