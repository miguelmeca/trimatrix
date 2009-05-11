<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="workplaceg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:pane id="g_3" height="100%" width="28" >
<t:rowworkpagefavorites id="g_4" objectbinding="#{d.WPFavorites}" rowalignmenty="bottom" withicontext="false" />
</t:pane>
<t:splitpane id="g_5" height="100%" width="100%" >
<t:splitpanesplit id="g_6" padding="1" rowdistance="35" >
<t:rowdistance id="g_7" />
<t:row id="g_8" >
<t:outlookbar id="g_9" height="100%" width="100%" >
<t:outlookbaritem id="g_10" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="admin" rendered="#{d.WorkplaceUI.renderAdmin}" text="#{rr.literals.role_admin}" />
<t:outlookbaritem id="g_11" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="coach" rendered="#{d.WorkplaceUI.renderCoach}" text="#{rr.literals.role_trainer}" />
<t:outlookbaritem id="g_12" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="athlete" rendered="#{d.WorkplaceUI.renderAthlete}" text="#{rr.literals.role_athlet}" />
<t:outlookbarcontent id="g_13" >
<t:rowworkplacefunctiontree id="g_14" foreground="#000000" objectbinding="#{d.WPFunctionTreeAthlet}" rendered="#{d.WorkplaceUI.selectedRole == 2}" />
<t:rowworkplacefunctiontree id="g_15" foreground="#000000" objectbinding="#{d.WPFunctionTreeAdmin}" rendered="#{d.WorkplaceUI.selectedRole == 0}" treenodebgpaint="border(0,0,100%,100%,#C0C0C0,2)" />
<t:rowworkplacefunctiontree id="g_16" foreground="#000000" objectbinding="#{d.WPFunctionTreeCoach}" rendered="#{d.WorkplaceUI.selectedRole == 1}" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_17" >
<t:rowworkpagecontainer id="g_18" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:row id="g_19" >
<t:pane id="g_20" >
<t:rowworkpageselector id="g_21" objectbinding="#{d.workpageContainer}" />
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
