package com.alevel.entity;

import javax.persistence.*;

@Entity
@Table(name = "operation_categories")
public class OperationCategories extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id")
    private Operations operation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_category_id")
    private IncomeCategory incomeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    public OperationCategories() {
    }

    public OperationCategories(Operations operation, IncomeCategory incomeCategory, ExpenseCategory expenseCategory) {
        this.operation = operation;
        this.incomeCategory = incomeCategory;
        this.expenseCategory = expenseCategory;
    }

    public Operations getOperation() {
        return operation;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }
}