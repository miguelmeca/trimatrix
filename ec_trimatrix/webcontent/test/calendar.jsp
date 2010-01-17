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
<t:pane id="g_3" rowalignmenty="top" >
<t:rowdistance id="g_4" height="10" />
<t:row id="g_5" >
<t:coldistance id="g_6" width="100%" />
<t:button id="g_7" align="center" text="Heute" />
<t:coldistance id="g_8" width="100%" />
</t:row>
<t:rowdistance id="g_9" height="10" />
<t:row id="g_10" >
<t:calendar id="g_11" exacttime="true" flush="true" rowalignmenty="top" timezone="CET" />
</t:row>
</t:pane>
<t:coldistance id="g_12" />
<t:pane id="g_13" height="100%" width="100%" >
<t:row id="g_14" >
<t:button id="g_15" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:button id="g_16" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
</t:row>
<t:rowdistance id="g_17" />
<t:row id="g_18" >
<t:pane id="g_19" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,vertical)" border="top:1;left:1;right:1;color:#C0C0C0" width="100%" >
<t:rowdistance id="g_20" height="5" />
<t:row id="g_21" >
<t:label id="g_22" align="center" font="size:12;weight:bold" text="KW" width="60" />
<t:label id="g_23" align="center" font="size:16;weight:bold" text="Montag" width="100%" />
<t:label id="g_24" align="center" font="size:16;weight:bold" text="Dienstag" width="100%" />
<t:label id="g_25" align="center" font="size:16;weight:bold" text="Mittwoch" width="100%" />
<t:label id="g_26" align="center" font="size:16;weight:bold" text="Donnerstag" width="100%" />
<t:label id="g_27" align="center" font="size:16;weight:bold" text="Freitag" width="100%" />
<t:label id="g_28" align="center" font="size:16;weight:bold" text="Samstag" width="100%" />
<t:label id="g_29" align="center" font="size:16;weight:bold" text="Sonntag" width="100%" />
</t:row>
<t:row id="g_30" >
<t:label id="g_31" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.weekNumber}" width="60" />
<t:label id="g_32" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[0]}" width="100%" />
<t:label id="g_33" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[1]}" width="100%" />
<t:label id="g_34" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[2]}" width="100%" />
<t:label id="g_35" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[3]}" width="100%" />
<t:label id="g_36" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[4]}" width="100%" />
<t:label id="g_37" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[5]}" width="100%" />
<t:label id="g_38" align="center" font="size:12;weight:bold" text="#{d.ScheduleUI.dayInfos[6]}" width="100%" />
</t:row>
<t:rowdistance id="g_39" height="5" />
</t:pane>
</t:row>
<t:row id="g_40" >
<t:pane id="g_41" height="100%" width="100%" >
<t:row id="g_42" >
<t:schedule id="g_43" background="null!" bgpaint="rectangle(0,0,100%,100%,#80808060,#80808000,horizontal)" border="#C0C0C0" height="100%" numberofblocks="12" orientation="vertical" schedulemax="24" width="60" >
<t:scheduleitem id="g_44" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="0" schedulewidth="1" text="06:00" />
<t:scheduleitem id="g_45" scheduleleft="1" schedulewidth="1" />
<t:scheduleitem id="g_46" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="2" schedulewidth="1" text="07:00" />
<t:scheduleitem id="g_47" scheduleleft="3" schedulewidth="1" />
<t:scheduleitem id="g_48" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="4" schedulewidth="1" text="08:00" />
<t:scheduleitem id="g_49" scheduleleft="5" schedulewidth="1" />
<t:scheduleitem id="g_50" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="6" schedulewidth="1" text="09:00" />
<t:scheduleitem id="g_51" scheduleleft="7" schedulewidth="1" />
<t:scheduleitem id="g_52" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="8" schedulewidth="1" text="10:00" />
<t:scheduleitem id="g_53" scheduleleft="9" schedulewidth="1" />
<t:scheduleitem id="g_54" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="10" schedulewidth="1" text="11:00" />
<t:scheduleitem id="g_55" scheduleleft="11" schedulewidth="1" />
<t:scheduleitem id="g_56" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="12" schedulewidth="1" text="12:00" />
<t:scheduleitem id="g_57" scheduleleft="13" schedulewidth="1" />
<t:scheduleitem id="g_58" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="14" schedulewidth="1" text="13:00" />
<t:scheduleitem id="g_59" scheduleleft="15" schedulewidth="1" />
<t:scheduleitem id="g_60" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="16" schedulewidth="1" text="14:00" />
<t:scheduleitem id="g_61" scheduleleft="17" schedulewidth="1" />
<t:scheduleitem id="g_62" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="18" schedulewidth="1" text="15:00" />
<t:scheduleitem id="g_63" scheduleleft="19" schedulewidth="1" />
<t:scheduleitem id="g_64" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="20" schedulewidth="1" text="16:00" />
<t:scheduleitem id="g_65" scheduleleft="21" schedulewidth="1" />
<t:scheduleitem id="g_66" align="right!" font="size:12;weight:bold" resizingenabled="false" scheduleleft="22" schedulewidth="1" text="17:00" />
<t:scheduleitem id="g_67" scheduleleft="23" schedulewidth="1" />
</t:schedule>
<t:schedule id="g_68" actionListener="#{d.ScheduleUI.sp0.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day0}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_69" actionListener="#{d.ScheduleUI.sp1.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day1}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_70" actionListener="#{d.ScheduleUI.sp2.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day2}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_71" actionListener="#{d.ScheduleUI.sp3.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day3}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_72" actionListener="#{d.ScheduleUI.sp4.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day4}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_73" actionListener="#{d.ScheduleUI.sp5.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day5}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
<t:schedule id="g_74" actionListener="#{d.ScheduleUI.sp6.onScheduleAction}" background="#FFFFFF" border="top:1;left:0;right:1;bottom:1;color:#C0C0C0" componentbinding="#{d.ScheduleUI.day6}" dropreceive="schedule" height="100%" numberofblocks="24" orientation="vertical" padding="5" popupmenu="SCHEDULE" schedulemax="48" width="100%" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
