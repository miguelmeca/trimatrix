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
<t:row id="g_2" />
<t:row id="g_3" >
<t:fixgrid id="g_4" avoidroundtrips="true" objectbinding="#{d.ZonesDetailUI.gridZones}" sbvisibleamount="10" width="100%" >
<t:gridcol id="g_5" align="center" text="Bereich" width="100" >
<t:field id="g_6" align="center" background=".{color}" font="size:14;weight:bold" foreground=".{foreground}" text=".{shortcut}" width="100" />
</t:gridcol>
<t:gridcol id="g_7" align="center" text="von HR" width="80" >
<t:formattedfield id="g_8" align="center" format="int" value=".{hrLow}" />
</t:gridcol>
<t:gridcol id="g_9" align="center" text="bis HR" width="80" >
<t:formattedfield id="g_10" align="center" format="int" value=".{hrHigh}" />
</t:gridcol>
<t:gridcol id="g_11" align="center" text="von HR %" width="80" >
<t:formattedfield id="g_12" align="center" format="int" value=".{hrLowPrct}" />
</t:gridcol>
<t:gridcol id="g_13" align="center" text="bis HR %" width="80" >
<t:formattedfield id="g_14" align="center" format="int" value=".{hrHighPrct}" />
</t:gridcol>
<t:gridcol id="g_15" align="center" text="von Geschwindigkeit" width="120" >
<t:formattedfield id="g_16" align="center" format="double" value=".{speedLow}" />
</t:gridcol>
<t:gridcol id="g_17" align="center" text="bis Geschwindigkeit" width="120" >
<t:formattedfield id="g_18" align="center" format="double" value=".{speedHigh}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
