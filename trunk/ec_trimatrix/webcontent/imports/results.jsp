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
<t:tabbedpane id="g_3" height="100%" width="100%" >
<t:tabbedpanetab id="g_4" rowdistance="5" text="#{rr.literals.competition}" >
<t:row id="g_5" >
<t:label id="g_6" height="20" text="#{rr.literals.competition}" width="120" />
<t:link id="g_7" actionListener="#{d.ResultsImportUI.onCompetitionClicked}" align="left" focusable="true" foreground="#000000" height="20" text="#{d.ResultsImportUI.compDesc}" width="200" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.ResultsImportUI.onCompetitionSearch}" image="/images/icons/magnifier.png" imageheight="16" text="#{rr.literals.search}" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.category}" width="120" />
<t:combofield id="g_12" actionListener="#{d.ResultsImportUI.onCategoryF4}" flush="true" maxlength="10" text="#{d.ResultsImportUI.category}" width="100" />
</t:row>
<t:row id="g_13" >
<t:foldablepane id="g_14" opened="#{d.ResultsImportUI.statusMapping}" rowdistance="5" text="#{rr.literals.mapping}" width="100%" >
<t:row id="g_15" >
<t:label id="g_16" font="weight:bold" text="#{rr.literals.template}" />
<t:coldistance id="g_17" />
<t:combofield id="g_18" actionListener="#{d.ResultsImportUI.onTemplateF4}" editable="true" text="#{d.ResultsImportUI.template}" width="200" />
<t:coldistance id="g_19" />
<t:button id="g_20" actionListener="#{d.ResultsImportUI.onSaveTemplate}" image="/images/icons/save.png" imageheight="16" text="#{rr.literals.save_template}" />
</t:row>
<t:rowdistance id="g_21" height="10" />
<t:row id="g_22" >
<t:label id="g_23" text="#{rr.literals.start_import}" width="150" />
<t:spinner id="g_24" editable="true" maxvalue="65536" minvalue="0" value="#{d.ResultsImportUI.startRow}" width="50" />
</t:row>
<t:rowline id="g_25" />
<t:row id="g_26" >
<t:label id="g_27" font="size:16;weight:bold" text="#{rr.literals.column_mapping}" />
</t:row>
<t:row id="g_28" >
<t:label id="g_29" text="#{rr.literals.column} #{rr.literals.ranking}" width="150" />
<t:spinner id="g_30" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowPosition}" width="50" />
<t:coldistance id="g_31" />
<t:label id="g_32" text="#{rr.literals.column} #{rr.literals.time}" width="150" />
<t:spinner id="g_33" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowTime}" width="50" />
</t:row>
<t:row id="g_34" >
<t:label id="g_35" text="#{rr.literals.column} #{rr.literals.athlete} #{rr.literals.person_first_name}" width="150" />
<t:spinner id="g_36" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowAthleteFirstname}" width="50" />
<t:coldistance id="g_37" />
<t:label id="g_38" text="#{rr.literals.column} #{rr.literals.athlete} #{rr.literals.person_last_name}" width="150" />
<t:spinner id="g_39" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowAthleteLastname}" width="50" />
</t:row>
<t:row id="g_40" >
<t:label id="g_41" text="#{rr.literals.column} #{rr.literals.swim_split}" width="150" />
<t:spinner id="g_42" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowSwimSplit}" width="50" />
</t:row>
<t:row id="g_43" >
<t:label id="g_44" text="#{rr.literals.column} #{rr.literals.bike_split}" width="150" />
<t:spinner id="g_45" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowBikeSplit}" width="50" />
</t:row>
<t:row id="g_46" >
<t:label id="g_47" text="#{rr.literals.column} #{rr.literals.run_split}" width="150" />
<t:spinner id="g_48" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowRunSplit}" width="50" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_49" >
<t:fileuploadbutton id="g_50" actionListener="#{d.ResultsImportUI.onUploadFile}" fileextensions="xls" image="/images/icons/upload.png" imageheight="16" text="#{rr.literals.upload_file}" />
<t:coldistance id="g_51" />
<t:label id="g_52" font="weight:bold" text="#{d.ResultsImportUI.filename}" />
</t:row>
<t:rowdistance id="g_53" />
<t:row id="g_54" >
<t:foldablepane id="g_55" opened="#{d.ResultsImportUI.statusImportData}" text="#{rr.literals.import_data}" width="100%" >
<t:row id="g_56" >
<t:checkbox id="g_57" selected="#{d.ResultsImportUI.importBestSwim}" />
<t:coldistance id="g_58" />
<t:label id="g_59" text="#{rr.literals.best_swimmer} / #{rr.literals.best_swim_split}" width="250" />
<t:coldistance id="g_60" />
<t:field id="g_61" text="#{d.ResultsImportUI.bestSwimmer}" width="150" />
<t:coldistance id="g_62" />
<t:field id="g_63" actionListener="#{d.ResultsImportUI.onTimeFlush}" align="center" clientname="swim" flush="true" maxlength="8" text="#{d.ResultsImportUI.bestSwimSplit}" userhint="hh:mm:ss" width="60" />
</t:row>
<t:row id="g_64" >
<t:checkbox id="g_65" selected="#{d.ResultsImportUI.importBestBike}" />
<t:coldistance id="g_66" />
<t:label id="g_67" text="#{rr.literals.best_biker} / #{rr.literals.best_bike_split}" width="250" />
<t:coldistance id="g_68" />
<t:field id="g_69" text="#{d.ResultsImportUI.bestBiker}" width="150" />
<t:coldistance id="g_70" />
<t:field id="g_71" actionListener="#{d.ResultsImportUI.onTimeFlush}" align="center" clientname="bike" flush="true" maxlength="8" text="#{d.ResultsImportUI.bestBikeSplit}" userhint="hh:mm:ss" width="60" />
</t:row>
<t:row id="g_72" >
<t:checkbox id="g_73" selected="#{d.ResultsImportUI.importBestRun}" />
<t:coldistance id="g_74" />
<t:label id="g_75" text="#{rr.literals.best_runner} / #{rr.literals.best_run_split}" width="250" />
<t:coldistance id="g_76" />
<t:field id="g_77" text="#{d.ResultsImportUI.bestRunner}" width="150" />
<t:coldistance id="g_78" />
<t:field id="g_79" actionListener="#{d.ResultsImportUI.onTimeFlush}" align="center" clientname="run" flush="true" maxlength="8" text="#{d.ResultsImportUI.bestRunSplit}" userhint="hh:mm:ss" width="60" />
</t:row>
<t:rowdistance id="g_80" height="15" />
<t:row id="g_81" >
<t:fixgrid id="g_82" horizontalscrollmode="autowithresize" multiselect="true" multiselectmode="1" objectbinding="#{d.ResultsImportUI.gridImport}" sbvisibleamount="25" selectorcolumn="1" selectorcolumnimagefalse="/images/icons/checkbox_false.png" selectorcolumnimagetrue="/images/icons/checkbox_true.png" showemptyrows="false" >
<t:gridcol id="g_83" text="#{rr.literals.athlete} (#{rr.literals.file})" width="150" >
<t:field id="g_84" enabled="false" text=".{athlete}" />
</t:gridcol>
<t:gridcol id="g_85" text="#{rr.literals.my_athlete}" width="150" >
<t:combofield id="g_86" actionListener=".{onScoutedAthleteF4}" text=".{scoutedAthlete}" />
</t:gridcol>
<t:gridcol id="g_87" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_88" align="center" format="int" value=".{position}" />
</t:gridcol>
<t:gridcol id="g_89" comment="overall" text="#{rr.literals.time}" width="80" >
<t:field id="g_90" actionListener=".{onTimeFlush}" align="center" clientname="overall" flush="true" maxlength="8" text=".{time}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_91" text="#{rr.literals.swim_split}" width="80" >
<t:field id="g_92" actionListener=".{onTimeFlush}" align="center" clientname="swim" flush="true" maxlength="8" text=".{swimSplit}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_93" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_94" align="center" format="int" value=".{swimPosition}" />
</t:gridcol>
<t:gridcol id="g_95" text="#{rr.literals.bike_split}" width="80" >
<t:field id="g_96" actionListener=".{onTimeFlush}" align="center" clientname="bike" flush="true" maxlength="8" text=".{bikeSplit}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_97" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_98" align="center" format="int" value=".{bikePosition}" />
</t:gridcol>
<t:gridcol id="g_99" text="#{rr.literals.run_split}" width="80" >
<t:field id="g_100" actionListener=".{onTimeFlush}" align="center" clientname="run" flush="true" maxlength="8" text=".{runSplit}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_101" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_102" align="center" format="int" value=".{runPosition}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_103" />
<t:row id="g_104" >
<t:button id="g_105" actionListener="#{d.ResultsImportUI.onImport}" image="/images/icons/import.png" imageheight="16" text="#{rr.literals.import}" />
</t:row>
</t:foldablepane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_106" rowdistance="5" text="#{rr.literals.athlete}" >
<t:row id="g_107" >
<t:label id="g_108" height="20" text="#{rr.literals.athlete}" width="120" />
<t:link id="g_109" actionListener="#{d.ResultsImportUI.onCompetitionClicked}" align="left" focusable="true" foreground="#000000" height="20" text="#{d.ResultsImportUI.athleteDescription}" width="200" />
<t:coldistance id="g_110" />
<t:button id="g_111" actionListener="#{d.ResultsImportUI.onAthleteSearch}" image="/images/icons/magnifier.png" imageheight="16" text="#{rr.literals.search}" />
</t:row>
<t:row id="g_112" >
<t:label id="g_113" text="#{rr.literals.category}" width="120" />
<t:combofield id="g_114" actionListener="#{d.ResultsImportUI.onCategoryF4}" flush="true" maxlength="10" text="#{d.ResultsImportUI.category}" width="100" />
</t:row>
<t:row id="g_115" >
<t:button id="g_116" actionListener="#{d.ResultsImportUI.onLoadAthlete}" text="#{rr.literals.load_data}" />
</t:row>
<t:row id="g_117" >
<t:foldablepane id="g_118" opened="#{d.ResultsImportUI.statusImportAthleteData}" text="#{rr.literals.import_data}" width="100%" >
<t:row id="g_119" >
<t:fixgrid id="g_120" horizontalscrollmode="autowithresize" multiselect="true" multiselectmode="1" objectbinding="#{d.ResultsImportUI.gridImportAthlete}" sbvisibleamount="25" selectorcolumn="1" selectorcolumnimagefalse="/images/icons/checkbox_false.png" selectorcolumnimagetrue="/images/icons/checkbox_true.png" showemptyrows="false" >
<t:gridcol id="g_121" text="#{rr.literals.competition}" width="150" >
<t:field id="g_122" enabled="false" text=".{competitionDesc}" />
</t:gridcol>
<t:gridcol id="g_123" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_124" align="center" format="int" value=".{position}" />
</t:gridcol>
<t:gridcol id="g_125" comment="overall" text="#{rr.literals.time}" width="80" >
<t:field id="g_126" actionListener=".{onTimeFlush}" align="center" clientname="overall" flush="true" maxlength="8" text=".{time}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_127" text="#{rr.literals.swim_split}" width="80" >
<t:field id="g_128" actionListener=".{onTimeFlush}" align="center" clientname="swim" flush="true" maxlength="8" text=".{swimSplit}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_129" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_130" align="center" format="int" value=".{swimPosition}" />
</t:gridcol>
<t:gridcol id="g_131" text="#{rr.literals.bike_split}" width="80" >
<t:field id="g_132" actionListener=".{onTimeFlush}" align="center" clientname="bike" flush="true" maxlength="8" text=".{bikeSplit}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_133" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_134" align="center" format="int" value=".{bikePosition}" />
</t:gridcol>
<t:gridcol id="g_135" text="#{rr.literals.run_split}" width="80" >
<t:field id="g_136" actionListener=".{onTimeFlush}" align="center" clientname="run" flush="true" maxlength="8" text=".{runSplit}" userhint="hh:mm:ss" width="60" />
</t:gridcol>
<t:gridcol id="g_137" text="#{rr.literals.ranking}" width="50" >
<t:formattedfield id="g_138" align="center" format="int" value=".{runPosition}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_139" />
<t:row id="g_140" >
<t:button id="g_141" actionListener="#{d.ResultsImportUI.onImportAthlete}" image="/images/icons/import.png" imageheight="16" text="#{rr.literals.import}" />
</t:row>
</t:foldablepane>
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
