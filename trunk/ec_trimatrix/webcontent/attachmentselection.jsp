<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="attachmentselectiong_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:icon id="g_3" actionListener="#{d.AttachmentSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_4" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.AttachmentSelectionUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_5" actionListener="#{d.AttachmentSelectionUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
</t:row>
<t:row id="g_6" >
<t:fixgrid id="g_7" avoidroundtrips="true" drawoddevenrows="true" multiselect="false" objectbinding="#{d.AttachmentSelectionUI.gridList}" sbvisibleamount="20" selectorcolumn="1" width="100%" >
<t:gridcol id="g_8" width="25" >
<t:icon id="g_9" image=".{entity.icon}" imageheight="20" imagewidth="20" rowalignmenty="center" />
</t:gridcol>
<t:gridcol id="g_10" align="center" searchenabled="true" sortreference=".{entity.category}" text="#{rr.literals.category}" width="100" >
<t:label id="g_11" text=".{entity.category}" />
</t:gridcol>
<t:gridcol id="g_12" align="center" searchenabled="true" sortreference=".{entity.description}" text="#{rr.literals.description}" width="200" >
<t:label id="g_13" text=".{entity.description}" />
</t:gridcol>
<t:gridcol id="g_14" align="center" searchenabled="true" sortreference=".{entity.filename}" text="#{rr.literals.file_name}" width="150" >
<t:label id="g_15" text=".{entity.filename}" />
</t:gridcol>
<t:gridcol id="g_16" align="center" searchenabled="true" sortreference=".{entity.owner}" text="#{rr.literals.owner}" width="150" >
<t:label id="g_17" text=".{entity.owner}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_18" >
<t:button id="g_19" actionListener="#{d.AttachmentSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_20" width="100%" />
<t:button id="g_21" actionListener="#{d.AttachmentSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
