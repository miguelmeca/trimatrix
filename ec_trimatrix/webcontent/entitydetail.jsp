<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="entitydetailg_sv">
<t:rowdistance id="g_1" />
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.EntityDetailUI.onNew}" rendered="#{d.EntityDetailUI.renderNewButton}" text="#{rr.literals.new}" />
<t:coldistance id="g_5" width="3" />
<t:button id="g_6" actionListener="#{d.EntityDetailUI.onEditShow}" rendered="#{d.EntityDetailUI.renderEditButton}" text="#{d.EntityDetailUI.txtEditButton}" />
<t:coldistance id="g_8" width="3" />
<t:button id="g_9" actionListener="#{d.EntityDetailUI.onSave}" rendered="#{d.EntityDetailUI.renderSaveButton}" text="#{rr.literals.save}" />
<t:coldistance id="g_11" width="100%" />
<t:button id="g_12" actionListener="#{d.EntityDetailUI.onDelete}" rendered="#{d.EntityDetailUI.renderDeleteButton}" text="#{rr.literals.delete}" />
</t:row>
<t:rowdistance id="g_14" />
<t:rowinclude id="g_15" page="#{d.EntityDetailUI.entityDetailPage}" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
