package com.alevel.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Accounts extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance")
    private Integer balance;

    @OneToMany(mappedBy = "account")
    private List<Operations> operations;

    public User getUser() {
        return user;
    }

    public Integer getBalance() {
        return balance;
    }
}
