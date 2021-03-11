package com.example.test.provideTest.exceptions.errorDictionaries;

import lombok.Getter;

/**
 * DAO Command Error Dictionary
 *
 * Enum to expose the valid error messages for DAOCommandException
 * @author Manuel Pimentel
 */
public enum DAOCommandErrorDictionary {
    INVALID_DATA("INVALID_DATA", "The provided data is invalid"),
    NO_RESULT("NO_RESULT", "No result found for the executed query"),
    MORE_THAN_ONE_RESULT("MORE_THAN_ONE_RESULT", "More than one result when expecting just one"),
    SAVING_UPDATING_ERROR("SAVING_UPDATING_ERROR", "The entity could not be saved/updated");

    @Getter
    private String errorName;
    @Getter
    private String errorMessage;

    DAOCommandErrorDictionary(String errorName, String errorMessage) {
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }
}
