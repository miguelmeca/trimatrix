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
<t:label id="g_3" text="Start" />
<t:coldistance id="g_4" />
<t:calendarfield id="g_5" exacttime="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.start}" />
<t:coldistance id="g_6" />
<t:label id="g_7" text="Ende" />
<t:coldistance id="g_8" />
<t:calendarfield id="g_9" exacttime="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleChangePopUp.end}" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" text="Farbe" width="90" />
<t:colorfield id="g_12" background="#{d.ScheduleChangePopUp.color}" flush="true" focusable="false" foreground="#FFFFFF00" value="#{d.ScheduleChangePopUp.color}" width="100" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" text="Type" width="90" />
<t:combobox id="g_15" validvaluesbinding="#{helper.vvb.scheduletype}" value="#{d.ScheduleChangePopUp.type}" width="100" />
</t:row>
<t:row id="g_16" >
<t:label id="g_17" text="Beschreibung" width="80" />
<t:coldistance id="g_18" />
<t:textarea id="g_19" height="50" text="#{d.ScheduleChangePopUp.text}" width="250" />
</t:row>
<t:row id="g_20" >
<t:pane id="g_21" width="100%" >
<t:row id="g_22" >
<t:pane id="g_23" comment="run" rowdistance="5" width="100%" >
<t:row id="g_24" >
<t:label id="g_25" font="size:16;weight:bold" text="#{rr.literals.run}" />
</t:row>
<t:row id="g_26" >
<t:fixgrid id="g_27" objectbinding="#{tobedfined}" >
<t:gridcol id="g_28" text="#{rr.literals.duration}" width="100" >
<t:field id="g_29" width="100" />
</t:gridcol>
<t:gridcol id="g_30" text="#{rr.literals.intensity}" width="100" />
<t:gridcol id="g_31" text="#{rr.literals.hr}" width="100" />
<t:gridcol id="g_32" text="#{rr.literals.lactate}" width="100" />
<t:gridcol id="g_33" text="#{rr.literals.comment}" width="100" />
</t:fixgrid>
</t:row>
<t:row id="g_34" >
<t:button id="g_35" image="/images/icons/add.png" imageheight="16" text="#{rr.literals.add}" />
<t:coldistance id="g_36" />
<t:button id="g_37" image="/images/icons/remove.png" imageheight="16" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_38" >
<t:button id="g_39" actionListener="#{d.ScheduleChangePopUp.onOk}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_40" />
<t:button id="g_41" actionListener="#{d.ScheduleChangePopUp.onCancel}" image="/images/icons/cancel.png" imageheight="15" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
