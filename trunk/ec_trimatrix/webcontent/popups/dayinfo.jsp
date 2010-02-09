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
<t:label id="g_11" text="#{rr.literals.hr_rest}" width="100" />
<t:formattedfield id="g_12" align="center" format="int" maxlength="3" value="#{d.DayInfoPopUp.dayInfos.restingHr}" width="100" />
</t:row>
<t:row id="g_13" >
<t:coldistance id="g_14" width="100%" />
<t:button id="g_15" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" rendered="#{d.EntityDetailUI.renderSaveButton}" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
