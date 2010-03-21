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
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:foldablepane id="g_3" rowdistance="5" text="#{rr.literals.summary}" width="100%" >
<t:row id="g_4" >
<t:label id="g_5" text="Start" />
<t:coldistance id="g_6" />
<t:calendarfield id="g_7" exacttime="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.start}" />
<t:coldistance id="g_8" />
<t:label id="g_9" text="Ende" />
<t:coldistance id="g_10" />
<t:calendarfield id="g_11" exacttime="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.end}" />
<t:coldistance id="g_12" width="100%" />
<t:colorfield id="g_13" background="#{d.ScheduleChangePopUp.color}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
<t:row id="g_14" >
<t:label id="g_15" text="#{rr.literals.total_duration}" width="90" />
<t:field id="g_16" align="center" width="100" />
</t:row>
<t:row id="g_17" >
<t:label id="g_18" text="Type" width="90" />
<t:combobox id="g_19" flush="true" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="100" />
</t:row>
<t:row id="g_20" >
<t:label id="g_21" text="Beschreibung" width="80" />
<t:coldistance id="g_22" />
<t:textarea id="g_23" height="50" text="#{d.ScheduleChangePopUp.text}" width="100%" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_24" >
<t:pane id="g_25" width="100%" >
<t:row id="g_26" >
<t:pane id="g_27" comment="run" rowdistance="5" width="100%" />
</t:row>
<t:row id="g_28" >
<t:foldablepane id="g_29" font="size:14;weight:bold" rowdistance="5" text="#{rr.literals.run}" width="100%" >
<t:row id="g_30" >
<t:coldistance id="g_31" width="100%" />
<t:link id="g_32" actionListener="#{d.ScheduleChangePopUp.gridRun.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_33" >
<t:fixgrid id="g_34" columndragdropenabled="false" horizontalscrollmode="autowithresize" objectbinding="#{d.ScheduleChangePopUp.gridRun}" sbvisibleamount="10" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_35" text="#{rr.literals.duration}" width="100" >
<t:field id="g_36" actionListener=".{onTimeFlush}" align="center" bgpaint="mandatory()" flush="true" text=".{duration}" width="100" />
</t:gridcol>
<t:gridcol id="g_37" text="#{rr.literals.intensity}" width="100" >
<t:combobox id="g_38" actionListener=".{onChangeIntensity}" bgpaint="mandatory()" flush="true" validvaluesbinding="#{d.ScheduleUI.vvbZones}" value=".{zone}" withnullitem="true" withvaluecomment="true" />
</t:gridcol>
<t:gridcol id="g_39" text="#{rr.literals.hr_low}" width="100" >
<t:formattedfield id="g_40" format="int" value=".{hrLow}" />
</t:gridcol>
<t:gridcol id="g_41" text="#{rr.literals.hr_high}" width="100" >
<t:formattedfield id="g_42" format="int" value=".{hrHigh}" />
</t:gridcol>
<t:gridcol id="g_43" text="#{rr.literals.lactate_low}" width="100" >
<t:formattedfield id="g_44" format="double" value=".{lactateLow}" />
</t:gridcol>
<t:gridcol id="g_45" text="#{rr.literals.lactate_high}" width="100" >
<t:formattedfield id="g_46" format="double" value=".{lactateHigh}" />
</t:gridcol>
<t:gridcol id="g_47" text="#{rr.literals.comment}" width="150" >
<t:textarea id="g_48" text=".{comment}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_49" >
<t:button id="g_50" actionListener="#{d.ScheduleChangePopUp.onAddItem}" image="/images/icons/add.png" imageheight="16" text="#{rr.literals.add}" />
<t:coldistance id="g_51" />
<t:button id="g_52" actionListener="#{d.ScheduleChangePopUp.onRemoveItem}" image="/images/icons/remove.png" imageheight="16" text="#{rr.literals.remove}" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_53" >
<t:button id="g_54" actionListener="#{d.ScheduleChangePopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_55" />
<t:button id="g_56" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
