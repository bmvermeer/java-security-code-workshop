# Hint 7

Solution : 

```java
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM USERS WHERE username LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet result = statement.executeQuery();
            List<User> foundUsers = createUsersFromResultSet(result);
```