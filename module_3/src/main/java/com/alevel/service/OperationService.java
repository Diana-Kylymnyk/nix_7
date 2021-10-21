package com.alevel.service;

import com.alevel.dao.impl.OperationsDaoImpl;
import com.alevel.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class OperationService {
    private final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final OperationsDaoImpl operationsDaoImpl;

    public OperationService(EntityManager entityManager) {
        this.operationsDaoImpl = new OperationsDaoImpl(entityManager);
    }

    public void createOperation(User user, Operations operations) {
        LOGGER_INFO.info("Start adding operations to account.");

        if (operations == null || operations.getOperationCategories().size() == 0) {
            LOGGER_ERROR.error("Error with creating new operations! Categories is null or empty.");
            throw new IllegalArgumentException("Operations must got categories!");
        }

        if (!user.getId().equals(operations.getAccount().getUser().getId())) {
            LOGGER_ERROR.error("Error with creating new operations!" +
                            " Account with id {} doesn't belong to user with id {}.",
                    operations.getAccount().getId(), user.getId());
            throw new RuntimeException(new IllegalAccessException("Current account doesn't belong to user!"));
        }

        if (operations.getResult() == 0) {
            LOGGER_ERROR.error("Error with creating new operations! Result must be grater than 0.");
            throw new IllegalArgumentException("Operations can`t produce zero result!");
        }

        if (operations.getAccount().getBalance() + operations.getResult() < 0) {
            LOGGER_ERROR.error("Error with creating new operations! " +
                    "Balance on account after operations lower than 0.");
            throw new IllegalArgumentException("Minus balance after that operations!");
        }

        operationsDaoImpl.createOperation(operations);
        addOperationCategories(operations.getOperationCategories());

        if (operations.getType() == OperationTypes.EXPENSE && operations.getResult() > 0 ||
                operations.getType() == OperationTypes.INCOME && operations.getResult() < 0) {
            LOGGER_ERROR.error("Error while creating new operations! Type doesn't fit the result.");
            throw new IllegalArgumentException("Operations type doesn't fit the result!");
        }

        LOGGER_INFO.info("Successful adding operations with id {} to account with id {}",
                operations.getId(),
                operations.getAccount().getId());
    }

    public Accounts getAccountById(Long id) {
        LOGGER_INFO.info("Start getting account with id {}", id);
        Accounts account = operationsDaoImpl.getAccountById(id);
        if (account == null) {
            LOGGER_ERROR.error("Error get account by id! No account with id {}.", id);
            throw new IllegalArgumentException("No account with current id!");
        }
        LOGGER_INFO.info("Successful getting account with id {}", id);
        return account;
    }

    public ExpenseCategory getExpenseCategoryByName(String name) {
        LOGGER_INFO.info("Start getting expense category with name {}",
                name);
        ExpenseCategory expenseCategory = operationsDaoImpl.getExpenseCategoryByName(name);
        if (expenseCategory == null) {
            LOGGER_ERROR.error("Error get expense category by name! No expense category with name {}.", name);
            throw new IllegalArgumentException("No expense category with current name");
        }
        LOGGER_INFO.info("Successful getting expense category with name {}", name);
        return expenseCategory;
    }

    public IncomeCategory getIncomeCategoryByName(String name) {
        LOGGER_INFO.info("Start getting income category with name {}",
                name);
        IncomeCategory incomeCategory = operationsDaoImpl.getIncomeCategoryByName(name);
        if (incomeCategory == null) {
            LOGGER_ERROR.error("Error get income category by name! No income category with name {}.", name);
            throw new IllegalArgumentException("No income category with current name");
        }
        LOGGER_INFO.info("Successful getting income category with name {}", name);
        return incomeCategory;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        LOGGER_INFO.info("Start getting user with phone number {}", phoneNumber);
        User user = operationsDaoImpl.getUserByPhoneNumber(phoneNumber);
        if (user == null) {
            LOGGER_ERROR.error("Error get user by phone number! No user with phone number {}.", phoneNumber);
            throw new IllegalArgumentException("No user with this phone_number");
        }
        LOGGER_INFO.info("Successful getting user with phone number {}", phoneNumber);
        return user;
    }

    public void addOperationCategories(List<OperationCategories> operationCategories) {
        LOGGER_INFO.info("Start adding operation categories to database");
        for (OperationCategories operationCategory : operationCategories) {
            IncomeCategory incomeCategory = operationCategory.getIncomeCategory();
            ExpenseCategory expenseCategory = operationCategory.getExpenseCategory();
            Operations operations = operationCategory.getOperation();

            if (incomeCategory == null && expenseCategory == null ||
                    incomeCategory != null && expenseCategory != null) {
                LOGGER_ERROR.error("Error adding operations category!" +
                        " Operations category can have only one type of category.");
                throw new IllegalArgumentException("Must be one type of category!");
            }

            if (operations.getType() == null) {
                if (incomeCategory == null) {
                    operations.setType(OperationTypes.EXPENSE);
                } else {
                    operations.setType(OperationTypes.INCOME);
                }
            } else {
                if (operations.getType() == OperationTypes.EXPENSE && incomeCategory != null ||
                        operations.getType() == OperationTypes.INCOME && expenseCategory != null) {
                    LOGGER_ERROR.error("Error adding operations category! " +
                            "All categories must have the same type.");
                    throw new IllegalArgumentException("All categories must have the same type!");
                }
            }
        }
        operationsDaoImpl.addOperationCategories(operationCategories);
        LOGGER_INFO.info("Successful adding operation categories to database");
    }
}
