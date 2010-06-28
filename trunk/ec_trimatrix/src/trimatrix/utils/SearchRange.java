package trimatrix.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import trimatrix.structures.SRange;

public class SearchRange {
	private Map<String, Set<SRange<?>>> ranges;

	public Map<String, Set<SRange<?>>> getRanges() {
		return ranges;
	}

	private Set<String> aliases;

	public Set<String> getAliases() {
		return aliases;
	}

	public SearchRange() {
		ranges = new HashMap<String, Set<SRange<?>>>();
		aliases = new HashSet<String>();
	}

	public SearchRange(List<SRange<?>> sranges) {
		ranges = new HashMap<String, Set<SRange<?>>>();
		aliases = new HashSet<String>();
		for(SRange<?> range : sranges) {
			add(range);
		}
	}

	public void add(SRange<?> range) {
		if (ranges.containsKey(range.field)) {
			ranges.get(range.field).add(range);
		} else {
			Set<SRange<?>> rangeSet = new HashSet<SRange<?>>();
			rangeSet.add(range);
			ranges.put(range.field, rangeSet);
		}
		// alias necessary?
		if (range.field.contains(".")) {
			String[] fieldParts = range.field.split("\\.");
			aliases.add(fieldParts[0]);
		}
	}

	public Criteria buildCriteria(Session session, Class claz) {
		Criteria criteria = session.createCriteria(claz);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // distinct
		criteria.setCacheable(true);	// use cache
		// add aliases
		for (String alias : getAliases()) {
			criteria.createAlias(alias, alias);
		}
		// add fields
		for (String key : getRanges().keySet()) {
			List<SRange<?>> ranges = new ArrayList<SRange<?>>(getRanges().get(key));
			if (ranges.size() == 1) {
				criteria.add(getSimpleExpression(ranges.get(0)));
			} else {
				Disjunction disjunction = Restrictions.disjunction();
				for(SRange<?> range : ranges) {
					disjunction.add(getSimpleExpression(range));
				}
				criteria.add(disjunction);
			}
		}

		return criteria;
	}

	private SimpleExpression getSimpleExpression(SRange<?> range) {
		switch (range.operator) {
		case EQ:
			return Restrictions.eq(range.field, range.value);
		case NE:
			return Restrictions.ne(range.field, range.value);
		case GT:
			return Restrictions.gt(range.field, range.value);
		case GE:
			return Restrictions.ge(range.field, range.value);
		case LT:
			return Restrictions.lt(range.field, range.value);
		case LE:
			return Restrictions.le(range.field, range.value);
		case CT:
			return Restrictions.like(range.field, (String)range.value, MatchMode.ANYWHERE).ignoreCase();
		default:
			return null;
		}

	}
}
