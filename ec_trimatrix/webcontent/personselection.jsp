<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="personselectiong_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:icon id="g_3" actionListener="#{d.PersonSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_4" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.PersonSelectionUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_5" actionListener="#{d.PersonSelectionUI.gridList.textSearcher.oneNext}" image="/eclntjsfserver/images/control_play.png" />
</t:row>
<t:row id="g_6" >
<t:fixgrid id="g_7" avoidroundtrips="true" cellselection="true" drawoddevenrows="true" multiselect="false" objectbinding="#{d.PersonSelectionUI.gridList}" persistid="gridList" sbvisibleamount="20" selectorcolumn="1" width="100%" >
<t:gridcol id="g_8" align="center" searchenabled="true" sortreference=".{entity.salutation}" text="#{rr.literals.person_salutation}" width="50" >
<t:label id="g_9" text=".{entity.salutation}" />
</t:gridcol>
<t:gridcol id="g_10" align="center" searchenabled="true" sortreference=".{entity.nameFirst}" text="#{rr.literals.person_first_name}" width="100" >
<t:label id="g_11" text=".{entity.nameFirst}" />
</t:gridcol>
<t:gridcol id="g_12" align="center" searchenabled="true" sortreference=".{entity.nameLast}" text="#{rr.literals.person_last_name}" width="100" >
<t:label id="g_13" text=".{entity.nameLast}" />
</t:gridcol>
<t:gridcol id="g_14" align="center" searchenabled="true" sortreference=".{entity.email}" text="#{rr.literals.email}" width="200" >
<t:label id="g_15" text=".{entity.email}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_16" >
<t:button id="g_17" actionListener="#{d.PersonSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_18" width="100%" />
<t:button id="g_19" actionListener="#{d.PersonSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
