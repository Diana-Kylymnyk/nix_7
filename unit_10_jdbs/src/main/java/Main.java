import dao.impl.DbDaoImpl;
import entity.LocationEntity;
import entity.ProblemEntity;
import entity.RouteEntity;
import entity.SolutionEntity;
import service.DbService;
import service.ResultService;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ResultService resultService = new ResultService();
        DbService dbService = new DbService(new DbDaoImpl());
        try {
            List<LocationEntity> locations = dbService.readAllLocations();
            List<RouteEntity> routes = dbService.readAllRouts();
            List<ProblemEntity> problems = dbService.getUnsolvedProblems();
            List<SolutionEntity> solutionsOfProblems = resultService.findSolutionForUnsolvedProblem(problems);

            System.out.println("Locations:");
            locations.forEach(location -> System.out.println(location.getId() + ". " + location.getName()));
            System.out.println("\nRoutes:");
            routes.forEach(route -> System.out.println(route.getId() + ". Cost from " + route.getFromId() + " to " + route.getToId() + " is " + route.getCost()));
            System.out.println("\nUnsolved problems:");
            problems.forEach(problem -> System.out.println(problem.getId() + ". " + problem.getFromId() + " to " + problem.getToId()));
            System.out.println("\nSolutions:");
            solutionsOfProblems.forEach(solution -> System.out.println("For " + solution.getProblemId() + " problem: " + solution.getCost()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
