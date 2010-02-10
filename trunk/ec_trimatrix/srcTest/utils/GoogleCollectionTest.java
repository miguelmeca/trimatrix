package utils;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class GoogleCollectionTest {
	@Test
	public void testList() {
		List<String> list1 = Lists.newArrayList("1", "2", "3");
		List<Double> list2 = Lists.transform(list1,
				new Function<String, Double>() {
					public Double apply(String from) {
						return Double.parseDouble(from);
					}
				});
		System.out.println(Joiner.on(" | ").join(list2));
	}

}
