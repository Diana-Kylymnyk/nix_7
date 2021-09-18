package tasks.levelThird;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FindTheMostProfitableWay {

    private static final String INPUT_TXT = "files/levelThird/input.txt";
    private static final String OUTPUT_TXT = "files/levelThird/output.txt";

    public static void realization() {
        System.out.println("\nFind the most profitable way");
        try {
            Path path = Paths.get(INPUT_TXT);
            System.out.println("Input data:");
            ArrayList<String> graph = (ArrayList<String>) Files.readAllLines(path);
            graph.forEach(System.out::println);

            BufferedReader reader = new BufferedReader(new FileReader(INPUT_TXT));
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);

            System.out.println("\nOutput data:");
            findWaysInGraph(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findWaysInGraph(ArrayList<String> stringGraph) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        ArrayList<String> vertices = new ArrayList<>();
        ArrayList<String[]> allEdges = new ArrayList<>();

        int countVer = Integer.parseInt(stringGraph.get(0));
        ArrayList<Integer> countEdgesOfEveryCity = new ArrayList<>();

        int end = 0;
        for (int i = 1; i < stringGraph.size(); i++) {
            vertices.add(stringGraph.get(i));
            int countEdges = (Integer.parseInt((stringGraph.get(i + 1))));
            countEdgesOfEveryCity.add(countEdges);
            i += 2;
            String[] edgesOfCity = new String[countEdges];
            for (int j = 0; j < edgesOfCity.length; i++) {
                edgesOfCity[j] = stringGraph.get(i);
                j++;
            }
            allEdges.add(edgesOfCity);
            countVer--;
            i--;
            if (countVer == 0) {
                end = i + 1;
                break;
            }
        }

        countVer = Integer.parseInt(stringGraph.get(0));
        for (int i = 0; i < countVer; i++) {
            graph.addVertex(vertices.get(i));
        }

        for (int i = 0; i < countVer; i++) {
            for (int j = 0; j < countEdgesOfEveryCity.get(i); j++) {
                String[] edgeString = allEdges.get(i)[j].split(" ");
                String target = vertices.get(Integer.parseInt(edgeString[0]) - 1);
                double weight = Double.parseDouble(edgeString[1]);
                if (!graph.containsEdge(vertices.get(i), target)) {
                    DefaultWeightedEdge edge = graph.addEdge(vertices.get(i), target);
                    graph.setEdgeWeight(edge, weight);
                }
            }
        }
        findWay(graph, stringGraph.subList(end, stringGraph.size()));
    }

    private static void findWay(Graph<String, DefaultWeightedEdge> graph, List<String> findWay) {
        DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath = new DijkstraShortestPath<>(graph);
        StringBuilder output = new StringBuilder();

        for (int i = 1; i < Integer.parseInt(findWay.get(0)) + 1; i++) {
            String[] sourceAndTarget = findWay.get(i).split(" ");
            double weight = shortestPath.getPath(sourceAndTarget[0], sourceAndTarget[1]).getWeight();
            System.out.println(weight);
            output.append(weight).append("\n");
        }

        try {
            Files.writeString(Paths.get(OUTPUT_TXT), output);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
