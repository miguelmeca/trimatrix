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
<t:pane id="g_2" padding="5" rowdistance="5" width="100%" >
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
<t:fixgrid id="g_20" avoidroundtrips="true" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ZonesDetailUI.gridZones}" rowheight="40" sbvisibleamount="10" showemptyrows="false" >
<t:gridcol id="g_21" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.area}" width="100" >
<t:field id="g_22" align="center" background=".{color}" enabled="false" font="size:14;weight:bold" foreground=".{foreground}" text=".{shortcut}" width="100" />
</t:gridcol>
<t:gridcol id="g_23" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} #{rr.literals.run}" width="100" >
<t:pane id="g_24" width="100%" >
<t:row id="g_25" >
<t:formattedfield id="g_26" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrLowRun}" width="100%" />
</t:row>
<t:rowline id="g_27" />
<t:row id="g_28" >
<t:formattedfield id="g_29" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrHighRun}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_30" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} % #{rr.literals.run}" width="100" >
<t:pane id="g_31" width="100%" >
<t:row id="g_32" >
<t:formattedfield id="g_33" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="false" format="int" value=".{hrLowPrctRun}" width="100%" />
</t:row>
<t:rowline id="g_34" />
<t:row id="g_35" >
<t:formattedfield id="g_36" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="false" format="int" value=".{hrHighPrctRun}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_37" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} #{rr.literals.bike}" width="100" >
<t:pane id="g_38" width="100%" >
<t:row id="g_39" >
<t:formattedfield id="g_40" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrLowBike}" width="100%" />
</t:row>
<t:rowline id="g_41" />
<t:row id="g_42" >
<t:formattedfield id="g_43" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrHighBike}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_44" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} % #{rr.literals.bike}" width="100" >
<t:pane id="g_45" width="100%" >
<t:row id="g_46" >
<t:formattedfield id="g_47" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="false" format="int" value=".{hrLowPrctBike}" width="100%" />
</t:row>
<t:rowline id="g_48" />
<t:row id="g_49" >
<t:formattedfield id="g_50" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="false" format="int" value=".{hrHighPrctBike}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_51" searchenabled="false" sortenabled="false" text="#{rr.literals.speed} #{rr.literals.swim}" width="180" >
<t:pane id="g_52" width="100%" >
<t:row id="g_53" >
<t:formattedfield id="g_54" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedLowSwim}" width="100%" />
</t:row>
<t:rowline id="g_55" />
<t:row id="g_56" >
<t:formattedfield id="g_57" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedHighSwim}" width="100%" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_58" searchenabled="false" sortenabled="false" text="#{rr.literals.time} #{rr.literals.swim}" width="100" >
<t:pane id="g_59" width="100%" >
<t:row id="g_60" >
<t:field id="g_61" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="false" text=".{timeLowSwim}" width="100%" />
</t:row>
<t:rowline id="g_62" />
<t:row id="g_63" >
<t:field id="g_64" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="false" text=".{timeHighSwim}" width="100%" />
</t:row>
</t:pane>
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
