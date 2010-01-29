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
<t:foldablepopupinclude id="g_15" height="100%" page="../scheduletemplates.jsp" popupwidth="200" text="Templates" width="100%" />
</t:row>
</t:pane>
<t:coldistance id="g_16" />
<t:pane id="g_17" height="100%" width="100%" >
<t:row id="g_18" >
<t:button id="g_19" actionListener="#{d.ScheduleUI.onPreviousWeek}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_20" actionListener="#{d.ScheduleUI.onNextWeek}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_21" />
<t:button id="g_22" actionListener="#{d.ScheduleUI.onMoveToday}" align="center" font="weight:bold" rowalignmenty="center" text="Heute" />
<t:coldistance id="g_23" />
<t:label id="g_24" bgpaint="nodisabled()" font="weight:bold" text="#{d.ScheduleUI.actualDate}" />
</t:row>
<t:rowdistance id="g_25" height="4" />
<t:row id="g_26" >
<t:tabbedpane id="g_27" font="weight:bold" height="100%" transparent="true" width="100%" >
<t:tabbedpanetab id="g_28" padding="0" text="Woche" >
<t:row id="g_29" >
<t:pane id="g_30" height="100%" padding="0" width="100%" >
<t:captureanimator id="g_31" animationtype="#{d.ScheduleUI.animationType}" trigger="#{d.ScheduleUI.animationTrigger}" />
<t:row id="g_32" >
<t:pane id="g_33" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_34" >
<t:label id="g_35" align="center" font="size:12;weight:bold" text="KW" width="60" />
<t:label id="g_36" align="center" font="size:16;weight:bold" text="Montag" width="16%" />
<t:label id="g_37" align="center" font="size:16;weight:bold" text="Dienstag" width="16%" />
<t:label id="g_38" align="center" font="size:16;weight:bold" text="Mittwoch" width="16%" />
<t:label id="g_39" align="center" font="size:16;weight:bold" text="Donnerstag" width="16%" />
<t:label id="g_40" align="center" font="size:16;weight:bold" text="Freitag" width="16%" />
<t:label id="g_41" align="center" font="size:16;weight:bold" text="Samstag" width="10%" />
<t:label id="g_42" align="center" font="size:16;weight:bold" text="Sonntag" width="10%" />
</t:row>
<t:rowdistance id="g_43" height="5" />
<t:row id="g_44" >
<t:label id="g_45" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_46" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[0]}" width="16%" />
<t:label id="g_47" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[1]}" width="16%" />
<t:label id="g_48" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[2]}" width="16%" />
<t:label id="g_49" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[3]}" width="16%" />
<t:label id="g_50" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[4]}" width="16%" />
<t:label id="g_51" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[5]}" width="10%" />
<t:label id="g_52" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[6]}" width="10%" />
</t:row>
<t:rowdistance id="g_53" height="5" />
</t:pane>
</t:row>
<t:rowdistance id="g_54" />
<t:row id="g_55" >
<t:pane id="g_56" height="100%" width="100%" >
<t:row id="g_57" >
<t:schedule id="g_58" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="12" orientation="vertical" schedulemax="24" width="60" >
<t:scheduleitem id="g_59" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_60" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_61" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_62" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_63" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_64" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_65" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_66" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_67" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_68" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_69" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_70" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_71" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_72" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_73" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_74" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_75" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_76" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_77" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_78" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_79" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_80" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_81" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_82" scheduleleft="23" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_83" attributemacro="scheduleMacro(0)" width="16%" />
<t:schedule id="g_84" attributemacro="scheduleMacro(1)" width="16%" />
<t:schedule id="g_85" attributemacro="scheduleMacro(2)" width="16%" />
<t:schedule id="g_86" attributemacro="scheduleMacro(3)" width="16%" />
<t:schedule id="g_87" attributemacro="scheduleMacro(4)" width="16%" />
<t:schedule id="g_88" attributemacro="scheduleMacro(5)" width="10%" />
<t:schedule id="g_89" attributemacro="scheduleMacro(6)" width="10%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_90" bgpaint="!null" text="Agenda" >
<t:row id="g_91" >
<t:fixgrid id="g_92" avoidroundtrips="true" background="#FFFFFF" drawoddevenrows="true" horizontalscrollmode="autowithresize" noscrollmode="true" objectbinding="#{d.ScheduleUI.gridAgenda}" sbvisibleamount="30" showemptyrows="false" width="100%" >
<t:gridcol id="g_93" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_94" background=".{scheduleItem.color}" />
</t:gridcol>
<t:gridcol id="g_95" align="center" searchenabled="true" sortenabled="true" text="Start" width="150" >
<t:formattedfield id="g_96" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.from}" />
</t:gridcol>
<t:gridcol id="g_97" align="center" searchenabled="true" sortenabled="true" text="Ende" width="150" >
<t:formattedfield id="g_98" align="center" enabled="false" exacttime="true" focusable="false" format="datetime" formatmask="medium" value=".{scheduleItem.to}" />
</t:gridcol>
<t:gridcol id="g_99" align="center" searchenabled="true" sortenabled="true" text="Type" width="100" >
<t:label id="g_100" text=".{scheduleItem.type}" />
</t:gridcol>
<t:gridcol id="g_101" align="center" searchenabled="true" sortenabled="true" text="Beschreibung" width="200" >
<t:label id="g_102" text=".{scheduleItem.text}" />
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
<t:menuitem id="g_103" command="cmdAdd" text="Neu" />
</t:popupmenu>
<t:popupmenu id="SCHEDULEITEM" >
<t:menuitem id="g_104" command="cmdEdit" text="Ändern" />
<t:menuitem id="g_105" command="cmdRemove" text="Löschen" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
