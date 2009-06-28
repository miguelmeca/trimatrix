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
<t:button id="g_7" bgpaint="rectangle(0,0,100%-10,100%,#FF0000);roundedrectangle(0,0,100%,100%,10,10,#FF0000)" font="weight:bold" stylevariant="WP_ISOLATEDWORKPAGE" text="X" />
</t:row>
<t:rowdistance id="g_8" height="15" />
<t:row id="g_9" >
<t:foldablepane id="g_10" opened="true" text="Definition" width="100%" >
<t:row id="g_11" >
<t:label id="g_12" text="Name des Athleten" width="150" />
<t:field id="g_13" text="#{d.TestUI.name}" width="200" />
</t:row>
<t:rowdistance id="g_14" />
<t:row id="g_15" >
<t:label id="g_16" text="Email Empfänger (Doktor)" width="150" />
<t:field id="g_17" text="#{d.TestUI.email}" width="200" />
<t:coldistance id="g_18" />
</t:row>
<t:rowdistance id="g_19" height="15" />
<t:row id="g_20" >
<t:button id="g_21" actionListener="#{d.TestUI.onSend}" text="Test senden" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_22" />
<t:row id="g_23" >
<t:foldablepane id="g_24" text="Testprotokoll" width="100%" >
<t:row id="g_25" >
<t:label id="g_26" text="Geschwindigkeit" width="150" />
<t:field id="g_27" text="#{d.TestUI.valuesSpeed}" width="400" />
</t:row>
<t:row id="g_28" >
<t:label id="g_29" text="Herzfrequenz" width="150" />
<t:field id="g_30" text="#{d.TestUI.valuesHr}" width="400" />
</t:row>
<t:rowdistance id="g_31" />
<t:row id="g_32" >
<t:label id="g_33" text="Laktat" width="150" />
<t:field id="g_34" text="#{d.TestUI.valuesLactat}" width="400" />
</t:row>
<t:rowdistance id="g_35" height="15" />
<t:row id="g_36" >
<t:button id="g_37" actionListener="#{d.TestUI.onRefreshProtocol}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_38" />
<t:row id="g_39" >
<t:foldablepane id="g_40" text="Diagramm" width="100%" >
<t:row id="g_41" >
<t:label id="g_42" text="Formel" width="150" />
<t:field id="g_43" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.formula}" width="200" />
</t:row>
<t:rowdistance id="g_44" />
<t:row id="g_45" >
<t:label id="g_46" text="Korrelationsfaktor" width="150" />
<t:field id="g_47" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_48" />
<t:row id="g_49" >
<t:heximage id="g_50" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram}" valign="center" width="400" />
<t:coldistance id="g_51" />
<t:heximage id="g_52" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram2}" valign="center" width="400" />
</t:row>
<t:rowdistance id="g_53" height="15" />
<t:row id="g_54" >
<t:pane id="g_55" >
<t:row id="g_56" >
<t:label id="g_57" text="Geschwindigkeit" width="150" />
<t:field id="g_58" text="#{d.TestUI.speed}" width="50" />
<t:coldistance id="g_59" />
</t:row>
<t:rowdistance id="g_60" />
<t:row id="g_61" >
<t:label id="g_62" text="Laktat" width="150" />
<t:field id="g_63" text="#{d.TestUI.lactat}" width="50" />
</t:row>
<t:rowdistance id="g_64" />
<t:row id="g_65" >
<t:label id="g_66" text="Herzfrequenz" width="150" />
<t:field id="g_67" text="#{d.TestUI.hr}" width="50" />
</t:row>
</t:pane>
<t:button id="g_68" actionListener="#{d.TestUI.onCalculate}" text="Berechne Werte" />
</t:row>
<t:rowdistance id="g_69" height="15" />
<t:row id="g_70" >
<t:button id="g_71" actionListener="#{d.TestUI.onRefreshDiagram}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
