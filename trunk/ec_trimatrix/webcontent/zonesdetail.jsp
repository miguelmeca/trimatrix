<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="zonesdetailg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.ZonesDetailUI.onAddZone}" text="#{rr.literals.zone_add}" />
<t:coldistance id="g_4" />
<t:button id="g_5" actionListener="#{d.ZonesDetailUI.onDeleteZone}" text="#{rr.literals.zone_delete}" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.ZonesDetailUI.onSave}" text="#{rr.literals.save}" />
</t:row>
<t:rowdistance id="g_8" height="10" />
<t:rowline id="g_9" />
<t:rowdistance id="g_10" height="10" />
<t:row id="g_11" >
<t:fixgrid id="g_12" avoidroundtrips="true" cellselection="true" objectbinding="#{d.ZonesDetailUI.gridZones}" sbvisibleamount="10" width="100%" >
<t:gridcol id="g_13" columnresizingenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_14" />
</t:gridcol>
<t:gridcol id="g_15" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.color}" width="100" >
<t:colorfield id="g_16" actionListener=".{onChangeColor}" background=".{color2}" flush="true" value=".{color}" />
</t:gridcol>
<t:gridcol id="g_17" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.shortcut}" width="100" >
<t:field id="g_18" maxlength="20" text=".{token}" width="100" />
</t:gridcol>
<t:gridcol id="g_19" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.description}" width="200" >
<t:field id="g_20" maxlength="50" text=".{description}" width="100" />
</t:gridcol>
<t:gridcol id="g_21" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.lactate_low}" width="80" >
<t:formattedfield id="g_22" format="double" value=".{lactateLow}" />
</t:gridcol>
<t:gridcol id="g_23" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.lactate_high}" width="80" >
<t:formattedfield id="g_24" format="double" value=".{lactateHigh}" />
</t:gridcol>
<t:gridcol id="g_25" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_low}" width="60" >
<t:formattedfield id="g_26" format="int" value=".{lactateLow}" />
</t:gridcol>
<t:gridcol id="g_27" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_high}" width="60" >
<t:formattedfield id="g_28" format="int" value=".{lactateHigh}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_29" />
<t:row id="g_30" >
<t:button id="g_31" actionListener="#{d.ZonesDetailUI.onShiftUp}" text="#{rr.literals.shift_up}" />
<t:coldistance id="g_32" />
<t:button id="g_33" actionListener="#{d.ZonesDetailUI.onShiftDown}" text="#{rr.literals.shift_down}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
