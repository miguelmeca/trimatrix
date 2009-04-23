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
              rendered="#{eclntdefscr.modalPopups[0].opened == true}"
              opened="#{eclntdefscr.modalPopups[0].opened}" 
              title="#{eclntdefscr.modalPopups[0].title}" 
              left="#{eclntdefscr.modalPopups[0].left}" 
              top="#{eclntdefscr.modalPopups[0].top}" 
              height="#{eclntdefscr.modalPopups[0].height}" 
              width="#{eclntdefscr.modalPopups[0].width}" >
  <t:htrow id="g_100" width="100%" styleClass="classtitlebarback">
    <t:htlabel id="g_101" text="#{eclntdefscr.modalPopups[0].title}" styleClass="classtitlebartext" width="100%">
    </t:htlabel>
    <t:htbutton id="g_102" image="../eclntjsfserver/images/window_close.png" styleClass="classbuttonplain" actionListener="#{eclntdefscr.modalPopups[0].onPopupClosedByUser}">
    </t:htbutton>
  </t:htrow>
  <t:htrow id="g_201" width="100%" height="100%">
    <t:htpane id="g_202" styleClass="classpopupinnercontent" width="100%" height="100%">
      <t:rowinclude id="g_25" 
                    page="#{eclntdefscr.modalPopups[0].page}" 
                    contentreplace="#{eclntdefscr.modalPopups[0].contentReplace}" 
                    contentreplacedrilldown="#{eclntdefscr.modalPopups[0].contentReplaceDrillDown}"/>
    </t:htpane>
  </t:htrow>
</t:htmodalpopup>

</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
