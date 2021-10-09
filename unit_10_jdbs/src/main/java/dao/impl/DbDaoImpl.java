package dao.impl;

import dao.DbDao;
import entity.LocationEntity;
import entity.ProblemEntity;
import entity.RouteEntity;
import entity.SolutionEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DbDaoImpl implements DbDao {

    private final Connection connection;

    public DbDaoImpl() {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String pass = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DbDaoImpl.class.getResourceAsStream("/app.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return properties;
    }

    public List<ProblemEntity> getUnsolvedProblems() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM problems a LEFT JOIN solutions b ON a.id = b.problem_id WHERE b.problem_id IS NULL")) {
            List<ProblemEntity> problems = new ArrayList<>();
            ProblemEntity problem;
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                problem = new ProblemEntity();
                problem.setId(resultSet.getInt("id"));
                problem.setFromId(resultSet.getInt("from_id"));
                problem.setToId(resultSet.getInt("to_id"));
                problems.add(problem);
            }
            return problems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LocationEntity getLocationById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM locations WHERE id LIKE ?")) {
            LocationEntity location = new LocationEntity();
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));
            }
            return location;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LocationEntity> readAllLocations() {
        try (Statement statement = connection.createStatement()) {
            List<LocationEntity> locations = new ArrayList<>();
            LocationEntity location;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM locations");
            while (resultSet.next()) {
                location = new LocationEntity();
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));

                locations.add(location);
            }
            return locations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RouteEntity> readAllRouts() {
        try (Statement statement = connection.createStatement()) {
            List<RouteEntity> routes = new ArrayList<>();
            RouteEntity route;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM routes");
            while (resultSet.next()) {
                route = new RouteEntity();
                route.setId(resultSet.getInt("id"));
                route.setFromId(resultSet.getInt("from_id"));
                route.setToId(resultSet.getInt("to_id"));
                route.setCost(resultSet.getInt("cost"));

                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSolutions(List<SolutionEntity> solutions) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO solutions (problem_id,cost) values(?,?)")) {
            connection.setAutoCommit(false);
            for (SolutionEntity solution : solutions) {
                preparedStatement.setInt(1, solution.getProblemId());
                preparedStatement.setInt(2, solution.getCost());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}