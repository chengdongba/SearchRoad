package com.dqchen.www.searchroad;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dqchen.www.searchroad.map_util.MapInfo;
import com.dqchen.www.searchroad.map_util.MapUtils;
import com.dqchen.www.searchroad.map_util.Node;
import com.dqchen.www.searchroad.map_util.ShowMapView;

import static com.dqchen.www.searchroad.map_util.MapUtils.map;
import static com.dqchen.www.searchroad.map_util.MapUtils.printMap;
import static com.dqchen.www.searchroad.map_util.MapUtils.touchFlag;

public class MainActivity extends AppCompatActivity {

    ShowMapView showMapView;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        handler = new Handler(Looper.getMainLooper());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMapView = findViewById(R.id.show);

    }

    public void cal(View view) {
        MapInfo mapInfo = new MapInfo(map, map[0].length, map.length, new Node(MapUtils.startCol, MapUtils.startRow), new Node(MapUtils.endCol, MapUtils.endRow));
        Log.i("dechen","start="+MapUtils.startCol+" "+MapUtils.startRow);
        Log.i("dechen","end="+MapUtils.endCol+" "+MapUtils.endRow);
        new Astar().start(mapInfo);
        new MoveThread(showMapView).start();
    }

    public void reset(View view) {
        MapUtils.path = null;
        MapUtils.result.clear();
        MapUtils.touchFlag = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]==2){
                    map[i][j]=0;
                }
            }
        }
        showMapView.invalidate();
    }

    class MoveThread extends Thread{
        ShowMapView showMapView;
        public MoveThread(ShowMapView showMapView){
            this.showMapView = showMapView;
        }

        @Override
        public void run() {
            while (MapUtils.result.size()>0){
                Log.i("dqchen",MapUtils.result.size()+" ");
                Node node = MapUtils.result.pop();
                map[node.coord.y][node.coord.x] = 2;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showMapView.invalidate();
                    }
                });

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                map[node.coord.y][node.coord.x] = 0;
            }
            touchFlag = 0;
        }
    }
}
