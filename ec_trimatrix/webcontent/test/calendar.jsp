<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="test_calendarg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:pane id="g_3" height="100%" rowalignmenty="top" >
<t:rowdistance id="g_4" />
<t:row id="g_5" >
<t:combobox id="g_6" actionListener="#{d.ScheduleUI.onChangeAthlete}" flush="true" font="size:12;weight:bold" validvaluesbinding="#{d.ScheduleUI.vvbAthletes}" value="#{d.ScheduleUI.athleteID}" width="100%" withnullitem="false" />
</t:row>
<t:rowdistance id="g_7" height="10" />
<t:row id="g_8" >
<t:calendar id="g_9" actionListener="#{d.ScheduleUI.onDateAction}" exacttime="true" flush="true" rowalignmenty="top" timezone="CET" value="#{d.ScheduleUI.date}" />
</t:row>
<t:rowdistance id="g_10" height="10" />
<t:row id="g_11" >
<t:link id="g_12" actionListener="#{d.ScheduleUI.onCopySchedules}" foreground="#000000" text="Copy schedules" />
</t:row>
<t:rowdistance id="g_13" height="10" />
<t:row id="g_14" >
<t:link id="g_15" actionListener="#{d.ScheduleUI.onCreateTemplate}" foreground="#000000" text="Create template" />
</t:row>
<t:rowdistance id="g_16" height="10" />
<t:row id="g_17" >
<t:link id="g_18" actionListener="#{d.ScheduleUI.onCreateSerie}" foreground="#000000" text="Create serie" />
</t:row>
<t:rowdistance id="g_19" height="10" />
<t:row id="g_20" >
<t:foldablepopupinclude id="g_21" height="100%" page="../scheduletemplates.jsp" popupwidth="200" text="Templates" width="100%" />
</t:row>
<t:rowdistance id="g_22" />
<t:row id="g_23" >
<t:foldablepopupinclude id="g_24" height="100%" page="../scheduleseries.jsp" popupwidth="200" text="Series" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_25" />
<t:pane id="g_26" height="100%" width="100%" >
<t:row id="g_27" >
<t:button id="g_28" actionListener="#{d.ScheduleUI.onPreviousWeek}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_29" actionListener="#{d.ScheduleUI.onNextWeek}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_30" />
<t:button id="g_31" actionListener="#{d.ScheduleUI.onMoveToday}" align="center" font="weight:bold" rowalignmenty="center" text="Heute" />
<t:coldistance id="g_32" />
<t:label id="g_33" bgpaint="nodisabled()" font="weight:bold" text="#{d.ScheduleUI.actualDate}" />
<t:coldistance id="g_34" width="100%" />
<t:filedownloadbutton id="g_35" actionListener="#{d.ScheduleUI.onCopySchedules}" fileextensions="xls" filename="performance.xls" image="/images/icons/print.png" openimmediately="true" opensupported="true" text="Drucken" url="#{d.ScheduleUI.printReportUrl}" />
</t:row>
<t:rowdistance id="g_36" height="4" />
<t:row id="g_37" >
<t:tabbedpane id="g_38" font="weight:bold" height="100%" transparent="true" width="100%" >
<t:tabbedpanetab id="g_39" padding="0" text="Woche" >
<t:row id="g_40" >
<t:pane id="g_41" height="100%" padding="0" width="100%" >
<t:captureanimator id="g_42" animationtype="#{d.ScheduleUI.animationType}" trigger="#{d.ScheduleUI.animationTrigger}" />
<t:row id="g_43" >
<t:pane id="g_44" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_45" >
<t:label id="g_46" align="center" font="size:12;weight:bold" text="KW" width="60" />
<t:label id="g_47" align="center" font="size:16;weight:bold" text="Montag" width="16%" />
<t:button id="g_48" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="0" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.0}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_49" align="center" font="size:16;weight:bold" text="Dienstag" width="16%" />
<t:button id="g_50" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="1" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.1}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_51" align="center" font="size:16;weight:bold" text="Mittwoch" width="16%" />
<t:button id="g_52" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="2" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.2}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_53" align="center" font="size:16;weight:bold" text="Donnerstag" width="16%" />
<t:button id="g_54" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="3" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.3}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_55" align="center" font="size:16;weight:bold" text="Freitag" width="16%" />
<t:button id="g_56" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="4" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.4}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_57" align="center" font="size:16;weight:bold" text="Samstag" width="10%" />
<t:button id="g_58" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="5" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.5}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
<t:label id="g_59" align="center" font="size:16;weight:bold" text="Sonntag" width="10%" />
<t:button id="g_60" actionListener="#{d.ScheduleUI.onDayInfo}" clientname="6" contentareafilled="false" image="#{d.ScheduleUI.dayInfo.6}" imageheight="15" imagewidth="15" rowalignmenty="top" width="15" />
</t:row>
<t:rowdistance id="g_61" height="5" />
<t:row id="g_62" >
<t:label id="g_63" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_64" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[0]}" width="16%" />
<t:label id="g_65" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[1]}" width="16%" />
<t:label id="g_66" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[2]}" width="16%" />
<t:label id="g_67" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[3]}" width="16%" />
<t:label id="g_68" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[4]}" width="16%" />
<t:label id="g_69" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[5]}" width="10%" />
<t:label id="g_70" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.day[6]}" width="10%" />
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
<t:schedule id="g_113" attributemacro="scheduleMacro(0)" width="16%" />
<t:schedule id="g_114" attributemacro="scheduleMacro(1)" width="16%" />
<t:schedule id="g_115" attributemacro="scheduleMacro(2)" width="16%" />
<t:schedule id="g_116" attributemacro="scheduleMacro(3)" width="16%" />
<t:schedule id="g_117" attributemacro="scheduleMacro(4)" width="16%" />
<t:schedule id="g_118" attributemacro="scheduleMacro(5)" width="10%" />
<t:schedule id="g_119" attributemacro="scheduleMacro(6)" width="10%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_120" bgpaint="!null" text="Agenda" >
<t:row id="g_121" >
<t:fixgrid id="g_122" avoidroundtrips="true" background="#FFFFFF" drawoddevenrows="true" horizontalscrollmode="autowithresize" noscrollmode="true" objectbinding="#{d.ScheduleUI.gridAgenda}" sbvisibleamount="30" showemptyrows="false" width="100%" >
<t:gridcol id="g_123" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_124" background=".{scheduleItem.color}" />
</t:gridcol>
<t:gridcol id="g_125" align="center" searchenabled="true" sortenabled="true" text="Start" width="150" >
<t:formattedfield id="g_126" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.from}" />
</t:gridcol>
<t:gridcol id="g_127" align="center" searchenabled="true" sortenabled="true" text="Ende" width="150" >
<t:formattedfield id="g_128" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.to}" />
</t:gridcol>
<t:gridcol id="g_129" align="center" searchenabled="true" sortenabled="true" text="Type" width="100" >
<t:label id="g_130" text=".{scheduleItem.type}" />
</t:gridcol>
<t:gridcol id="g_131" align="center" searchenabled="true" sortenabled="true" text="Beschreibung" width="200" >
<t:label id="g_132" text=".{scheduleItem.text}" />
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
<t:menuitem id="g_133" command="cmdAdd" text="Neu" />
</t:popupmenu>
<t:popupmenu id="SCHEDULEITEM" >
<t:menuitem id="g_134" command="cmdEdit" text="Ändern" />
<t:menuitem id="g_135" command="cmdRemove" text="Löschen" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
