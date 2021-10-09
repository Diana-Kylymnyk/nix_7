package service;

import dao.DbDao;
import entity.LocationEntity;
import entity.ProblemEntity;
import entity.RouteEntity;
import entity.SolutionEntity;
import exception.InvalidIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DbService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private final DbDao Dao;

    public DbService(DbDao dao) {
        Dao = dao;
    }

    public List<ProblemEntity> getUnsolvedProblems() throws SQLException {
        LOGGER_INFO.info("Getting unsolved problems.");
        return Dao.getUnsolvedProblems();
    }

    public LocationEntity getLocationById(Integer id) throws SQLException, InvalidIdException {
        LOGGER_INFO.info("Getting location by id.");
        if (id <= 0) throw new InvalidIdException();
        return Dao.getLocationById(id);
    }

    public List<RouteEntity> readAllRouts() throws SQLException {
        LOGGER_INFO.info("Reading all routes.");
        return Dao.readAllRouts();
    }

    public List<LocationEntity> readAllLocations() throws SQLException {
        LOGGER_INFO.info("Reading locations.");
        return Dao.readAllLocations();
    }

    public void createSolution(List<SolutionEntity> solutions) throws SQLException {
        LOGGER_INFO.info("Creating solution.");
        Dao.createSolutions(solutions);
    }
}
