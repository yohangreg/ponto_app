package com.pontoapp.pontoapp.entity;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Coloumn(name = "login", nullable = false)
    private String login;

    @Coloumn(name = "password_hash", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<timesheet> timesheet;

    
}
