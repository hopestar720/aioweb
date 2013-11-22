package com.xhsoft.framework.common.utils;


/**
 * 功能描述： 根據經緯度計算兩點距離
 * 
 * @version 2.0
 * @author daixl
 */
public class DistanceUtil {
	private static final double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 功能描述： 根据数据计算获得一个Double值
	 *
	 * @param lat1 A點緯度
	 * @param lng1 A點經度
	 * @param lat2 B點緯度
	 * @param lng2 B點經度
	 * @return A B兩點距離
	 * @author daixl
	 * @since  2011.2.23
	 */
	public static double GetDistance(double lat1,
			double lng1,
			double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;

		s = (double) Math.round(s * 10000) / 10000;
		return s;
	}

    public enum GaussSphere{
        Beijing54,
        Xian80,
        WGS84,
    } 
    private static double Rad(double d){
        return d * Math.PI / 180.0;
    }
    public static double DistanceOfTwoPoints(double lng1,double lat1,double lng2,double lat2,GaussSphere gs){
        double radLat1 = Rad(lat1);
        double radLat2 = Rad(lat2);
        double a = radLat1 - radLat2;
        double b = Rad(lng1) - Rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
         Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b/2),2)));
        s = s * (gs == GaussSphere.WGS84 ? 6378137.0 : (gs == GaussSphere.Xian80 ? 6378140.0 : 6378245.0));
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
