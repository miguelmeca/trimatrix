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
<t:combobox id="g_8" attributemacro="entityDetailMacro(CompetitionDetailUI,type)" validvaluesbinding="#{d.CompetitionDetailUI.compTypesVvb}" width="200" />
</t:row>
<t:row id="g_9" >
<t:label id="g_10" text="#{rr.literals.date}" width="100" />
<t:calendarfield id="g_11" attributemacro="entityDetailMacro(CompetitionDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:row id="g_12" >
<t:label id="g_13" rowalignmenty="top" text="#{rr.literals.address}" width="100" />
<t:textarea id="g_14" attributemacro="entityDetailMacro(CompetitionDetailUI,address)" height="55" maxlength="100" width="300" />
</t:row>
<t:row id="g_15" >
<t:label id="g_16" text="#{rr.literals.country}" width="100" />
<t:combobox id="g_17" attributemacro="entityDetailMacro(CompetitionDetailUI,country)" validvaluesbinding="#{d.CompetitionDetailUI.countriesVvb}" width="200" />
</t:row>
<t:row id="g_18" comment="Factors" rendered="#{d.CompetitionDetailUI.myCompetition}" >
<t:pane id="g_19" >
<t:row id="g_20" >
<t:label id="g_21" text="Faktoren" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
