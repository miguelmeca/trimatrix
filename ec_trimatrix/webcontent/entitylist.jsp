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
<t:rowdistance id="g_7" />
<t:rowdynamiccontent id="g_8" contentbinding="#{d.EntityListUI.dynRow}" />
<t:rowdistance id="g_9" />
<t:row id="g_10" >
<t:button id="g_11" actionListener="#{d.EntityListUI.onRefresh}" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_12" width="100%" />
<t:button id="g_13" actionListener="#{d.EntityListUI.gridList.onEditColumnDetails}" text="#{rr.literals.list_configure}" />
<t:button id="g_14" actionListener="#{d.EntityListUI.saveGridState}" text="#{rr.literals.save}" />
<t:button id="g_15" actionListener="#{d.EntityListUI.loadGridState}" text="#{rr.literals.load}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
