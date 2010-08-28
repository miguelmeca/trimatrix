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
<t:rowdynamiccontent id="g_3" contentbinding="#{d.ScheduleChangePopUp.labelRow}" />
<t:row id="g_4" >
<t:foldablepane id="g_5" font="size:14;weight:bold" rowdistance="5" text="#{rr.literals.summary}" width="100%" >
<t:row id="g_6" rendered="#{d.ScheduleChangePopUp.template==true}" >
<t:label id="g_7" text="#{rr.literals.template}" width="90" />
<t:field id="g_8" bgpaint="mandatory()" text="#{d.ScheduleChangePopUp.templateName}" width="250" />
<t:coldistance id="g_9" width="100%" />
<t:colorfield id="g_10" background="#{d.ScheduleChangePopUp.color}" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
<t:row id="g_11" >
<t:label id="g_12" text="#{rr.literals.type}" width="90" />
<t:combobox id="g_13" actionListener="#{d.ScheduleChangePopUp.onChangeType}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.enableType}" flush="true" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="120" />
</t:row>
<t:row id="g_14" >
<t:label id="g_15" text="#{rr.literals.description}" width="80" />
<t:coldistance id="g_16" />
<t:textarea id="g_17" enabled="#{d.ScheduleChangePopUp.creatorsSight}" height="50" text="#{d.ScheduleChangePopUp.text}" width="300" />
<t:coldistance id="g_18" />
<t:label id="g_19" text="#{rr.literals.comment}" width="80" />
<t:coldistance id="g_20" />
<t:textarea id="g_21" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" height="50" text="#{d.ScheduleChangePopUp.comment}" width="300" />
</t:row>
<t:row id="g_22" rendered="#{d.ScheduleChangePopUp.template==false}" >
<t:label id="g_23" text="#{rr.literals.start}" width="90" />
<t:calendarfield id="g_24" enabled="#{d.ScheduleChangePopUp.creatorsSight}" exacttime="true" flush="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.start}" width="120" />
<t:coldistance id="g_25" />
<t:label id="g_26" text="#{rr.literals.total_duration}" />
<t:coldistance id="g_27" />
<t:field id="g_28" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight}" text="#{d.ScheduleChangePopUp.duration}" width="80" />
<t:coldistance id="g_29" />
<t:label id="g_30" rendered="#{d.ScheduleChangePopUp.typeOrd==3}" text="#{rr.literals.total_distance} (m)" />
<t:coldistance id="g_31" />
<t:formattedfield id="g_32" align="center" enabled="false" format="int" rendered="#{d.ScheduleChangePopUp.typeOrd==3}" value="#{d.ScheduleChangePopUp.distance}" width="80" />
<t:coldistance id="g_33" />
<t:checkbox id="g_34" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" selected="#{d.ScheduleChangePopUp.done}" text="#{rr.literals.done}" />
<t:coldistance id="g_35" width="100%" />
<t:colorfield id="g_36" background="#{d.ScheduleChangePopUp.color}" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_37" >
<t:pane id="g_38" padding="0" rowdistance="0" width="100%" >
<t:row id="g_39" >
<t:foldablepane id="g_40" comment="details" font="size:14;weight:bold" rendered="#{d.ScheduleChangePopUp.detailRelevant}" rowdistance="5" text="#{rr.literals.details}" width="100%" >
<t:row id="g_41" rendered="#{d.ScheduleChangePopUp.personsSight}" >
<t:button id="g_42" actionListener="#{d.ScheduleChangePopUp.onSetDone}" image="/images/icons/done.png" imageheight="15" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.unit_passed}" />
</t:row>
<t:row id="g_43" comment="run" rendered="#{d.ScheduleChangePopUp.typeOrd==1}" >
<t:fixgrid id="g_44" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_45" comment="duration target" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_46" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_47" comment="intensity" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_48" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_49" comment="hr" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr} (#{rr.literals.target})" width="100" >
<t:pane id="g_50" width="100%" >
<t:row id="g_51" >
<t:formattedfield id="g_52" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_53" />
<t:row id="g_54" >
<t:formattedfield id="g_55" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_56" comment="lactate" text="#{rr.literals.lactate} (#{rr.literals.target})" width="100" >
<t:pane id="g_57" >
<t:row id="g_58" >
<t:formattedfield id="g_59" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_60" />
<t:row id="g_61" >
<t:formattedfield id="g_62" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_63" comment="duration actual" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_64" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_65" comment="avg hr" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_66" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_67" comment="lactate actual" text="#{rr.literals.lactate} (#{rr.literals.debit})" width="100" >
<t:formattedfield id="g_68" align="center" clientname="lactateActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="double" value=".{scheduleDetail.lactateActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_69" comment="comment" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_70" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_71" comment="bike" rendered="#{d.ScheduleChangePopUp.typeOrd==2}" >
<t:fixgrid id="g_72" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_73" comment="duration target" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_74" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_75" comment="intensity" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_76" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_77" comment="hr" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr} (#{rr.literals.target})" width="100" >
<t:pane id="g_78" width="100%" >
<t:row id="g_79" >
<t:formattedfield id="g_80" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_81" />
<t:row id="g_82" >
<t:formattedfield id="g_83" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_84" comment="lactate" text="#{rr.literals.lactate} (#{rr.literals.target})" width="100" >
<t:pane id="g_85" >
<t:row id="g_86" >
<t:formattedfield id="g_87" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_88" />
<t:row id="g_89" >
<t:formattedfield id="g_90" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_91" comment="power" text="#{rr.literals.power}" width="80" >
<t:formattedfield id="g_92" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.power}" width="10080" />
</t:gridcol>
<t:gridcol id="g_93" comment="cadence" text="#{rr.literals.cadence}" width="100" >
<t:formattedfield id="g_94" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.cadence}" width="100" />
</t:gridcol>
<t:gridcol id="g_95" comment="duration actual" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_96" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_97" comment="avg hr" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_98" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_99" comment="lactate actual" text="#{rr.literals.lactate} (#{rr.literals.debit})" width="100" >
<t:formattedfield id="g_100" align="center" clientname="lactateActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="double" value=".{scheduleDetail.lactateActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_101" comment="comment" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_102" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_103" comment="swim" rendered="#{d.ScheduleChangePopUp.typeOrd==3}" >
<t:fixgrid id="g_104" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_105" comment="unit" text="#{rr.literals.unit}" width="80" >
<t:field id="g_106" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" font="size:16" maxlength="20" text=".{scheduleDetail.unit}" width="80" />
</t:gridcol>
<t:gridcol id="g_107" comment="description" text="#{rr.literals.description}" width="150" >
<t:textarea id="g_108" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
<t:gridcol id="g_109" comment="distance" text="#{rr.literals.distance} (m)" width="80" >
<t:formattedfield id="g_110" actionListener=".{onChangeIntensity}" align="center" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.distance}" width="80" />
</t:gridcol>
<t:gridcol id="g_111" comment="total_distance" text="#{rr.literals.total} (m)" width="80" >
<t:formattedfield id="g_112" actionListener=".{onChangeIntensity}" align="center" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.totalDistance}" width="80" />
</t:gridcol>
<t:gridcol id="g_113" comment="intensity" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_114" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_115" comment="target time" text="#{rr.literals.avg_target_time}" width="100" >
<t:pane id="g_116" width="100%" >
<t:row id="g_117" >
<t:field id="g_118" actionListener=".{onTimeFlush}" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" clientname="timeLowSwim" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" maxlength="9" text=".{scheduleDetail.timeLow}" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="100%" />
</t:row>
<t:rowline id="g_119" />
<t:row id="g_120" >
<t:field id="g_121" actionListener=".{onTimeFlush}" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" clientname="timeHighSwim" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" maxlength="9" text=".{scheduleDetail.timeHigh}" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_122" comment="time avg" text="#{rr.literals.avg_target_time} (#{rr.literals.debit})" width="100" >
<t:field id="g_123" actionListener=".{onTimeFlush}" align="center" clientname="timeAvgSwim" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" maxlength="9" text=".{scheduleDetail.timeAvg}" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="100" />
</t:gridcol>
<t:gridcol id="g_124" comment="comment" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_125" enabled="#{d.ScheduleChangePopUp.creatorsSight}" text=".{scheduleDetail.description}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_126" rendered="#{d.ScheduleChangePopUp.creatorsSight}" >
<t:button id="g_127" actionListener="#{d.ScheduleChangePopUp.onAddItem}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_128" />
<t:button id="g_129" actionListener="#{d.ScheduleChangePopUp.onRemoveItem}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_130" rendered="#{d.ScheduleChangePopUp.renderButtons}" >
<t:button id="g_131" actionListener="#{d.ScheduleChangePopUp.onSave}" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_132" />
<t:button id="g_133" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
<t:coldistance id="g_134" />
<t:button id="g_135" actionListener="#{d.ScheduleChangePopUp.onDelete}" image="/images/icons/delete.png" imageheight="15" rendered="#{d.ScheduleChangePopUp.creatorsSight}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
