package utils;

import org.junit.Test;

import trimatrix.structures.SRange;
import trimatrix.utils.SearchRange;

public class SearchRangeTest {

	@Test
	public void testSearchRange() {
		SearchRange sr = new SearchRange();
		String field1 = "firstName";
		String field2 = "lastName";
		String aliasField1 = "profile.weight";
		String aliasField2 = "profile2.heartrate";
		String aliasField3 = "profile.height";
		sr.add(new SRange<String>(field1, SRange.Operator.EQ, "Markus"));
		sr.add(new SRange<String>(field1, SRange.Operator.EQ, "Daniela"));
		sr.add(new SRange<String>(field2, SRange.Operator.EQ, "Bucher"));
		sr.add(new SRange<Integer>(aliasField1, SRange.Operator.EQ, 68));
		sr.add(new SRange<Integer>(aliasField2, SRange.Operator.EQ, 196));
		sr.add(new SRange<Integer>(aliasField3, SRange.Operator.EQ, 184));
		sr.add(new SRange<Integer>(aliasField1, SRange.Operator.EQ, 54));
		System.out.println("Aliase:");
		for(String alias : sr.getAliases()) {
			System.out.println(alias);
		}
		System.out.println("Fields:");
		for(String key : sr.getRanges().keySet()) {
			System.out.println("Key: " + key);
			for(SRange range : sr.getRanges().get(key)) {
				System.out.println(range.field + " - " + range.value);
			}
		}
	}
}
