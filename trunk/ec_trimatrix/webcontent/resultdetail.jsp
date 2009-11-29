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
<t:rowdynamiccontent id="g_2" contentbinding="#{d.ResultDetailUI.labelRow}" />
<t:row id="g_3" comment="general" >
<t:pane id="g_4" rowdistance="5" >
<t:row id="g_5" comment="Competition" >
<t:label id="g_6" height="20" text="#{rr.literals.competition}" width="120" />
<t:link id="g_7" actionListener="#{d.ResultDetailUI.onCompetitionClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,competition)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.ResultDetailUI.onCompetitionSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_10" comment="Scouter" rendered="#{d.ResultDetailUI.adminView}" >
<t:label id="g_11" height="20" text="#{rr.literals.role_scouter}" width="120" />
<t:link id="g_12" actionListener="#{d.ResultDetailUI.onScoutClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,scout)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_13" />
<t:button id="g_14" actionListener="#{d.ResultDetailUI.onScoutSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_15" comment="Athlete" >
<t:label id="g_16" height="20" text="#{rr.literals.athlete}" width="120" />
<t:link id="g_17" actionListener="#{d.ResultDetailUI.onAthleteClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,athlete)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_18" />
<t:button id="g_19" actionListener="#{d.ResultDetailUI.onAthleteSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_20" >
<t:label id="g_21" text="#{rr.literals.ranking}" width="120" />
<t:field id="g_22" attributemacro="entityDetailMacro(ResultDetailUI,final_position)" maxlength="5" regex="(DSQ|DNF|\d.*)" regexmode="1" userhint="DNF, DSQ, 1 ... 999" width="100" />
</t:row>
<t:row id="g_23" >
<t:label id="g_24" text="#{rr.literals.time}" width="120" />
<t:field id="g_25" attributemacro="entityDetailMacro(ResultDetailUI,time)" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" userhint="hh:mm:ss" width="100" />
</t:row>
<t:row id="g_26" >
<t:label id="g_27" rowalignmenty="top" text="#{rr.literals.comment}" width="120" />
<t:textarea id="g_28" attributemacro="entityDetailMacro(ResultDetailUI,comment)" height="55" width="350" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_29" comment="tria" rendered="#{d.ResultDetailUI.tria}" >
<t:pane id="g_30" rowdistance="5" width="100%" >
<t:row id="g_31" >
<t:label id="g_32" text="#{rr.literals.category}" width="120" />
<t:combofield id="g_33" actionListener="#{d.ResultDetailUI.onCategoryF4}" attributemacro="entityDetailMacro(ResultDetailUI,category_tria)" maxlength="10" width="100" />
</t:row>
<t:row id="g_34" >
<t:label id="g_35" text="#{rr.literals.swimsuit}" width="120" />
<t:checkbox id="g_36" enabled="#{d.ResultDetailUI.enabled}" selected="#{d.ResultDetailUI.values.swimsuit}" />
</t:row>
<t:row id="g_37" >
<t:label id="g_38" text="#{rr.literals.swim_split}" width="120" />
<t:field id="g_39" attributemacro="entityDetailMacro(ResultDetailUI,swim_split)" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" userhint="hh:mm:ss" width="100" />
<t:coldistance id="g_40" width="45" />
<t:label id="g_41" text="#{rr.literals.run_split}" width="100" />
<t:field id="g_42" attributemacro="entityDetailMacro(ResultDetailUI,run_split)" maxlength="8" regex="\d\d:[0-5]\d:[0-5]\d" regexmode="1" width="100" />
</t:row>
<t:row id="g_43" >
<t:label id="g_44" text="#{rr.literals.ranking}" width="120" />
<t:field id="g_45" attributemacro="entityDetailMacro(ResultDetailUI,swim_pos)" maxlength="5" regex="(DSQ|DNF|\d.*)" regexmode="1" userhint="DNF, DSQ, 1 ... 999" width="100" />
<t:coldistance id="g_46" width="45" />
<t:label id="g_47" text="#{rr.literals.ranking}" width="100" />
<t:field id="g_48" attributemacro="entityDetailMacro(ResultDetailUI,run_pos)" maxlength="5" regex="(DSQ|DNF|\d.*)" regexmode="1" userhint="DNF, DSQ, 1 ... 999" width="100" />
</t:row>
<t:row id="g_49" >
<t:label id="g_50" text="#{rr.literals.deficit}" width="120" />
<t:field id="g_51" actionListener="#{d.ResultDetailUI.onSplitsChange}" attributemacro="entityDetailMacro(ResultDetailUI,swim_def)" clientname="swim_def" flush="true" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="100" />
<t:coldistance id="g_52" width="45" />
<t:label id="g_53" text="#{rr.literals.deficit}" width="100" />
<t:field id="g_54" actionListener="#{d.ResultDetailUI.onSplitsChange}" attributemacro="entityDetailMacro(ResultDetailUI,run_def)" clientname="run_def" flush="true" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="100" />
</t:row>
<t:row id="g_55" >
<t:label id="g_56" text="#{rr.literals.best_split}" width="120" />
<t:field id="g_57" actionListener="#{d.ResultDetailUI.onSplitsChange}" attributemacro="entityDetailMacro(ResultDetailUI,best_swim_split)" clientname="best_swim_split" flush="true" width="100" />
<t:coldistance id="g_58" width="45" />
<t:label id="g_59" text="#{rr.literals.best_split}" width="100" />
<t:field id="g_60" actionListener="#{d.ResultDetailUI.onSplitsChange}" attributemacro="entityDetailMacro(ResultDetailUI,best_run_split)" clientname="best_run_split" flush="true" width="100" />
</t:row>
<t:row id="g_61" >
<t:label id="g_62" text="#{rr.literals.deficit_percent}" width="120" />
<t:formattedfield id="g_63" align="center" background="#{d.ResultDetailUI.colorSwim}" enabled="false" font="weight:bold" format="double" userhint="%" value="#{d.ResultDetailUI.percentDeficitSwim}" width="100" />
<t:coldistance id="g_64" width="45" />
<t:label id="g_65" text="#{rr.literals.deficit_percent}" width="100" />
<t:formattedfield id="g_66" align="center" background="#{d.ResultDetailUI.colorRun}" enabled="false" font="weight:bold" format="double" userhint="%" value="#{d.ResultDetailUI.percentDeficitRun}" width="100" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
