package org.gteperu.erp.everest.service;

import org.gteperu.erp.everest.domain.Tokenpassword;
import java.util.List;

public interface TokenpasswordService {



    public List<Tokenpassword> retornaObjTokenpassword(Tokenpassword idtokenpassword);

    public Integer updateTokenpassword(Tokenpassword idtokenpassword);
    public Integer eliminaTokenpasswordGlobal(Tokenpassword idtokenpassword);

    public Integer insertaTokenpassword(Tokenpassword idtokenpassword);

    public Integer eliminaTokenpassword(Tokenpassword idtokenpassword);

    public Tokenpassword retornaTokenObjeto(Tokenpassword t);

    public Integer consumoTokenpassword(Tokenpassword idtokenpassword);
}