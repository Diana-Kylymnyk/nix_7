package com.alevel.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "operations")
public class Operations extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Accounts account;

    @Column(name = "result")
    private Integer result;

    @Column(name = "date_time")
    private Instant dateTime;

    @OneToMany(mappedBy = "operation")
    private List<OperationCategories> operationCategories;

    @Transient
    private OperationTypes type;

    public Operations() {
    }

    public Operations(Long id, Accounts account, Integer result, Instant dateTime) {
        this.id = id;
        this.account = account;
        this.result = result;
        this.dateTime = dateTime;
    }

    public Operations(Accounts account, Integer result, Instant dateTime) {
        this.account = account;
        this.result = result;
        this.dateTime = dateTime;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Integer getResult() {
        return result;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public OperationTypes getType() {
        return type;
    }

    public void setType(OperationTypes type) {
        this.type = type;
    }

    public List<OperationCategories> getOperationCategories() {
        return operationCategories;
    }

    public void setOperationCategories(List<OperationCategories> operationCategories) {
        this.operationCategories = operationCategories;
    }
}
