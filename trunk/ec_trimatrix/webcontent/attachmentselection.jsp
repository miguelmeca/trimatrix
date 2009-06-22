<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="attachmentselectiong_sv">
<t:row id="g_1" >
<t:icon id="g_2" actionListener="#{d.AttachmentSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_3" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.AttachmentSelectionUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_4" actionListener="#{d.AttachmentSelectionUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
</t:row>
<t:row id="g_5" >
<t:fixgrid id="g_6" avoidroundtrips="true" border="top:1;color:#808080" drawoddevenrows="true" multiselect="false" objectbinding="#{d.AttachmentSelectionUI.gridList}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_7" width="25" >
<t:icon id="g_8" image=".{entity.icon}" imageheight="20" imagewidth="20" rowalignmenty="center" />
</t:gridcol>
<t:gridcol id="g_9" align="center" searchenabled="true" sortreference=".{entity.category}" text="#{rr.literals.category}" width="100" >
<t:label id="g_10" text=".{entity.category}" />
</t:gridcol>
<t:gridcol id="g_11" align="center" searchenabled="true" sortreference=".{entity.description}" text="#{rr.literals.description}" width="200" >
<t:label id="g_12" text=".{entity.description}" />
</t:gridcol>
<t:gridcol id="g_13" align="center" searchenabled="true" sortreference=".{entity.filename}" text="#{rr.literals.file_name}" width="150" >
<t:label id="g_14" text=".{entity.filename}" />
</t:gridcol>
<t:gridcol id="g_15" align="center" searchenabled="true" sortreference=".{entity.owner}" text="#{rr.literals.owner}" width="150" >
<t:label id="g_16" text=".{entity.owner}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_17" >
<t:button id="g_18" actionListener="#{d.AttachmentSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_19" width="100%" />
<t:button id="g_20" actionListener="#{d.AttachmentSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
