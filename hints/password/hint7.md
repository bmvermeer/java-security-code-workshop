# Hint 7

Change the return value of the `check()` function in 

```java
return encoder.matches(password,users.get(0).getPassword());
```

As the BCryptPasswordEncoder will internally generate a random salt. Therefor you cannot just encode the incomming String and compare it to the hash.