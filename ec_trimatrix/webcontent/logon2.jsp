<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="logon2g_sv">
<t:beanprocessing id="g_1" >
<t:clientconfig id="g_2" country="#{d.Logon2UI.clientCountry}" language="#{d.Logon2UI.clientLanguage}" />
</t:beanprocessing>
<t:rowbodypane id="g_3" >
<t:row id="g_4" >
<t:clock id="g_5" clockformat="dateLong" />
</t:row>
<t:rowdistance id="g_6" />
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.logon_user}" />
<t:coldistance id="g_9" />
</t:row>
<t:rowdistance id="g_10" />
<t:row id="g_11" >
<t:link id="g_12" actionListener="#{d.Logon2UI.onGerman}" text="Deutsch" />
<t:coldistance id="g_13" />
<t:link id="g_14" actionListener="#{d.Logon2UI.onEnglish}" text="Englisch" />
<t:coldistance id="g_15" />
<t:link id="g_16" actionListener="#{d.Logon2UI.onReload}" text="Reload" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
