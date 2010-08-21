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
<t:label id="g_12" text="#{rr.literals.resultion_test_dia}" width="200" />
<t:coldistance id="g_13" />
<t:formattedfield id="g_14" bgpaint="write(100%-5,100%-5,#{rr.literals.width},9,#a0a0a0,rightmiddle)" format="int" value="#{d.PreferencesPanelUI.widthTestsDia}" width="40" />
<t:coldistance id="g_15" />
<t:formattedfield id="g_16" bgpaint="write(100%-5,100%-5,#{rr.literals.height2},9,#a0a0a0,rightmiddle)" format="int" value="#{d.PreferencesPanelUI.heightTestsDia}" width="40" />
</t:row>
<t:row id="g_17" >
<t:label id="g_18" rowalignmenty="top" text="#{rr.literals.competition_categories}" width="200" />
<t:coldistance id="g_19" />
<t:textarea id="g_20" height="40" text="#{d.PreferencesPanelUI.competitionCategories}" userhint="#{rr.literals.separate_semicolon}" width="350" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_21" rowdistance="5" text="#{rr.literals.defaults}" >
<t:row id="g_22" >
<t:pane id="g_23" height="100%" rowdistance="5" width="100%" >
<t:row id="g_24" >
<t:fixgrid id="g_25" avoidroundtrips="true" horizontalscrollmode="autowithresize" objectbinding="#{d.PreferencesPanelUI.gridDefaults}" sbvisibleamount="25" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_26" align="center" text="#{rr.literals.field}" width="120" >
<t:combobox id="g_27" bgpaint="mandatory()" value=".{userDefaults.field}" >
<t:comboboxitem id="g_28" text="#{rr.literals.type} (#{rr.literals.competition})" value="comptype" />
<t:comboboxitem id="g_29" text="#{rr.literals.subtype} (#{rr.literals.competition})" value="compsubtype" />
<t:comboboxitem id="g_30" text="#{rr.literals.tolerance} (#{rr.literals.limits})" value="tolerance" />
</t:combobox>
</t:gridcol>
<t:gridcol id="g_31" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.dependency}" width="120" >
<t:field id="g_32" text=".{userDefaults.dependency}" />
</t:gridcol>
<t:gridcol id="g_33" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.value}" width="150" >
<t:field id="g_34" bgpaint="mandatory()" text=".{userDefaults.value}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_35" >
<t:button id="g_36" actionListener="#{d.PreferencesPanelUI.onAddDefaultItem}" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.list_add}" />
<t:coldistance id="g_37" />
<t:button id="g_38" actionListener="#{d.PreferencesPanelUI.onRemoveDefaultItem}" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.list_remove}" />
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_39" rowdistance="5" text="#{rr.literals.day_info}" >
<t:row id="g_40" >
<t:combobox id="g_41" actionListener="#{d.PreferencesPanelUI.onChangeAthlete}" enabled="#{d.PreferencesPanelUI.trainer}" flush="true" focusable="#{d.ScheduleUI.trainer}" font="size:12;weight:bold" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.PreferencesPanelUI.athleteID}" width="200" withnullitem="false" />
</t:row>
<t:rowline id="g_42" />
<t:row id="g_43" >
<t:label id="g_44" font="size:12;weight:bold" text="#{rr.literals.hr_rest}" width="200" />
<t:coldistance id="g_45" />
<t:toggle id="g_46" selected="#{d.PreferencesPanelUI.dayInfo.restingHr}" />
</t:row>
<t:row id="g_47" >
<t:label id="g_48" font="size:12;weight:bold" text="#{rr.literals.weather}" width="200" />
<t:coldistance id="g_49" />
<t:toggle id="g_50" selected="#{d.PreferencesPanelUI.dayInfo.weather}" />
</t:row>
<t:row id="g_51" >
<t:label id="g_52" font="size:12;weight:bold" text="#{rr.literals.city}" width="200" />
<t:coldistance id="g_53" />
<t:toggle id="g_54" selected="#{d.PreferencesPanelUI.dayInfo.location}" />
</t:row>
<t:row id="g_55" >
<t:label id="g_56" font="size:12;weight:bold" text="#{rr.literals.weight}" width="200" />
<t:coldistance id="g_57" />
<t:toggle id="g_58" selected="#{d.PreferencesPanelUI.dayInfo.weight}" />
</t:row>
<t:row id="g_59" >
<t:label id="g_60" font="size:12;weight:bold" text="#{rr.literals.dinners}" width="200" />
<t:coldistance id="g_61" />
<t:toggle id="g_62" selected="#{d.PreferencesPanelUI.dayInfo.dinners}" />
</t:row>
<t:row id="g_63" >
<t:label id="g_64" font="size:12;weight:bold" text="#{rr.literals.fluids_intake}" width="200" />
<t:coldistance id="g_65" />
<t:toggle id="g_66" selected="#{d.PreferencesPanelUI.dayInfo.fluids}" />
</t:row>
<t:row id="g_67" >
<t:label id="g_68" font="size:12;weight:bold" text="#{rr.literals.sleeping_hours} / #{rr.literals.sleeping_quality}" width="200" />
<t:coldistance id="g_69" />
<t:toggle id="g_70" selected="#{d.PreferencesPanelUI.dayInfo.sleep}" />
</t:row>
<t:row id="g_71" >
<t:label id="g_72" font="size:12;weight:bold" text="#{rr.literals.tiredness}" width="200" />
<t:coldistance id="g_73" />
<t:toggle id="g_74" selected="#{d.PreferencesPanelUI.dayInfo.tiredness}" />
</t:row>
<t:row id="g_75" >
<t:label id="g_76" font="size:12;weight:bold" text="#{rr.literals.feeling}" width="200" />
<t:coldistance id="g_77" />
<t:toggle id="g_78" selected="#{d.PreferencesPanelUI.dayInfo.feeling}" />
</t:row>
<t:row id="g_79" >
<t:label id="g_80" font="size:12;weight:bold" text="#{rr.literals.training_intensity}" width="200" />
<t:coldistance id="g_81" />
<t:toggle id="g_82" selected="#{d.PreferencesPanelUI.dayInfo.intensity}" />
</t:row>
<t:row id="g_83" >
<t:label id="g_84" font="size:12;weight:bold" text="#{rr.literals.training_valuation}" width="200" />
<t:coldistance id="g_85" />
<t:toggle id="g_86" selected="#{d.PreferencesPanelUI.dayInfo.valuation}" />
</t:row>
<t:row id="g_87" >
<t:label id="g_88" font="size:12;weight:bold" text="#{rr.literals.laboratory}" width="200" />
<t:coldistance id="g_89" />
<t:toggle id="g_90" selected="#{d.PreferencesPanelUI.dayInfo.laboratory}" />
</t:row>
<t:row id="g_91" >
<t:label id="g_92" font="size:12;weight:bold" text="#{rr.literals.days}" width="200" />
<t:coldistance id="g_93" />
<t:toggle id="g_94" selected="#{d.PreferencesPanelUI.dayInfo.days}" />
</t:row>
<t:row id="g_95" >
<t:label id="g_96" font="size:12;weight:bold" text="#{rr.literals.illness}" width="200" />
<t:coldistance id="g_97" />
<t:toggle id="g_98" selected="#{d.PreferencesPanelUI.dayInfo.illness}" />
</t:row>
<t:row id="g_99" >
<t:label id="g_100" font="size:12;weight:bold" text="#{rr.literals.massage}" width="200" />
<t:coldistance id="g_101" />
<t:toggle id="g_102" selected="#{d.PreferencesPanelUI.dayInfo.massage}" />
</t:row>
<t:row id="g_103" >
<t:label id="g_104" font="size:12;weight:bold" text="#{rr.literals.therapy}" width="200" />
<t:coldistance id="g_105" />
<t:toggle id="g_106" selected="#{d.PreferencesPanelUI.dayInfo.therapy}" />
</t:row>
<t:row id="g_107" >
<t:label id="g_108" font="size:12;weight:bold" text="#{rr.literals.comments}" width="200" />
<t:coldistance id="g_109" />
<t:toggle id="g_110" selected="#{d.PreferencesPanelUI.dayInfo.comments}" />
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
