package trimatrix.ui.utils;

import org.eclnt.jsfserver.bufferedcontent.DefaultBufferedContent;

import trimatrix.db.Attachments;

public class MyBufferedContentForAttachment extends DefaultBufferedContent{

	Attachments attachment;
	
	public MyBufferedContentForAttachment(Attachments attachment) {
		this.attachment = attachment;
	}	
	
	public byte[] getContent() {
		return attachment.getFileContent();
	}

	public String getContentType() {
		return attachment.getMimeType();
	}

}
