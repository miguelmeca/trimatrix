<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="zonesdetailg_sv">
<t:row id="g_1" >
<t:pane id="g_2" rowdistance="5" width="100%" >
<t:row id="g_3" >
<t:button id="g_4" actionListener="#{d.ZonesDetailUI.onEdit}" enabled="#{d.ZonesDetailUI.changeAllowed}" image="/images/icons/edit.png" imageheight="15" rendered="#{d.ZonesDetailUI.renderEditButton}" text="#{rr.literals.edit}" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.ZonesDetailUI.onSave}" image="/images/icons/save.png" imageheight="15" rendered="#{d.ZonesDetailUI.renderSaveButton}" text="#{rr.literals.save}" />
<t:coldistance id="g_7" />
<t:button id="g_8" actionListener="#{d.ZonesDetailUI.onCancel}" image="/images/icons/cancel.png" imageheight="15" rendered="#{d.ZonesDetailUI.renderCancelButton}" text="#{rr.literals.cancel}" />
</t:row>
<t:row id="g_9" >
<t:label id="g_10" text="#{rr.literals.coach}" />
<t:coldistance id="g_11" />
<t:link id="g_12" actionListener="#{d.ZonesDetailUI.onCoachClicked}" align="left" enabled="true" focusable="true" foreground="#000000" height="20" text="#{d.ZonesDetailUI.coach}" width="200" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" text="#{rr.literals.distance_time}" />
<t:coldistance id="g_15" />
<t:formattedfield id="g_16" align="center" flush="true" format="int" value="#{d.ZonesDetailUI.distance}" width="60" />
<t:coldistance id="g_17" />
<t:label id="g_18" text="m" />
</t:row>
<t:row id="g_19" >
<t:fixgrid id="g_20" avoidroundtrips="true" horizontalscrollmode="autowithresize" objectbinding="#{d.ZonesDetailUI.gridZones}" sbvisibleamount="10" showemptyrows="false" >
<t:gridcol id="g_21" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.area}" width="100" >
<t:field id="g_22" align="center" background=".{color}" enabled="false" font="size:14;weight:bold" foreground=".{foreground}" text=".{shortcut}" width="100" />
</t:gridcol>
<t:gridcol id="g_23" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_low}" width="80" >
<t:formattedfield id="g_24" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrLow}" />
</t:gridcol>
<t:gridcol id="g_25" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_high}" width="80" >
<t:formattedfield id="g_26" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrHigh}" />
</t:gridcol>
<t:gridcol id="g_27" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_low}" width="80" >
<t:formattedfield id="g_28" align="center" enabled="false" format="int" value=".{hrLowPrct}" />
</t:gridcol>
<t:gridcol id="g_29" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.hr_prct_high}" width="80" >
<t:formattedfield id="g_30" align="center" enabled="false" format="int" value=".{hrHighPrct}" />
</t:gridcol>
<t:gridcol id="g_31" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.speed_low}" width="120" >
<t:formattedfield id="g_32" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedLow}" />
</t:gridcol>
<t:gridcol id="g_33" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.speed_high}" width="120" >
<t:formattedfield id="g_34" align="center" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedHigh}" />
</t:gridcol>
<t:gridcol id="g_35" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.time_low}" width="80" >
<t:field id="g_36" align="center" enabled="false" text=".{timeLow}" />
</t:gridcol>
<t:gridcol id="g_37" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.time_high}" width="80" >
<t:field id="g_38" align="center" enabled="false" text=".{timeHigh}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
