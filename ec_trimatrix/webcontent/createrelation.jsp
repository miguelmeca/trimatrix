<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="createrelationg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" height="20" text="#{rr.literals.rel_partner1}" width="100" x="15" y="15" />
<t:field id="g_4" bgpaint="mandatory()" enabled="false" focusable="false" height="20" text="#{d.CreateRelationUI.partner1}" width="150" x="110" y="15" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.CreateRelationUI.onSelectPartner1}" height="20" text="#{rr.literals.select}" width="80" x="260" y="15" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" height="20" text="#{rr.literals.rel_relationship}" width="100" x="15" y="45" />
<t:combobox id="g_9" bgpaint="mandatory()" enabled="#{d.CreateRelationUI.reltypEnabled}" height="20" validvaluesbinding="#{d.CreateRelationUI.reltypeVvb}" value="#{d.CreateRelationUI.reltyp}" valuetextmode="TEXT" width="150" withnullitem="false" x="110" y="45" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" height="20" text="#{rr.literals.rel_partner2}" width="100" x="15" y="75" />
<t:field id="g_12" bgpaint="mandatory()" enabled="false" focusable="false" height="20" text="#{d.CreateRelationUI.partner2}" width="150" x="110" y="75" />
<t:coldistance id="g_13" />
<t:button id="g_14" actionListener="#{d.CreateRelationUI.onSelectPartner2}" height="20" text="#{rr.literals.select}" width="80" x="260" y="75" />
</t:row>
<t:rowdistance id="g_15" height="100%" />
<t:row id="g_16" >
<t:button id="g_17" actionListener="#{d.CreateRelationUI.onSave}" text="#{rr.literals.new}" />
<t:coldistance id="g_18" width="100%" />
<t:button id="g_19" actionListener="#{d.CreateRelationUI.onCancel}" text="#{rr.literals.cancel}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
