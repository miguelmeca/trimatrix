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
<t:rowdistance id="g_10" height="35" />
<t:row id="g_11" >
<t:outlookbar id="g_12" height="100%" width="100%" >
<t:outlookbaritem id="g_13" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="admin" rendered="#{d.WorkplaceUI.renderAdmin}" text="#{rr.literals.role_admin}" />
<t:outlookbaritem id="g_14" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="coach" rendered="#{d.WorkplaceUI.renderCoach}" text="#{rr.literals.role_trainer}" />
<t:outlookbaritem id="g_15" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="athlete" rendered="#{d.WorkplaceUI.renderAthlete}" text="#{rr.literals.role_athlet}" />
<t:outlookbarcontent id="g_16" >
<t:rowworkplacefunctiontree id="g_17" foreground="#000000" objectbinding="#{d.WPFunctionTreeAthlet}" rendered="#{d.WorkplaceUI.selectedRole == 2}" singleclickexecute="true" />
<t:rowworkplacefunctiontree id="g_18" foreground="#000000" objectbinding="#{d.WPFunctionTreeAdmin}" rendered="#{d.WorkplaceUI.selectedRole == 0}" singleclickexecute="true" />
<t:rowworkplacefunctiontree id="g_19" foreground="#000000" objectbinding="#{d.WPFunctionTreeCoach}" rendered="#{d.WorkplaceUI.selectedRole == 1}" singleclickexecute="true" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
<t:row id="g_20" >
<t:pane id="g_21" height="25" width="100%" >
<t:rowworkplacefunctiontree id="g_22" foreground="#000000" objectbinding="#{d.WPFunctionTreeTest}" />
</t:pane>
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_23" >
<t:row id="g_24" >
<t:pane id="g_25" height="100%" width="100%" >
<t:rowworkpagecontainer id="g_26" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:pane>
<t:pane id="g_27" height="100%" >
<t:rowdistance id="g_28" height="100%" />
<t:row id="g_29" >
<t:icon id="g_30" actionListener="#{d.WorkplaceUI.onShowHideLabels}" image="#{d.WorkplaceUI.labelsShowHideImage}" />
</t:row>
</t:pane>
<t:pane id="g_31" height="100%" rendered="#{d.WorkplaceUI.renderLabels}" rowdistance="35" >
<t:rowdistance id="g_32" height="35" />
<t:rowdynamiccontent id="g_33" contentbinding="#{d.WorkplaceUI.labels}" />
</t:pane>
</t:row>
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:row id="g_34" >
<t:pane id="g_35" width="100%" >
<t:rowworkpageselector id="g_36" objectbinding="#{d.workpageContainer}" />
</t:pane>
</t:row>
<t:popupmenu id="WORKPLACE" >
<t:menuitem id="g_37" actionListener="#{d.workpageContainer.onCloseCurrentWorkpage}" hotkey="ctrl-115" text="#{rr.literals.close_current_wp}" />
<t:menuitem id="g_38" actionListener="#{d.workpageContainer.onCloseAllWorkpages}" hotkey="ctrl-shift-115" text="#{rr.literals.close_all_wps}" />
</t:popupmenu>
<t:popupmenu id="LABEL" popupborder="#808080" >
<t:menuitem id="g_39" command="#{d.WorkplaceUI.changeLabelCommand}" text="#{rr.literals.change}" />
<t:menuitem id="g_40" command="#{d.WorkplaceUI.deleteLabelCommand}" text="#{rr.literals.delete}" />
</t:popupmenu>
<t:beanprocessing id="g_41" >
<t:sessioncloser id="g_42" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
