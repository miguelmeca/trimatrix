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
<t:formattedfield id="g_16" align="center" format="int" value="#{d.ZonesDetailUI.distance}" width="60" />
<t:coldistance id="g_17" />
<t:label id="g_18" text="m" />
<t:coldistance id="g_19" />
<t:button id="g_20" actionListener="#{d.ZonesDetailUI.onAddIndividualZone}" enabled="#{d.ZonesDetailUI.enabled}" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_21" />
<t:button id="g_22" actionListener="#{d.ZonesDetailUI.onRefresh}" image="/images/icons/refresh.png" imageheight="15" text="#{rr.literals.list_refresh}" />
</t:row>
<t:row id="g_23" >
<t:fixgrid id="g_24" avoidroundtrips="true" headlinerowheight="20" horizontalscrollmode="autowithresize" objectbinding="#{d.ZonesDetailUI.gridZones}" rowheight="40" sbvisibleamount="10" showemptyrows="false" >
<t:gridcol id="g_25" align="center" searchenabled="false" sortenabled="false" text="#{rr.literals.area}" width="100" >
<t:field id="g_26" align="center" background=".{color}" enabled="false" font="size:14;weight:bold" foreground=".{foreground}" text=".{shortcut}" width="100" />
</t:gridcol>
<t:gridcol id="g_27" comment="hr run" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} #{rr.literals.run}" width="100" >
<t:pane id="g_28" width="100%" >
<t:rowline id="g_29" />
<t:row id="g_30" >
<t:formattedfield id="g_31" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrLowRun}" width="100%" />
</t:row>
<t:row id="g_32" >
<t:formattedfield id="g_33" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrHighRun}" width="100%" />
</t:row>
<t:rowline id="g_34" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_35" comment="hr run target" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} #{rr.literals.run} (#{rr.literals.target})" width="100" >
<t:pane id="g_36" width="100%" >
<t:rowline id="g_37" />
<t:row id="g_38" >
<t:formattedfield id="g_39" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrTargetLowRun}" width="100%" />
</t:row>
<t:row id="g_40" >
<t:formattedfield id="g_41" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrTargetHighRun}" width="100%" />
</t:row>
<t:rowline id="g_42" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_43" comment="hr percent run" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} % #{rr.literals.run}" width="100" >
<t:pane id="g_44" width="100%" >
<t:rowline id="g_45" />
<t:row id="g_46" >
<t:formattedfield id="g_47" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="false" format="int" value=".{hrLowPrctRun}" width="100%" />
</t:row>
<t:row id="g_48" >
<t:formattedfield id="g_49" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="false" format="int" value=".{hrHighPrctRun}" width="100%" />
</t:row>
<t:rowline id="g_50" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_51" comment="hr bike" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} #{rr.literals.bike}" width="100" >
<t:pane id="g_52" width="100%" >
<t:rowline id="g_53" />
<t:row id="g_54" >
<t:formattedfield id="g_55" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrLowBike}" width="100%" />
</t:row>
<t:row id="g_56" >
<t:formattedfield id="g_57" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrHighBike}" width="100%" />
</t:row>
<t:rowline id="g_58" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_59" comment="hr bike" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} #{rr.literals.bike} (#{rr.literals.target})" width="100" >
<t:pane id="g_60" width="100%" >
<t:rowline id="g_61" />
<t:row id="g_62" >
<t:formattedfield id="g_63" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrTargetLowBike}" width="100%" />
</t:row>
<t:row id="g_64" >
<t:formattedfield id="g_65" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="int" value=".{hrTargetHighBike}" width="100%" />
</t:row>
<t:rowline id="g_66" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_67" comment="hr percent bike" searchenabled="false" sortenabled="false" text="#{rr.literals.hr} % #{rr.literals.bike}" width="100" >
<t:pane id="g_68" width="100%" >
<t:rowline id="g_69" />
<t:row id="g_70" >
<t:formattedfield id="g_71" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="false" format="int" value=".{hrLowPrctBike}" width="100%" />
</t:row>
<t:row id="g_72" >
<t:formattedfield id="g_73" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="false" format="int" value=".{hrHighPrctBike}" width="100%" />
</t:row>
<t:rowline id="g_74" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_75" searchenabled="false" sortenabled="false" text="#{rr.literals.speed} #{rr.literals.swim}" width="180" >
<t:pane id="g_76" width="100%" >
<t:rowline id="g_77" />
<t:row id="g_78" >
<t:formattedfield id="g_79" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedLowSwim}" width="100%" />
</t:row>
<t:row id="g_80" >
<t:formattedfield id="g_81" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="#{d.ZonesDetailUI.enabled}" format="double" value=".{speedHighSwim}" width="100%" />
</t:row>
<t:rowline id="g_82" />
</t:pane>
</t:gridcol>
<t:gridcol id="g_83" searchenabled="false" sortenabled="false" text="#{rr.literals.time} #{rr.literals.swim}" width="100" >
<t:pane id="g_84" width="100%" >
<t:rowline id="g_85" />
<t:row id="g_86" >
<t:field id="g_87" align="right!" bgpaint="write(5,2,#{rr.literals.from},left)" enabled="false" text=".{timeLowSwim}" width="100%" />
</t:row>
<t:row id="g_88" >
<t:field id="g_89" align="right!" bgpaint="write(5,2,#{rr.literals.to},left)" enabled="false" text=".{timeHighSwim}" width="100%" />
</t:row>
<t:rowline id="g_90" />
</t:pane>
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_91" />
<t:rowdynamiccontent id="g_92" contentbinding="#{d.ZonesDetailUI.individualSwimZones}" />
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
