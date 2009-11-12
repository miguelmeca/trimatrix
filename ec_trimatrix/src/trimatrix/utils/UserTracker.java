package trimatrix.utils;

public class UserTracker {
	private static Integer	loggedInUsers;
	
	static {
		loggedInUsers = 0;
	}

	public static void increment() {
		loggedInUsers++;
	}

	public static void decrement() {
		loggedInUsers--;
	}

	public static Integer getLoggedInusers() {
		return loggedInUsers;
	}
}
