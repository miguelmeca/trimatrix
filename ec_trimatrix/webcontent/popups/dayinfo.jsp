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
<t:label id="g_14" text="#{rr.literals.weather}" width="#{d.DayInfoPopUp.width}" />
<t:combofield id="g_15" width="100" />
<t:coldistance id="g_16" />
<t:label id="g_17" text="#{rr.literals.temperature}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_18" editable="true" maxvalue="60" minvalue="-30" width="50" />
</t:row>
<t:row id="g_19" >
<t:label id="g_20" text="#{rr.literals.weight} #{rr.literals.morning}" width="#{d.DayInfoPopUp.width}" />
<t:label id="g_21" text="#{rr.literals.weight} #{rr.literals.evening}" width="#{d.DayInfoPopUp.width}" />
</t:row>
<t:row id="g_22" >
<t:label id="g_23" text="#{rr.literals.tiredness}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_24" editable="true" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_25" >
<t:label id="g_26" text="#{rr.literals.feeling}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_27" editable="true" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_28" >
<t:label id="g_29" text="#{rr.literals.training_intensitsy}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_30" editable="true" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_31" >
<t:label id="g_32" text="#{rr.literals.training_valuation}" width="#{d.DayInfoPopUp.width}" />
<t:spinner id="g_33" editable="true" maxvalue="5" minvalue="1" />
</t:row>
<t:row id="g_34" >
<t:checkbox id="g_35" text="#{rr.literals.restday}" />
<t:coldistance id="g_36" />
<t:checkbox id="g_37" text="#{rr.literals.travelday}" />
<t:coldistance id="g_38" />
<t:checkbox id="g_39" text="#{rr.literals.camp}" />
</t:row>
<t:row id="g_40" >
<t:checkbox id="g_41" text="#{rr.literals.illness}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_42" width="100" />
</t:row>
<t:row id="g_43" >
<t:checkbox id="g_44" text="#{rr.literals.massage}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_45" width="100" />
</t:row>
<t:row id="g_46" >
<t:checkbox id="g_47" text="#{rr.literals.therapie}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_48" width="100" />
</t:row>
<t:row id="g_49" >
<t:label id="g_50" rowalignmenty="top" text="#{rr.literals.comment}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_51" height="60" width="200" />
</t:row>
<t:row id="g_52" >
<t:label id="g_53" rowalignmenty="top" text="#{rr.literals.comment} #{rr.literals.coach}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_54" height="60" width="200" />
</t:row>
<t:row id="g_55" >
<t:button id="g_56" actionListener="#{d.DayInfoPopUp.onPrefill}" image="/images/icons/prefill.png" imageheight="15" text="#{rr.literals.prefill}" />
<t:coldistance id="g_57" width="100%" />
<t:button id="g_58" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
