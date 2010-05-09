<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="reportsg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:field id="g_3" text="#{d.ReportsUI.reportUrl}" width="300" />
<t:coldistance id="g_4" />
<t:button id="g_5" actionListener="#{d.ReportsUI.onLoadReport}" text="Load report" />
</t:row>
<t:row id="g_6" >
<t:browser id="g_7" enabled="true" height="100%" url="#{d.ReportsUI.browserUrl}" width="100%" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
