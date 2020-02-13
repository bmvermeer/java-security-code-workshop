# Hint 8

You can optionally supply a "strength" (a.k.a. log rounds in BCrypt).  The larger the strength parameter the more work will have to be done (exponentially) to hash the passwords. The default value is 10.



```java
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
```