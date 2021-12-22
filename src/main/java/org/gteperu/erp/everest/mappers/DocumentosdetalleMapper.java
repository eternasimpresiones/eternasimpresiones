package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Documentosdetalle;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface DocumentosdetalleMapper {

    @Select("select iddocumentosdetalle,idclasificadorcostos,iddocumentos,idconcepto,idunidadmedida,costounitario,importe,fecharegistro,cantidad from documentosdetalle ;")
    public List<Documentosdetalle> retornaDocumentosdetalle();

    @Select("select iddocumentosdetalle,idclasificadorcostos,iddocumentos,idconcepto,idunidadmedida,costounitario,importe,fecharegistro,cantidad from documentosdetalle where estado='1';")
    public List<Documentosdetalle> retornaDocumentosdetalleTodas();

    @Select(" SELECT iddocumentosdetalle, iddocumentos, idconcepto, idunidadmedida,        costounitario, importe, fecharegistro, cantidad, idordenguia,        idclasificadorcostos, estadoguiado, cantidadguiada  FROM documentosdetalle where iddocumentosdetalle=#{iddocumentosdetalle}")
    public Documentosdetalle retornaDocumentosdetallePorId(Documentosdetalle d);

    public List<Documentosdetalle> retornaDocumentosdetallePorEstado(@Param("documentosdetalle") Documentosdetalle iddocumentosdetalle);

    public List<Documentosdetalle> retornaDocumentosdetalleLikePorNombre(@Param("documentosdetalle") Documentosdetalle iddocumentosdetalle);

    @Select("SELECT fd.diasentrega,og.idordencompra,fd.idclasificadorcostos,fd.iddocumentosdetalle, fd.iddocumentos, fd.idconcepto, \n"
    + "fd.idunidadmedida, fd.costounitario,fd.importe, fd.fecharegistro, \n"
    + "fd.cantidad, fd.idordenguia FROM documentosdetalle fd inner join ordenguia og on og.idordenguia=fd.idordenguia "
    + "where og.idordencompra=#{idordencompra};")
    public List<Documentosdetalle> retornaObjDocumentosdetalle(@Param("idordencompra") Integer idordencompra);

    @Update("update documentosdetalle set  idclasificadorcostos=#{idclasificadorcostos},iddocumentos=#{iddocumentos}, idconcepto=#{idconcepto}, idunidadmedida=#{idunidadmedida}, costounitario=#{costounitario}, importe=#{importe}, fecharegistro=#{fecharegistro}, cantidad=#{cantidad} where iddocumentosdetalle =#{iddocumentosdetalle};")
    public Integer updateDocumentosdetalle(Documentosdetalle iddocumentosdetalle);

    @Update("update documentosdetalle set estadoguiado=#{estadoguiado}, cantidadguiada=#{cantidadguiada} where iddocumentosdetalle =#{iddocumentosdetalle};")
    public Integer updateDocumentosdetalleCantidadGuiada(Documentosdetalle iddocumentosdetalle);

    @Insert(" insert into documentosdetalle(diasentrega,iddocumentos,idconcepto,idunidadmedida,costounitario,importe,fecharegistro,cantidad)values(#{diasentrega},#{iddocumentos},#{idconcepto},#{idunidadmedida},#{costounitario},#{importe},#{fecharegistro},#{cantidad});")
    @SelectKey(statement = "select currval('sec_documentosdetalle') as iddocumentosdetalle", keyProperty = "iddocumentosdetalle", before = false, resultType = int.class)
    @Options(useGeneratedKeys = true, keyProperty = "iddocumentosdetalle", flushCache = FlushCachePolicy.TRUE)
    public Integer insertaDocumentosdetalle(Documentosdetalle iddocumentosdetalle);

    public Integer insertaDocumentosdetallelist(@Param("list") List<Documentosdetalle> lsDetalle, @Param("idcodigodocumentos") Integer idcodigofactura);
    public Integer insertaDocumentosdetallelistDesdeNotaPedido(@Param("list") List<Documentosdetalle> lsDetalle, @Param("idcodigodocumentos") Integer idcodigofactura);

    @Delete(" delete from  documentosdetalle where iddocumentosdetalle=#{iddocumentosdetalle}; ")
    public Integer eliminaDocumentosdetalle(Documentosdetalle iddocumentosdetalle);
}
