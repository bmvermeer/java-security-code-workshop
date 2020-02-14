# Hint 7

Change the return value of the `check()` function in 

```java
return encoder.matches(password,users.get(0).getPassword());
```

The BCryptPasswordEncoder will internally generate a random salt. Therefore you cannot just encode the incoming String and compare it to the hash.