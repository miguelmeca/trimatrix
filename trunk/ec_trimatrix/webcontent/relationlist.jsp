<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="relationlistg_sv">
<t:row id="g_1" >
<t:pane id="g_2" >
<t:row id="g_3" >
<t:button id="g_4" actionListener="#{d.RelationListUI.onAdd}" enabled="#{d.RelationListUI.createAllowed}" text="#{rr.literals.list_add}" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.RelationListUI.onRemove}" enabled="#{d.RelationListUI.deleteAllowed}" text="#{rr.literals.list_remove}" />
</t:row>
<t:rowinclude id="g_7" />
</t:pane>
</t:row>
<t:rowdistance id="g_8" />
<t:row id="g_9" >
<t:arraygrid id="g_10" avoidroundtrips="true" columnresizingenabled="true" drawoddevenrows="true" height="100%" multiselect="false" objectbinding="#{d.RelationListUI.grid}" rowpopupmenu="ROW" sbvisibleamount="30" width="100%" />
</t:row>
<t:rowdistance id="g_11" />
<t:row id="g_12" >
<t:button id="g_13" actionListener="#{d.RelationListUI.onRefresh}" text="#{rr.literals.list_refresh}" />
</t:row>
<t:popupmenu id="ROW" >
<t:menuitem id="g_14" command="add" enabled="#{d.RelationListUI.createAllowed}" text="#{rr.literals.list_add}" />
<t:menuitem id="g_15" command="remove" enabled="#{d.RelationListUI.deleteAllowed}" text="#{rr.literals.list_remove}" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->