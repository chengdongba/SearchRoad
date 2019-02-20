package com.dqchen.www.searchroad.map_util;

import android.util.Log;

/**
 * Created by Administrator on 2019/2/16.
 *
 * 地图实例类
 *
 * int[][] map : 二维数组地图
 * int width : 宽度
 * int height : 高度
 * Node start : 起点
 * Node end : 终点
 *
 */

public class MapInfo {

    public int[][] map;
    public int width;
    public int height;
    public Node start;
    public Node end;

    public MapInfo(int[][] map, int width, int height, Node start, Node end) {
        this.map = map;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
        Log.i("dqchen","地图初始化成功");
    }
}
