<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

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
</t:row>
<t:row id="g_6" >
<t:label id="g_7" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_8" attributemacro="entityDetailMacro(CompetitionDetailUI,type)" enabled="#{d.CompetitionDetailUI.typeEnabled}" validvaluesbinding="#{d.CompetitionDetailUI.compTypesVvb}" width="200" />
</t:row>
<t:row id="g_9" >
<t:label id="g_10" text="#{rr.literals.date}" width="100" />
<t:calendarfield id="g_11" attributemacro="entityDetailMacro(CompetitionDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:row id="g_12" >
<t:label id="g_13" rowalignmenty="top" text="#{rr.literals.address}" width="100" />
<t:textarea id="g_14" attributemacro="entityDetailMacro(CompetitionDetailUI,address)" height="55" width="300" />
</t:row>
<t:row id="g_15" >
<t:label id="g_16" text="#{rr.literals.country}" width="100" />
<t:combobox id="g_17" attributemacro="entityDetailMacro(CompetitionDetailUI,country)" validvaluesbinding="#{d.CompetitionDetailUI.countriesVvb}" width="200" />
</t:row>
<t:rowdistance id="g_18" />
<t:row id="g_19" comment="Limits" rendered="#{d.CompetitionDetailUI.myCompetition}" >
<t:pane id="g_20" rowdistance="5" width="100%" >
<t:row id="g_21" >
<t:label id="g_22" font="size:12;weight:bold" text="#{rr.literals.limits}" />
</t:row>
<t:row id="g_23" >
<t:fixgrid id="g_24" avoidroundtrips="true" objectbinding="#{d.CompetitionDetailUI.gridLimits}" sbvisibleamount="10" >
<t:gridcol id="g_25" columnresizingenabled="false" searchenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_26" />
</t:gridcol>
<t:gridcol id="g_27" align="center" text="#{rr.literals.category}" width="120" >
<t:combofield id="g_28" actionListener=".{onCategoryF4}" editable="true" enabled="#{d.CompetitionDetailUI.enabled}" flush="true" flushtimer="1000" keepfocus="true" maxlength="10" text=".{category}" />
</t:gridcol>
<t:gridcol id="g_29" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.green_area_high}" width="120" >
<t:formattedfield id="g_30" align="center" background="#00FF00" enabled="#{d.CompetitionDetailUI.enabled}" font="weight:bold" format="double" userhint="%" value=".{greenAreaHigh}" />
</t:gridcol>
<t:gridcol id="g_31" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.red_area_low}" width="120" >
<t:formattedfield id="g_32" align="center" background="#FF0000" enabled="#{d.CompetitionDetailUI.enabled}" font="weight:bold" format="double" userhint="%" value=".{redAreaLow}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_33" >
<t:button id="g_34" actionListener="#{d.CompetitionDetailUI.onAddLimit}" enabled="#{d.CompetitionDetailUI.enabled}" text="#{rr.literals.list_add}" />
<t:coldistance id="g_35" />
<t:button id="g_36" actionListener="#{d.CompetitionDetailUI.onRemoveLimit}" enabled="#{d.CompetitionDetailUI.enabled}" text="#{rr.literals.list_remove}" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
