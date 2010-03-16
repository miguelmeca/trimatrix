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
<t:combofield id="g_9" actionListener="#{d.ResultsImportUI.onCategoryF4}" flush="true" maxlength="10" text="#{d.ResultsImportUI.category}" width="100" />
</t:row>
<t:row id="g_10" >
<t:icon id="g_11" enabled="false" image="#{d.ResultsImportUI.mappingImage}" />
<t:button id="g_12" enabled="false" image="/images/icons/mapping.png" imageheight="16" text="#{rr.literals.mapping}" />
</t:row>
<t:row id="g_13" >
<t:fileuploadbutton id="g_14" actionListener="#{d.ResultsImportUI.onUploadFile}" fileextensions="xls" image="/images/icons/upload.png" imageheight="16" text="#{rr.literals.upload_file}" />
<t:coldistance id="g_15" />
<t:label id="g_16" text="#{d.ResultsImportUI.filename}" />
</t:row>
<t:rowdistance id="g_17" />
<t:row id="g_18" >
<t:shadowedpane id="g_19" padding="5" width="100%" >
<t:row id="g_20" >
<t:checkbox id="g_21" />
<t:coldistance id="g_22" />
<t:label id="g_23" text="#{rr.literals.best_swimmer} / #{rr.literals.best_swim_split}" width="250" />
<t:coldistance id="g_24" />
<t:field id="g_25" text="#{d.ResultsImportUI.bestSwimmer}" width="150" />
<t:coldistance id="g_26" />
<t:field id="g_27" actionListener="#{d.ResultsImportUI.onTimeFlush}" align="center" clientname="swim" flush="true" maxlength="8" text="#{d.ResultsImportUI.bestSwimSplit}" userhint="hh:mm:ss" width="60" />
</t:row>
<t:row id="g_28" >
<t:checkbox id="g_29" />
<t:coldistance id="g_30" />
<t:label id="g_31" text="#{rr.literals.best_biker} / #{rr.literals.best_bike_split}" width="250" />
<t:coldistance id="g_32" />
<t:field id="g_33" text="#{d.ResultsImportUI.bestBiker}" width="150" />
<t:coldistance id="g_34" />
<t:field id="g_35" actionListener="#{d.ResultsImportUI.onTimeFlush}" align="center" clientname="bike" flush="true" maxlength="8" text="#{d.ResultsImportUI.bestBikeSplit}" userhint="hh:mm:ss" width="60" />
</t:row>
<t:row id="g_36" >
<t:checkbox id="g_37" />
<t:coldistance id="g_38" />
<t:label id="g_39" text="#{rr.literals.best_runner} / #{rr.literals.best_run_split}" width="250" />
<t:coldistance id="g_40" />
<t:field id="g_41" text="#{d.ResultsImportUI.bestRunner}" width="150" />
<t:coldistance id="g_42" />
<t:field id="g_43" actionListener="#{d.ResultsImportUI.onTimeFlush}" align="center" clientname="run" flush="true" maxlength="8" text="#{d.ResultsImportUI.bestRunSplit}" userhint="hh:mm:ss" width="60" />
</t:row>
<t:rowdistance id="g_44" height="15" />
<t:row id="g_45" >
<t:fixgrid id="g_46" horizontalscrollmode="autowithresize" multiselect="true" multiselectmode="1" objectbinding="#{d.ResultsImportUI.gridImport}" sbvisibleamount="25" selectorcolumn="1" selectorcolumnimagefalse="/images/icons/checkbox_false.png" selectorcolumnimagetrue="/images/icons/checkbox_true.png" showemptyrows="false" >
<t:gridcol id="g_47" text="#{rr.literals.athlete} (#{rr.literals.file})" width="150" >
<t:field id="g_48" enabled="false" text=".{athlete}" />
</t:gridcol>
<t:gridcol id="g_49" text="#{rr.literals.my_athlete}" >
<t:combofield id="g_50" actionListener=".{onScoutedAthlete}" />
</t:gridcol>
<t:gridcol id="g_51" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_52" align="center" format="int" value=".{position}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_53" />
<t:row id="g_54" >
<t:button id="g_55" actionListener="#{d.ResultsImportUI.onImport}" image="/images/icons/import.png" imageheight="16" text="#{rr.literals.import}" />
</t:row>
</t:shadowedpane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
