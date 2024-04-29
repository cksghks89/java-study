package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {
	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<>();

		m.put("one", 1); // auto boxing 발생
		m.put("two", 2);
		m.put("three", 3);

		int i = m.get("one"); // auto unboxing 발생
		int j = m.get(new String("one")); // 동질성 비교 (동일성 비교 x)
		System.out.println(i + ":" + j);
		
		m.put("three", 3333);
		System.out.println(m.get("three"));
		
		// 순회
		Set<String> keySet = m.keySet();
		
	}
}
