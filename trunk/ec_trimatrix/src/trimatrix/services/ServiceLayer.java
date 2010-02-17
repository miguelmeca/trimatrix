package trimatrix.services;

import org.springframework.context.ApplicationContext;

import trimatrix.utils.Dictionary;

public class ServiceLayer {
	private ValueListBindingService valueListBindingService;
	private Dictionary dictionaryService;
	private EncryptionService encryptionService;
	private ResolverService resolverService;
	private SQLExecutorService sqlExecutorService;
	private AuthorizationService authorizationService;
	private MailService mailService;

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

	public EncryptionService getEncryptionService() {
		return encryptionService;
	}

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public ResolverService getResolverService() {
		return resolverService;
	}

	public void setResolverService(ResolverService resolverService) {
		this.resolverService = resolverService;
	}

	public SQLExecutorService getSqlExecutorService() {
		return sqlExecutorService;
	}

	public void setSqlExecutorService(SQLExecutorService sqlExecutorService) {
		this.sqlExecutorService = sqlExecutorService;
	}

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}

	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public static ServiceLayer getFromApplicationContext(ApplicationContext ctx) {
		return (ServiceLayer) ctx.getBean("serviceLayer");
	}
}
