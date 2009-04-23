<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="eclntjsfserver_includes_showservererrorg_19">
<t:rowtitlebar id="g_2" bgpaint="rectangle(0,0,100%,100%,#680000,#FF0000,vertical)" text="Server Side Error occurred" />
<t:rowbodypane id="g_3" bgpaint="rectangle(0,0,100%,100%,#D0D0D0,#FFFFFF,vertical)" >
  <t:row id="g_4" >
    <t:textpane id="g_5" text="An error occurred within your server side application that was not caught by the application processing. Please check with your application provider." width="100%" />
  </t:row>
  <t:rowdistance id="g_6" height="10" />
  <t:row id="g_7" >
    <t:label id="g_8" text="Server Side Stack Trace" />
  </t:row>
  <t:row id="g_9" >
    <t:textarea id="g_10" height="100%" text="#{eclntdefscr.showServerError.text}" width="100%" />
  </t:row>
</t:rowbodypane>
<t:pageaddons/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
