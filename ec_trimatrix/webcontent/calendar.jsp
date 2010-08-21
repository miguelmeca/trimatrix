<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="calendarg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:pane id="g_3" height="100%" rowalignmenty="top" >
<t:rowdistance id="g_4" />
<t:row id="g_5" >
<t:combobox id="g_6" actionListener="#{d.ScheduleUI.onChangeAthlete}" enabled="#{d.ScheduleUI.trainer}" flush="true" focusable="#{d.ScheduleUI.trainer}" font="size:12;weight:bold" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.ScheduleUI.athleteID}" width="100%" withnullitem="false" />
</t:row>
<t:rowdistance id="g_7" height="10" />
<t:row id="g_8" >
<t:calendar id="g_9" actionListener="#{d.ScheduleUI.onDateAction}" exacttime="true" flush="true" rowalignmenty="top" timezone="CET" value="#{d.ScheduleUI.date}" />
</t:row>
<t:rowdistance id="g_10" height="10" />
<t:row id="g_11" rendered="#{d.ScheduleUI.trainer}" >
<t:pane id="g_12" rendered="#{d.ScheduleUI.trainer}" rowdistance="5" width="100%" >
<t:row id="g_13" >
<t:button id="g_14" actionListener="#{d.ScheduleUI.onCopySchedules}" image="/images/icons/copy.png" imageheight="15" text="#{rr.literals.copy}" width="100%" />
</t:row>
<t:row id="g_15" >
<t:button id="g_16" actionListener="#{d.ScheduleUI.onDeleteSchedules}" image="/images/icons/delete.png" imageheight="15" text="#{rr.literals.delete_week}" width="100%" />
</t:row>
<t:row id="g_17" >
<t:button id="g_18" actionListener="#{d.ScheduleUI.onCreateTemplate}" image="/images/icons/template.png" imageheight="15" text="#{rr.literals.create_template}" width="100%" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_19" height="10" />
<t:row id="g_20" rendered="#{d.ScheduleUI.trainer}" >
<t:foldablepopupinclude id="g_21" height="100%" page="scheduletemplates.jsp" popupwidth="200" rendered="#{d.ScheduleUI.trainer}" text="#{rr.literals.templates}" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_22" />
<t:pane id="g_23" height="100%" width="100%" >
<t:row id="g_24" >
<t:button id="g_25" actionListener="#{d.ScheduleUI.onPreviousWeek}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_26" actionListener="#{d.ScheduleUI.onNextWeek}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_27" />
<t:button id="g_28" actionListener="#{d.ScheduleUI.onMoveToday}" align="center" font="weight:bold" rowalignmenty="center" text="#{rr.literals.today}" />
<t:coldistance id="g_29" />
<t:label id="g_30" bgpaint="nodisabled()" font="weight:bold" text="#{d.ScheduleUI.actualDate}" />
<t:coldistance id="g_31" width="100%" />
<t:button id="g_32" actionListener="#{d.ScheduleUI.onMobile}" image="/images/icons/mobile.png" imageheight="15" text="#{rr.literals.mobile_link}" />
<t:coldistance id="g_33" />
<t:button id="g_34" actionListener="#{d.ScheduleUI.onPrintReport}" image="/images/icons/print.png" imageheight="15" text="#{rr.literals.print}" />
</t:row>
<t:rowdistance id="g_35" height="4" />
<t:row id="g_36" >
<t:tabbedpane id="g_37" font="weight:bold" height="100%" transparent="true" width="100%" >
<t:tabbedpanetab id="g_38" padding="0" text="#{rr.literals.week}" >
<t:row id="g_39" >
<t:pane id="g_40" height="100%" padding="0" width="100%" >
<t:captureanimator id="g_41" animationtype="#{d.ScheduleUI.animationType}" trigger="#{d.ScheduleUI.animationTrigger}" />
<t:row id="g_42" >
<t:pane id="g_43" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_44" >
<t:label id="g_45" align="center" font="size:12;weight:bold" text="#{rr.literals.cw}" width="60" />
<t:label id="g_46" align="center" font="size:16;weight:bold" text="#{rr.literals.monday}" width="100%" />
<t:button id="g_47" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="0" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.0}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_48" align="center" font="size:16;weight:bold" text="#{rr.literals.tuesday}" width="100%" />
<t:button id="g_49" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="1" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.1}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_50" align="center" font="size:16;weight:bold" text="#{rr.literals.wednesday}" width="100%" />
<t:button id="g_51" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="2" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.2}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_52" align="center" font="size:16;weight:bold" text="#{rr.literals.thursday}" width="100%" />
<t:button id="g_53" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="3" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.3}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_54" align="center" font="size:16;weight:bold" text="#{rr.literals.friday}" width="100%" />
<t:button id="g_55" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="4" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.4}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_56" align="center" font="size:16;weight:bold" text="#{rr.literals.saturday}" width="100%" />
<t:button id="g_57" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="5" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.5}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_58" align="center" font="size:16;weight:bold" text="#{rr.literals.sunday}" width="100%" />
<t:button id="g_59" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="6" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.6}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
</t:row>
<t:rowdistance id="g_60" height="5" />
<t:row id="g_61" >
<t:label id="g_62" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_63" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[0]}" width="100%" />
<t:label id="g_64" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[1]}" width="100%" />
<t:label id="g_65" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[2]}" width="100%" />
<t:label id="g_66" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[3]}" width="100%" />
<t:label id="g_67" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[4]}" width="100%" />
<t:label id="g_68" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[5]}" width="100%" />
<t:label id="g_69" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[6]}" width="100%" />
</t:row>
<t:rowdistance id="g_70" height="5" />
</t:pane>
</t:row>
<t:rowdistance id="g_71" />
<t:row id="g_72" >
<t:pane id="g_73" height="100%" width="100%" >
<t:row id="g_74" >
<t:schedule id="g_75" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="18" orientation="vertical" schedulemax="36" width="60" >
<t:scheduleitem id="g_76" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="05:00" />
<t:scheduleitem id="g_77" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_78" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_79" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_80" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_81" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_82" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_83" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_84" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_85" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_86" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_87" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_88" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_89" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_90" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_91" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_92" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_93" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_94" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_95" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_96" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_97" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_98" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_99" scheduleleft="23" schedulewidth="1" />
<t:scheduleitem id="g_100" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="24" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_101" scheduleleft="25" schedulewidth="1" />
<t:scheduleitem id="g_102" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="26" schedulewidth="1" text="18:00" />
<t:scheduleitem id="g_103" scheduleleft="27" schedulewidth="1" />
<t:scheduleitem id="g_104" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="28" schedulewidth="1" text="19:00" />
<t:scheduleitem id="g_105" scheduleleft="29" schedulewidth="1" />
<t:scheduleitem id="g_106" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="30" schedulewidth="1" text="20:00" />
<t:scheduleitem id="g_107" scheduleleft="31" schedulewidth="1" />
<t:scheduleitem id="g_108" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="32" schedulewidth="1" text="21:00" />
<t:scheduleitem id="g_109" scheduleleft="33" schedulewidth="1" />
<t:scheduleitem id="g_110" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="34" schedulewidth="1" text="22:00" />
<t:scheduleitem id="g_111" scheduleleft="35" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_112" attributemacro="scheduleMacro(0)" width="100%" />
<t:schedule id="g_113" attributemacro="scheduleMacro(1)" width="100%" />
<t:schedule id="g_114" attributemacro="scheduleMacro(2)" width="100%" />
<t:schedule id="g_115" attributemacro="scheduleMacro(3)" width="100%" />
<t:schedule id="g_116" attributemacro="scheduleMacro(4)" width="100%" />
<t:schedule id="g_117" attributemacro="scheduleMacro(5)" width="100%" />
<t:schedule id="g_118" attributemacro="scheduleMacro(6)" width="100%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_119" bgpaint="!null" rowdistance="5" text="#{rr.literals.agenda}" >
<t:row id="g_120" >
<t:foldablepane id="g_121" opened="true" rowdistance="5" text="#{rr.literals.selection}" width="100%" >
<t:row id="g_122" >
<t:label id="g_123" text="#{rr.literals.athlete}" width="90" />
<t:combobox id="g_124" actionListener="#{d.ScheduleUI.onChangeAthlete}" enabled="#{d.ScheduleUI.trainer}" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.ScheduleUI.searchAthleteId}" width="200" withnullitem="false" />
</t:row>
<t:row id="g_125" >
<t:label id="g_126" text="#{rr.literals.from} #{rr.literals.date}" width="90" />
<t:calendarfield id="g_127" flush="true" timezone="CET" value="#{d.ScheduleUI.fromDate}" width="90" />
<t:coldistance id="g_128" />
<t:label id="g_129" text="#{rr.literals.to} #{rr.literals.date}" width="90" />
<t:calendarfield id="g_130" flush="true" fromdate="#{d.ScheduleUI.fromDate}" timezone="CET" value="#{d.ScheduleUI.toDate}" width="90" />
</t:row>
<t:row id="g_131" >
<t:button id="g_132" actionListener="#{d.ScheduleUI.onAgendaSearch}" image="/images/icons/magnifier.png" imageheight="15" text="#{rr.literals.search}" />
<t:coldistance id="g_133" />
<t:button id="g_134" actionListener="#{d.ScheduleUI.onClearAgendaSearch}" image="/images/icons/delete.png" imageheight="15" text="#{rr.literals.clear}" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_135" >
<t:fixgrid id="g_136" avoidroundtrips="true" background="#FFFFFF" drawoddevenrows="true" horizontalscrollmode="autowithresize" noscrollmode="true" objectbinding="#{d.ScheduleUI.gridAgenda}" sbvisibleamount="30" showemptyrows="false" >
<t:gridcol id="g_137" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_138" background=".{scheduleItem.color}" />
</t:gridcol>
<t:gridcol id="g_139" text="#{rr.literals.done}" width="50" >
<t:checkbox id="g_140" align="center" enabled="false" selected=".{scheduleItem.done}" />
</t:gridcol>
<t:gridcol id="g_141" text="#{rr.literals.athlete}" width="100" >
<t:label id="g_142" text=".{scheduleItem.personDesc}" />
</t:gridcol>
<t:gridcol id="g_143" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.start}" width="150" >
<t:formattedfield id="g_144" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.start}" />
</t:gridcol>
<t:gridcol id="g_145" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.end}" width="150" >
<t:formattedfield id="g_146" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.end}" />
</t:gridcol>
<t:gridcol id="g_147" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.type}" width="100" >
<t:label id="g_148" text=".{scheduleItem.typeDesc}" />
</t:gridcol>
<t:gridcol id="g_149" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.description}" width="200" >
<t:label id="g_150" text=".{scheduleItem.description}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:tabbedpanetab>
</t:tabbedpane>
</t:row>
<t:row id="g_151" >
<t:label id="g_152" font="size:10;weight:bold" text="#{rr.literals.run}: #{d.ScheduleUI.runHours} h" />
<t:coldistance id="g_153" />
<t:label id="g_154" font="size:10;weight:bold" text="#{rr.literals.bike}: #{d.ScheduleUI.bikeHours} h" />
<t:coldistance id="g_155" />
<t:label id="g_156" font="size:10;weight:bold" text="#{rr.literals.swim}: #{d.ScheduleUI.swimHours} h" />
<t:coldistance id="g_157" />
<t:label id="g_158" font="size:10;weight:bold" text="#{rr.literals.total}: #{d.ScheduleUI.totalHours} h" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:popupmenu id="SCHEDULE" >
<t:menuitem id="g_159" command="cmdAdd" text="#{rr.literals.new}" />
</t:popupmenu>
<t:popupmenu id="SCHEDULEITEM" >
<t:menuitem id="g_160" command="cmdEdit" image="/resize(15,15):/images/icons/edit.png" text="#{rr.literals.change}" />
<t:menuitem id="g_161" command="cmdRemove" image="/resize(15,15):/images/icons/delete.png" text="#{rr.literals.delete}" />
<t:menuitem id="g_162" command="cmdCopy" image="/resize(15,15):/images/icons/copy.png" text="#{rr.literals.copy}" />
</t:popupmenu>
<t:beanprocessing id="g_163" >
<t:filedownload id="g_164" fileextensions="#{d.ScheduleUI.printReportExtension}" filename="#{d.ScheduleUI.printReportFilename}" openimmediately="true" opensupported="true" trigger="#{d.ScheduleUI.downloadTrigger}" url="#{d.ScheduleUI.printReportUrl}" />
<t:jshowurl id="g_165" trigger="#{d.ScheduleUI.browserTrigger}" url="#{d.ScheduleUI.mobileUrl}" usedesktop="true" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
