package org.jesperancinha.ocp11.crums.crum8;

import org.h2.jdbc.JdbcSQLDataException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.jesperancinha.console.consolerizer.Consolerizer.printBlueGenericTitleLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printGreenGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printRedGenericLn;
import static org.jesperancinha.console.consolerizer.Consolerizer.printRedThrowableAndExit;

public class Crum8 {
    public static void main(String[] args) {
        printBlueGenericTitleLn("Crum 8 - Falling out of indexes in prepared statements");

        final String query = "Select  * from PAINTING where AUTHOR = ?";

        try (final Connection connection = DriverManager.getConnection("jdbc:h2:mem:", "sa", "")) {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                "CREATE TABLE PAINTING (" + "ID INT AUTO_INCREMENT, AUTHOR VARCHAR(500))");
            preparedStatement.executeUpdate();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(0, "Favourite Author");

        } catch (JdbcSQLDataException e) {
            printRedGenericLn(
                "The specific exception is a JdbcSQLDataException which is also a SQLException. Note that Data is not in the name of the base class");
            printRedGenericLn(
                "Index 0 and bellow will not work. Prepared statements only work with 1 based indexes -> %s", e);
        } catch (SQLException throwables) {
            printRedThrowableAndExit(throwables);
        }
        printGreenGenericLn("Generally speaking we get a SQLException if something goes wrong with the database");
        printGreenGenericLn("In our specific case the most specific Exception we get is a JdbcSQLDataException");
    }
}