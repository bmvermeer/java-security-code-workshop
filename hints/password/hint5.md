# Hint 5

Use the OWASP encoder library to do so

```java
    public User(String firstname, String lastname, String username, String password, String comment) {
        ...
        this.password = PasswordUtil.encodePassword(password);
        ...
    }

```