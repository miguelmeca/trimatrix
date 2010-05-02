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
<t:row id="g_3" comment="single" rendered="#{d.ScheduleCopyPopUp.singleCopy}" >
<t:pane id="g_4" rowdistance="5" >
<t:row id="g_5" >
<t:pane id="g_6" border="#808080" rowdistance="5" >
<t:rowtitlebar id="g_7" text="#{rr.literals.target}" />
<t:row id="g_8" >
<t:pane id="g_9" padding="2" rowdistance="5" >
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.athlete}" width="90" />
<t:combobox id="g_12" actionListener="#{d.ScheduleUI.onChangeAthlete}" bgpaint="mandatory()" flush="true" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.ScheduleCopyPopUp.copyAthleteId}" width="150" withnullitem="false" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" text="#{rr.literals.date}" width="90" />
<t:calendarfield id="g_15" bgpaint="mandatory()" enabled="#{d.ScheduleChangePopUp.creatorsSight}" exacttime="true" flush="true" format="datetime" formatmask="short" timezone="CET" value="#{d.ScheduleCopyPopUp.copyDate}" width="120" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_16" comment="multiple" rendered="#{d.ScheduleCopyPopUp.multiCopy}" >
<t:pane id="g_17" rowdistance="5" >
<t:row id="g_18" >
<t:pane id="g_19" border="#808080" rowdistance="5" width="250" >
<t:rowtitlebar id="g_20" text="#{rr.literals.source}" />
<t:row id="g_21" >
<t:pane id="g_22" padding="2" rowdistance="5" >
<t:row id="g_23" >
<t:label id="g_24" text="#{rr.literals.from} #{rr.literals.date}" width="90" />
<t:calendarfield id="g_25" bgpaint="mandatory()" exacttime="true" flush="true" format="date" formatmask="medium" timezone="CET" value="#{d.ScheduleCopyPopUp.fromDate}" width="90" />
</t:row>
<t:row id="g_26" >
<t:label id="g_27" text="#{rr.literals.to} #{rr.literals.date}" width="90" />
<t:calendarfield id="g_28" exacttime="true" flush="true" format="date" formatmask="medium" timezone="CET" value="#{d.ScheduleCopyPopUp.toDate}" width="90" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_29" >
<t:pane id="g_30" border="#808080" rowdistance="5" width="250" >
<t:rowtitlebar id="g_31" text="#{rr.literals.target}" />
<t:row id="g_32" >
<t:pane id="g_33" padding="2" rowdistance="5" >
<t:row id="g_34" >
<t:label id="g_35" text="#{rr.literals.athlete}" width="90" />
<t:combobox id="g_36" actionListener="#{d.ScheduleUI.onChangeAthlete}" bgpaint="mandatory()" flush="true" validvaluesbinding="#{helper.vvb.myathletesandme}" value="#{d.ScheduleCopyPopUp.copyAthleteId}" width="150" withnullitem="false" />
</t:row>
<t:row id="g_37" >
<t:label id="g_38" text="#{rr.literals.date}" width="90" />
<t:calendarfield id="g_39" bgpaint="mandatory()" exacttime="true" flush="true" format="date" formatmask="medium" timezone="CET" value="#{d.ScheduleCopyPopUp.copyDate}" width="90" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_40" >
<t:button id="g_41" actionListener="#{d.ScheduleCopyPopUp.onCopy}" image="/images/icons/copy.png" imageheight="16" text="#{rr.literals.copy}" />
<t:coldistance id="g_42" />
<t:button id="g_43" actionListener="#{d.ScheduleCopyPopUp.onCancel}" image="/images/icons/cancel.png" imageheight="16" text="#{rr.literals.cancel}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
