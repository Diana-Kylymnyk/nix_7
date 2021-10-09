package service;

import dao.DbDao;
import dao.impl.DbDaoImpl;
import entity.LocationEntity;
import entity.ProblemEntity;
import entity.RouteEntity;
import entity.SolutionEntity;
import exception.InvalidIdException;
import exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");

    public List<SolutionEntity> findSolutionForUnsolvedProblem(List<ProblemEntity> problems) {

        LOGGER_INFO.info("Find solution for unsolved problem.");
        try (DbDao dao = new DbDaoImpl()) {
            DbService dbService = new DbService(dao);

            GraphService graphService = new GraphService();
            List<LocationEntity> locationsFrom = new ArrayList<>();
            List<LocationEntity> locationsTo = new ArrayList<>();

            for (ProblemEntity problem : problems) {
                locationsFrom.add(dbService.getLocationById(problem.getFromId()));
                locationsTo.add(dbService.getLocationById(problem.getToId()));
            }

            List<LocationEntity> locations = dbService.readAllLocations();
            List<RouteEntity> routes = dbService.readAllRouts();
            graphService.initializeGraph(locations, routes);

            List<SolutionEntity> solutions = new ArrayList<>();

            for (int i = 0; i < problems.size(); i++) {
                solutions.add(graphService.shortestWay(locationsFrom.get(i), locationsTo.get(i)));
            }

            SolutionEntity solution;
            for (int i = 0; i < problems.size(); i++) {
                solution = solutions.get(i);
                solution.setProblemId(problems.get(i).getId());
            }

            dbService.createSolution(solutions);
            return solutions;
        } catch (SQLException | InvalidIdException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
