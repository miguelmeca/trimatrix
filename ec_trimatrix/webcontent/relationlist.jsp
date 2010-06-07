<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="relationlistg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:pane id="g_3" >
<t:row id="g_4" >
<t:button id="g_5" actionListener="#{d.RelationListUI.onAdd}" enabled="#{d.RelationListUI.createAllowed}" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.list_add}" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.RelationListUI.onRemove}" enabled="#{d.RelationListUI.deleteAllowed}" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.list_remove}" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.RelationListUI.onStandard}" enabled="#{d.RelationListUI.changeAllowed}" text="#{rr.literals.list_set_standard}" />
</t:row>
<t:rowinclude id="g_10" />
</t:pane>
</t:row>
<t:row id="g_11" >
<t:arraygrid id="g_12" avoidroundtrips="true" columnresizingenabled="true" drawoddevenrows="true" height="100%" multiselect="false" objectbinding="#{d.RelationListUI.grid}" rowpopupmenu="ROW" sbvisibleamount="30" width="100%" />
</t:row>
<t:row id="g_13" >
<t:button id="g_14" actionListener="#{d.RelationListUI.onRefresh}" image="/images/icons/refresh.png" imageheight="15" text="#{rr.literals.list_refresh}" />
</t:row>
</t:rowbodypane>
<t:popupmenu id="ROW" >
<t:menuitem id="g_15" command="add" enabled="#{d.RelationListUI.createAllowed}" text="#{rr.literals.list_add}" />
<t:menuitem id="g_16" command="remove" enabled="#{d.RelationListUI.deleteAllowed}" text="#{rr.literals.list_remove}" />
<t:menuseparator id="g_17" />
<t:menuitem id="g_18" command="standard" enabled="#{d.RelationListUI.changeAllowed}" text="#{rr.literals.list_set_standard}" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
