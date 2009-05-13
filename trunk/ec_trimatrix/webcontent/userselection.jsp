<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="userselectiong_sv">
<t:row id="g_1" >
<t:fixgrid id="g_2" avoidroundtrips="true" border="top:1;color:#808080" drawoddevenrows="true" multiselect="false" objectbinding="#{d.UserSelectionUI.gridList}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_3" align="center" searchenabled="true" sortreference=".{entity.user_name}" text="#{rr.literals.user_user_name}" width="100" >
<t:label id="g_4" text=".{entity.user_name}" />
</t:gridcol>
<t:gridcol id="g_5" align="center" searchenabled="true" sortreference=".{entity.user_name}" text="#{rr.literals.user_person}" width="100" >
<t:label id="g_6" text=".{entity.person}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_7" >
<t:button id="g_8" actionListener="#{d.UserSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_9" width="100%" />
<t:button id="g_10" actionListener="#{d.UserSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
