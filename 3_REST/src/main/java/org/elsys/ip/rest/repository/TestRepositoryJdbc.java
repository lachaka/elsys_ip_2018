package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRepositoryJdbc {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    static final String USER = "root";
    static final String PASS = "root";

    public List<Test> getTestList() {
        List<Test> tests = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT id, name FROM test";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Test t = new Test();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                tests.add(t);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tests;
    }

}
