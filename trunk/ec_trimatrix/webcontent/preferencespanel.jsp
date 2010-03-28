<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="preferencespanelg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.PreferencesPanelUI.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
<t:row id="g_4" >
<t:tabbedpane id="g_5" height="100%" width="100%" >
<t:tabbedpanetab id="g_6" rowdistance="5" text="#{rr.literals.preferences}" >
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.sbvisibleamount}" width="200" />
<t:coldistance id="g_9" />
<t:formattedfield id="g_10" format="int" value="#{d.PreferencesPanelUI.sbvisibleamount}" width="40" />
</t:row>
<t:row id="g_11" >
<t:label id="g_12" rowalignmenty="top" text="#{rr.literals.competition_categories}" width="200" />
<t:coldistance id="g_13" />
<t:textarea id="g_14" height="40" text="#{d.PreferencesPanelUI.competitionCategories}" userhint="#{rr.literals.separate_semicolon}" width="350" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_15" rowdistance="5" text="#{rr.literals.defaults}" >
<t:row id="g_16" >
<t:pane id="g_17" height="100%" rowdistance="5" width="100%" >
<t:row id="g_18" >
<t:fixgrid id="g_19" avoidroundtrips="true" horizontalscrollmode="autowithresize" objectbinding="#{d.PreferencesPanelUI.gridDefaults}" sbvisibleamount="25" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_20" align="center" text="#{rr.literals.field}" width="120" >
<t:combobox id="g_21" bgpaint="mandatory()" value=".{userDefaults.field}" >
<t:comboboxitem id="g_22" text="#{rr.literals.type} (#{rr.literals.competition})" value="comptype" />
<t:comboboxitem id="g_23" text="#{rr.literals.subtype} (#{rr.literals.competition})" value="compsubtype" />
<t:comboboxitem id="g_24" text="#{rr.literals.tolerance} (#{rr.literals.limits})" value="tolerance" />
</t:combobox>
</t:gridcol>
<t:gridcol id="g_25" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.dependency}" width="120" >
<t:field id="g_26" text=".{userDefaults.dependency}" />
</t:gridcol>
<t:gridcol id="g_27" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.value}" width="150" >
<t:field id="g_28" bgpaint="mandatory()" text=".{userDefaults.value}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_29" >
<t:button id="g_30" actionListener="#{d.PreferencesPanelUI.onAddDefaultItem}" image="/images/icons/add.png" imageheight="16" text="#{rr.literals.list_add}" />
<t:coldistance id="g_31" />
<t:button id="g_32" actionListener="#{d.PreferencesPanelUI.onRemoveDefaultItem}" image="/images/icons/remove.png" imageheight="16" text="#{rr.literals.list_remove}" />
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
</t:tabbedpane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
