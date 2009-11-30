<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>

<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="eclntjsfserver_includes_modalpopupg_3">

<t:htmodalpopup id="g_24" 
              rendered="#{eclntdefscr.htModalPopup.opened == true}"
              opened="#{eclntdefscr.htModalPopup.opened}" 
              title="#{eclntdefscr.htModalPopup.title}" 
              left="#{eclntdefscr.htModalPopup.left}" 
              top="#{eclntdefscr.htModalPopup.top}" 
              height="#{eclntdefscr.htModalPopup.height}" 
              width="#{eclntdefscr.htModalPopup.width}" >
  <t:htrow id="g_100" width="100%" styleClass="classtitlebarback">
    <t:htlabel id="g_101" text="#{eclntdefscr.htModalPopup.title}" styleClass="classtitlebartext" width="100%">
    </t:htlabel>
    <t:htbutton id="g_102" image="../eclntjsfserver/images/window_close.png" styleClass="classbuttonplain" actionListener="#{eclntdefscr.htModalPopup.onPopupClosedByUser}">
    </t:htbutton>
  </t:htrow>
  <t:htrow id="g_201" width="100%" height="100%">
    <t:htpane id="g_202" styleClass="classpopupinnercontent" width="100%" height="100%">
      <t:rowinclude id="g_25" 
                    page="#{eclntdefscr.htModalPopup.page}" 
                    contentreplace="#{eclntdefscr.htModalPopup.contentReplace}" 
                    contentreplacedrilldown="#{eclntdefscr.htModalPopup.contentReplaceDrillDown}"/>
    </t:htpane>
  </t:htrow>
</t:htmodalpopup>

</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
