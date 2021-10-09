package dao;

import entity.LocationEntity;
import entity.ProblemEntity;
import entity.RouteEntity;
import entity.SolutionEntity;

import java.sql.SQLException;
import java.util.List;

public interface DbDao extends AutoCloseable {

    List<ProblemEntity> getUnsolvedProblems() throws SQLException;

    LocationEntity getLocationById(Integer id) throws SQLException;

    List<LocationEntity> readAllLocations() throws SQLException;

    List<RouteEntity> readAllRouts() throws SQLException;

    void createSolutions(List<SolutionEntity> solutions) throws SQLException;

    @Override
    void close() throws SQLException;
}