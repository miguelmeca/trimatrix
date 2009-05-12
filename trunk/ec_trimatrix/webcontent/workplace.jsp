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
<t:rowdistance id="g_5" height="25" />
<t:row id="g_6" >
<t:icon id="g_7" actionListener="#{d.WorkplaceUI.onShowHideFunctions}" image="#{d.WorkplaceUI.toggleShowHideImage}" />
</t:row>
</t:pane>
<t:splitpane id="g_8" border="top:0" dividercolor="#FFFFFF00" dividerlocation="#{d.WorkplaceUI.dividerLocation}" dividersize="5" height="100%" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_9" padding="1" rowdistance="35" >
<t:rowdistance id="g_10" />
<t:row id="g_11" >
<t:outlookbar id="g_12" height="100%" width="100%" >
<t:outlookbaritem id="g_13" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="admin" rendered="#{d.WorkplaceUI.renderAdmin}" text="#{rr.literals.role_admin}" />
<t:outlookbaritem id="g_14" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="coach" rendered="#{d.WorkplaceUI.renderCoach}" text="#{rr.literals.role_trainer}" />
<t:outlookbaritem id="g_15" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="athlete" rendered="#{d.WorkplaceUI.renderAthlete}" text="#{rr.literals.role_athlet}" />
<t:outlookbarcontent id="g_16" >
<t:rowworkplacefunctiontree id="g_17" foreground="#000000" objectbinding="#{d.WPFunctionTreeAthlet}" rendered="#{d.WorkplaceUI.selectedRole == 2}" />
<t:rowworkplacefunctiontree id="g_18" foreground="#000000" objectbinding="#{d.WPFunctionTreeAdmin}" rendered="#{d.WorkplaceUI.selectedRole == 0}" treenodebgpaint="border(0,0,100%,100%,#C0C0C0,2)" />
<t:rowworkplacefunctiontree id="g_19" foreground="#000000" objectbinding="#{d.WPFunctionTreeCoach}" rendered="#{d.WorkplaceUI.selectedRole == 1}" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_20" >
<t:rowworkpagecontainer id="g_21" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:row id="g_22" >
<t:pane id="g_23" width="100%" >
<t:rowworkpageselector id="g_24" objectbinding="#{d.workpageContainer}" />
</t:pane>
</t:row>
<t:popupmenu id="WORKPLACE" >
<t:menuitem id="g_25" />
<t:menuitem id="g_26" />
<t:menuitem id="g_27" />
<t:menuitem id="g_28" />
<t:menuitem id="g_29" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
