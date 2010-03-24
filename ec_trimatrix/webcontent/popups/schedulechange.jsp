<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_schedulechangeg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:foldablepane id="g_3" font="size:14;weight:bold" rowdistance="5" text="#{rr.literals.summary}" width="100%" >
<t:row id="g_4" >
<t:label id="g_5" text="Start" width="90" />
<t:calendarfield id="g_6" enabled="#{d.ScheduleChangePopUp.creatorsSight}" exacttime="true" flush="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.start}" width="120" />
<t:coldistance id="g_7" />
<t:label id="g_8" text="#{rr.literals.total_duration}" />
<t:coldistance id="g_9" />
<t:field id="g_10" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight}" text="#{d.ScheduleChangePopUp.duration}" width="80" />
<t:coldistance id="g_11" />
<t:checkbox id="g_12" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" selected="#{d.ScheduleChangePopUp.done}" text="#{rr.literals.done}" />
<t:coldistance id="g_13" width="100%" />
<t:colorfield id="g_14" background="#{d.ScheduleChangePopUp.color}" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
<t:row id="g_15" />
<t:row id="g_16" >
<t:label id="g_17" text="Type" width="90" />
<t:combobox id="g_18" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="120" />
</t:row>
<t:row id="g_19" >
<t:label id="g_20" text="Beschreibung" width="80" />
<t:coldistance id="g_21" />
<t:textarea id="g_22" enabled="#{d.ScheduleChangePopUp.creatorsSight}" height="50" text="#{d.ScheduleChangePopUp.text}" width="100%" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_23" >
<t:pane id="g_24" width="100%" >
<t:row id="g_25" >
<t:pane id="g_26" comment="run" rowdistance="5" width="100%" />
</t:row>
<t:row id="g_27" >
<t:foldablepane id="g_28" font="size:14;weight:bold" rowdistance="5" text="#{rr.literals.run}" width="100%" >
<t:row id="g_29" >
<t:coldistance id="g_30" width="100%" />
<t:link id="g_31" actionListener="#{d.ScheduleChangePopUp.gridRun.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_32" >
<t:fixgrid id="g_33" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridRun}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_34" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_35" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="duration" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" text=".{scheduleRun.duration}" width="100" />
</t:gridcol>
<t:gridcol id="g_36" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_37" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{d.ScheduleUI.vvbZones}" value=".{scheduleRun.zone}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_38" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_39" width="100%" >
<t:row id="g_40" >
<t:formattedfield id="g_41" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleRun.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_42" />
<t:row id="g_43" >
<t:formattedfield id="g_44" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleRun.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_45" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_46" >
<t:row id="g_47" >
<t:formattedfield id="g_48" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleRun.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_49" />
<t:row id="g_50" >
<t:formattedfield id="g_51" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleRun.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_52" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_53" actionListener=".{onTimeFlush}" align="center" clientname="durationAthlete" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" text=".{scheduleRun.durationAthlete}" width="100" />
</t:gridcol>
<t:gridcol id="g_54" text="#{rr.literals.avg_hr}" width="100" >
<t:formattedfield id="g_55" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleRun.hrAvgAthlete}" />
</t:gridcol>
<t:gridcol id="g_56" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_57" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleRun.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_58" rendered="#{d.ScheduleChangePopUp.creatorsSight}" >
<t:button id="g_59" actionListener="#{d.ScheduleChangePopUp.onAddItem}" image="/images/icons/add.png" imageheight="16" text="#{rr.literals.add}" />
<t:coldistance id="g_60" />
<t:button id="g_61" actionListener="#{d.ScheduleChangePopUp.onRemoveItem}" image="/images/icons/remove.png" imageheight="16" text="#{rr.literals.remove}" />
</t:row>
<t:row id="g_62" rendered="#{d.ScheduleChangePopUp.personsSight}" >
<t:button id="g_63" actionListener="#{d.ScheduleChangePopUp.onSetDone}" image="/images/icons/accept.png" imageheight="16" text="#{rr.literals.unit_passed}" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_64" >
<t:button id="g_65" actionListener="#{d.ScheduleChangePopUp.onSave}" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_66" />
<t:button id="g_67" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
