package trimatrix.utils;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.orm.hibernate3.support.AbstractLobType;

public class BlobUserType extends AbstractLobType {

	public int[] sqlTypes() {
		return new int[] { Types.BLOB };
	}

	public Class returnedClass() {
		return InputStream.class;
	}

	protected Object nullSafeGetInternal(ResultSet rs, String[] names, Object owner, LobHandler lobHandler) throws SQLException, HibernateException {
		return lobHandler.getBlobAsBinaryStream(rs, names[0]);
	}

	protected void nullSafeSetInternal(PreparedStatement ps, int index, Object value, LobCreator lobCreator) throws SQLException, HibernateException {
		if (value != null) {
			lobCreator.setBlobAsBinaryStream(ps, index, (InputStream) value, -1);
		} else {
			lobCreator.setBlobAsBytes(ps, index, null);
		}
	}
}