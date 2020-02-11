package io.snyk.demo.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @NonNull private String firstname;
    @NonNull private String lastname;
    @Id
    @NonNull private String username;
    @NonNull private String password;

    @Column(length = 10000)  private String comment;




}
