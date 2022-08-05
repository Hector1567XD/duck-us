package common.utils;

import common.game.engine.node.NodeI;

public class NodeDistanceHelper {
    public static double getDistance(NodeI nodeOne, NodeI nodeTwo) {
        return Math.sqrt((Math.pow(nodeOne.getX()-nodeTwo.getX(),2)) + (Math.pow(nodeOne.getY()-nodeTwo.getY(),2)));
    }
}
