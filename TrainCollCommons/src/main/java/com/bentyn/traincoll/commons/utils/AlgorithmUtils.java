package com.bentyn.traincoll.commons.utils;

public class AlgorithmUtils {
	private static final double PRECISION=0.00000001;
	/**
	 * Convert speed in km/h to m/s
	 * @param speedInKph
	 * @return
	 */
	public static double kphToMps(double speedInKph){
		// 1000.0/3600.0 == 1/3.6 
		return speedInKph/3.6;
	}

	public static double[] quadric(double a,double b, double c){
		if (Math.abs(a)<PRECISION  && Math.abs(b)<PRECISION){
			return new double[]{c};
		}
		if (Math.abs(a)<PRECISION  && Math.abs(b)>PRECISION){
			return new double[]{-c/(b)};
		}
		double delta = b*b - 4*a*c;
		if (Math.abs(delta) < PRECISION){
			return new double[]{-b/(2.0*a)};
		}
		if(delta<0){
			return new double[0];
		}
		double deltaRoot = Math.sqrt(delta);

		if ( b >= 0.0 ){
			double x1 = (-b - deltaRoot)/(2.0*a);
			double x2 = 2*c/(-b-deltaRoot);
			return new double [] {x1,x2};
		}
		else{

			double x1 = 2*c/(-b+deltaRoot);
			double x2 = (-b + deltaRoot)/(2.0*a);
			return new double [] {x1,x2};
		}
	}
}
