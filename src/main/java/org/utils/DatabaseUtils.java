
package org.utils;

import org.config.ConfigReader;
import org.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtils {

    public static User getUserById(int id) {

        String url = ConfigReader.get("db.url");
        String dbUsername = ConfigReader.get("db.username");
        String dbPassword = ConfigReader.get("db.password");

        String sql = """
                SELECT username, password
                FROM users
                WHERE id = ?
                """;

        try (
                Connection connection = DriverManager.getConnection(
                        url, dbUsername, dbPassword
                );
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return new User(
                            result.getString("username"),
                            result.getString("password")
                    );
                }

                throw new RuntimeException(
                        "User not found with id: " + id
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Database query failed", e
            );
        }
    }
}
