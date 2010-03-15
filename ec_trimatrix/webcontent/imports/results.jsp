<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="imports_resultsg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" height="20" text="#{rr.literals.competition}" width="120" />
<t:link id="g_4" actionListener="#{d.ResultDetailUI.onCompetitionClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,competition)" enabled="true" focusable="true" foreground="#000000" height="20" text="#{d.ResultsImportUI.compDesc}" width="200" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.ResultsImportUI.onCompetitionSearch}" image="/images/icons/magnifier.png" imageheight="16" text="#{rr.literals.search}" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.category}" width="120" />
<t:combofield id="g_9" actionListener="#{d.ResultDetailUI.onCategoryF4}" attributemacro="entityDetailMacro(ResultDetailUI,category_tria)" flush="true" maxlength="10" width="100" />
</t:row>
<t:row id="g_10" >
<t:icon id="g_11" image="#{d.ResultsImportUI.mappingImage}" />
<t:button id="g_12" image="/images/icons/mapping.png" imageheight="16" text="#{rr.literals.mapping}" />
</t:row>
<t:row id="g_13" >
<t:fileuploadbutton id="g_14" image="/images/icons/upload.png" imageheight="16" text="#{rr.literals.upload_file}" />
<t:coldistance id="g_15" />
<t:label id="g_16" text="Dateiname" />
</t:row>
<t:rowdistance id="g_17" />
<t:row id="g_18" >
<t:shadowedpane id="g_19" padding="5" width="100%" >
<t:row id="g_20" >
<t:checkbox id="g_21" />
<t:coldistance id="g_22" />
<t:label id="g_23" text="#{rr.literals.best_swimmer} / #{rr.literals.best_swim_split}" width="250" />
<t:coldistance id="g_24" />
<t:field id="g_25" width="150" />
<t:coldistance id="g_26" />
<t:field id="g_27" actionListener="#{d.ResultDetailUI.onTimeFlush}" align="center" attributemacro="entityDetailMacro(ResultDetailUI,run_split)" background="#{d.ResultDetailUI.colorRun}" clientname="run" flush="true" maxlength="8" userhint="hh:mm:ss" width="60" />
</t:row>
<t:row id="g_28" >
<t:checkbox id="g_29" />
<t:coldistance id="g_30" />
<t:label id="g_31" text="#{rr.literals.best_biker} / #{rr.literals.best_bike_split}" width="250" />
<t:coldistance id="g_32" />
<t:field id="g_33" width="150" />
<t:coldistance id="g_34" />
<t:field id="g_35" actionListener="#{d.ResultDetailUI.onTimeFlush}" align="center" attributemacro="entityDetailMacro(ResultDetailUI,run_split)" background="#{d.ResultDetailUI.colorRun}" clientname="run" flush="true" maxlength="8" userhint="hh:mm:ss" width="60" />
</t:row>
<t:row id="g_36" >
<t:checkbox id="g_37" />
<t:coldistance id="g_38" />
<t:label id="g_39" text="#{rr.literals.best_runner} / #{rr.literals.best_run_split}" width="250" />
<t:coldistance id="g_40" />
<t:field id="g_41" width="150" />
<t:coldistance id="g_42" />
<t:field id="g_43" actionListener="#{d.ResultDetailUI.onTimeFlush}" align="center" attributemacro="entityDetailMacro(ResultDetailUI,run_split)" background="#{d.ResultDetailUI.colorRun}" clientname="run" flush="true" maxlength="8" userhint="hh:mm:ss" width="60" />
</t:row>
<t:rowdistance id="g_44" height="15" />
<t:row id="g_45" >
<t:fixgrid id="g_46" horizontalscrollmode="autowithresize" multiselect="true" multiselectmode="1" objectbinding="#{tobedfined}" sbvisibleamount="25" selectorcolumn="1" selectorcolumnimagefalse="/images/icons/checkbox_false.pn" selectorcolumnimagetrue="/images/icons/checkbox_true.pn" showemptyrows="false" width="100%" >
<t:gridcol id="g_47" text="Column" width="100" />
</t:fixgrid>
</t:row>
<t:rowdistance id="g_48" />
<t:row id="g_49" >
<t:button id="g_50" image="/images/icons/import.png" imageheight="16" text="#{rr.literals.import}" />
</t:row>
</t:shadowedpane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
