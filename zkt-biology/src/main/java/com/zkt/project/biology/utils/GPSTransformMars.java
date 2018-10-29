package com.zkt.project.biology.utils;

/*
 * GPS坐标转换为高德地图坐标
 * 输入GPS坐标，单位度，数据类型double，参数一为Lat,参数二为Lng
 * 输出高德地图坐标，单位度，数据类型double[]，参数一为Lat,参数二为Lng
 * 
 * */
public class GPSTransformMars {
	
	private static double pi = Math.PI;
	private static double a = 6378245.0;
	private static double ee = 0.00669342162296594323;

	public static double[] transLatLng(double wgLat, double wgLng) {
		double[] ds = new double[2];
		double dLat = transLat(wgLng - 105.0, wgLat - 35.0, pi);
		double dLng = transLng(wgLng - 105.0, wgLat - 35.0, pi);
		double radLat = wgLat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLng = (dLng * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		ds[0] = wgLat + dLat;
		ds[1] = wgLng + dLng;
		return ds;
	}

	private static double transLat(double x, double y, double pi) {
		double ret;
		ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2
				* Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	private static double transLng(double x, double y, double pi) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
				* Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
				* pi)) * 2.0 / 3.0;
		return ret;
	}
	
}
