<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="userselectiong_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:icon id="g_3" actionListener="#{d.UserSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_4" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.UserSelectionUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_5" actionListener="#{d.UserSelectionUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
</t:row>
<t:row id="g_6" >
<t:fixgrid id="g_7" avoidroundtrips="true" drawoddevenrows="true" multiselect="false" objectbinding="#{d.UserSelectionUI.gridList}" sbvisibleamount="20" selectorcolumn="1" width="100%" >
<t:gridcol id="g_8" align="center" searchenabled="true" sortreference=".{entity.user_name}" text="#{rr.literals.user_user_name}" width="100" >
<t:label id="g_9" text=".{entity.user_name}" />
</t:gridcol>
<t:gridcol id="g_10" align="center" searchenabled="true" sortreference=".{entity.person}" text="#{rr.literals.user_person}" width="100" >
<t:label id="g_11" text=".{entity.person}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_12" >
<t:button id="g_13" actionListener="#{d.UserSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_14" width="100%" />
<t:button id="g_15" actionListener="#{d.UserSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
