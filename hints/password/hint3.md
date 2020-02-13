# Hint 3

Implementation

```java
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public static String encodePassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
```