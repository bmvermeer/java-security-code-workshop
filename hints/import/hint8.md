# Hint 8

Add these three lines to set some features to both the `factory` and the `saxParser`

```java
    factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
    saxParser.getXMLReader().setFeature("http://xml.org/sax/features/external-general-entities", false);
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
```

Do this after setting the `saxParser` (around line 45 in `XMLProcessor`)