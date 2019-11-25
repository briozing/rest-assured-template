package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author KohitijDas
 */
public class ObjectMapperUtils {

    private static Logger logger = Log4JFactory.getLogger("ObjectMapper MainUtils Logger");

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Json file to java pojo conversion
     *
     * @param filepath
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String filepath, Class<T> returnType) {
        logger.debug("*** filepath: " + filepath);
        try {
            File f = new File(filepath);
            logger.debug("Is File object is Null : " + (f == null) + "");
            return objectMapper.readValue(f, returnType);
        } catch (UnrecognizedPropertyException e) {
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
        } catch (JsonParseException e) {
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");

        } catch (JsonMappingException e) {
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");

        } catch (IOException e) {
            logger.info("IOException occurs ");
            AppAssert.assertTrue(false, "JSON Exception");
        }

        return null;
    }

    /**
     * Json String to Java pojo conversion
     *
     * @param jsonString
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T readValueFromString(String jsonString, Class<T> returnType) {
        try {
            return objectMapper.readValue(jsonString, returnType);
        } catch (UnrecognizedPropertyException e) {
            logger.info("UnrecognizedPropertyException occurs  in JSON " + jsonString);
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
        } catch (JsonParseException e) {
            logger.info("JsonParseException occurs In JSON " + jsonString);
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
        } catch (JsonMappingException e) {
            logger.info("JsonMappingException occurs In JSON" + jsonString);
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
        } catch (IOException e) {
            logger.info("IOException occurs   " + jsonString);
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
        }

        return null;
    }

    /**
     * Object to JSON as String
     *
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.info("JSON Error " + e);
        }
        return s;
    }

    /**
     * @param value
     * @param filepath
     * @return
     */
    public static boolean writeValueInFile(Object value, String filepath) {

        File f = new File(filepath);

        logger.info("Is File object is Null : " + (f == null) + "");
        try {

            objectMapper.writeValue(f, value);
            return true;
        } catch (JsonProcessingException e) {
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
            return false;
        } catch (IOException e) {
            logger.info("JSON Error " + e);
            AppAssert.assertTrue(false, "JSON Exception");
            return false;
        }
    }

    /**
     * POJO to soap
     *
     * @param obj
     * @return
     */
    public static String marshall(Object obj) {
        String output = null;
        try {
            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.marshal(obj, sw);
            output = sw.toString();
        } catch (JAXBException jb) {
            logger.info("Error occured while marshalling object to xml {}" + jb.getMessage());
            logger.info(jb.getMessage());
            AppAssert.assertTrue(false, "JSON Exception");
        }

        return output;
    }
}