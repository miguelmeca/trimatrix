package trimatrix.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

@Aspect
public class CacheMonitor {
   public static final Log logger = LogFactory.getLog(CacheMonitor.class);
   private final static NumberFormat NF = new DecimalFormat("0.0###");

   private SessionFactory sessionFactory;
   public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

   @Around("execution(* trimatrix.db.*DAO.*(..)) or execution(* trimatrix.services.SQLExecutorService.*(..))")
   public Object log(ProceedingJoinPoint pjp) throws Throwable {
      if (!logger.isDebugEnabled()) {
         return pjp.proceed();
      }

      Statistics statistics = sessionFactory.getStatistics();
      statistics.setStatisticsEnabled(true);

      long hit0 = statistics.getQueryCacheHitCount();
      long miss0 = statistics.getSecondLevelCacheMissCount();

      Object result = pjp.proceed();

      long hit1 = statistics.getQueryCacheHitCount();
      long miss1 = statistics.getQueryCacheMissCount();

      double ratio = (double) hit1 / (hit1 + miss1);

      if (hit1 > hit0) {
         logger.debug(String.format("CACHE HIT; Ratio=%s; Signature=%s#%s()", NF.format(ratio), pjp.getTarget().getClass().getName(), pjp.getSignature().toShortString()));
      }
      else if (miss1 > miss0){
         logger.debug(String.format("CACHE MISS; Ratio=%s; Signature=%s#%s()", NF.format(ratio), pjp.getTarget().getClass().getName(), pjp.getSignature().toShortString()));
      }
      else {
         logger.debug("query cache not used");
      }

      return result;
   }
}