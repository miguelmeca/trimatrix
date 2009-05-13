<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="createrelationg_sv">
<t:row id="g_1" >
<t:paintarea id="g_2" height="100%" width="100%" >
<t:label id="g_3" height="20" text="#{rr.literals.rel_partner1}" width="100" x="15" y="15" />
<t:field id="g_4" bgpaint="mandatory()" enabled="false" focusable="false" height="20" width="150" x="110" y="15" />
<t:button id="g_5" actionListener="#{d.CreateRelationUI.onSelectPartner1}" height="20" text="#{rr.literals.select}" width="80" x="260" y="15" />
<t:label id="g_6" height="20" text="#{rr.literals.rel_relationship}" width="100" x="15" y="45" />
<t:combobox id="g_7" bgpaint="mandatory()" enabled="#{d.CreateRelationUI.reltypEnabled}" height="20" validvaluesbinding="#{d.CreateRelationUI.reltypeVvb}" value="#{d.CreateRelationUI.reltyp}" valuetextmode="TEXT" width="150" withnullitem="false" x="110" y="45" />
<t:label id="g_8" height="20" text="#{rr.literals.rel_partner2}" width="100" x="15" y="75" />
<t:field id="g_9" bgpaint="mandatory()" enabled="false" focusable="false" height="20" width="150" x="110" y="75" />
<t:button id="g_10" actionListener="#{d.CreateRelationUI.onSelectPartner2}" height="20" text="#{rr.literals.select}" width="80" x="260" y="75" />
</t:paintarea>
</t:row>
<t:row id="g_11" >
<t:button id="g_12" actionListener="#{d.CreateRelationUI.onSave}" text="#{rr.literals.new}" />
<t:coldistance id="g_13" width="100%" />
<t:button id="g_14" actionListener="#{d.CreateRelationUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
