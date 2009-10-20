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
<t:label id="g_3" text="Trainingsbereiche" />
</t:row>
<t:row id="g_4" >
<t:fixgrid id="g_5" avoidroundtrips="0" objectbinding="#{d.ZonesDetailUI.gridZones}" sbvisibleamount="25" width="100%" >
<t:gridcol id="g_6" align="center" searchenabled="false" sortenabled="false" text="Farbe" width="100" >
<t:colorfield id="g_7" actionListener=".{onChangeColor}" background=".{color2}" flush="true" value=".{color}" />
</t:gridcol>
<t:gridcol id="g_8" align="center" searchenabled="false" sortenabled="false" text="Kürzel" width="100" >
<t:field id="g_9" text=".{token}" width="100" />
</t:gridcol>
<t:gridcol id="g_10" align="center" searchenabled="false" sortenabled="false" text="Beschreibung" width="100" >
<t:field id="g_11" text=".{description}" width="100" />
</t:gridcol>
<t:gridcol id="g_12" align="center" searchenabled="false" sortenabled="false" text="von Laktat" width="100" >
<t:formattedfield id="g_13" format="double" value=".{lactateLow}" />
</t:gridcol>
<t:gridcol id="g_14" align="center" searchenabled="false" sortenabled="false" text="bis Laktat" width="100" >
<t:formattedfield id="g_15" format="double" value=".{lactateHigh}" />
</t:gridcol>
<t:gridcol id="g_16" text="Button" width="100" >
<t:button id="g_17" actionListener=".{onShowColor}" text="Color" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_18" >
<t:button id="g_19" actionListener="#{d.ZonesDetailUI.onAddZone}" text="Neue Zone" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
