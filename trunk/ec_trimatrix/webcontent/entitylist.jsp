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
<t:pane id="g_2" rowdistance="5" width="100%" >
<t:row id="g_3" >
<t:pane id="g_4" >
<t:row id="g_5" rendered="#{d.EntityListUI.renderButtons}" >
<t:button id="g_6" actionListener="#{d.EntityListUI.onNew}" enabled="#{d.EntityListUI.createAllowed}" text="#{rr.literals.new}" />
<t:coldistance id="g_7" />
<t:button id="g_8" actionListener="#{d.EntityListUI.onDelete}" enabled="#{d.EntityListUI.deleteAllowed}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_9" rendered="#{d.EntityListUI.renderButtons}" >
<t:icon id="g_10" actionListener="#{d.EntityListUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_11" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.EntityListUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_12" actionListener="#{d.EntityListUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
<t:coldistance id="g_13" width="100%" />
<t:link id="g_14" actionListener="#{d.EntityListUI.gridList.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:rowline id="g_15" />
<t:rowdynamiccontent id="g_16" contentbinding="#{d.EntityListUI.dynRow}" />
<t:row id="g_17" rendered="#{d.EntityListUI.renderButtons}" >
<t:button id="g_18" actionListener="#{d.EntityListUI.onRefresh}" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_19" width="100%" />
<t:button id="g_20" actionListener="#{d.EntityListUI.gridList.onEditColumnDetails}" text="#{rr.literals.list_configure}" />
<t:button id="g_21" actionListener="#{d.EntityListUI.saveGridState}" text="#{rr.literals.save}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
