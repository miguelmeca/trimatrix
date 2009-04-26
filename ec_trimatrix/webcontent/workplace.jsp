<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="workplaceg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:pane id="g_3" height="100%" width="28" >
<t:rowworkpagefavorites id="g_4" objectbinding="#{d.WPFavorites}" rowalignmenty="bottom" withicontext="false" />
</t:pane>
<t:splitpane id="g_5" height="100%" width="100%" >
<t:splitpanesplit id="g_6" padding="1" rowdistance="35" >
<t:rowdistance id="g_7" />
<t:rowworkplacefunctiontree id="g_8" foreground="#000000" objectbinding="#{d.WPFunctionTreeComponents}" treenodebgpaint="border(0,0,100%,100%,#C0C0C0,2)" />
</t:splitpanesplit>
<t:splitpanesplit id="g_9" >
<t:rowworkpagecontainer id="g_10" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:row id="g_11" >
<t:pane id="g_12" >
<t:rowworkpageselector id="g_13" objectbinding="#{d.workpageContainer}" />
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->