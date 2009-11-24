<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="zonesdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.ZonesDetailUI.onEdit}" enabled="#{d.ZonesDetailUI.changeAllowed}" rendered="#{d.ZonesDetailUI.renderEditButton}" text="#{rr.literals.edit}" />
<t:coldistance id="g_4" />
<t:button id="g_5" actionListener="#{d.ZonesDetailUI.onSave}" rendered="#{d.ZonesDetailUI.renderSaveButton}" text="#{rr.literals.save}" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.ZonesDetailUI.onCancel}" rendered="#{d.ZonesDetailUI.renderCancelButton}" text="#{rr.literals.cancel}" />
</t:row>
<t:row id="g_8" >
<t:label id="g_9" text="Distanz für Zeit" />
<t:coldistance id="g_10" />
<t:combobox id="g_11" bufferobjecttype="Integer" combopopupwidth="60" editable="false" flush="true" itemborder="noborder" value="#{d.ZonesDetailUI.distance}" width="60" withnullitem="false" >
<t:comboboxitem id="g_12" text="100 m" value="100" />
<t:comboboxitem id="g_13" text="200 m" value="200" />
</t:combobox>
<t:coldistance id="g_14" />
</t:row>
<t:row id="g_15" >
<t:fixgrid id="g_16" avoidroundtrips="true" objectbinding="#{d.ZonesDetailUI.gridZones}" sbvisibleamount="10" width="100%" >
<t:gridcol id="g_17" align="center" searchenabled="false" sortenabled="false" text="Bereich" width="100" >
<t:field id="g_18" align="center" background=".{color}" enabled="false" font="size:14;weight:bold" foreground=".{foreground}" text=".{shortcut}" width="100" />
</t:gridcol>
<t:gridcol id="g_19" align="center" searchenabled="false" sortenabled="false" text="von HR" width="80" >
<t:formattedfield id="g_20" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrLow}" />
</t:gridcol>
<t:gridcol id="g_21" align="center" searchenabled="false" sortenabled="false" text="bis HR" width="80" >
<t:formattedfield id="g_22" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrHigh}" />
</t:gridcol>
<t:gridcol id="g_23" align="center" searchenabled="false" sortenabled="false" text="von HR %" width="80" >
<t:formattedfield id="g_24" align="center" enabled="false" format="int" value=".{hrLowPrct}" />
</t:gridcol>
<t:gridcol id="g_25" align="center" searchenabled="false" sortenabled="false" text="bis HR %" width="80" >
<t:formattedfield id="g_26" align="center" enabled="false" format="int" value=".{hrHighPrct}" />
</t:gridcol>
<t:gridcol id="g_27" align="center" searchenabled="false" sortenabled="false" text="von Geschwindigkeit" width="120" >
<t:formattedfield id="g_28" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedLow}" />
</t:gridcol>
<t:gridcol id="g_29" align="center" searchenabled="false" sortenabled="false" text="bis Geschwindigkeit" width="120" >
<t:formattedfield id="g_30" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedHigh}" />
</t:gridcol>
<t:gridcol id="g_31" align="center" searchenabled="false" sortenabled="false" text="von Zeit" width="80" >
<t:field id="g_32" align="center" enabled="false" text=".{timeLow}" />
</t:gridcol>
<t:gridcol id="g_33" align="center" searchenabled="false" sortenabled="false" text="bis Zeit" width="80" >
<t:field id="g_34" align="center" enabled="false" text=".{timeHigh}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
