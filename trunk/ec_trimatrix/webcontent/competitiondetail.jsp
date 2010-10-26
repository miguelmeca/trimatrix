<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="competitiondetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:rowdynamiccontent id="g_2" contentbinding="#{d.CompetitionDetailUI.labelRow}" />
<t:row id="g_3" >
<t:label id="g_4" text="#{rr.literals.description}" width="100" />
<t:field id="g_5" attributemacro="entityDetailMacro(CompetitionDetailUI,description)" maxlength="100" width="300" />
<t:coldistance id="g_6" width="20" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_9" actionListener="#{d.CompetitionDetailUI.onChangeType}" attributemacro="entityDetailMacro(CompetitionDetailUI,type)" enabled="#{d.CompetitionDetailUI.typeEnabled}" flush="true" validvaluesbinding="#{d.CompetitionDetailUI.compTypesVvb}" width="200" withnullitem="true" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.subtype}" width="100" />
<t:combobox id="g_12" attributemacro="entityDetailMacro(CompetitionDetailUI,subtype)" enabled="#{d.CompetitionDetailUI.typeEnabled}" flush="true" validvaluesbinding="#{d.CompetitionDetailUI.compSubtypesVvb}" width="200" withnullitem="true" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" text="#{rr.literals.date}" width="100" />
<t:calendarfield id="g_15" attributemacro="entityDetailMacro(CompetitionDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:row id="g_16" >
<t:label id="g_17" rowalignmenty="top" text="#{rr.literals.address}" width="100" />
<t:textarea id="g_18" attributemacro="entityDetailMacro(CompetitionDetailUI,address)" height="55" width="300" />
</t:row>
<t:row id="g_19" >
<t:label id="g_20" text="#{rr.literals.country}" width="100" />
<t:combobox id="g_21" attributemacro="entityDetailMacro(CompetitionDetailUI,country)" validvaluesbinding="#{helper.vvb.country}" width="200" />
</t:row>
<t:row id="g_22" rendered="#{d.CompetitionDetailUI.resultList}" >
<t:button id="g_23" actionListener="#{d.CompetitionDetailUI.onCreateResults}" enabled="#{d.CompetitionDetailUI.enabled}" text="#{rr.literals.create_results}" tooltip="#{rr.messages.create_result_detail}" />
</t:row>
<t:rowdistance id="g_24" />
<t:row id="g_25" comment="Limits" rendered="#{d.CompetitionDetailUI.myCompetition}" >
<t:pane id="g_26" height="100%" rowdistance="5" width="100%" >
<t:row id="g_27" >
<t:label id="g_28" font="size:12;weight:bold" text="#{rr.literals.limits}" />
</t:row>
<t:row id="g_29" >
<t:fixgrid id="g_30" avoidroundtrips="true" horizontalscrollmode="autowithresize" objectbinding="#{d.CompetitionDetailUI.gridLimits}" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_31" text="#{rr.literals.results_list}" width="100" >
<t:button id="g_32" actionListener=".{onAddResultList}" enabled=".{resultListActive}" image=".{resultsListIcon}" imageheight="15" />
</t:gridcol>
<t:gridcol id="g_33" align="center" text="#{rr.literals.category}" width="120" >
<t:combofield id="g_34" actionListener=".{onCategoryF4}" editable="true" enabled=".{categoryChangeable}" flush="true" flushtimer="1000" keepfocus="true" maxlength="10" text=".{category}" />
</t:gridcol>
<t:gridcol id="g_35" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.swimsuit}" width="60" >
<t:checkbox id="g_36" align="center" enabled="#{d.CompetitionDetailUI.enabled}" selected=".{swimsuit}" />
</t:gridcol>
<t:gridcol id="g_37" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.cutoff_swim}" width="120" >
<t:field id="g_38" align="center" background="#00FF00" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" font="weight:bold" maxlength="8" text=".{cutoffSwim}" tooltip="#{rr.literals.swim_corridor}" userhint="hh:mm:ss / xxx% " />
</t:gridcol>
<t:gridcol id="g_39" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.tol_swim}" width="120" >
<t:formattedfield id="g_40" align="center" enabled="#{d.CompetitionDetailUI.enabled}" format="int" userhint="#{rr.literals.seconds}" value=".{tolSwim}" />
</t:gridcol>
<t:gridcol id="g_41" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_swim_split}" width="130" >
<t:field id="g_42" align="center" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" maxlength="8" text=".{swimSplit}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_43" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_swimmer}" width="160" >
<t:field id="g_44" align="center" enabled="#{d.CompetitionDetailUI.enabled}" text=".{swimAthlet}" />
</t:gridcol>
<t:gridcol id="g_45" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.cutoff_run}" width="120" >
<t:field id="g_46" align="center" background="#00FF00" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" font="weight:bold" maxlength="8" text=".{cutoffRun}" tooltip="#{rr.literals.run_corridor}" userhint="hh:mm:ss / xxx%" />
</t:gridcol>
<t:gridcol id="g_47" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.tol_run}" width="120" >
<t:formattedfield id="g_48" align="center" enabled="#{d.CompetitionDetailUI.enabled}" format="int" userhint="#{rr.literals.seconds}" value=".{tolRun}" />
</t:gridcol>
<t:gridcol id="g_49" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_run_split}" width="130" >
<t:field id="g_50" align="center" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" maxlength="8" text=".{runSplit}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_51" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_runner}" width="160" >
<t:field id="g_52" align="center" enabled="#{d.CompetitionDetailUI.enabled}" text=".{runAthlet}" />
</t:gridcol>
<t:gridcol id="g_53" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.cutoff_bike}" width="120" >
<t:field id="g_54" align="center" background="#00FF00" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" font="weight:bold" maxlength="8" text=".{cutoffBike}" tooltip="#{rr.literals.bike_corridor}" userhint="hh:mm:ss / xxx%" />
</t:gridcol>
<t:gridcol id="g_55" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.tol_bike}" width="120" >
<t:formattedfield id="g_56" align="center" enabled="#{d.CompetitionDetailUI.enabled}" format="int" userhint="#{rr.literals.seconds}" value=".{tolBike}" />
</t:gridcol>
<t:gridcol id="g_57" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_bike_split}" width="130" >
<t:field id="g_58" align="center" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" maxlength="8" text=".{bikeSplit}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_59" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_biker}" width="160" >
<t:field id="g_60" align="center" enabled="#{d.CompetitionDetailUI.enabled}" text=".{bikeAthlet}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_61" >
<t:button id="g_62" actionListener="#{d.CompetitionDetailUI.onAddLimit}" enabled="#{d.CompetitionDetailUI.enabled}" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.list_add}" />
<t:coldistance id="g_63" />
<t:button id="g_64" actionListener="#{d.CompetitionDetailUI.onRemoveLimit}" enabled="#{d.CompetitionDetailUI.enabled}" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.list_remove}" />
<t:coldistance id="g_65" width="100%" />
<t:button id="g_66" actionListener="#{d.CompetitionDetailUI.gridLimits.onEditColumnDetails}" image="/images/icons/configure.png" imageheight="15" text="#{rr.literals.list_configure}" />
<t:coldistance id="g_67" />
<t:button id="g_68" actionListener="#{d.CompetitionDetailUI.saveGridState}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
