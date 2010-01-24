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
<t:rowdistance id="g_7" height="20" />
<t:row id="g_8" >
<t:coldistance id="g_9" width="100%" />
<t:button id="g_10" align="center" dragsend="schedule:new" text="Neuer Kontakt" width="150" />
<t:coldistance id="g_11" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_12" />
<t:pane id="g_13" height="100%" width="100%" >
<t:row id="g_14" >
<t:button id="g_15" actionListener="#{d.ScheduleUI.onPreviousWeek}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_16" actionListener="#{d.ScheduleUI.onNextWeek}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_17" />
<t:button id="g_18" actionListener="#{d.ScheduleUI.onMoveToday}" align="center" font="weight:bold" rowalignmenty="center" text="Heute" />
<t:coldistance id="g_19" />
<t:label id="g_20" bgpaint="nodisabled()" font="weight:bold" text="#{d.ScheduleUI.actualDate}" />
</t:row>
<t:rowdistance id="g_21" height="4" />
<t:row id="g_22" >
<t:tabbedpane id="g_23" font="weight:bold" height="100%" transparent="true" width="100%" >
<t:tabbedpanetab id="g_24" padding="0" text="Woche" >
<t:row id="g_25" >
<t:pane id="g_26" height="100%" padding="0" width="100%" >
<t:captureanimator id="g_27" animationtype="#{d.ScheduleUI.animationType}" trigger="#{d.ScheduleUI.animationTrigger}" />
<t:row id="g_28" >
<t:pane id="g_29" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_30" >
<t:label id="g_31" align="center" font="size:12;weight:bold" text="KW" width="60" />
<t:label id="g_32" align="center" font="size:16;weight:bold" text="Montag" width="16%" />
<t:label id="g_33" align="center" font="size:16;weight:bold" text="Dienstag" width="16%" />
<t:label id="g_34" align="center" font="size:16;weight:bold" text="Mittwoch" width="16%" />
<t:label id="g_35" align="center" font="size:16;weight:bold" text="Donnerstag" width="16%" />
<t:label id="g_36" align="center" font="size:16;weight:bold" text="Freitag" width="16%" />
<t:label id="g_37" align="center" font="size:16;weight:bold" text="Samstag" width="10%" />
<t:label id="g_38" align="center" font="size:16;weight:bold" text="Sonntag" width="10%" />
</t:row>
<t:rowdistance id="g_39" height="5" />
<t:row id="g_40" >
<t:label id="g_41" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_42" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[0]}" width="16%" />
<t:label id="g_43" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[1]}" width="16%" />
<t:label id="g_44" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[2]}" width="16%" />
<t:label id="g_45" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[3]}" width="16%" />
<t:label id="g_46" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[4]}" width="16%" />
<t:label id="g_47" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[5]}" width="10%" />
<t:label id="g_48" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[6]}" width="10%" />
</t:row>
<t:rowdistance id="g_49" height="5" />
</t:pane>
</t:row>
<t:rowdistance id="g_50" />
<t:row id="g_51" >
<t:pane id="g_52" height="100%" width="100%" >
<t:row id="g_53" >
<t:schedule id="g_54" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="12" orientation="vertical" schedulemax="24" width="60" >
<t:scheduleitem id="g_55" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_56" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_57" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_58" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_59" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_60" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_61" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_62" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_63" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_64" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_65" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_66" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_67" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_68" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_69" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_70" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_71" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_72" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_73" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_74" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_75" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_76" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_77" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_78" scheduleleft="23" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_79" attributemacro="scheduleMacro(0)" componentbinding="#{d.ScheduleUI.day0}" width="16%" />
<t:schedule id="g_80" attributemacro="scheduleMacro(1)" componentbinding="#{d.ScheduleUI.day1}" width="16%" />
<t:schedule id="g_81" attributemacro="scheduleMacro(2)" componentbinding="#{d.ScheduleUI.day2}" width="16%" />
<t:schedule id="g_82" attributemacro="scheduleMacro(3)" componentbinding="#{d.ScheduleUI.day3}" width="16%" />
<t:schedule id="g_83" attributemacro="scheduleMacro(4)" componentbinding="#{d.ScheduleUI.day4}" width="16%" />
<t:schedule id="g_84" attributemacro="scheduleMacro(5)" componentbinding="#{d.ScheduleUI.day5}" width="10%" />
<t:schedule id="g_85" attributemacro="scheduleMacro(6)" componentbinding="#{d.ScheduleUI.day6}" width="10%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_86" text="Agenda" />
</t:tabbedpane>
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:popupmenu id="SCHEDULE" >
<t:menuitem id="g_87" command="cmdAdd" text="Neu" />
</t:popupmenu>
<t:popupmenu id="SCHEDULEITEM" >
<t:menuitem id="g_88" command="cmdEdit" text="Ändern" />
<t:menuitem id="g_89" command="cmdRemove" text="Löschen" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
