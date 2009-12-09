<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="entitydetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.EntityDetailUI.onNew}" enabled="#{d.EntityDetailUI.createAllowed}" image="/images/icons/new.png" imageheight="15" rendered="#{d.EntityDetailUI.renderNewButton}" text="#{rr.literals.new}" />
<t:coldistance id="g_4" width="3" />
<t:button id="g_5" actionListener="#{d.EntityDetailUI.onEdit}" enabled="#{d.EntityDetailUI.changeAllowed}" image="/images/icons/edit.png" imageheight="15" rendered="#{d.EntityDetailUI.renderEditButton}" text="#{rr.literals.edit}" />
<t:coldistance id="g_6" width="3" />
<t:button id="g_7" actionListener="#{d.EntityDetailUI.onSave}" image="/images/icons/save.png" imageheight="15" rendered="#{d.EntityDetailUI.renderSaveButton}" text="#{rr.literals.save}" />
<t:coldistance id="g_8" width="3" />
<t:button id="g_9" actionListener="#{d.EntityDetailUI.onCancel}" image="/images/icons/cancel.png" imageheight="15" rendered="#{d.EntityDetailUI.renderCancelButton}" text="#{rr.literals.cancel}" />
<t:coldistance id="g_10" width="100%" />
<t:button id="g_11" actionListener="#{d.EntityDetailUI.onCopy}" image="/images/icons/copy.png" imageheight="15" rendered="#{d.EntityDetailUI.renderCopyButton}" text="#{rr.literals.copy}" />
<t:coldistance id="g_12" width="3" />
<t:button id="g_13" actionListener="#{d.EntityDetailUI.onDelete}" enabled="#{d.EntityDetailUI.deleteAllowed}" image="/images/icons/delete.png" imageheight="15" rendered="#{d.EntityDetailUI.renderDeleteButton}" text="#{rr.literals.delete}" />
</t:row>
<t:rowinclude id="g_14" page="#{d.EntityDetailUI.entityDetailPage}" />
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
