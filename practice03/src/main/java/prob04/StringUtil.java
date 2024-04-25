package prob04;

public class StringUtil {
	public static String concatenate(String[] str) {
		StringBuilder result = new StringBuilder();
		
		for (String cur : str) result.append(cur);
		
		return result.toString();
	}
}
