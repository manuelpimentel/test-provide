package com.example.test.provideTest.utils.http;

import com.example.test.provideTest.exceptions.ObjectParserException;
import com.example.test.provideTest.exceptions.errorDictionaries.ObjectParserErrorDictionary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class HttpUtils {

    public static <T> T parseXMLResponse(String xmlPayload, Class<T> tClass) throws ObjectParserException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlPayload);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ObjectParserException(ObjectParserErrorDictionary.XML_PARSING_ERROR.getErrorMessage(), e);
        }
    }

}
