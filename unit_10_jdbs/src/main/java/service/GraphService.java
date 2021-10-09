package service;

import entity.LocationEntity;
import entity.RouteEntity;
import entity.SolutionEntity;
import exception.NotFoundException;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GraphService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;

    public void initializeGraph(List<LocationEntity> locations, List<RouteEntity> routes) throws NotFoundException {
        LOGGER_INFO.info("Initialize graph.");
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (LocationEntity location : locations) {
            graph.addVertex(location.getName());
        }

        DefaultWeightedEdge edge;
        for (RouteEntity route : routes) {
            LocationEntity from = findLocationById(locations, route.getFromId());
            LocationEntity to = findLocationById(locations, route.getToId());

            edge = graph.addEdge(from.getName(), to.getName());
            graph.setEdgeWeight(edge, route.getCost());
        }
    }

    public SolutionEntity shortestWay(LocationEntity from, LocationEntity to) {
        LOGGER_INFO.info("Find shortest way.");
        int maxCost = 200_000;
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);

        double cost = shortestPath.getPath(from.getName(), to.getName()).getWeight();
        SolutionEntity solution = new SolutionEntity();
        if (cost <= maxCost)
            solution.setCost((int) cost);
        else {
            LOGGER_WARN.warn("The cost of the way is more than 200_000");
            solution.setCost(maxCost);
        }
        return solution;
    }

    private LocationEntity findLocationById(List<LocationEntity> locations, Integer id) throws NotFoundException {
        LOGGER_INFO.info("Find location by id.");
        for (LocationEntity location : locations) {
            if (location.getId().equals(id)) return location;
        }
        LOGGER_WARN.warn("Some problem with location.");
        throw new NotFoundException("location.");
    }
}
