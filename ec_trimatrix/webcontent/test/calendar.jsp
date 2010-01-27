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
<t:pane id="g_3" rowalignmenty="top" >
<t:rowdistance id="g_4" height="28" />
<t:row id="g_5" >
<t:calendar id="g_6" actionListener="#{d.ScheduleUI.onDateAction}" exacttime="true" flush="true" rowalignmenty="top" timezone="CET" value="#{d.ScheduleUI.date}" />
</t:row>
<t:rowdistance id="g_7" height="10" />
<t:row id="g_8" >
<t:link id="g_9" actionListener="#{d.ScheduleUI.onCopySchedules}" foreground="#000000" text="Copy schedules" />
</t:row>
<t:rowdistance id="g_10" height="10" />
<t:row id="g_11" >
<t:link id="g_12" actionListener="#{d.ScheduleUI.onCreateTemplate}" foreground="#000000" text="Create template" />
</t:row>
<t:rowdistance id="g_13" height="10" />
<t:row id="g_14" >
<t:foldablepopupinclude id="g_15" height="200" page="/test/test.jsp" text="Templates" width="100%" />
</t:row>
<t:row id="g_16" >
<t:coldistance id="g_17" width="100%" />
<t:button id="g_18" align="center" dragsend="schedule:new" text="Neuer Kontakt" width="150" />
<t:coldistance id="g_19" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_20" />
<t:pane id="g_21" height="100%" width="100%" >
<t:row id="g_22" >
<t:button id="g_23" actionListener="#{d.ScheduleUI.onPreviousWeek}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_24" actionListener="#{d.ScheduleUI.onNextWeek}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_25" />
<t:button id="g_26" actionListener="#{d.ScheduleUI.onMoveToday}" align="center" font="weight:bold" rowalignmenty="center" text="Heute" />
<t:coldistance id="g_27" />
<t:label id="g_28" bgpaint="nodisabled()" font="weight:bold" text="#{d.ScheduleUI.actualDate}" />
</t:row>
<t:rowdistance id="g_29" height="4" />
<t:row id="g_30" >
<t:tabbedpane id="g_31" font="weight:bold" height="100%" transparent="true" width="100%" >
<t:tabbedpanetab id="g_32" padding="0" text="Woche" >
<t:row id="g_33" >
<t:pane id="g_34" height="100%" padding="0" width="100%" >
<t:captureanimator id="g_35" animationtype="#{d.ScheduleUI.animationType}" trigger="#{d.ScheduleUI.animationTrigger}" />
<t:row id="g_36" >
<t:pane id="g_37" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_38" >
<t:label id="g_39" align="center" font="size:12;weight:bold" text="KW" width="60" />
<t:label id="g_40" align="center" font="size:16;weight:bold" text="Montag" width="16%" />
<t:label id="g_41" align="center" font="size:16;weight:bold" text="Dienstag" width="16%" />
<t:label id="g_42" align="center" font="size:16;weight:bold" text="Mittwoch" width="16%" />
<t:label id="g_43" align="center" font="size:16;weight:bold" text="Donnerstag" width="16%" />
<t:label id="g_44" align="center" font="size:16;weight:bold" text="Freitag" width="16%" />
<t:label id="g_45" align="center" font="size:16;weight:bold" text="Samstag" width="10%" />
<t:label id="g_46" align="center" font="size:16;weight:bold" text="Sonntag" width="10%" />
</t:row>
<t:rowdistance id="g_47" height="5" />
<t:row id="g_48" >
<t:label id="g_49" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_50" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[0]}" width="16%" />
<t:label id="g_51" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[1]}" width="16%" />
<t:label id="g_52" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[2]}" width="16%" />
<t:label id="g_53" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[3]}" width="16%" />
<t:label id="g_54" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[4]}" width="16%" />
<t:label id="g_55" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[5]}" width="10%" />
<t:label id="g_56" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[6]}" width="10%" />
</t:row>
<t:rowdistance id="g_57" height="5" />
</t:pane>
</t:row>
<t:rowdistance id="g_58" />
<t:row id="g_59" >
<t:pane id="g_60" height="100%" width="100%" >
<t:row id="g_61" >
<t:schedule id="g_62" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="12" orientation="vertical" schedulemax="24" width="60" >
<t:scheduleitem id="g_63" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_64" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_65" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_66" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_67" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_68" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_69" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_70" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_71" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_72" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_73" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_74" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_75" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_76" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_77" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_78" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_79" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_80" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_81" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_82" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_83" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_84" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_85" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_86" scheduleleft="23" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_87" attributemacro="scheduleMacro(0)" width="16%" />
<t:schedule id="g_88" attributemacro="scheduleMacro(1)" width="16%" />
<t:schedule id="g_89" attributemacro="scheduleMacro(2)" width="16%" />
<t:schedule id="g_90" attributemacro="scheduleMacro(3)" width="16%" />
<t:schedule id="g_91" attributemacro="scheduleMacro(4)" width="16%" />
<t:schedule id="g_92" attributemacro="scheduleMacro(5)" width="10%" />
<t:schedule id="g_93" attributemacro="scheduleMacro(6)" width="10%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_94" text="Agenda" />
</t:tabbedpane>
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:popupmenu id="SCHEDULE" >
<t:menuitem id="g_95" command="cmdAdd" text="Neu" />
</t:popupmenu>
<t:popupmenu id="SCHEDULEITEM" >
<t:menuitem id="g_96" command="cmdEdit" text="Ändern" />
<t:menuitem id="g_97" command="cmdRemove" text="Löschen" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
