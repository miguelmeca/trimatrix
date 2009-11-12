<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="adminpanelg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" text="Anzahl angemeldeter Benuter" />
<t:coldistance id="g_4" />
<t:formattedfield id="g_5" format="int" value="#{d.AdminPanelUI.countUsers}" width="100" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.AdminPanelUI.onRefresh}" text="Aktualisieren" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
