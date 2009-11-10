<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="competitionselectiong_sv">
<t:row id="g_1" >
<t:fixgrid id="g_2" avoidroundtrips="true" border="top:1;color:#808080" drawoddevenrows="true" multiselect="false" objectbinding="#{d.CompetitionSelectionUI.gridList}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_3" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.date}" width="100" >
<t:calendarfield id="g_4" enabled="false" timezone="CET" value=".{entity.date}" />
</t:gridcol>
<t:gridcol id="g_5" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.description}" width="200" >
<t:label id="g_6" text=".{entity.description}" />
</t:gridcol>
<t:gridcol id="g_7" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.category}" width="120" >
<t:label id="g_8" text=".{entity.type}" />
</t:gridcol>
<t:gridcol id="g_9" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.address}" width="250" >
<t:field id="g_10" enabled="false" text=".{entity.address}" />
</t:gridcol>
<t:gridcol id="g_11" align="center" searchenabled="true" sortreference=".{entity.country}" text="#{rr.literals.country}" width="100" >
<t:label id="g_12" text=".{entity.country}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_13" >
<t:button id="g_14" actionListener="#{d.DoctorSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_15" width="100%" />
<t:button id="g_16" actionListener="#{d.DoctorSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
