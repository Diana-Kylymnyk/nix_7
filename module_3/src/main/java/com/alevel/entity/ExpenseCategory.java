package com.alevel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "expense_categories")
public class ExpenseCategory extends BasicEntity {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }
}