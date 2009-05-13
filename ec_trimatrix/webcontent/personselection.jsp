<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="personselectiong_sv">
<t:row id="g_1" >
<t:fixgrid id="g_2" avoidroundtrips="true" border="top:1;color:#808080" drawoddevenrows="true" multiselect="false" objectbinding="#{d.PersonSelectionUI.gridList}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_3" align="center" searchenabled="true" sortreference=".{entity.name_last}" text="#{rr.literals.person_salutation}" width="50" >
<t:label id="g_4" text=".{entity.salutation}" />
</t:gridcol>
<t:gridcol id="g_5" align="center" searchenabled="true" sortreference=".{entity.name_last}" text="#{rr.literals.person_first_name}" width="100" >
<t:label id="g_6" text=".{entity.name_first}" />
</t:gridcol>
<t:gridcol id="g_7" align="center" searchenabled="true" sortreference=".{entity.name_last}" text="#{rr.literals.person_last_name}" width="100" >
<t:label id="g_8" text=".{entity.name_last}" />
</t:gridcol>
<t:gridcol id="g_9" align="center" searchenabled="true" sortreference=".{entity.name_last}" text="#{rr.literals.person_email}" width="100" >
<t:label id="g_10" text=".{entity.email}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_11" >
<t:button id="g_12" actionListener="#{d.PersonSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_13" width="100%" />
<t:button id="g_14" actionListener="#{d.PersonSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
