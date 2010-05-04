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
<t:tabbedpanetab id="g_33" rowdistance="5" text="#{rr.literals.day_info}" >
<t:row id="g_34" >
<t:label id="g_35" font="size:12;weight:bold" text="#{rr.literals.hr_rest}" width="200" />
<t:coldistance id="g_36" />
<t:toggle id="g_37" selected="#{d.PreferencesPanelUI.dayInfo.restingHr}" />
</t:row>
<t:row id="g_38" >
<t:label id="g_39" font="size:12;weight:bold" text="#{rr.literals.weather}" width="200" />
<t:coldistance id="g_40" />
<t:toggle id="g_41" selected="#{d.PreferencesPanelUI.dayInfo.weather}" />
</t:row>
<t:row id="g_42" >
<t:label id="g_43" font="size:12;weight:bold" text="#{rr.literals.city}" width="200" />
<t:coldistance id="g_44" />
<t:toggle id="g_45" selected="#{d.PreferencesPanelUI.dayInfo.location}" />
</t:row>
<t:row id="g_46" >
<t:label id="g_47" font="size:12;weight:bold" text="#{rr.literals.weight}" width="200" />
<t:coldistance id="g_48" />
<t:toggle id="g_49" selected="#{d.PreferencesPanelUI.dayInfo.weight}" />
</t:row>
<t:row id="g_50" >
<t:label id="g_51" font="size:12;weight:bold" text="#{rr.literals.dinners}" width="200" />
<t:coldistance id="g_52" />
<t:toggle id="g_53" selected="#{d.PreferencesPanelUI.dayInfo.dinners}" />
</t:row>
<t:row id="g_54" >
<t:label id="g_55" font="size:12;weight:bold" text="#{rr.literals.fluids_intake}" width="200" />
<t:coldistance id="g_56" />
<t:toggle id="g_57" selected="#{d.PreferencesPanelUI.dayInfo.fluids}" />
</t:row>
<t:row id="g_58" >
<t:label id="g_59" font="size:12;weight:bold" text="#{rr.literals.sleeping_hours} / #{rr.literals.sleeping_quality}" width="200" />
<t:coldistance id="g_60" />
<t:toggle id="g_61" selected="#{d.PreferencesPanelUI.dayInfo.sleep}" />
</t:row>
<t:row id="g_62" >
<t:label id="g_63" font="size:12;weight:bold" text="#{rr.literals.tiredness}" width="200" />
<t:coldistance id="g_64" />
<t:toggle id="g_65" selected="#{d.PreferencesPanelUI.dayInfo.tiredness}" />
</t:row>
<t:row id="g_66" >
<t:label id="g_67" font="size:12;weight:bold" text="#{rr.literals.feeling}" width="200" />
<t:coldistance id="g_68" />
<t:toggle id="g_69" selected="#{d.PreferencesPanelUI.dayInfo.feeling}" />
</t:row>
<t:row id="g_70" >
<t:label id="g_71" font="size:12;weight:bold" text="#{rr.literals.training_intensity}" width="200" />
<t:coldistance id="g_72" />
<t:toggle id="g_73" selected="#{d.PreferencesPanelUI.dayInfo.intensity}" />
</t:row>
<t:row id="g_74" >
<t:label id="g_75" font="size:12;weight:bold" text="#{rr.literals.training_valuation}" width="200" />
<t:coldistance id="g_76" />
<t:toggle id="g_77" selected="#{d.PreferencesPanelUI.dayInfo.valuation}" />
</t:row>
<t:row id="g_78" >
<t:label id="g_79" font="size:12;weight:bold" text="#{rr.literals.laboratory}" width="200" />
<t:coldistance id="g_80" />
<t:toggle id="g_81" selected="#{d.PreferencesPanelUI.dayInfo.laboratory}" />
</t:row>
<t:row id="g_82" >
<t:label id="g_83" font="size:12;weight:bold" text="#{rr.literals.days}" width="200" />
<t:coldistance id="g_84" />
<t:toggle id="g_85" selected="#{d.PreferencesPanelUI.dayInfo.days}" />
</t:row>
<t:row id="g_86" >
<t:label id="g_87" font="size:12;weight:bold" text="#{rr.literals.illness}" width="200" />
<t:coldistance id="g_88" />
<t:toggle id="g_89" selected="#{d.PreferencesPanelUI.dayInfo.illness}" />
</t:row>
<t:row id="g_90" >
<t:label id="g_91" font="size:12;weight:bold" text="#{rr.literals.massage}" width="200" />
<t:coldistance id="g_92" />
<t:toggle id="g_93" selected="#{d.PreferencesPanelUI.dayInfo.massage}" />
</t:row>
<t:row id="g_94" >
<t:label id="g_95" font="size:12;weight:bold" text="#{rr.literals.therapy}" width="200" />
<t:coldistance id="g_96" />
<t:toggle id="g_97" selected="#{d.PreferencesPanelUI.dayInfo.therapy}" />
</t:row>
<t:row id="g_98" >
<t:label id="g_99" font="size:12;weight:bold" text="#{rr.literals.comments}" width="200" />
<t:coldistance id="g_100" />
<t:toggle id="g_101" selected="#{d.PreferencesPanelUI.dayInfo.comments}" />
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
