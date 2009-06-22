<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="entitylistg_sv">
<t:row id="g_1" >
<t:pane id="g_2" >
<t:row id="g_3" >
<t:button id="g_4" actionListener="#{d.EntityListUI.onNew}" enabled="#{d.EntityListUI.createAllowed}" text="#{rr.literals.new}" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.EntityListUI.onDelete}" enabled="#{d.EntityListUI.deleteAllowed}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_7" height="10" />
<t:rowline id="g_8" />
<t:rowdistance id="g_9" height="10" />
<t:row id="g_10" >
<t:icon id="g_11" actionListener="#{d.EntityListUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_12" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.EntityListUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_13" actionListener="#{d.EntityListUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
<t:coldistance id="g_14" width="100%" />
<t:link id="g_15" actionListener="#{d.EntityListUI.gridList.onOpenGridFunctions}" text="Liste..." />
<t:filedownloadlink id="g_16" actionListener="#{d.EntityListUI.gridList.exporter.exportURLCsvWholeGrid}" filename="grid.csv" opensupported="true" text="CSV" />
<t:filedownloadlink id="g_17" actionListener="#{d.EntityListUI.gridList.exporter.exportURLXMLWholeGrid}" filename="grid.xml" opensupported="true" text="XML" />
</t:row>
<t:rowdynamiccontent id="g_18" contentbinding="#{d.EntityListUI.dynRow}" />
<t:rowdistance id="g_19" />
<t:row id="g_20" >
<t:button id="g_21" actionListener="#{d.EntityListUI.onRefresh}" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_22" width="100%" />
<t:button id="g_23" actionListener="#{d.EntityListUI.gridList.onEditColumnDetails}" text="#{rr.literals.list_configure}" />
<t:button id="g_24" actionListener="#{d.EntityListUI.saveGridState}" text="#{rr.literals.save}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
