package com.briozing.automation.common;

import com.briozing.automation.factory.JDBCTemplateFactory;
import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.utils.AppAssert;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

/**
 * Every service will  create DBConnection object with passed properties
 * <p>
 * DB will get initilise when object created and need to call closeconnection funciton once work done.
 * <p>
 * <p>
 * default constructor is private for fail safe
 *
 * @author KohitijDas
 */
public class DBConnection {

    private String dbServer;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private JdbcTemplate jDBCTemplate;

    /**
     * created so that no object getting create without db details like port, host,
     * server, user name and password
     */
    @SuppressWarnings("unused")
    private DBConnection() {
    }

    public DBConnection(String dbServer, String dbPort, String dbName, String dbUser, String dbPassword) {
        this.dbName = dbName;
        this.dbPort = dbPort;
        this.dbServer = dbServer;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        init();
    }

    private void init() {
        if (jDBCTemplate == null) {
            jDBCTemplate = JDBCTemplateFactory.getDbConnection(dbServer, dbPort, dbName, dbUser, dbPassword);
        }
    }

    public JdbcTemplate getJDBCTemplate() {
        return jDBCTemplate;
    }

    /**
     * @throws SQLException
     */
    public void closeDbConnection() {
        try {
            if (jDBCTemplate != null && jDBCTemplate.getDataSource() != null)
                if (jDBCTemplate.getDataSource().getConnection() != null)
                    if (!jDBCTemplate.getDataSource().getConnection().isClosed()) {
                        Log4JFactory.getCommonLogger().info("==========closing Database Connection==============");
                        jDBCTemplate.getDataSource().getConnection().close();
                    }
        } catch (SQLException e) {
            Log4JFactory.getCommonLogger().info("==========Closed Database Connection==============");
            AppAssert.assertTrue(false, "Error while closing connection");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        closeDbConnection();
        super.finalize();
    }

}
