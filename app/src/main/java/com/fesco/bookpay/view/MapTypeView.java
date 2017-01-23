package com.fesco.bookpay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.fesco.bookpay.activity.R;

/**
 * Created by gong.min on 2016/9/26.
 */
public class MapTypeView extends LinearLayout implements View.OnClickListener{

    private MapView mapView;

    private BaiduMap baiduMap;//百度地图对象控制器

    private MapStatus mapStatus;//百度地图状态

    private Button zoomOut, zoomIn;



    public MapTypeView(Context context) {
        super(context);
        initZoomControlView(context);
    }

    public MapTypeView(Context context, AttributeSet attr) {
        super(context, attr);
        initZoomControlView(context);
    }

    private void initZoomControlView(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.zoom_maptpye_layout, this);
        zoomIn = (Button) view.findViewById(R.id.zoom_maptpye_plus);
        zoomOut = (Button) view.findViewById(R.id.zoom_maptpye_reduce);
        zoomIn.setOnClickListener(this);
        zoomOut.setOnClickListener(this);


    }

    /**
     * set {@link MapView}
     */
    public void setMapView(MapView mapView)
    {
        if(mapView != null)
        {
            this.mapView = mapView;
            baiduMap = mapView.getMap();


        }else{
            throw new NullPointerException("you should call setMapView(MapView mapView) at first");
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.zoom_maptpye_plus:

                //卫星地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);


                break;
            case R.id.zoom_maptpye_reduce:
                //普通地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

                break;
            default:
                break;
        }
    }

}