<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="labelsearchresultg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:foldablepane id="g_3" height="100%" rendered="#{d.LabelSearchResultUI.renderAttachments}" text="#{rr.literals.attachments}" width="100%" >
<t:rowinclude id="g_4" contentreplacedrilldown="EntityListUI:LabelSearchResultUI.AttachmentsListUI" page="entitylist.jsp" />
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
