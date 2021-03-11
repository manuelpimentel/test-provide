package com.example.test.provideTest.DAO.config;

import com.example.test.provideTest.exceptions.DAOCommandException;

/**
 * DAO Command interface
 *
 * This interface is the contract implemented by all the commands that serves as a DAO Command.
 *
 * @author Manuel Pimentel
 * @param <T> Entity that will be worked by the DAO Command
 */
public interface IDAOCommand<T> {

    /**
     * Executes the code in the DAO Command
     *
     * @return An instance of the entity worked by the DAO Command.
     * @throws DAOCommandException Thrown if any exception happen in the execution of the DAO Command.
     */
    T execute() throws DAOCommandException;

    /**
     * Receives the objects to be worked by the DAO Command
     *
     * @param args Variable arguments needed to execute the DAO Command.
     * @throws DAOCommandException Thrown if any exception happen in the execution of the DAO Command.
     */
    void setAttributes(Object... args) throws DAOCommandException;
}
