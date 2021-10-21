package com.alevel.dao;

import com.alevel.entity.*;

import java.util.List;

public interface OperationsDao {

    void createOperation(Operations operation);

    Accounts getAccountById(Long id);

    ExpenseCategory getExpenseCategoryByName(String name);

    IncomeCategory getIncomeCategoryByName(String name);

    User getUserByPhoneNumber(String phoneNumber);

    void addOperationCategories(List<OperationCategories> operationCategories);
}
