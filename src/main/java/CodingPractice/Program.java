package CodingPractice;

import java.util.*;

class Program {

    public static void main(String[] args) {
        int startRow = 0;
        int startCol = 1;
        int endRow = 4;
        int endCol = 3;
        int[][] graph = {{0,0,0,0,0}, {0,1,1,1,0},{0,0,0,0,0},{1,0,1,1,1},{0,0,0,0,0}};
        Program o = new Program();
        System.out.println(Arrays.deepToString(o.aStarAlgorithm(startRow, startCol, endRow, endCol, graph)));
    }

    class Node {
        String id;
        int row,col,value, distanceFromStart, estimatedDistanceToEnd;
        Node cameFrom;
        Node (int row, int col, int value) {
            this.id = String.join("-", ""+ row, ""+col);
            this.row = row;
            this.col = col;
            this.value = value;
            this.distanceFromStart = Integer.MAX_VALUE;
            this.estimatedDistanceToEnd = Integer.MAX_VALUE;
            this.cameFrom = null;
        }

    };

    public int[][] aStarAlgorithm(int startRow, int startCol, int endRow, int endCol, int[][] graph) {
        // Write your code here.
        List<List<Node>> nodes = initializaNodes(graph);
        Node startNode = nodes.get(startRow).get(startCol);
        Node endNode = nodes.get(endRow).get(endCol);
        startNode.distanceFromStart = 0;
        startNode.estimatedDistanceToEnd = calManhattanDistance(startNode, endNode);
        Set<String> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.estimatedDistanceToEnd));
        pq.add(startNode);
        visited.add(startNode.id);
        while (!pq.isEmpty()) {
            Node currentMinDistanceNode = pq.remove();
            if (currentMinDistanceNode == endNode) break;
            List<Node> neighbors = getNeighboringNodes(currentMinDistanceNode, nodes);
            for (Node neighbor: neighbors) {
                if (neighbor.value == 1) continue;
                int tentativeDistanceToNeighbor = currentMinDistanceNode.distanceFromStart + 1;
                if (tentativeDistanceToNeighbor >= neighbor.distanceFromStart) continue;
                neighbor.cameFrom = currentMinDistanceNode;
                neighbor.distanceFromStart = tentativeDistanceToNeighbor;
                neighbor.estimatedDistanceToEnd = tentativeDistanceToNeighbor + calManhattanDistance(neighbor, endNode);
                if (!visited.contains(neighbor.id)) {
                    pq.add(neighbor);
                    visited.add(neighbor.id);
                }
                else {
                    pq.remove(neighbor);
                    pq.add(neighbor);
                }
            }
        }
        return reconstructPath(endNode);
    }

    List<List<Node>> initializaNodes(int[][] graph) {
        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0;i < graph.length; i++) {
            List<Node> node = new ArrayList<>();
            nodes.add(node);
            for (int j = 0;j < graph[i].length; j++) {
                nodes.get(i).add(new Node(i, j, graph[i][j]));
            }
        }
        return nodes;
    }

    int calManhattanDistance (Node current, Node end) {
        return Math.abs(current.row - end.row) +
                Math.abs(current.col - end.col);
    }

    List<Node> getNeighboringNodes (Node node, List<List<Node>> nodes) {
        List<Node> neighbors = new ArrayList<>();
        int numRows = nodes.size();
        int numCols = nodes.get(0).size();
        int row = node.row;
        int col = node.col;
        if (row < numRows - 1) neighbors.add(nodes.get(row+1).get(col));
        if (row > 0) neighbors.add(nodes.get(row-1).get(col));
        if (col < numCols - 1) neighbors.add(nodes.get(row).get(col+1));
        if (col > 0) neighbors.add(nodes.get(row).get(col-1));

        return neighbors;
    }

    int[][] reconstructPath (Node endNode) {
        if (endNode.cameFrom == null) return new int[][]{};
        Node currentNode = endNode;
        List<List<Integer>> path = new ArrayList<>();

        while (currentNode != null) {
            List<Integer> nodeData = new ArrayList<>();
            nodeData.add(currentNode.row);
            nodeData.add(currentNode.col);
            path.add(nodeData);
            currentNode = currentNode.cameFrom;
        }
        int[][] res = new int[path.size()][2];
        for (int i = 0; i < res.length;i++) {
            res[i][0] = path.get(res.length - 1 - i).get(0);
            res[i][1] = path.get(res.length - 1 - i).get(1);
        }
        return res;
    }



}

