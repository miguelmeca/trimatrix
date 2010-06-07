<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_templatechangeg_sv">
<t:row id="g_1" >
<t:pane id="g_2" rowdistance="5" stylevariant="popup" >
<t:row id="g_3" >
<t:label id="g_4" font="weight:bold" text="#{rr.literals.template}" />
<t:coldistance id="g_5" />
<t:field id="g_6" actionListener="#{d.ResultsImportUI.onTemplateF4}" text="#{d.ResultsImportUI.template}" width="200" />
</t:row>
<t:rowdistance id="g_7" height="10" />
<t:row id="g_8" >
<t:label id="g_9" text="#{rr.literals.start_import}" width="150" />
<t:spinner id="g_10" editable="true" maxvalue="65536" minvalue="0" value="#{d.ResultsImportUI.startRow}" width="50" />
</t:row>
<t:rowline id="g_11" />
<t:row id="g_12" >
<t:label id="g_13" font="size:16;weight:bold" text="#{rr.literals.column_mapping}" />
</t:row>
<t:row id="g_14" >
<t:pane id="g_15" comment="triathlon" padding="5" rowdistance="5" >
<t:row id="g_16" >
<t:label id="g_17" text="#{rr.literals.column} #{rr.literals.ranking}" width="150" />
<t:spinner id="g_18" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowPosition}" width="50" />
<t:coldistance id="g_19" />
<t:label id="g_20" text="#{rr.literals.column} #{rr.literals.time}" width="150" />
<t:spinner id="g_21" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowTime}" width="50" />
</t:row>
<t:row id="g_22" >
<t:label id="g_23" text="#{rr.literals.column} #{rr.literals.athlete} #{rr.literals.person_first_name}" width="150" />
<t:spinner id="g_24" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowAthleteFirstname}" width="50" />
<t:coldistance id="g_25" />
<t:label id="g_26" text="#{rr.literals.column} #{rr.literals.athlete} #{rr.literals.person_last_name}" width="150" />
<t:spinner id="g_27" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowAthleteLastname}" width="50" />
</t:row>
<t:row id="g_28" >
<t:label id="g_29" text="#{rr.literals.column} #{rr.literals.swim_split}" width="150" />
<t:spinner id="g_30" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowSwimSplit}" width="50" />
</t:row>
<t:row id="g_31" >
<t:label id="g_32" text="#{rr.literals.column} #{rr.literals.bike_split}" width="150" />
<t:spinner id="g_33" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowBikeSplit}" width="50" />
</t:row>
<t:row id="g_34" >
<t:label id="g_35" text="#{rr.literals.column} #{rr.literals.run_split}" width="150" />
<t:spinner id="g_36" editable="true" maxvalue="255" minvalue="0" value="#{d.ResultsImportUI.rowRunSplit}" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_37" >
<t:button id="g_38" actionListener="#{d.ResultsImportUI.onSaveTemplate}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_39" />
<t:button id="g_40" actionListener="#{d.ResultsImportUI.onSaveTemplate}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
