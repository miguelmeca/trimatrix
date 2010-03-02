<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_dayinfog_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:coldistance id="g_3" width="100%" />
<t:button id="g_4" actionListener="#{d.DayInfoPopUp.onPreviousDay}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:coldistance id="g_5" />
<t:label id="g_6" text="#{d.DayInfoPopUp.selectedDate}" />
<t:coldistance id="g_7" />
<t:button id="g_8" actionListener="#{d.DayInfoPopUp.onNextDay}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_9" width="100%" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.hr_rest}" width="#{d.DayInfoPopUp.width}" />
<t:formattedfield id="g_12" align="center" format="int" maxlength="3" value="#{d.DayInfoPopUp.dayInfos.restingHr}" width="100" />
</t:row>
<t:row id="g_13" >
<t:combofield id="g_14" />
</t:row>
<t:row id="g_15" >
<t:label id="g_16" text="#{rr.literals.tiredness}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_17" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_18" >
<t:label id="g_19" text="#{rr.literals.feeling}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_20" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_21" >
<t:label id="g_22" text="#{rr.literals.training_intensitsy}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_23" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_24" >
<t:label id="g_25" text="#{rr.literals.training_valuation}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_26" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_27" >
<t:checkbox id="g_28" text="#{rr.literals.restday}" />
<t:coldistance id="g_29" />
<t:checkbox id="g_30" text="#{rr.literals.travelday}" />
<t:coldistance id="g_31" />
<t:checkbox id="g_32" text="#{rr.literals.camp}" />
</t:row>
<t:row id="g_33" >
<t:checkbox id="g_34" text="#{rr.literals.illness}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_35" width="100" />
</t:row>
<t:row id="g_36" >
<t:checkbox id="g_37" text="#{rr.literals.massage}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_38" width="100" />
</t:row>
<t:row id="g_39" >
<t:checkbox id="g_40" text="#{rr.literals.therapie}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_41" width="100" />
</t:row>
<t:row id="g_42" >
<t:label id="g_43" rowalignmenty="top" text="#{rr.literals.comment}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_44" height="60" width="200" />
</t:row>
<t:row id="g_45" >
<t:label id="g_46" rowalignmenty="top" text="#{rr.literals.comment} #{rr.literals.coach}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_47" height="60" width="200" />
</t:row>
<t:row id="g_48" >
<t:button id="g_49" actionListener="#{d.DayInfoPopUp.onPrefill}" image="/images/icons/prefill.png" imageheight="15" text="#{rr.literals.prefill}" />
<t:coldistance id="g_50" width="100%" />
<t:button id="g_51" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
