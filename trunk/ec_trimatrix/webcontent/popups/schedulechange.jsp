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
<t:combobox id="g_23" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.enableType}" flush="true" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="120" />
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
<t:foldablepane id="g_31" comment="details" font="size:14;weight:bold" rendered="#{d.ScheduleChangePopUp.detailRelevant}" rowdistance="5" text="#{rr.literals.details}" width="100%" >
<t:row id="g_32" rendered="#{d.ScheduleChangePopUp.personsSight}" >
<t:button id="g_33" actionListener="#{d.ScheduleChangePopUp.onSetDone}" image="/images/icons/accept.png" imageheight="16" text="#{rr.literals.unit_passed}" />
</t:row>
<t:row id="g_34" comment="run" rendered="#{d.ScheduleChangePopUp.typeOrd==1}" >
<t:fixgrid id="g_35" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_36" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_37" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_38" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_39" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_40" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_41" width="100%" >
<t:row id="g_42" >
<t:formattedfield id="g_43" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_44" />
<t:row id="g_45" >
<t:formattedfield id="g_46" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_47" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_48" >
<t:row id="g_49" >
<t:formattedfield id="g_50" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_51" />
<t:row id="g_52" >
<t:formattedfield id="g_53" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_54" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_55" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_56" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_57" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_58" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_59" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_60" comment="bike" rendered="#{d.ScheduleChangePopUp.typeOrd==2}" >
<t:fixgrid id="g_61" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_62" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_63" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_64" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_65" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_66" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_67" width="100%" >
<t:row id="g_68" >
<t:formattedfield id="g_69" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_70" />
<t:row id="g_71" >
<t:formattedfield id="g_72" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_73" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_74" >
<t:row id="g_75" >
<t:formattedfield id="g_76" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_77" />
<t:row id="g_78" >
<t:formattedfield id="g_79" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_80" text="#{rr.literals.power}" width="80" >
<t:formattedfield id="g_81" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.power}" width="10080" />
</t:gridcol>
<t:gridcol id="g_82" text="#{rr.literals.cadence}" width="100" >
<t:formattedfield id="g_83" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.cadence}" width="100" />
</t:gridcol>
<t:gridcol id="g_84" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_85" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_86" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_87" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_88" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_89" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_90" comment="swim" rendered="#{d.ScheduleChangePopUp.typeOrd==3}" >
<t:fixgrid id="g_91" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_92" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_93" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_94" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_95" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_96" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_97" width="100%" >
<t:row id="g_98" >
<t:formattedfield id="g_99" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_100" />
<t:row id="g_101" >
<t:formattedfield id="g_102" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_103" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_104" >
<t:row id="g_105" >
<t:formattedfield id="g_106" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_107" />
<t:row id="g_108" >
<t:formattedfield id="g_109" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_110" text="#{rr.literals.power}" width="80" >
<t:formattedfield id="g_111" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.power}" width="10080" />
</t:gridcol>
<t:gridcol id="g_112" text="#{rr.literals.cadence}" width="100" >
<t:formattedfield id="g_113" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.cadence}" width="100" />
</t:gridcol>
<t:gridcol id="g_114" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_115" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_116" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_117" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_118" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_119" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_120" rendered="#{d.ScheduleChangePopUp.creatorsSight}" >
<t:button id="g_121" actionListener="#{d.ScheduleChangePopUp.onAddItem}" image="/images/icons/add.png" imageheight="16" text="#{rr.literals.add}" />
<t:coldistance id="g_122" />
<t:button id="g_123" actionListener="#{d.ScheduleChangePopUp.onRemoveItem}" image="/images/icons/remove.png" imageheight="16" text="#{rr.literals.remove}" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_124" >
<t:button id="g_125" actionListener="#{d.ScheduleChangePopUp.onSave}" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_126" />
<t:button id="g_127" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
<t:coldistance id="g_128" width="100%" />
<t:button id="g_129" actionListener="#{d.ScheduleChangePopUp.onDelete}" image="/images/icons/delete.png" imageheight="16" rendered="#{d.ScheduleChangePopUp.creatorsSight}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
