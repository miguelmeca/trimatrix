package trimatrix.services;

import org.springframework.context.ApplicationContext;

import trimatrix.utils.Dictionary;

public class ServiceLayer {
	private ValueListBindingService valueListBindingService;
	private Dictionary dictionaryService;
	private EntityResolverService entityResolverService;
	private SQLExecutorService sqlExecutorService;

	public ValueListBindingService getValueListBindingService() {
		return valueListBindingService;
	}

	public void setValueListBindingService(
			ValueListBindingService valueListBindingService) {
		this.valueListBindingService = valueListBindingService;
	}
		
	public Dictionary getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(Dictionary dictionaryService) {
		this.dictionaryService = dictionaryService;
	}	

	public EntityResolverService getEntityResolverService() {
		return entityResolverService;
	}

	public void setEntityResolverService(EntityResolverService entityResolverService) {
		this.entityResolverService = entityResolverService;
	}

	public SQLExecutorService getSqlExecutorService() {
		return sqlExecutorService;
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}

	public static ServiceLayer getFromApplicationContext(ApplicationContext ctx) {
		return (ServiceLayer) ctx.getBean("serviceLayer");
	}
}
