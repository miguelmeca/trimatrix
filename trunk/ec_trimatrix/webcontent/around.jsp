<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="aroundg_sv">
<t:row id="g_1" >
<t:pane id="g_2" bgpaint="image(0,0,100%,100%,/images/background.jpg,lefttop);rectangle(0,0,100%,100%,#FFFFFF40);" height="100%" width="100%" >
<t:rowinclude id="g_3" page="#{d.AroundUI.contentPage}" />
</t:pane>
</t:row>
<t:rowstatusbar id="g_4" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
