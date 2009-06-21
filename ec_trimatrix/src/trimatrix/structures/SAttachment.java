package trimatrix.structures;

public class SAttachment {
	public String name;
	public String mimeType;
	public byte[] content;

	public SAttachment(String name, byte[] content, String mimeType) {
		this.name = name;
		this.content = content;
		this.mimeType = mimeType;
	}	
}
