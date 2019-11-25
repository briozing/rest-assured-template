package com.briozing.automation.factory;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * to get Db connection common class
 *
 * @author KohitijDas
 */
public class JDBCTemplateFactory {

    private static Logger logger = Log4JFactory.getLogger("JDBCTemplateFactory");

    private JDBCTemplateFactory() {
    }

    public static JdbcTemplate getDbConnection(String dbServer, String dbPort, String dbName, String dbUser,
                                               String bPassword) {
        JdbcTemplate jDBCTemplate = null;
        try {
            String dbUrl = "jdbc:mysql://" + dbServer + ":" + dbPort + "/" + dbName + "";
            logger.info("Databse URL: " + dbUrl);
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl(dbUrl);
            ds.setUsername(dbUser);
            ds.setPassword(bPassword);
            jDBCTemplate = new JdbcTemplate(ds);
            logger.info("Connection created to: " + dbUrl);
        } catch (Exception e) {
            logger.info("Exception in getting DBConnection: " + e);
            return null;
        }

        return jDBCTemplate;
    }

}
