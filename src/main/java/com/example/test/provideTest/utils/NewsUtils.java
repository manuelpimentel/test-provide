package com.example.test.provideTest.utils;

import com.example.test.provideTest.exceptions.ObjectParserException;
import com.example.test.provideTest.exceptions.errorDictionaries.ObjectParserErrorDictionary;
import com.example.test.provideTest.utils.http.dtos.response.RSS.RSSItemElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * News Utils
 *
 * Util functions to handle the News entity.
 * @author Manuel Pimentel
 */
@Component
public class NewsUtils {
    /**
     * RSS_DEFAULT_DATE_FORMAT
     * Constant of the RSS default date format
     */
    public static final String RSS_DEFAULT_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss Z";
    /**
     * ID_FORMAT
     * Constant of the format of the News Entity unique identifier.
     */
    private static final String ID_FORMAT = "ddMMyyhhmmss";

    /**
     * Parse News Id
     *
     * Method that parse the RSS News item to generate an unique id based on the publication date of the News.
     *
     * @param item RSSItemElement of the RSS News API.
     * @return Created unique identifier in Long format
     * @throws ObjectParserException Thrown if any error occurs during the parsing of the item.
     */
    public Long parseNewsId(RSSItemElement item) throws ObjectParserException {
        String parsedId = parseDateToStringDate(null, parseStringDateToDate(null, item.get_date()));
        return Long.valueOf(parsedId);
    }

    /**
     * Parse String Date To Date
     *
     * Method to convert an String date to Date data type
     *
     * @param format The format of the String date to parse
     * @param strDate Date in String format
     * @return Date converted to Date data type
     * @throws ObjectParserException Thrown if any error occurs during the parsing of the date.
     */
    public Date parseStringDateToDate(String format, String strDate) throws ObjectParserException {
        try {
            return new SimpleDateFormat(format != null ? format : RSS_DEFAULT_DATE_FORMAT).parse(strDate);
        } catch (IllegalArgumentException | NullPointerException | ParseException e) {
            throw new ObjectParserException(ObjectParserErrorDictionary.DATE_PARSING_ERROR.getErrorMessage(), e);
        }
    }

    /**
     * Parse Date To String Date
     *
     * Method to convert a date to String
     *
     * @param format The format of the date to format
     * @param date Date in Date format
     * @return Date converted to String
     * @throws ObjectParserException Thrown if any error occurs during the parsing of the date.
     */
    private String parseDateToStringDate(String format, Date date) throws ObjectParserException {
        try {
            return new SimpleDateFormat(format != null ? format : ID_FORMAT).format(date);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ObjectParserException(ObjectParserErrorDictionary.DATE_PARSING_ERROR.getErrorMessage(), e);
        }
    }

    /**
     * Clean HTML Content
     *
     * Method that cleans a string value of any HTML tag.
     *
     * @param value value to clean
     * @return cleaned string
     */
    public String cleanHTMLContent( String value )
    {
        return Jsoup.clean(value, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }
}
