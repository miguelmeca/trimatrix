<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="entitylistg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:pane id="g_3" >
<t:row id="g_4" >
<t:button id="g_5" actionListener="#{d.EntityListUI.onNew}" enabled="#{d.EntityListUI.createAllowed}" rendered="#{d.EntityListUI.renderButtons}" text="#{rr.literals.new}" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.EntityListUI.onDelete}" enabled="#{d.EntityListUI.deleteAllowed}" rendered="#{d.EntityListUI.renderButtons}" text="#{rr.literals.delete}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_8" >
<t:icon id="g_9" actionListener="#{d.EntityListUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_10" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.EntityListUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_11" actionListener="#{d.EntityListUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
<t:coldistance id="g_12" width="100%" />
<t:link id="g_13" actionListener="#{d.EntityListUI.gridList.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:rowline id="g_14" />
<t:rowdynamiccontent id="g_15" contentbinding="#{d.EntityListUI.dynRow}" />
<t:row id="g_16" >
<t:button id="g_17" actionListener="#{d.EntityListUI.onRefresh}" rendered="#{d.EntityListUI.renderButtons}" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_18" width="100%" />
<t:button id="g_19" actionListener="#{d.EntityListUI.gridList.onEditColumnDetails}" rendered="#{d.EntityListUI.renderButtons}" text="#{rr.literals.list_configure}" />
<t:button id="g_20" actionListener="#{d.EntityListUI.saveGridState}" rendered="#{d.EntityListUI.renderButtons}" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
