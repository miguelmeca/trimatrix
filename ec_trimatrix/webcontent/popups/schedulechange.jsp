<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

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
<t:button id="g_21" actionListener="#{d.ScheduleChangePopUp.onOk}" text="Ok" />
<t:coldistance id="g_22" />
<t:button id="g_23" actionListener="#{d.ScheduleChangePopUp.onCancel}" text="Abbrechen" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
