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
<t:combobox id="g_13" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.enableType}" flush="true" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="120" />
</t:row>
<t:row id="g_14" >
<t:label id="g_15" text="#{rr.literals.description}" width="80" />
<t:coldistance id="g_16" />
<t:textarea id="g_17" enabled="#{d.ScheduleChangePopUp.creatorsSight}" height="50" text="#{d.ScheduleChangePopUp.text}" width="300" />
</t:row>
<t:row id="g_18" rendered="#{d.ScheduleChangePopUp.template==false}" >
<t:label id="g_19" text="#{rr.literals.start}" width="90" />
<t:calendarfield id="g_20" enabled="#{d.ScheduleChangePopUp.creatorsSight}" exacttime="true" flush="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.start}" width="120" />
<t:coldistance id="g_21" />
<t:label id="g_22" text="#{rr.literals.total_duration}" />
<t:coldistance id="g_23" />
<t:field id="g_24" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight}" text="#{d.ScheduleChangePopUp.duration}" width="80" />
<t:coldistance id="g_25" />
<t:checkbox id="g_26" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" selected="#{d.ScheduleChangePopUp.done}" text="#{rr.literals.done}" />
<t:coldistance id="g_27" width="100%" />
<t:colorfield id="g_28" background="#{d.ScheduleChangePopUp.color}" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_29" >
<t:pane id="g_30" padding="0" rowdistance="0" width="100%" >
<t:row id="g_31" >
<t:foldablepane id="g_32" comment="details" font="size:14;weight:bold" rendered="#{d.ScheduleChangePopUp.detailRelevant}" rowdistance="5" text="#{rr.literals.details}" width="100%" >
<t:row id="g_33" rendered="#{d.ScheduleChangePopUp.personsSight}" >
<t:button id="g_34" actionListener="#{d.ScheduleChangePopUp.onSetDone}" image="/images/icons/done.png" imageheight="15" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.unit_passed}" />
</t:row>
<t:row id="g_35" comment="run" rendered="#{d.ScheduleChangePopUp.typeOrd==1}" >
<t:fixgrid id="g_36" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_37" comment="duration target" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_38" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_39" comment="intensity" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_40" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_41" comment="hr" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_42" width="100%" >
<t:row id="g_43" >
<t:formattedfield id="g_44" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_45" />
<t:row id="g_46" >
<t:formattedfield id="g_47" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_48" comment="lactate" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_49" >
<t:row id="g_50" >
<t:formattedfield id="g_51" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_52" />
<t:row id="g_53" >
<t:formattedfield id="g_54" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_55" comment="duration actual" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_56" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_57" comment="avg hr" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_58" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_59" comment="comment" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_60" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_61" comment="bike" rendered="#{d.ScheduleChangePopUp.typeOrd==2}" >
<t:fixgrid id="g_62" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_63" comment="duration target" text="#{rr.literals.duration} (#{rr.literals.plan})" width="100" >
<t:field id="g_64" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" clientname="durationTarget" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationTarget}" width="100" />
</t:gridcol>
<t:gridcol id="g_65" comment="intensity" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_66" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_67" comment="hr" rendered="#{d.ScheduleChangePopUp.template==false}" text="#{rr.literals.hr}" width="100" >
<t:pane id="g_68" width="100%" >
<t:row id="g_69" >
<t:formattedfield id="g_70" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrLow}" width="100%" />
</t:row>
<t:rowline id="g_71" />
<t:row id="g_72" >
<t:formattedfield id="g_73" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="int" value=".{scheduleDetail.hrHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_74" comment="lactate" text="#{rr.literals.lactate}" width="100" >
<t:pane id="g_75" >
<t:row id="g_76" >
<t:formattedfield id="g_77" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateLow}" width="100%" />
</t:row>
<t:rowline id="g_78" />
<t:row id="g_79" >
<t:formattedfield id="g_80" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ScheduleChangePopUp.creatorsSight}" format="double" height="50%" value=".{scheduleDetail.lactateHigh}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_81" comment="power" text="#{rr.literals.power}" width="80" >
<t:formattedfield id="g_82" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.power}" width="10080" />
</t:gridcol>
<t:gridcol id="g_83" comment="cadence" text="#{rr.literals.cadence}" width="100" >
<t:formattedfield id="g_84" actionListener=".{onTimeFlush}" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.cadence}" width="100" />
</t:gridcol>
<t:gridcol id="g_85" comment="duration actual" text="#{rr.literals.duration} (#{rr.literals.debit})" width="100" >
<t:field id="g_86" actionListener=".{onTimeFlush}" align="center" clientname="durationActual" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" maxlength="8" text=".{scheduleDetail.durationActual}" width="100" />
</t:gridcol>
<t:gridcol id="g_87" comment="avg hr" text="#{rr.literals.hr_avg}" width="100" >
<t:formattedfield id="g_88" align="center" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" font="size:16" format="int" value=".{scheduleDetail.hrAvg}" />
</t:gridcol>
<t:gridcol id="g_89" comment="comment" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_90" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_91" comment="swim" rendered="#{d.ScheduleChangePopUp.typeOrd==3}" >
<t:fixgrid id="g_92" columndragdropenabled="false" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridDetail}" rowheight="40" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_93" comment="unit" text="#{rr.literals.unit}" width="80" >
<t:field id="g_94" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" font="size:16" maxlength="20" text=".{scheduleDetail.unit}" width="80" />
</t:gridcol>
<t:gridcol id="g_95" comment="description" text="#{rr.literals.description}" width="150" >
<t:textarea id="g_96" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" text=".{scheduleDetail.comment}" />
</t:gridcol>
<t:gridcol id="g_97" comment="distance" text="#{rr.literals.distance} (m)" width="80" >
<t:formattedfield id="g_98" actionListener=".{onChangeIntensity}" align="center" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" format="int" value=".{scheduleDetail.distance}" width="80" />
</t:gridcol>
<t:gridcol id="g_99" comment="intensity" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_100" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" font="size:16" validvaluesbinding="#{helper.vvb.myzonesdefinition}" value=".{scheduleDetail.zoneId}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_101" comment="target time" text="#{rr.literals.avg_target_time}" width="100" >
<t:pane id="g_102" width="100%" >
<t:row id="g_103" >
<t:field id="g_104" actionListener=".{onTimeFlush}" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" clientname="timeLowSwim" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" maxlength="9" text=".{scheduleDetail.timeLow}" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="100%" />
</t:row>
<t:rowline id="g_105" />
<t:row id="g_106" >
<t:field id="g_107" actionListener=".{onTimeFlush}" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" clientname="timeHighSwim" enabled="#{d.ScheduleChangePopUp.creatorsSight}" flush="true" maxlength="9" text=".{scheduleDetail.timeHigh}" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_108" comment="time avg" text="#{rr.literals.avg_target_time} (#{rr.literals.debit})" width="100" >
<t:field id="g_109" actionListener=".{onTimeFlush}" align="center" clientname="timeAvgSwim" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" flush="true" font="size:16" maxlength="9" text=".{scheduleDetail.timeAvg}" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="100" />
</t:gridcol>
<t:gridcol id="g_110" comment="comment" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_111" enabled="#{d.ScheduleChangePopUp.creatorsSight}" text=".{scheduleDetail.description}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_112" rendered="#{d.ScheduleChangePopUp.creatorsSight}" >
<t:button id="g_113" actionListener="#{d.ScheduleChangePopUp.onAddItem}" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_114" />
<t:button id="g_115" actionListener="#{d.ScheduleChangePopUp.onRemoveItem}" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_116" rendered="#{d.ScheduleChangePopUp.renderButtons}" >
<t:button id="g_117" actionListener="#{d.ScheduleChangePopUp.onSave}" enabled="#{d.ScheduleChangePopUp.creatorsSight||#{d.ScheduleChangePopUp.personsSight}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_118" />
<t:button id="g_119" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
<t:coldistance id="g_120" />
<t:button id="g_121" actionListener="#{d.ScheduleChangePopUp.onDelete}" image="/images/icons/delete.png" imageheight="15" rendered="#{d.ScheduleChangePopUp.creatorsSight}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
