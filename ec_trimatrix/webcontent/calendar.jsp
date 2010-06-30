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
<t:rowdistance id="g_11" height="10" />
<t:row id="g_12" >
<t:pane id="g_13" rowdistance="5" width="100%" >
<t:row id="g_14" >
<t:button id="g_15" actionListener="#{d.ScheduleUI.onCopySchedules}" image="/images/icons/copy.png" imageheight="15" text="#{rr.literals.copy}" width="100%" />
</t:row>
<t:row id="g_16" >
<t:button id="g_17" actionListener="#{d.ScheduleUI.onCreateTemplate}" image="/images/icons/template.png" imageheight="15" text="#{rr.literals.create_template}" width="100%" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_18" height="10" />
<t:row id="g_19" />
<t:rowdistance id="g_20" height="10" />
<t:row id="g_21" >
<t:foldablepopupinclude id="g_22" height="100%" page="scheduletemplates.jsp" popupwidth="200" text="#{rr.literals.templates}" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_23" />
<t:pane id="g_24" height="100%" width="100%" >
<t:row id="g_25" >
<t:button id="g_26" actionListener="#{d.ScheduleUI.onPreviousWeek}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_27" actionListener="#{d.ScheduleUI.onNextWeek}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_28" />
<t:button id="g_29" actionListener="#{d.ScheduleUI.onMoveToday}" align="center" font="weight:bold" rowalignmenty="center" text="#{rr.literals.today}" />
<t:coldistance id="g_30" />
<t:label id="g_31" bgpaint="nodisabled()" font="weight:bold" text="#{d.ScheduleUI.actualDate}" />
<t:coldistance id="g_32" width="100%" />
<t:button id="g_33" actionListener="#{d.ScheduleUI.onMobile}" image="/images/icons/mobile.png" imageheight="15" text="#{rr.literals.mobile_link}" />
<t:coldistance id="g_34" />
<t:button id="g_35" actionListener="#{d.ScheduleUI.onPrintReport}" image="/images/icons/print.png" imageheight="15" text="#{rr.literals.print}" />
</t:row>
<t:rowdistance id="g_36" height="4" />
<t:row id="g_37" >
<t:tabbedpane id="g_38" font="weight:bold" height="100%" transparent="true" width="100%" >
<t:tabbedpanetab id="g_39" padding="0" text="#{rr.literals.week}" >
<t:row id="g_40" >
<t:pane id="g_41" height="100%" padding="0" width="100%" >
<t:captureanimator id="g_42" animationtype="#{d.ScheduleUI.animationType}" trigger="#{d.ScheduleUI.animationTrigger}" />
<t:row id="g_43" >
<t:pane id="g_44" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_45" >
<t:label id="g_46" align="center" font="size:12;weight:bold" text="#{rr.literals.cw}" width="60" />
<t:label id="g_47" align="center" font="size:16;weight:bold" text="#{rr.literals.monday}" width="100%" />
<t:button id="g_48" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="0" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.0}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_49" align="center" font="size:16;weight:bold" text="#{rr.literals.tuesday}" width="100%" />
<t:button id="g_50" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="1" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.1}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_51" align="center" font="size:16;weight:bold" text="#{rr.literals.wednesday}" width="100%" />
<t:button id="g_52" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="2" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.2}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_53" align="center" font="size:16;weight:bold" text="#{rr.literals.thursday}" width="100%" />
<t:button id="g_54" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="3" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.3}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_55" align="center" font="size:16;weight:bold" text="#{rr.literals.friday}" width="100%" />
<t:button id="g_56" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="4" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.4}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_57" align="center" font="size:16;weight:bold" text="#{rr.literals.saturday}" width="100%" />
<t:button id="g_58" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="5" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.5}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_59" align="center" font="size:16;weight:bold" text="#{rr.literals.sunday}" width="100%" />
<t:button id="g_60" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="6" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.6}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
</t:row>
<t:rowdistance id="g_61" height="5" />
<t:row id="g_62" >
<t:label id="g_63" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_64" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[0]}" width="100%" />
<t:label id="g_65" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[1]}" width="100%" />
<t:label id="g_66" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[2]}" width="100%" />
<t:label id="g_67" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[3]}" width="100%" />
<t:label id="g_68" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[4]}" width="100%" />
<t:label id="g_69" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[5]}" width="100%" />
<t:label id="g_70" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[6]}" width="100%" />
</t:row>
<t:rowdistance id="g_71" height="5" />
</t:pane>
</t:row>
<t:rowdistance id="g_72" />
<t:row id="g_73" >
<t:pane id="g_74" height="100%" width="100%" >
<t:row id="g_75" >
<t:schedule id="g_76" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="18" orientation="vertical" schedulemax="36" width="60" >
<t:scheduleitem id="g_77" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="05:00" />
<t:scheduleitem id="g_78" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_79" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_80" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_81" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_82" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_83" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_84" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_85" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_86" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_87" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_88" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_89" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_90" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_91" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_92" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_93" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_94" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_95" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_96" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_97" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_98" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_99" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_100" scheduleleft="23" schedulewidth="1" />
<t:scheduleitem id="g_101" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="24" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_102" scheduleleft="25" schedulewidth="1" />
<t:scheduleitem id="g_103" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="26" schedulewidth="1" text="18:00" />
<t:scheduleitem id="g_104" scheduleleft="27" schedulewidth="1" />
<t:scheduleitem id="g_105" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="28" schedulewidth="1" text="19:00" />
<t:scheduleitem id="g_106" scheduleleft="29" schedulewidth="1" />
<t:scheduleitem id="g_107" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="30" schedulewidth="1" text="20:00" />
<t:scheduleitem id="g_108" scheduleleft="31" schedulewidth="1" />
<t:scheduleitem id="g_109" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="32" schedulewidth="1" text="21:00" />
<t:scheduleitem id="g_110" scheduleleft="33" schedulewidth="1" />
<t:scheduleitem id="g_111" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="34" schedulewidth="1" text="22:00" />
<t:scheduleitem id="g_112" scheduleleft="35" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_113" attributemacro="scheduleMacro(0)" width="100%" />
<t:schedule id="g_114" attributemacro="scheduleMacro(1)" width="100%" />
<t:schedule id="g_115" attributemacro="scheduleMacro(2)" width="100%" />
<t:schedule id="g_116" attributemacro="scheduleMacro(3)" width="100%" />
<t:schedule id="g_117" attributemacro="scheduleMacro(4)" width="100%" />
<t:schedule id="g_118" attributemacro="scheduleMacro(5)" width="100%" />
<t:schedule id="g_119" attributemacro="scheduleMacro(6)" width="100%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_120" bgpaint="!null" rowdistance="5" text="#{rr.literals.agenda}" >
<t:row id="g_121" >
<t:foldablepane id="g_122" opened="true" rowdistance="5" text="#{rr.literals.selection}" width="100%" >
<t:row id="g_123" >
<t:label id="g_124" text="#{rr.literals.athlete}" width="90" />
<t:combobox id="g_125" actionListener="#{d.ScheduleUI.onChangeAthlete}" enabled="#{d.ScheduleUI.trainer}" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.ScheduleUI.searchAthleteId}" width="200" withnullitem="false" />
</t:row>
<t:row id="g_126" >
<t:label id="g_127" text="#{rr.literals.from} #{rr.literals.date}" width="90" />
<t:calendarfield id="g_128" flush="true" timezone="CET" value="#{d.ScheduleUI.fromDate}" width="90" />
<t:coldistance id="g_129" />
<t:label id="g_130" text="#{rr.literals.to} #{rr.literals.date}" width="90" />
<t:calendarfield id="g_131" flush="true" fromdate="#{d.ScheduleUI.fromDate}" timezone="CET" value="#{d.ScheduleUI.toDate}" width="90" />
</t:row>
<t:row id="g_132" >
<t:button id="g_133" actionListener="#{d.ScheduleUI.onAgendaSearch}" image="/images/icons/magnifier.png" imageheight="15" text="#{rr.literals.search}" />
<t:coldistance id="g_134" />
<t:button id="g_135" actionListener="#{d.ScheduleUI.onClearAgendaSearch}" image="/images/icons/delete.png" imageheight="15" text="#{rr.literals.clear}" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_136" >
<t:fixgrid id="g_137" avoidroundtrips="true" background="#FFFFFF" drawoddevenrows="true" horizontalscrollmode="autowithresize" noscrollmode="true" objectbinding="#{d.ScheduleUI.gridAgenda}" sbvisibleamount="30" showemptyrows="false" >
<t:gridcol id="g_138" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_139" background=".{scheduleItem.color}" />
</t:gridcol>
<t:gridcol id="g_140" text="#{rr.literals.done}" width="50" >
<t:checkbox id="g_141" align="center" enabled="false" selected=".{scheduleItem.done}" />
</t:gridcol>
<t:gridcol id="g_142" text="#{rr.literals.athlete}" width="100" >
<t:label id="g_143" text=".{scheduleItem.personDesc}" />
</t:gridcol>
<t:gridcol id="g_144" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.start}" width="150" >
<t:formattedfield id="g_145" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.start}" />
</t:gridcol>
<t:gridcol id="g_146" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.end}" width="150" >
<t:formattedfield id="g_147" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.end}" />
</t:gridcol>
<t:gridcol id="g_148" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.type}" width="100" >
<t:label id="g_149" text=".{scheduleItem.typeDesc}" />
</t:gridcol>
<t:gridcol id="g_150" align="center" searchenabled="true" sortenabled="true" text="#{rr.literals.description}" width="200" >
<t:label id="g_151" text=".{scheduleItem.description}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:tabbedpanetab>
</t:tabbedpane>
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:popupmenu id="SCHEDULE" >
<t:menuitem id="g_152" command="cmdAdd" text="#{rr.literals.new}" />
</t:popupmenu>
<t:popupmenu id="SCHEDULEITEM" >
<t:menuitem id="g_153" command="cmdEdit" image="/resize(15,15):/images/icons/edit.png" text="#{rr.literals.change}" />
<t:menuitem id="g_154" command="cmdRemove" image="/resize(15,15):/images/icons/delete.png" text="#{rr.literals.delete}" />
<t:menuitem id="g_155" command="cmdCopy" image="/resize(15,15):/images/icons/copy.png" text="#{rr.literals.copy}" />
</t:popupmenu>
<t:beanprocessing id="g_156" >
<t:filedownload id="g_157" fileextensions="#{d.ScheduleUI.printReportExtension}" filename="#{d.ScheduleUI.printReportFilename}" openimmediately="true" opensupported="true" trigger="#{d.ScheduleUI.downloadTrigger}" url="#{d.ScheduleUI.printReportUrl}" />
<t:jshowurl id="g_158" trigger="#{d.ScheduleUI.browserTrigger}" url="#{d.ScheduleUI.mobileUrl}" usedesktop="true" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->