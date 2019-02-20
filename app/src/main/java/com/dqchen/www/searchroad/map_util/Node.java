package com.dqchen.www.searchroad.map_util;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2019/2/16.
 * 路径结点实例类
 * <p>
 * Coord coord: 坐标
 * Node parent : 父节点
 * int g : 起点到当前节点的实际代价
 * int h : 当前节点到终点的预估代价
 */

public class Node implements Comparable<Node> {

    public Coord coord;
    public Node parent;
    public int g;
    public int h;

    public Node(int x, int y) {
        this.coord = new Coord(x, y);
    }

    public Node(Coord coord, Node parent, int g, int h) {
        this.coord = coord;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }

    @Override
    public int compareTo(@NonNull Node o) {
        if (o == null) {
            return -1;
        } else {
            if (o.g + o.h > g + h) {
                return -1;
            } else if (o.g + o.h < g + h) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
