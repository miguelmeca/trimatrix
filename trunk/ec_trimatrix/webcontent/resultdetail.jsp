<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="resultdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" comment="Competition" >
<t:label id="g_3" height="20" text="#{rr.literals.competition}" width="100" />
<t:link id="g_4" actionListener="#{d.ResultDetailUI.onCompetitionClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,competition)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.ResultDetailUI.onCompetitionSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_7" comment="Scouter" rendered="#{d.ResultDetailUI.adminView}" >
<t:label id="g_8" height="20" text="#{rr.literals.role_scouter}" width="100" />
<t:link id="g_9" actionListener="#{d.ResultDetailUI.onScoutClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,scout)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_10" />
<t:button id="g_11" actionListener="#{d.ResultDetailUI.onScoutSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_12" comment="Athlete" >
<t:label id="g_13" height="20" text="#{rr.literals.athlete}" width="100" />
<t:link id="g_14" actionListener="#{d.ResultDetailUI.onAthleteClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,athlete)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_15" />
<t:button id="g_16" actionListener="#{d.ResultDetailUI.onAthleteSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_17" >
<t:label id="g_18" text="#{rr.literals.ranking}" width="100" />
<t:field id="g_19" attributemacro="entityDetailMacro(ResultDetailUI,final_position)" width="100" />
</t:row>
<t:row id="g_20" >
<t:label id="g_21" text="#{rr.literals.time}" width="100" />
<t:field id="g_22" attributemacro="entityDetailMacro(ResultDetailUI,time)" maxlength="8" userhint="hh:mm:ss" width="100" />
</t:row>
<t:row id="g_23" >
<t:label id="g_24" rowalignmenty="top" text="#{rr.literals.comment}" width="100" />
<t:textarea id="g_25" attributemacro="entityDetailMacro(ResultDetailUI,comment)" height="55" width="350" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
