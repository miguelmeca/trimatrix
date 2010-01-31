<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="competitiondetailg_sv">
<t:row id="g_1" >
<t:pane id="g_2" rowdistance="5" width="100%" >
<t:rowdynamiccontent id="g_3" contentbinding="#{d.CompetitionDetailUI.labelRow}" />
<t:row id="g_4" >
<t:label id="g_5" text="#{rr.literals.description}" width="100" />
<t:field id="g_6" attributemacro="entityDetailMacro(CompetitionDetailUI,description)" maxlength="100" width="300" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_9" attributemacro="entityDetailMacro(CompetitionDetailUI,type)" enabled="#{d.CompetitionDetailUI.typeEnabled}" validvaluesbinding="#{d.CompetitionDetailUI.compTypesVvb}" width="200" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.date}" width="100" />
<t:calendarfield id="g_12" attributemacro="entityDetailMacro(CompetitionDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" rowalignmenty="top" text="#{rr.literals.address}" width="100" />
<t:textarea id="g_15" attributemacro="entityDetailMacro(CompetitionDetailUI,address)" height="55" width="300" />
</t:row>
<t:row id="g_16" >
<t:label id="g_17" text="#{rr.literals.country}" width="100" />
<t:combobox id="g_18" attributemacro="entityDetailMacro(CompetitionDetailUI,country)" validvaluesbinding="#{d.CompetitionDetailUI.countriesVvb}" width="200" />
</t:row>
<t:rowdistance id="g_19" />
<t:row id="g_20" comment="Limits" rendered="#{d.CompetitionDetailUI.myCompetition}" >
<t:pane id="g_21" rowdistance="5" width="100%" >
<t:row id="g_22" >
<t:label id="g_23" font="size:12;weight:bold" text="#{rr.literals.limits}" />
</t:row>
<t:row id="g_24" >
<t:fixgrid id="g_25" avoidroundtrips="true" horizontalscrollmode="autowithresize" objectbinding="#{d.CompetitionDetailUI.gridLimits}" sbvisibleamount="10" showemptyrows="false" >
<t:gridcol id="g_26" columnresizingenabled="false" searchenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_27" />
</t:gridcol>
<t:gridcol id="g_28" align="center" text="#{rr.literals.category}" width="120" >
<t:combofield id="g_29" actionListener=".{onCategoryF4}" editable="true" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" flushtimer="1000" keepfocus="true" maxlength="10" text=".{category}" />
</t:gridcol>
<t:gridcol id="g_30" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.green_area_high}" width="120" >
<t:field id="g_31" align="center" background="#00FF00" enabled="#{d.CompetitionDetailUI.enabled}" font="weight:bold" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" text=".{greenAreaHigh}" tooltip="#{rr.literals.swim_corridor}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_32" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.red_area_low}" width="120" >
<t:field id="g_33" align="center" background="#FF0000" enabled="#{d.CompetitionDetailUI.enabled}" font="weight:bold" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" text=".{redAreaLow}" tooltip="#{rr.literals.swim_corridor}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_34" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_swim_split}" width="130" >
<t:field id="g_35" align="center" enabled="#{d.CompetitionDetailUI.enabled}" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" text=".{swimSplit}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_36" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_swimer}" width="160" >
<t:field id="g_37" align="center" enabled="#{d.CompetitionDetailUI.enabled}" text=".{swimAthlet}" />
</t:gridcol>
<t:gridcol id="g_38" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_run_split}" width="130" >
<t:field id="g_39" align="center" enabled="#{d.CompetitionDetailUI.enabled}" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" text=".{runSplit}" userhint="hh:mm:ss" />
</t:gridcol>
<t:gridcol id="g_40" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.best_runner}" width="160" >
<t:field id="g_41" align="center" enabled="#{d.CompetitionDetailUI.enabled}" text=".{runAthlet}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_42" >
<t:button id="g_43" actionListener="#{d.CompetitionDetailUI.onAddLimit}" enabled="#{d.CompetitionDetailUI.enabled}" text="#{rr.literals.list_add}" />
<t:coldistance id="g_44" />
<t:button id="g_45" actionListener="#{d.CompetitionDetailUI.onRemoveLimit}" enabled="#{d.CompetitionDetailUI.enabled}" text="#{rr.literals.list_remove}" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
