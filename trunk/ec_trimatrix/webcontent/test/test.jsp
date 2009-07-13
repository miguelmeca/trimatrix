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
<t:rowdynamiccontent id="g_4" contentbinding="#{d.TestUI.labelRow}" />
<t:rowdistance id="g_5" height="15" />
<t:row id="g_6" >
<t:foldablepane id="g_7" opened="true" text="Definition" width="100%" >
<t:row id="g_8" >
<t:label id="g_9" text="Name des Athleten" width="150" />
<t:field id="g_10" text="#{d.TestUI.name}" width="200" />
</t:row>
<t:rowdistance id="g_11" />
<t:row id="g_12" >
<t:label id="g_13" text="Email Empfänger (Doktor)" width="150" />
<t:field id="g_14" text="#{d.TestUI.email}" width="200" />
<t:coldistance id="g_15" />
</t:row>
<t:rowdistance id="g_16" height="15" />
<t:row id="g_17" >
<t:button id="g_18" actionListener="#{d.TestUI.onSend}" text="Test senden" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_19" />
<t:row id="g_20" >
<t:foldablepane id="g_21" text="Testprotokoll" width="100%" >
<t:row id="g_22" >
<t:label id="g_23" text="Geschwindigkeit" width="150" />
<t:field id="g_24" text="#{d.TestUI.valuesSpeed}" width="400" />
</t:row>
<t:row id="g_25" >
<t:label id="g_26" text="Herzfrequenz" width="150" />
<t:field id="g_27" text="#{d.TestUI.valuesHr}" width="400" />
</t:row>
<t:rowdistance id="g_28" />
<t:row id="g_29" >
<t:label id="g_30" text="Laktat" width="150" />
<t:field id="g_31" text="#{d.TestUI.valuesLactat}" width="400" />
</t:row>
<t:rowdistance id="g_32" height="15" />
<t:row id="g_33" >
<t:button id="g_34" actionListener="#{d.TestUI.onRefreshProtocol}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_35" />
<t:row id="g_36" >
<t:foldablepane id="g_37" text="Diagramm" width="100%" >
<t:row id="g_38" >
<t:label id="g_39" text="Formel" width="150" />
<t:field id="g_40" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.formula}" width="200" />
</t:row>
<t:rowdistance id="g_41" />
<t:row id="g_42" >
<t:label id="g_43" text="Korrelationsfaktor" width="150" />
<t:field id="g_44" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_45" />
<t:row id="g_46" >
<t:heximage id="g_47" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram}" valign="center" width="400" />
<t:coldistance id="g_48" />
<t:heximage id="g_49" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram2}" valign="center" width="400" />
</t:row>
<t:rowdistance id="g_50" height="15" />
<t:row id="g_51" >
<t:pane id="g_52" >
<t:row id="g_53" >
<t:label id="g_54" text="Geschwindigkeit" width="150" />
<t:field id="g_55" text="#{d.TestUI.speed}" width="50" />
<t:coldistance id="g_56" />
</t:row>
<t:rowdistance id="g_57" />
<t:row id="g_58" >
<t:label id="g_59" text="Laktat" width="150" />
<t:field id="g_60" text="#{d.TestUI.lactat}" width="50" />
</t:row>
<t:rowdistance id="g_61" />
<t:row id="g_62" >
<t:label id="g_63" text="Herzfrequenz" width="150" />
<t:field id="g_64" text="#{d.TestUI.hr}" width="50" />
</t:row>
</t:pane>
<t:button id="g_65" actionListener="#{d.TestUI.onCalculate}" text="Berechne Werte" />
</t:row>
<t:rowdistance id="g_66" height="15" />
<t:row id="g_67" >
<t:button id="g_68" actionListener="#{d.TestUI.onRefreshDiagram}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
