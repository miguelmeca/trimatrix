package trimatrix.logic.helper;

import com.twolattes.json.Json;
import com.twolattes.json.types.NullSafeType;

public class DoubleType extends NullSafeType<Double, Json.Number> {
	  @Override
	  protected Json.Number nullSafeMarshall(Double entity) {
	    return Json.number(Double.valueOf(entity.longValue()));
	  }

	  @Override
	  protected Double nullSafeUnmarshall(Json.Number object) {
	    return Double.valueOf(object.getNumber().longValueExact());
	  }

	  public Class<Double> getReturnedClass() {
	    return Double.class;
	  }
}
