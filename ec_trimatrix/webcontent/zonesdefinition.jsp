<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="zonesdefinitiong_sv">
<t:row id="g_1" >
<t:pane id="g_2" padding="5" rowdistance="5" width="100%" >
<t:row id="g_3" >
<t:button id="g_4" actionListener="#{d.ZonesDefinitionUI.onAddZone}" enabled="#{d.ZonesDefinitionUI.changeAllowed}" image="/images/icons/add.png" text="#{rr.literals.zone_add}" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.ZonesDefinitionUI.onDeleteZone}" enabled="#{d.ZonesDefinitionUI.changeAllowed}" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.zone_delete}" />
<t:coldistance id="g_7" />
<t:button id="g_8" actionListener="#{d.ZonesDefinitionUI.onSave}" enabled="#{d.ZonesDefinitionUI.changeAllowed}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
<t:rowline id="g_9" />
<t:row id="g_10" >
<t:fixgrid id="g_11" avoidroundtrips="true" cellselection="true" horizontalscrollmode="autowithresize" objectbinding="#{d.ZonesDefinitionUI.gridZones}" sbvisibleamount="10" showemptyrows="false" >
<t:gridcol id="g_12" columnresizingenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_13" />
</t:gridcol>
<t:gridcol id="g_14" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.color}" width="100" >
<t:colorfield id="g_15" background=".{zonesDefinition.color}" enabled="#{d.ZonesDefinitionUI.changeAllowed}" flush="true" focusable="false" foreground=".{zonesDefinition.color}" value=".{zonesDefinition.color}" />
</t:gridcol>
<t:gridcol id="g_16" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.shortcut}" width="100" >
<t:field id="g_17" enabled="#{d.ZonesDefinitionUI.changeAllowed}" maxlength="20" text=".{zonesDefinition.shortcut}" width="100" />
</t:gridcol>
<t:gridcol id="g_18" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.description}" width="200" >
<t:field id="g_19" enabled="#{d.ZonesDefinitionUI.changeAllowed}" maxlength="50" text=".{zonesDefinition.description}" width="100" />
</t:gridcol>
<t:gridcol id="g_20" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.lactate_low}" width="80" >
<t:formattedfield id="g_21" enabled="#{d.ZonesDefinitionUI.changeAllowed}" format="double" value=".{zonesDefinition.lactateLow}" />
</t:gridcol>
<t:gridcol id="g_22" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.lactate_high}" width="80" >
<t:formattedfield id="g_23" enabled="#{d.ZonesDefinitionUI.changeAllowed}" format="double" value=".{zonesDefinition.lactateHigh}" />
</t:gridcol>
<t:gridcol id="g_24" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_low} #{rr.literals.run}" width="100" >
<t:formattedfield id="g_25" enabled="#{d.ZonesDefinitionUI.changeAllowed}" format="int" value=".{zonesDefinition.hrLowRun}" />
</t:gridcol>
<t:gridcol id="g_26" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_high} #{rr.literals.run}" width="100" >
<t:formattedfield id="g_27" enabled="#{d.ZonesDefinitionUI.changeAllowed}" format="int" value=".{zonesDefinition.hrHighRun}" />
</t:gridcol>
<t:gridcol id="g_28" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_low} #{rr.literals.bike}" width="100" >
<t:formattedfield id="g_29" enabled="#{d.ZonesDefinitionUI.changeAllowed}" format="int" value=".{zonesDefinition.hrLowBike}" />
</t:gridcol>
<t:gridcol id="g_30" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_high} #{rr.literals.bike}" width="100" >
<t:formattedfield id="g_31" enabled="#{d.ZonesDefinitionUI.changeAllowed}" format="int" value=".{zonesDefinition.hrHighBike}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_32" >
<t:button id="g_33" actionListener="#{d.ZonesDefinitionUI.onShiftUp}" enabled="#{d.ZonesDefinitionUI.changeAllowed}" image="/images/icons/arrow_up.png" imageheight="15" text="#{rr.literals.shift_up}" />
<t:coldistance id="g_34" />
<t:button id="g_35" actionListener="#{d.ZonesDefinitionUI.onShiftDown}" enabled="#{d.ZonesDefinitionUI.changeAllowed}" image="/images/icons/arrow_down.png" imageheight="15" text="#{rr.literals.shift_down}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
