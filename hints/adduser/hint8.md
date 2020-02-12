# Hint 8

You can use this in different places.

- Adjust the the controller (`UserController`) similar to this:

```java
    @PostMapping("/addUser")
    public String addUser(Model model, @ModelAttribute User user) {
        logger.info("New user submitted: {}", user);
        user.setComment(Encode.forHtml(user.getComment()));     //encoding the comment
        userRepo.save(user);
        model.addAttribute("message", "user "+ user.getUsername() + " added");
        return "addUser";
    }
```

- Alternatively you can override the constructor of `User` in a similar way like this:

```java
    public User(String firstname, String lastname, String username, String password, String comment) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.comment = Encode.forHtml(comment);   //encoding the comment
    }
```

Both these solutions are incomplete but it will give you some feeling what needs to be done in general. You can use libraries like the OWASP encoder library to sanitize user inputs.