package com.pontoapp.pontoapp.entity;

import java.util.Date;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "timesheet")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Coloumn(name = "dot", nullable = false)
    @Temporal(TemporalType.DATETIME)
    private Date dot;

    @ManyToOne
    @JoinColoumn(name = "user_id", nullable = false)
    private User user;

    public Day(DayDTO dayDTO) {
        BeanUtils.copyProperties(dayDTO, this);
    }

    public Day() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDot() {
        return dot;
    }

    public void setDot(Date dot) {
        this.dot = dot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
