<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="test_testg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:label id="g_3" align="center" font="size:20;weight:bold" text="Test" width="100%" />
</t:row>
<t:row id="g_4" >
<t:button id="g_5" bgpaint="roundedrectangle(0,0,100%,100%,10,10,#FF0000);rectangle(10,0,100%,100%,#FF0000)" stylevariant="WP_ISOLATEDWORKPAGE" text="Trimatrix Session" />
<t:coldistance id="g_6" width="1" />
<t:button id="g_7" actionListener="#{d.TestUI.onLabelDelete}" bgpaint="rectangle(0,0,100%-10,100%,#FF0000);roundedrectangle(0,0,100%,100%,10,10,#FF0000)" font="weight:bold" stylevariant="WP_ISOLATEDWORKPAGE" text="X" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.TestUI.onLabelSearch}" contentareafilled="false" image="/images/icons/magnifier.png" text="Label" />
<t:coldistance id="g_10" />
<t:button id="g_11" actionListener="#{d.TestUI.onRefresh}" text="Refresh" />
</t:row>
<t:rowdynamiccontent id="g_12" contentbinding="#{d.TestUI.labelRow}" />
<t:rowdistance id="g_13" height="15" />
<t:row id="g_14" >
<t:foldablepane id="g_15" opened="true" text="Definition" width="100%" >
<t:row id="g_16" >
<t:label id="g_17" text="Name des Athleten" width="150" />
<t:field id="g_18" text="#{d.TestUI.name}" width="200" />
</t:row>
<t:rowdistance id="g_19" />
<t:row id="g_20" >
<t:label id="g_21" text="Email Empfänger (Doktor)" width="150" />
<t:field id="g_22" text="#{d.TestUI.email}" width="200" />
<t:coldistance id="g_23" />
</t:row>
<t:rowdistance id="g_24" height="15" />
<t:row id="g_25" >
<t:button id="g_26" actionListener="#{d.TestUI.onSend}" text="Test senden" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_27" />
<t:row id="g_28" >
<t:foldablepane id="g_29" text="Testprotokoll" width="100%" >
<t:row id="g_30" >
<t:label id="g_31" text="Geschwindigkeit" width="150" />
<t:field id="g_32" text="#{d.TestUI.valuesSpeed}" width="400" />
</t:row>
<t:row id="g_33" >
<t:label id="g_34" text="Herzfrequenz" width="150" />
<t:field id="g_35" text="#{d.TestUI.valuesHr}" width="400" />
</t:row>
<t:rowdistance id="g_36" />
<t:row id="g_37" >
<t:label id="g_38" text="Laktat" width="150" />
<t:field id="g_39" text="#{d.TestUI.valuesLactat}" width="400" />
</t:row>
<t:rowdistance id="g_40" height="15" />
<t:row id="g_41" >
<t:button id="g_42" actionListener="#{d.TestUI.onRefreshProtocol}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_43" />
<t:row id="g_44" >
<t:foldablepane id="g_45" text="Diagramm" width="100%" >
<t:row id="g_46" >
<t:label id="g_47" text="Formel" width="150" />
<t:field id="g_48" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.formula}" width="200" />
</t:row>
<t:rowdistance id="g_49" />
<t:row id="g_50" >
<t:label id="g_51" text="Korrelationsfaktor" width="150" />
<t:field id="g_52" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_53" />
<t:row id="g_54" >
<t:heximage id="g_55" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram}" valign="center" width="400" />
<t:coldistance id="g_56" />
<t:heximage id="g_57" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram2}" valign="center" width="400" />
</t:row>
<t:rowdistance id="g_58" height="15" />
<t:row id="g_59" >
<t:pane id="g_60" >
<t:row id="g_61" >
<t:label id="g_62" text="Geschwindigkeit" width="150" />
<t:field id="g_63" text="#{d.TestUI.speed}" width="50" />
<t:coldistance id="g_64" />
</t:row>
<t:rowdistance id="g_65" />
<t:row id="g_66" >
<t:label id="g_67" text="Laktat" width="150" />
<t:field id="g_68" text="#{d.TestUI.lactat}" width="50" />
</t:row>
<t:rowdistance id="g_69" />
<t:row id="g_70" >
<t:label id="g_71" text="Herzfrequenz" width="150" />
<t:field id="g_72" text="#{d.TestUI.hr}" width="50" />
</t:row>
</t:pane>
<t:button id="g_73" actionListener="#{d.TestUI.onCalculate}" text="Berechne Werte" />
</t:row>
<t:rowdistance id="g_74" height="15" />
<t:row id="g_75" >
<t:button id="g_76" actionListener="#{d.TestUI.onRefreshDiagram}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
