<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="personselectiong_sv">
<t:row id="g_1" >
<t:icon id="g_2" actionListener="#{d.PersonSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_3" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.PersonSelectionUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_4" actionListener="#{d.PersonSelectionUI.gridList.textSearcher.oneNext}" image="/eclntjsfserver/images/control_play.png" />
</t:row>
<t:row id="g_5" >
<t:fixgrid id="g_6" avoidroundtrips="true" border="top:1;color:#808080" cellselection="true" drawoddevenrows="true" multiselect="false" objectbinding="#{d.PersonSelectionUI.gridList}" persistid="gridList" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_7" align="center" searchenabled="true" sortreference=".{entity.salutation}" text="#{rr.literals.person_salutation}" width="50" >
<t:label id="g_8" text=".{entity.salutation}" />
</t:gridcol>
<t:gridcol id="g_9" align="center" searchenabled="true" sortreference=".{entity.name_first}" text="#{rr.literals.person_first_name}" width="100" >
<t:label id="g_10" text=".{entity.name_first}" />
</t:gridcol>
<t:gridcol id="g_11" align="center" searchenabled="true" sortreference=".{entity.name_last}" text="#{rr.literals.person_last_name}" width="100" >
<t:label id="g_12" text=".{entity.name_last}" />
</t:gridcol>
<t:gridcol id="g_13" align="center" searchenabled="true" sortreference=".{entity.email}" text="#{rr.literals.email}" width="200" >
<t:label id="g_14" text=".{entity.email}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_15" >
<t:button id="g_16" actionListener="#{d.PersonSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_17" width="100%" />
<t:button id="g_18" actionListener="#{d.PersonSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->