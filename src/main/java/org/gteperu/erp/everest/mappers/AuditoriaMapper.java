package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Auditoria;
import org.gteperu.erp.everest.domain.Pagination;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface AuditoriaMapper {

    public List<Auditoria> retornaAuditoriaTodas(@Param("auditoria") Auditoria idauditoria);

    public List<Auditoria> retornaAuditoriaPorFiltro(@Param("auditoria") Auditoria idauditoria);

    @Select( "select idauditoria,mensaje,tabla,metodo,accion,idregistro,fecharegistro,idempleado from auditoria where idauditoria =#{idauditoria}")
    public Auditoria retornaObjAuditoria(Auditoria idauditoria);

 
    @Insert(" insert into auditoria(metodo,mensaje,tabla,accion,idregistro,idempleado)values(#{metodo},#{mensaje},#{tabla},#{accion},#{idregistro},#{idempleado});")
    public Integer insertaAuditoria(Auditoria idauditoria);

    public Pagination retornaCantidadList(@Param("auditoria") Auditoria idauditoria);
}