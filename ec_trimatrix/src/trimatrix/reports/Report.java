package trimatrix.reports;

import org.eclnt.jsfserver.bufferedcontent.DefaultBufferedContent;

public abstract class Report extends DefaultBufferedContent {
	public abstract String getFilename();
	public abstract String getExtension();
}
