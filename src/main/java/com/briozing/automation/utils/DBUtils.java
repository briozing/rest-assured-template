package com.briozing.automation.utils;

import com.briozing.automation.common.DBConnection;
import com.briozing.automation.factory.Log4JFactory;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author KohitijDas
 */
public class DBUtils {

    private JdbcTemplate jdbcTemplate = null;

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    public DBUtils(DBConnection dbConnection) {
        this.jdbcTemplate = dbConnection.getJDBCTemplate();
    }

    @SuppressWarnings("unused")
    private DBUtils() {
    }

    public int cleanTable(String tableName, String whereClause) {
        String query = "delete from " + tableName;
        if (whereClause != null)
            query += " where " + whereClause;
        try {
            MainUtils.reportingLog("Clean Table query: " + query, logger);
            return jdbcTemplate.update(query);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return 0;
    }

    public Map<String, Object> getRecord(String query) {
        try {
            Map<String, Object> record = jdbcTemplate.queryForMap(query);
            if (!record.isEmpty())
                logger.info(record.toString());
            return record;
        } catch (Exception ex) {
            logger.info("Error while getting record");
        }
        return null;
    }
}
