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
<t:row id="g_4" >
<t:icon id="g_5" actionListener="#{d.WorkplaceUI.onPreferences}" image="/images/icons/preferences.png" imageheight="25" imagewidth="25" />
</t:row>
<t:rowworkpagefavorites id="g_6" objectbinding="#{d.WPFavorites}" rowalignmenty="bottom" withicontext="false" />
<t:rowdistance id="g_7" height="25" />
<t:row id="g_8" >
<t:icon id="g_9" actionListener="#{d.WorkplaceUI.onShowHideFunctions}" image="#{d.WorkplaceUI.toggleShowHideImage}" />
</t:row>
</t:pane>
<t:splitpane id="g_10" border="top:0" dividercolor="#FFFFFF00" dividerlocation="#{d.WorkplaceUI.dividerLocation}" dividersize="5" height="100%" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_11" padding="1" rowdistance="5" >
<t:rowdistance id="g_12" height="5" />
<t:row id="g_13" >
<t:coldistance id="g_14" width="50%" />
<t:image id="g_15" align="center" image="/images/trimatrix.png" />
<t:coldistance id="g_16" width="50%" />
</t:row>
<t:row id="g_17" >
<t:outlookbar id="g_18" height="100%" width="100%" >
<t:outlookbaritem id="g_19" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="admin" rendered="#{d.WorkplaceUI.renderAdmin}" text="#{rr.literals.role_admin}" />
<t:outlookbaritem id="g_20" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="coach" rendered="#{d.WorkplaceUI.renderCoach}" text="#{rr.literals.role_trainer}" />
<t:outlookbaritem id="g_21" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="athlete" rendered="#{d.WorkplaceUI.renderAthlete}" text="#{rr.literals.role_athlet}" />
<t:outlookbaritem id="g_22" actionListener="#{d.WorkplaceUI.onSwitchRole}" clientname="scouter" rendered="#{d.WorkplaceUI.renderScouter}" text="#{rr.literals.role_scouter}" />
<t:outlookbarcontent id="g_23" >
<t:rowworkplacefunctiontree id="g_24" foreground="#000000" objectbinding="#{d.WPFunctionTreeAthlet}" rendered="#{d.WorkplaceUI.selectedRole == 2}" singleclickexecute="false" />
<t:rowworkplacefunctiontree id="g_25" foreground="#000000" objectbinding="#{d.WPFunctionTreeAdmin}" rendered="#{d.WorkplaceUI.selectedRole == 0}" singleclickexecute="false" />
<t:rowworkplacefunctiontree id="g_26" foreground="#000000" objectbinding="#{d.WPFunctionTreeCoach}" rendered="#{d.WorkplaceUI.selectedRole == 1}" singleclickexecute="false" />
<t:rowworkplacefunctiontree id="g_27" foreground="#000000" objectbinding="#{d.WPFunctionTreeScouter}" rendered="#{d.WorkplaceUI.selectedRole == 3}" singleclickexecute="false" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
<t:row id="g_28" >
<t:pane id="g_29" height="25" width="100%" >
<t:rowworkplacefunctiontree id="g_30" foreground="#000000" objectbinding="#{d.WPFunctionTreeTest}" />
</t:pane>
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_31" >
<t:row id="g_32" >
<t:pane id="g_33" height="100%" width="100%" >
<t:rowworkpagecontainer id="g_34" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:pane>
<t:pane id="g_35" height="100%" >
<t:rowdistance id="g_36" height="100%" />
<t:row id="g_37" >
<t:icon id="g_38" actionListener="#{d.WorkplaceUI.onShowHideLabels}" image="#{d.WorkplaceUI.labelsShowHideImage}" />
</t:row>
</t:pane>
<t:pane id="g_39" height="100%" rendered="#{d.WorkplaceUI.renderLabels}" rowdistance="35" >
<t:rowdistance id="g_40" height="35" />
<t:rowdynamiccontent id="g_41" contentbinding="#{d.WorkplaceUI.labels}" />
</t:pane>
</t:row>
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:row id="g_42" >
<t:pane id="g_43" width="100%" >
<t:rowworkpageselector id="g_44" objectbinding="#{d.workpageContainer}" />
</t:pane>
</t:row>
<t:popupmenu id="WORKPLACE" >
<t:menuitem id="g_45" actionListener="#{d.workpageContainer.onCloseCurrentWorkpage}" hotkey="ctrl-115" text="#{rr.literals.close_current_wp}" />
<t:menuitem id="g_46" actionListener="#{d.workpageContainer.onCloseAllWorkpages}" hotkey="ctrl-shift-115" text="#{rr.literals.close_all_wps}" />
</t:popupmenu>
<t:popupmenu id="LABEL" popupborder="#808080" >
<t:menuitem id="g_47" command="#{d.WorkplaceUI.changeLabelCommand}" text="#{rr.literals.change}" />
<t:menuitem id="g_48" command="#{d.WorkplaceUI.deleteLabelCommand}" text="#{rr.literals.delete}" />
</t:popupmenu>
<t:beanprocessing id="g_49" >
<t:sessioncloser id="g_50" />
<t:beanmethodinvoker id="g_51" actionListener="#{d.WorkplaceUI.checkSessionMessage}" jsfphase="invokeEnd" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
