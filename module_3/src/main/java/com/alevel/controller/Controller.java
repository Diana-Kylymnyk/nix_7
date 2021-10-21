package com.alevel.controller;

import com.alevel.dao.impl.ConnectionDaoImpl;
import com.alevel.entity.Accounts;
import com.alevel.entity.OperationCategories;
import com.alevel.entity.Operations;
import com.alevel.entity.User;
import com.alevel.service.ConnectionService;
import com.alevel.service.ExportDataService;
import com.alevel.service.OperationService;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller implements AutoCloseable {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final Scanner sc = new Scanner(System.in);
    private final ConnectionService connectionsService;
    private final EntityManager entityManager;
    private final SessionFactory sessionFactory;

    public Controller(String username, String password) {
        connectionsService = new ConnectionService(new ConnectionDaoImpl(username, password));
        sessionFactory = new Configuration().
                setProperty("hibernate.connection.username", username).
                setProperty("hibernate.connection.password", password).configure().buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    public void createOperation(String usersTelephone) {
        OperationService operationsControlService = new OperationService(entityManager);
        User user = operationsControlService.getUserByPhoneNumber(usersTelephone);

        System.out.println("\nAdd transactions");
        printUsersAccounts(user);
        String accountId = sc.nextLine();

        try {
            entityManager.getTransaction().begin();
            Accounts account = operationsControlService.getAccountById(Long.parseLong(accountId));
            List<OperationCategories> expenseOperationsCategories = new ArrayList<>();
            Operations expenseOperation = new Operations(account, -500, Instant.now());

            expenseOperationsCategories.add(new OperationCategories(
                    expenseOperation,
                    null,
                    operationsControlService.getExpenseCategoryByName("Cosmetics")));

            expenseOperationsCategories.add(new OperationCategories(
                    expenseOperation,
                    null,
                    operationsControlService.getExpenseCategoryByName("Clothes")));

            expenseOperation.setOperationCategories(expenseOperationsCategories);

            operationsControlService.createOperation(user, expenseOperation);
            List<OperationCategories> incomeOperationCategories = new ArrayList<>();
            Operations incomeOperation = new Operations(account, 1000, Instant.now());

            incomeOperationCategories.add(new OperationCategories(
                    incomeOperation,
                    operationsControlService.getIncomeCategoryByName("Salary"),
                    null));

            incomeOperation.setOperationCategories(incomeOperationCategories);

            operationsControlService.createOperation(user, incomeOperation);
            entityManager.getTransaction().commit();
        } catch (NumberFormatException | HibernateException e) {
            LOGGER_ERROR.error("Error ", e);
            entityManager.getTransaction().rollback();
        }
    }

    public void exportData(String usersTelephone, String filePath) {
        OperationService operationsControlService = new OperationService(entityManager);
        User user = operationsControlService.getUserByPhoneNumber(usersTelephone);

        menuForExportData();
        String line = sc.nextLine();

        try (Connection connection = connectionsService.getConnection()) {
            entityManager.getTransaction().begin();
            ExportDataService exportData;
            printUsersAccounts(user);
            String accountId = sc.nextLine();

            switch (line) {
                case "1":
                    exportData = new ExportDataService(connection);
                    exportData.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(0, 1, 1, 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    entityManager.getTransaction().commit();
                    break;
                case "2":
                    exportData = new ExportDataService(connection);
                    exportData.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    entityManager.getTransaction().commit();
                    break;
                case "3":
                    exportData = new ExportDataService(connection);
                    exportData.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1, 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    entityManager.getTransaction().commit();
                    break;
                case "4":
                    exportData = new ExportDataService(connection);
                    exportData.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    entityManager.getTransaction().commit();
                    break;
                default:
                    LOGGER_ERROR.warn("Incorrect index for export data");
                    exportData = new ExportDataService(connection);
                    exportData.exportOperationsInPeriodToCsv(
                            user,
                            operationsControlService.getAccountById(Long.parseLong(accountId)),
                            LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0),
                            LocalDateTime.now(),
                            filePath);
                    entityManager.getTransaction().commit();
                    break;

            }
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Error ", e);
            entityManager.getTransaction().rollback();
        } catch (SQLException e) {
            LOGGER_ERROR.error("Error ", e);
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error with connection");
        }
    }

    private void menuForExportData() {
        System.out.println("\nExport data");
        System.out.print("Choose the period of report:\n" +
                "1. Time\n" +
                "2. Day\n" +
                "3. Month\n" +
                "4. Year\n");
    }

    private void printUsersAccounts(User user) {
        System.out.println("Choose user's id from list");
        for (int i = 0; i < user.getAccounts().size(); i++) {
            System.out.println(user.getAccounts().get(i).getId() + " " +
                    user.getFirstName() + " " +
                    user.getSecondName() + " " +
                    user.getAccounts().get(i).getBalance());
        }
    }

    @Override
    public void close() {
        try {
            sessionFactory.close();
            connectionsService.close();
        } catch (SQLException | HibernateException e) {
            LOGGER_ERROR.error("Error ", e);
        }
    }
}
