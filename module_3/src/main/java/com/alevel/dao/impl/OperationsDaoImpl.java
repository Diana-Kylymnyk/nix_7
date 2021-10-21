package com.alevel.dao.impl;

import com.alevel.dao.OperationsDao;
import com.alevel.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class OperationsDaoImpl implements OperationsDao {

    private final EntityManager entityManager;
    private final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public OperationsDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createOperation(Operations operations) {
        LOGGER_INFO.info("Start create operations");
        entityManager.persist(operations);
        Query updateQuery = entityManager.createQuery(
                "update Accounts ac set ac.balance = ac.balance + :balance where ac.id = :id");

        updateQuery.setParameter("balance", operations.getResult());
        updateQuery.setParameter("id", operations.getAccount().getId());

        updateQuery.executeUpdate();
    }

    public Accounts getAccountById(Long id) {
        LOGGER_INFO.info("Start get account by id");
        return entityManager.find(Accounts.class, id);
    }

    public ExpenseCategory getExpenseCategoryByName(String name) {
        LOGGER_INFO.info("Start get expense category by name");
        TypedQuery<ExpenseCategory> query = entityManager.createQuery(
                "select ct from ExpenseCategory ct where ct.name = :name", ExpenseCategory.class);

        query.setParameter("name", name);
        query.setMaxResults(1);

        ExpenseCategory expenseCategory;
        try {
            expenseCategory = query.getSingleResult();
            return expenseCategory;
        } catch (NoResultException e) {
            LOGGER_WARN.warn("WARN ", e);
            return null;
        }
    }

    public IncomeCategory getIncomeCategoryByName(String name) {
        LOGGER_INFO.info("Start get income category by name");
        TypedQuery<IncomeCategory> query = entityManager.createQuery(
                "select ct from IncomeCategory ct where ct.name = :name",
                IncomeCategory.class);

        query.setParameter("name", name);
        query.setMaxResults(1);

        IncomeCategory incomeCategory;
        try {
            incomeCategory = query.getSingleResult();
            return incomeCategory;
        } catch (NoResultException e) {
            LOGGER_WARN.warn("WARN ", e);
            return null;
        }
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        LOGGER_INFO.info("Start get user by phone number");
        TypedQuery<User> query = entityManager.createQuery(
                "select us from User us where us.phoneNumber = :phone_number",
                User.class);

        query.setParameter("phone_number", phoneNumber);
        query.setMaxResults(1);

        User user;
        try {
            user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            LOGGER_WARN.warn("WARN ", e);
            return null;
        }
    }

    public void addOperationCategories(List<OperationCategories> operationCategories) {
        LOGGER_INFO.info("Start add operation categories");
        for (OperationCategories operationCategory : operationCategories) {
            entityManager.persist(operationCategory);
        }
    }
}
