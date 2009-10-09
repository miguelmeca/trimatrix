package trimatrix.utils.maths;

import org.jfree.data.function.Function2D;

public abstract class AFunctions {
	protected static final int SP = 4;
	
	/**
	 * Function for rounding
	 * @param d	value
	 * @param significantPrecision	precision
	 * @return	rounded value	
	 */
	protected static double roundSignificant(double d, int significantPrecision) {
		if (d == 0 || significantPrecision < 1 || significantPrecision > 14)
			return d;
		double mul10 = 1;
		double minVal = Math.pow(10, significantPrecision - 1);
		while (Math.abs(d) < minVal) {
			mul10 *= 10;
			d *= 10;
		}
		return Math.round(d) / mul10;
	}	
	
	/**
	 * Get correlation factor by Pearson
	 * @param xyArr1	first xy value array 
	 * @param xyArr2	second xy value array
	 * @return correlation
	 */
	public static Double getPearsonCorrelation(double[] xyArr1,double[] xyArr2){
		if (xyArr1 == null || xyArr1.length < 2 || xyArr1.length % 2 != 0)
			return null;
		if (xyArr2 == null || xyArr2.length < 2 || xyArr2.length % 2 != 0)
			return null;
		
        double sum_sq_x = 0;

        double sum_sq_y = 0;
        double sum_coproduct = 0;

        double mean_x = xyArr1[0];
        double mean_y = xyArr2[0];

        for(int i=2;i<xyArr1.length+1;i+=1){
            double sweep =Double.valueOf(i-1)/i;

            double delta_x = xyArr1[i-1]-mean_x;
            double delta_y = xyArr2[i-1]-mean_y;

            sum_sq_x += delta_x * delta_x * sweep;
            sum_sq_y += delta_y * delta_y * sweep;

            sum_coproduct += delta_x * delta_y * sweep;

            mean_x += delta_x / i;
            mean_y += delta_y / i;
        }
        
        double pop_sd_x = Math.sqrt(sum_sq_x/xyArr1.length);
        double pop_sd_y = Math.sqrt(sum_sq_y/xyArr1.length);

        double cov_x_y = sum_coproduct / xyArr1.length;
        return cov_x_y / (pop_sd_x*pop_sd_y);
    }	
	
	public interface IResult {
		public double getY(double x);
		public double getX(double y);
		public double[] getXYValues();
		public Function2D getFunction2D();
		public String getFormel();
	}
	
}
