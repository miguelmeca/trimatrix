<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="competitionselectiong_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:icon id="g_3" actionListener="#{d.CompetitionSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_4" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.CompetitionSelectionUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_5" actionListener="#{d.CompetitionSelectionUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
</t:row>
<t:row id="g_6" >
<t:fixgrid id="g_7" avoidroundtrips="true" drawoddevenrows="true" multiselect="false" objectbinding="#{d.CompetitionSelectionUI.gridList}" sbvisibleamount="20" selectorcolumn="1" width="100%" >
<t:gridcol id="g_8" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.date}" width="100" >
<t:calendarfield id="g_9" enabled="false" timezone="CET" value=".{entity.date}" />
</t:gridcol>
<t:gridcol id="g_10" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.description}" width="200" >
<t:label id="g_11" text=".{entity.description}" />
</t:gridcol>
<t:gridcol id="g_12" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.category}" width="120" >
<t:label id="g_13" text=".{entity.type}" />
</t:gridcol>
<t:gridcol id="g_14" align="center" searchenabled="true" sortreference=".{entity.name}" text="#{rr.literals.address}" width="250" >
<t:field id="g_15" enabled="false" text=".{entity.address}" />
</t:gridcol>
<t:gridcol id="g_16" align="center" searchenabled="true" sortreference=".{entity.country}" text="#{rr.literals.country}" width="100" >
<t:label id="g_17" text=".{entity.country}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_18" >
<t:button id="g_19" actionListener="#{d.CompetitionSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_20" width="100%" />
<t:button id="g_21" actionListener="#{d.CompetitionSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
