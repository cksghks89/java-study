package chapter03;

public class ArrayUtils {

	static double[] intToDouble(int[] source) {
		double[] result = new double[source.length];
		for (int i = 0; i < source.length; i++) result[i] = source[i];
		return result;
	}

}
