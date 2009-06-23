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
<t:rowdistance id="g_4" height="15" />
<t:row id="g_5" >
<t:foldablepane id="g_6" opened="true" text="Definition" width="100%" >
<t:row id="g_7" >
<t:label id="g_8" text="Name des Athleten" width="150" />
<t:field id="g_9" text="#{d.TestUI.name}" width="200" />
</t:row>
<t:rowdistance id="g_10" />
<t:row id="g_11" >
<t:label id="g_12" text="Email Empfänger (Doktor)" width="150" />
<t:field id="g_13" text="#{d.TestUI.email}" width="200" />
<t:coldistance id="g_14" />
</t:row>
<t:rowdistance id="g_15" height="15" />
<t:row id="g_16" >
<t:button id="g_17" actionListener="#{d.TestUI.onSend}" text="Test senden" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_18" />
<t:row id="g_19" >
<t:foldablepane id="g_20" text="Testprotokoll" width="100%" >
<t:row id="g_21" >
<t:label id="g_22" text="Geschwindigkeit" width="150" />
<t:field id="g_23" text="#{d.TestUI.valuesSpeed}" width="400" />
</t:row>
<t:row id="g_24" >
<t:label id="g_25" text="Herzfrequenz" width="150" />
<t:field id="g_26" text="#{d.TestUI.valuesHr}" width="400" />
</t:row>
<t:rowdistance id="g_27" />
<t:row id="g_28" >
<t:label id="g_29" text="Laktat" width="150" />
<t:field id="g_30" text="#{d.TestUI.valuesLactat}" width="400" />
</t:row>
<t:rowdistance id="g_31" height="15" />
<t:row id="g_32" >
<t:button id="g_33" actionListener="#{d.TestUI.onRefreshProtocol}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_34" />
<t:row id="g_35" >
<t:foldablepane id="g_36" text="Diagramm" width="100%" >
<t:row id="g_37" >
<t:label id="g_38" text="Formel" width="150" />
<t:field id="g_39" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.formula}" width="200" />
</t:row>
<t:rowdistance id="g_40" />
<t:row id="g_41" >
<t:label id="g_42" text="Korrelationsfaktor" width="150" />
<t:field id="g_43" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_44" />
<t:row id="g_45" >
<t:heximage id="g_46" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram}" valign="center" width="400" />
<t:coldistance id="g_47" />
<t:heximage id="g_48" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram2}" valign="center" width="400" />
</t:row>
<t:rowdistance id="g_49" height="15" />
<t:row id="g_50" >
<t:pane id="g_51" >
<t:row id="g_52" >
<t:label id="g_53" text="Geschwindigkeit" width="150" />
<t:field id="g_54" text="#{d.TestUI.speed}" width="50" />
<t:coldistance id="g_55" />
</t:row>
<t:rowdistance id="g_56" />
<t:row id="g_57" >
<t:label id="g_58" text="Laktat" width="150" />
<t:field id="g_59" text="#{d.TestUI.lactat}" width="50" />
</t:row>
<t:rowdistance id="g_60" />
<t:row id="g_61" >
<t:label id="g_62" text="Herzfrequenz" width="150" />
<t:field id="g_63" text="#{d.TestUI.hr}" width="50" />
</t:row>
</t:pane>
<t:button id="g_64" actionListener="#{d.TestUI.onCalculate}" text="Berechne Werte" />
</t:row>
<t:rowdistance id="g_65" height="15" />
<t:row id="g_66" >
<t:label id="g_67" text="Offset für Berechnung" width="150" />
<t:field id="g_68" regex="[0123456789.]+" regexmode="1" text="#{d.TestUI.offset}" width="50" />
<t:coldistance id="g_69" />
<t:button id="g_70" actionListener="#{d.TestUI.onRefreshDiagram2}" text="Neuladen" />
<t:coldistance id="g_71" />
<t:button id="g_72" actionListener="#{d.TestUI.onRefreshDiagram}" text="Neuladen Fixwerte" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
