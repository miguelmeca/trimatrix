<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="workplaceg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:coldistance id="g_3" width="100%" />
<t:label id="g_4" font="size:10;weight:bold" text="#{d.WorkplaceUI.loggedInUser}" />
<t:coldistance id="g_5" />
<t:workplaceperspectiveselector id="g_6" objectbinding="#{d.workpageContainer}" />
</t:row>
<t:row id="g_7" >
<t:pane id="g_8" height="100%" width="28" >
<t:row id="g_9" >
<t:icon id="g_10" actionListener="#{d.WorkplaceUI.onPreferences}" image="/images/icons/preferences.png" imageheight="22" imagewidth="22" />
</t:row>
<t:rowworkpagefavorites id="g_11" objectbinding="#{d.WPFavorites}" rowalignmenty="bottom" withicontext="false" />
<t:rowdistance id="g_12" height="25" />
<t:row id="g_13" >
<t:icon id="g_14" actionListener="#{d.WorkplaceUI.onShowHideFunctions}" image="#{d.WorkplaceUI.toggleShowHideImage}" />
</t:row>
</t:pane>
<t:splitpane id="g_15" border="top:0" dividercolor="#FFFFFF00" dividerlocation="#{d.WorkplaceUI.dividerLocation}" dividersize="5" height="100%" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_16" padding="1" rowdistance="5" >
<t:rowdistance id="g_17" height="5" />
<t:row id="g_18" >
<t:coldistance id="g_19" width="50%" />
<t:image id="g_20" align="center" image="/images/trimatrix.png" />
<t:coldistance id="g_21" width="50%" />
</t:row>
<t:row id="g_22" >
<t:outlookbar id="g_23" height="100%" width="100%" >
<t:outlookbaritem id="g_24" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="admin" rendered="#{d.WorkplaceUI.renderAdmin}" text="#{rr.literals.role_admin}" />
<t:outlookbaritem id="g_25" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="coach" rendered="#{d.WorkplaceUI.renderCoach}" text="#{rr.literals.role_trainer}" />
<t:outlookbaritem id="g_26" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="athlete" rendered="#{d.WorkplaceUI.renderAthlete}" text="#{rr.literals.role_athlet}" />
<t:outlookbaritem id="g_27" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="scouter" rendered="#{d.WorkplaceUI.renderScouter}" text="#{rr.literals.role_scouter}" />
<t:outlookbarcontent id="g_28" >
<t:rowworkplacefunctiontree id="g_29" foreground="#000000" objectbinding="#{d.WPFunctionTreeAthlet}" rendered="#{d.WorkplaceUI.selectedRole == 2}" singleclickexecute="false" />
<t:rowworkplacefunctiontree id="g_30" foreground="#000000" objectbinding="#{d.WPFunctionTreeAdmin}" rendered="#{d.WorkplaceUI.selectedRole == 0}" singleclickexecute="false" />
<t:rowworkplacefunctiontree id="g_31" foreground="#000000" objectbinding="#{d.WPFunctionTreeCoach}" rendered="#{d.WorkplaceUI.selectedRole == 1}" singleclickexecute="false" />
<t:rowworkplacefunctiontree id="g_32" foreground="#000000" objectbinding="#{d.WPFunctionTreeScouter}" rendered="#{d.WorkplaceUI.selectedRole == 3}" singleclickexecute="false" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
<t:row id="g_33" >
<t:pane id="g_34" height="25" width="100%" >
<t:rowworkplacefunctiontree id="g_35" foreground="#000000" objectbinding="#{d.WPFunctionTreeTest}" />
</t:pane>
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_36" >
<t:row id="g_37" >
<t:pane id="g_38" height="100%" width="100%" >
<t:rowworkplace id="g_39" animationtype="foglight" objectbinding="#{d.workpageContainer}" wpselectorposition="bottom" />
</t:pane>
<t:pane id="g_40" height="100%" >
<t:rowdistance id="g_41" height="100%" />
<t:row id="g_42" >
<t:icon id="g_43" actionListener="#{d.WorkplaceUI.onShowHideLabels}" image="#{d.WorkplaceUI.labelsShowHideImage}" />
</t:row>
</t:pane>
<t:pane id="g_44" height="100%" rendered="#{d.WorkplaceUI.renderLabels}" rowdistance="35" >
<t:rowdistance id="g_45" height="35" />
<t:rowdynamiccontent id="g_46" contentbinding="#{d.WorkplaceUI.labels}" />
</t:pane>
</t:row>
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:row id="g_47" >
<t:pane id="g_48" width="100%" >
<t:rowworkpageselector id="g_49" objectbinding="#{d.workpageContainer}" />
</t:pane>
</t:row>
<t:popupmenu id="WORKPLACE" >
<t:menuitem id="g_50" actionListener="#{d.workpageContainer.onCloseCurrentWorkpage}" hotkey="ctrl-115" text="#{rr.literals.close_current_wp}" />
<t:menuitem id="g_51" actionListener="#{d.workpageContainer.onCloseAllWorkpages}" hotkey="ctrl-shift-115" text="#{rr.literals.close_all_wps}" />
</t:popupmenu>
<t:popupmenu id="LABEL" popupborder="#808080" >
<t:menuitem id="g_52" command="#{d.WorkplaceUI.changeLabelCommand}" text="#{rr.literals.change}" />
<t:menuitem id="g_53" command="#{d.WorkplaceUI.deleteLabelCommand}" text="#{rr.literals.delete}" />
</t:popupmenu>
<t:beanprocessing id="g_54" >
<t:sessioncloser id="g_55" />
<t:beanmethodinvoker id="g_56" actionListener="#{d.WorkplaceUI.checkSessionMessage}" jsfphase="invokeEnd" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
