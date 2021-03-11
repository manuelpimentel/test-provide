package com.example.test.provideTest.DAO.atomic.param;

import com.example.test.provideTest.DAO.config.DAOCommand;
import com.example.test.provideTest.domain.Param;
import com.example.test.provideTest.exceptions.DAOCommandException;
import com.example.test.provideTest.exceptions.errorDictionaries.DAOCommandErrorDictionary;
import com.example.test.provideTest.repositories.ParamRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

/**
 * Get Param By Param Key
 *
 * DAO Command implementation to retrieve a Param entity instancy by ParamKey attribute
 * @author Manuel Pimentel
 */
@Component
@Log
public class GetParamByParamKey extends DAOCommand<Param> {
    private String _paramKey;

    @Autowired
    private ParamRepository paramRepository;

    @Override
    public Param execute() throws DAOCommandException {
        try {
            final Optional<Param> optionalParam = paramRepository.findParamBy_paramKey(_paramKey);
            if (!optionalParam.isPresent())
                throw new DAOCommandException(DAOCommandErrorDictionary.NO_RESULT.getErrorMessage());
            return optionalParam.get();
        } catch (NonUniqueResultException e) {
            throw new DAOCommandException(DAOCommandErrorDictionary.MORE_THAN_ONE_RESULT.getErrorMessage(), e);
        }
    }

    @Override
    public void setAttributes(Object... args) throws DAOCommandException {
        try {
            _paramKey = (String) args[0];
        }catch (Exception e)
        {
            throw new DAOCommandException(DAOCommandErrorDictionary.INVALID_DATA.getErrorMessage(), e);
        }
    }
}
