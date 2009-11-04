package trimatrix.utils;

import java.sql.Timestamp;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;

import trimatrix.entities.IEntityObject;

/**
 * This class fixes the mySQL specific problem of truncating ms of timestamps.
 * This causes problems with the Hibernate optimistic lock mechanism provided
 * by the Version annotation
 * @author reich
 *
 */
public class MySqlTimestampFixInterceptor extends EmptyInterceptor {
	@SuppressWarnings("unchecked")
	@Override
	public void postFlush(Iterator entities) {
		while (entities.hasNext()) {
			Object entity = entities.next();
			if (entity instanceof IEntityObject	&& ((IEntityObject) entity).getModifiedAt() != null) {
				IEntityObject entityObject = (IEntityObject) entity;
				//System.out.println(entityObject.getModifiedAt());
				Timestamp timestamp = new java.sql.Timestamp((entityObject.getModifiedAt().getTime() / 1000) * 1000);
				entityObject.setModifiedAt(timestamp);
				//System.out.println(entityObject.getModifiedAt());
			}
		}
	}
}
