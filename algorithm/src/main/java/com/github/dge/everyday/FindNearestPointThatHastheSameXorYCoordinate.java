package com.github.dge.everyday;

public class FindNearestPointThatHastheSameXorYCoordinate {

    public static void main(String[] args) {
        FindNearestPointThatHastheSameXorYCoordinate findNearestPointThatHastheSameXorYCoordinate = new FindNearestPointThatHastheSameXorYCoordinate();
        int[][] arr = {{1,2},{3,1},{2,4},{2,3},{4,4}};
        findNearestPointThatHastheSameXorYCoordinate.nearestValidPoint(3, 4, arr);
    }

    public int nearestValidPoint(int x, int y, int[][] points) {
        int shortPath = Integer.MAX_VALUE;
        int mixNum = Integer.MAX_VALUE;
        int length = points.length;
        for (int i = 0; i < length; i++) {
            if(points[i][0] == x || points[i][1] == y){
                int path = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                if(path < shortPath){
                    mixNum = i;
                    shortPath = path;
                }
            }
        }
        if(mixNum == Integer.MAX_VALUE){
            mixNum = -1;
        }
        return mixNum;
    }
}
