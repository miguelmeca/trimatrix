<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="test_calendarg_sv">
<t:rowbodypane id="g_1" background="null!" bgpaint="null!" >
<t:row id="g_2" >
<t:coldistance id="g_3" />
<t:pane id="g_4" rowalignmenty="top" >
<t:row id="g_5" >
<t:button id="g_6" align="center" text="Heute" />
<t:coldistance id="g_7" width="100%" />
<t:coldistance id="g_8" width="100%" />
</t:row>
<t:row id="g_9" >
<t:calendar id="g_10" exacttime="true" flush="true" rowalignmenty="top" timezone="CET" />
</t:row>
<t:rowdistance id="g_11" height="10" />
<t:rowdistance id="g_12" height="10" />
</t:pane>
<t:pane id="g_13" height="100%" width="100%" >
<t:row id="g_14" >
<t:button id="g_15" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_16" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
</t:row>
<t:row id="g_17" >
<t:pane id="g_18" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:row id="g_19" >
<t:label id="g_20" align="center" font="size:16;weight:bold" text="Montag" width="100%" />
<t:label id="g_21" align="center" font="size:16;weight:bold" text="Dienstag" width="100%" />
<t:label id="g_22" align="center" font="size:16;weight:bold" text="Mittwoch" width="100%" />
<t:label id="g_23" align="center" font="size:16;weight:bold" text="Donnerstag" width="100%" />
<t:label id="g_24" align="center" font="size:16;weight:bold" text="Freitag" width="100%" />
<t:label id="g_25" align="center" font="size:16;weight:bold" text="Samstag" width="100%" />
<t:label id="g_26" align="center" font="size:16;weight:bold" text="Sonntag" width="100%" />
<t:label id="g_27" align="center" font="size:12;weight:bold" text="KW" width="60" />
</t:row>
<t:row id="g_28" >
<t:label id="g_29" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[0]}" width="100%" />
<t:label id="g_30" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[1]}" width="100%" />
<t:label id="g_31" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[2]}" width="100%" />
<t:label id="g_32" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[3]}" width="100%" />
<t:label id="g_33" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[4]}" width="100%" />
<t:label id="g_34" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[5]}" width="100%" />
<t:label id="g_35" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[6]}" width="100%" />
<t:label id="g_36" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
</t:row>
<t:rowdistance id="g_37" height="5" />
<t:rowdistance id="g_38" height="5" />
</t:pane>
</t:row>
<t:row id="g_39" >
<t:pane id="g_40" height="100%" width="100%" >
<t:row id="g_41" >
<t:schedule id="g_42" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="12" orientation="vertical" schedulemax="24" width="60" >
<t:scheduleitem id="g_43" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_44" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_45" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_46" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_47" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_48" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_49" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_50" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_51" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_52" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_53" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_54" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_55" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_56" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_57" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_58" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_59" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_60" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_61" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_62" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_63" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_64" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_65" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_66" scheduleleft="23" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_67" actionListener="#{d.ScheduleUI.sp0.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day0}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="24" width="100%" >
<t:scheduleitem id="g_68" actionListener="#{d.ScheduleUI.sp0.onScheduleAction}" clientname="1" invokeevent="click" scheduleleft="0" schedulewidth="1" />
<t:scheduleitem id="g_69" actionListener="#{d.ScheduleUI.sp0.onScheduleAction}" invokeevent="click" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_70" clientname="2" scheduleleft="2" schedulewidth="1" />
<t:scheduleitem id="g_71" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_72" scheduleleft="4" schedulewidth="1" />
<t:scheduleitem id="g_73" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_74" scheduleleft="6" schedulewidth="1" />
<t:scheduleitem id="g_75" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_76" scheduleleft="8" schedulewidth="1" />
<t:scheduleitem id="g_77" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_78" scheduleleft="10" schedulewidth="1" />
<t:scheduleitem id="g_79" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_80" scheduleleft="12" schedulewidth="1" />
<t:scheduleitem id="g_81" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_82" scheduleleft="14" schedulewidth="1" />
<t:scheduleitem id="g_83" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_84" scheduleleft="16" schedulewidth="1" />
<t:scheduleitem id="g_85" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_86" scheduleleft="18" schedulewidth="1" />
<t:scheduleitem id="g_87" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_88" scheduleleft="20" schedulewidth="1" />
<t:scheduleitem id="g_89" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_90" scheduleleft="22" schedulewidth="1" />
<t:scheduleitem id="g_91" scheduleleft="23" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_92" actionListener="#{d.ScheduleUI.sp1.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day1}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_93" actionListener="#{d.ScheduleUI.sp2.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day2}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_94" actionListener="#{d.ScheduleUI.sp3.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day3}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_95" actionListener="#{d.ScheduleUI.sp4.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day4}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_96" actionListener="#{d.ScheduleUI.sp5.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day5}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_97" actionListener="#{d.ScheduleUI.sp6.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day6}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_98" />
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
