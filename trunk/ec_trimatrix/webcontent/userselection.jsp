<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="userselectiong_sv">
<t:row id="g_1" >
<t:icon id="g_2" actionListener="#{d.UserSelectionUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_3" actionListener="#{d.UserSelectionUI.gridList.textSearcher.text}" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" width="100" />
<t:icon id="g_4" actionListener="#{d.UserSelectionUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
<t:coldistance id="g_5" width="100%" />
<t:filedownloadlink id="g_6" actionListener="#{d.UserSelectionUI.gridList.exporter.exportURLCsvWholeGrid}" filename="grid.csv" opensupported="true" text="CSV" />
<t:filedownloadlink id="g_7" actionListener="#{d.UserSelectionUI.gridList.exporter.exportURLXMLWholeGrid}" filename="grid.xml" opensupported="true" text="XML" />
</t:row>
<t:row id="g_8" >
<t:fixgrid id="g_9" avoidroundtrips="true" border="top:1;color:#808080" drawoddevenrows="true" multiselect="false" objectbinding="#{d.UserSelectionUI.gridList}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_10" align="center" searchenabled="true" sortreference=".{entity.user_name}" text="#{rr.literals.user_user_name}" width="100" >
<t:label id="g_11" text=".{entity.user_name}" />
</t:gridcol>
<t:gridcol id="g_12" align="center" searchenabled="true" sortreference=".{entity.person}" text="#{rr.literals.user_person}" width="100" >
<t:label id="g_13" text=".{entity.person}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_14" >
<t:button id="g_15" actionListener="#{d.UserSelectionUI.onSelect}" text="#{rr.literals.select}" />
<t:coldistance id="g_16" width="100%" />
<t:button id="g_17" actionListener="#{d.UserSelectionUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
