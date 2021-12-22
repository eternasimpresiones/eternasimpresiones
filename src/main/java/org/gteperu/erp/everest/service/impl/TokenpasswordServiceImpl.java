package org.gteperu.erp.everest.service.impl;

import org.springframework.stereotype.Service;
import org.gteperu.erp.everest.domain.Tokenpassword;
import org.gteperu.erp.everest.mappers.TokenpasswordMapper;
import org.gteperu.erp.everest.service.TokenpasswordService;

import java.util.List;
import javax.annotation.Resource;

@Service("tokenpasswordService")
public class TokenpasswordServiceImpl implements TokenpasswordService {

    @Resource(name = "tokenpasswordMapper")
    TokenpasswordMapper tokenpasswordMapper;

   

    @Override
    public List<Tokenpassword> retornaObjTokenpassword(Tokenpassword idtokenpassword) {
        return tokenpasswordMapper.retornaObjTokenpassword(idtokenpassword);
    }
 @Override
    public Integer eliminaTokenpasswordGlobal(Tokenpassword idtokenpassword) {
        return tokenpasswordMapper.eliminaTokenpasswordGlobal(idtokenpassword);
    }
    @Override
    public Integer updateTokenpassword(Tokenpassword idtokenpassword) {
        return tokenpasswordMapper.updateTokenpassword(idtokenpassword);
    }

    @Override
    public Integer insertaTokenpassword(Tokenpassword idtokenpassword) {
        return tokenpasswordMapper.insertaTokenpassword(idtokenpassword);
    }

    @Override
    public Integer eliminaTokenpassword(Tokenpassword idtokenpassword) {
        return tokenpasswordMapper.eliminaTokenpassword(idtokenpassword);
    }

    @Override
    public Tokenpassword retornaTokenObjeto(Tokenpassword t) {
        return tokenpasswordMapper.retornaTokenObjeto(t);
    }

    @Override
    public Integer consumoTokenpassword(Tokenpassword idtokenpassword) {
        return tokenpasswordMapper.consumoTokenpassword(idtokenpassword);
    }
}