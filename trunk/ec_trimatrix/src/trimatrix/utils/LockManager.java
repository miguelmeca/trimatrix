package trimatrix.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import trimatrix.exceptions.EntityLockedException;

public class LockManager {
	private static ConcurrentMap<String, String> entityLockMap;

	static {
		entityLockMap = new ConcurrentHashMap<String, String>();
	}
	
	public static void lockEntry(String entityId) throws EntityLockedException{
		String sessionId = entityLockMap.get(entityId);
		if(sessionId!=null) {
			if(UserTracker.containsSession(sessionId)) {				
				throw new EntityLockedException(UserTracker.getUser(sessionId));
			} else {
				// remove lock
				UserTracker.deleteUser(sessionId);
			}
		}
		// set lock
		entityLockMap.putIfAbsent(entityId, Helper.getSession().getId());
	}
	
	public static void unlockEntity(String entityId) {
		entityLockMap.remove(entityId);
	}
	
	public static Map<String, String> getEntityLockMap() {
		return entityLockMap;
	}
}
