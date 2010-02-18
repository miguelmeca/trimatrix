package trimatrix.db;

public interface ITextDAO {
	public abstract IText findById(String key, String languageKey);
}
