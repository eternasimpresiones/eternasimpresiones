package org.gteperu.erp.everest.mappers;

import org.gteperu.erp.everest.domain.Pagination;
import org.gteperu.erp.everest.domain.Subgrupo;
import org.gteperu.erp.everest.domain.Sunat_padron;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface SunatPadronMapper {

public Integer insertaRucSunatPadronA(@Param("list") List<Sunat_padron> lsSunatPadron);
    
    public Integer insertaRucSunatPadronB(@Param("list") List<Sunat_padron> lsSunatPadron);
    
    @Delete("TRUNCATE sunat_padron_a;")
    public Integer eliminaRucSunatPadronA();
    
    @Delete("TRUNCATE sunat_padron_b;")
    public Integer eliminaRucSunatPadronB();
    
//    @Select("Select * from sunat_padron where estado=#{estado};")
    @Select("Select * from sunat_padron")
    public Sunat_padron retornaTablaPorEstado(Sunat_padron sunat_padron);
    
    @Update("update sunat_padron set estado=#{estado} where id_sunat_padron_i =#{id_sunat_padron_i};")
    public Integer updateEstadoSunatPadron(Sunat_padron sunat_padron);
    
    @Select("Select id_sunat_padron,ruc,contenido from sunat_padron_a where id_sunat_padron=#{id_sunat_padron};")
    public Sunat_padron consultaRuc_a(Sunat_padron sunat_padron);
    
    @Select("Select id_sunat_padron,ruc,contenido from sunat_padron_b where id_sunat_padron=#{id_sunat_padron};")
    public Sunat_padron consultaRuc_b(Sunat_padron sunat_padron);
    
    
}