package com.dqchen.www.searchroad.map_util;

/**
 * Created by Administrator on 2019/2/16.
 * 地图上坐标点的实例类
 */

public class Coord {

    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Coord) {
            Coord o = (Coord) obj;
            return x == o.x && y == o.y;
        } else {
            return false;
        }
    }
}
