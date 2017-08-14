package com.ffcs.icity.api.module.util;

import java.text.DecimalFormat;

public class RangeUtil {
//单位公里
	public static String getDistatce(double lat1, double lat2, double lon1,    double lon2) {
        double R = 6371;
        double distance = 0.0;
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
        DecimalFormat df = new DecimalFormat("#.00");
        if(distance<1){
        	 df = new DecimalFormat("0.00");
        }
        return (df.format(distance));
    }
	public static void main(String[] args) {
		System.out.println(new RangeUtil().getDistatce(39.995906, 40.006518, 116.484757, 116.220296));
	}
}
