<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_schedulecopyg_sv">
<t:row id="g_1" >
<t:pane id="g_2" rowdistance="5" stylevariant="popup" >
<t:row id="g_3" >
<t:label id="g_4" text="#{rr.literals.athlete}" width="90" />
<t:combobox id="g_5" actionListener="#{d.ScheduleUI.onChangeAthlete}" bgpaint="mandatory()" flush="true" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.ScheduleCopyPopUp.copyAthleteId}" width="150" withnullitem="false" />
</t:row>
<t:row id="g_6" >
<t:label id="g_7" text="#{rr.literals.date}" width="90" />
<t:calendarfield id="g_8" enabled="#{d.ScheduleChangePopUp.creatorsSight}" exacttime="true" flush="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleCopyPopUp.copyDate}" width="120" />
</t:row>
<t:row id="g_9" >
<t:button id="g_10" actionListener="#{d.ScheduleCopyPopUp.onCopy}" image="/images/icons/copy.png" imageheight="16" text="#{rr.literals.copy}" />
<t:coldistance id="g_11" />
<t:button id="g_12" actionListener="#{d.ScheduleCopyPopUp.onCancel}" image="/images/icons/cancel.png" imageheight="16" text="#{rr.literals.cancel}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
