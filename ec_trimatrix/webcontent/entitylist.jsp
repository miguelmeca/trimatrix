<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="entitylistg_sv">
<t:rowbodypane id="g_1" background="#00000010" rowdistance="5" >
<t:row id="g_2" >
<t:pane id="g_3" rowdistance="5" width="100%" >
<t:row id="g_4" >
<t:pane id="g_5" width="100%" >
<t:row id="g_6" rendered="#{d.EntityListUI.renderButtons}" >
<t:button id="g_7" actionListener="#{d.EntityListUI.onNew}" enabled="#{d.EntityListUI.createAllowed}" image="/images/icons/new.png" imageheight="15" text="#{rr.literals.new}" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.EntityListUI.onDelete}" enabled="#{d.EntityListUI.deleteAllowed}" image="/images/icons/delete.png" imageheight="15" text="#{rr.literals.delete}" />
<t:coldistance id="g_10" width="100%" />
<t:filedownloadbutton id="g_11" fileextensions="#{d.EntityListUI.printReportExtension}" filename="#{d.EntityListUI.printReportFilename}" image="/images/icons/print.png" imageheight="15" openimmediately="true" opensupported="true" rendered="#{d.EntityListUI.printSupported}" text="#{rr.literals.print}" url="#{d.EntityListUI.printReportUrl}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_12" rendered="#{d.EntityListUI.renderButtons}" >
<t:icon id="g_13" actionListener="#{d.EntityListUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_14" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.EntityListUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_15" actionListener="#{d.EntityListUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
<t:coldistance id="g_16" width="100%" />
<t:link id="g_17" actionListener="#{d.EntityListUI.gridList.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:rowline id="g_18" />
<t:rowdynamiccontent id="g_19" contentbinding="#{d.EntityListUI.dynRow}" />
<t:row id="g_20" rendered="#{d.EntityListUI.renderButtons}" >
<t:button id="g_21" actionListener="#{d.EntityListUI.onRefresh}" image="/images/icons/refresh.png" imageheight="15" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_22" width="100%" />
<t:button id="g_23" actionListener="#{d.EntityListUI.gridList.onEditColumnDetails}" image="/images/icons/configure.png" imageheight="15" text="#{rr.literals.list_configure}" />
<t:coldistance id="g_24" />
<t:button id="g_25" actionListener="#{d.EntityListUI.saveGridState}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
