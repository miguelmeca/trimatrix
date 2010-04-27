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
<t:row id="g_1" >
<t:pane id="g_2" padding="5" rowdistance="5" stylevariant="popup" width="100%" >
<t:row id="g_3" >
<t:foldablepane id="g_4" font="size:14;weight:bold" rowdistance="5" text="#{rr.literals.summary}" width="100%" >
<t:row id="g_5" rendered="#{d.ScheduleChangePopUp.template==false}" >
<t:label id="g_6" text="#{rr.literals.start}" width="90" />
<t:calendarfield id="g_7" enabled="#{d.ScheduleChangePopUp.creatorsSight}" exacttime="true" flush="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.start}" width="120" />
<t:coldistance id="g_8" />
<t:label id="g_9" text="#{rr.literals.total_duration}" />
<t:coldistance id="g_10" />
<t:field id="g_11" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight}" text="#{d.ScheduleChangePopUp.duration}" width="80" />
<t:coldistance id="g_12" />
<t:checkbox id="g_13" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" selected="#{d.ScheduleChangePopUp.done}" text="#{rr.literals.done}" />
<t:coldistance id="g_14" width="100%" />
<t:colorfield id="g_15" background="#{d.ScheduleChangePopUp.color}" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
<t:row id="g_16" rendered="#{d.ScheduleChangePopUp.template==true}" >
<t:label id="g_17" text="#{rr.literals.template}" width="90" />
<t:field id="g_18" bgpaint="mandatory()" text="#{d.ScheduleChangePopUp.templateName}" width="250" />
<t:coldistance id="g_19" width="100%" />
<t:colorfield id="g_20" background="#{d.ScheduleChangePopUp.color}" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
<t:row id="g_21" >
<t:label id="g_22" text="#{rr.literals.type}" width="90" />
<t:combobox id="g_23" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="120" />
</t:row>
<t:row id="g_24" >
<t:label id="g_25" text="#{rr.literals.description}" width="80" />
<t:coldistance id="g_26" />
<t:textarea id="g_27" enabled="#{d.ScheduleChangePopUp.creatorsSight}" height="50" text="#{d.ScheduleChangePopUp.text}" width="300" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_28" >
<t:pane id="g_29" padding="0" rowdistance="0" width="100%" >
<t:row id="g_30" >
<t:foldablepane id="g_31" comment="run" font="size:14;weight:bold" rowdistance="5" text="#{rr.literals.run}" width="100%" >
<t:row id="g_32" rendered="#{d.ScheduleChangePopUp.personsSight}" >
<t:button id="g_33" actionListener="#{d.ScheduleChangePopUp.onSetDone}" image="/images/icons/accept.png" imageheight="16" text="#{rr.literals.unit_passed}" />
</t:row>
<t:row id="g_34" >
<t:coldistance id="g_35" width="100%" />
<t:link id="g_36" actionListener="#{d.ScheduleChangePopUp.gridRun.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_37" >
<t:fixgrid id="g_38" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridRun}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_39" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_40" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="duration" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" text=".{scheduleRun.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_41" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_42" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleRun.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_43" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_44" width="100%" >
<t:row id="g_45" >
<t:formattedfield id="g_46" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleRun.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_47" />
<t:row id="g_48" >
<t:formattedfield id="g_49" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleRun.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_50" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_51" >
<t:row id="g_52" >
<t:formattedfield id="g_53" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleRun.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_54" />
<t:row id="g_55" >
<t:formattedfield id="g_56" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleRun.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_57" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_58" actionListener=".{onTimeFlush}" align="center" clientname="durationAthlete" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" text=".{scheduleRun.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_59" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_60" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleRun.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_61" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_62" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleRun.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_63" rendered="#{d.ScheduleChangePopUp.creatorsSight}" >
<t:button id="g_64" actionListener="#{d.ScheduleChangePopUp.onAddItem}" image="/images/icons/add.png" imageheight="16" text="#{rr.literals.add}" />
<t:coldistance id="g_65" />
<t:button id="g_66" actionListener="#{d.ScheduleChangePopUp.onRemoveItem}" image="/images/icons/remove.png" imageheight="16" text="#{rr.literals.remove}" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_67" >
<t:button id="g_68" actionListener="#{d.ScheduleChangePopUp.onSave}" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_69" />
<t:button id="g_70" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
<t:coldistance id="g_71" width="100%" />
<t:button id="g_72" actionListener="#{d.ScheduleChangePopUp.onDelete}" image="/images/icons/delete.png" imageheight="16" rendered="#{d.ScheduleChangePopUp.creatorsSight}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
