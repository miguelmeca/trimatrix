package trimatrix.utils;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author reich
 * http://redrata.com/2009/04/resolving-javasqltimestamp-does-not-have-a-no-arg-default-constructor/
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
    public Date marshal(Timestamp v) {
        return new Date(v.getTime());
    }
    public Timestamp unmarshal(Date v) {
        return new Timestamp(v.getTime());
    }
}