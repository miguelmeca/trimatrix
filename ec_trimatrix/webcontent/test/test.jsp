<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="test_testg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:pane id="g_3" background="#0000FF30" width="100%" >
<t:row id="g_4" />
</t:pane>
<t:label id="g_5" align="center" font="size:20;weight:bold" text="Test" width="100%" />
<t:button id="g_6" bgpaint="roundedrectangle(0,0,100%,100%,10,10,#FF0000,#FF0000,vertical)" contentareafilled="false" enabled="false" text="      " />
</t:row>
<t:rowdynamiccontent id="g_7" contentbinding="#{d.TestUI.labelRow}" />
<t:rowdistance id="g_8" height="15" />
<t:row id="g_9" >
<t:foldablepane id="g_10" curtaincolor="#FF0000" opened="true" text="Definition" width="100%" >
<t:row id="g_11" >
<t:label id="g_12" text="Name des Athleten" width="150" />
<t:field id="g_13" text="#{d.TestUI.name}" width="200" />
<tx:decspinner id="g_14" value="test" />
</t:row>
<t:rowdistance id="g_15" />
<t:row id="g_16" >
<t:label id="g_17" text="Email Empfänger (Doktor)" width="150" />
<t:field id="g_18" text="#{d.TestUI.email}" width="200" />
<t:coldistance id="g_19" />
</t:row>
<t:rowdistance id="g_20" height="15" />
<t:row id="g_21" >
<t:button id="g_22" actionListener="#{d.TestUI.onSend}" text="Test senden" />
<t:coldistance id="g_23" />
<t:button id="g_24" bgpaint="roundedrectangle(0,0,100%,100%,10,10,#FF0000,#FF0000,vertical)" contentareafilled="false" focusable="false" text="     " tooltip="Farbe ändern" />
<t:button id="g_25" contentareafilled="false" text="Bestellung" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_26" />
<t:row id="g_27" >
<t:foldablepane id="g_28" curtaincolor="#00FF00" foreground="#0000FF" headerbackground="#00FF00" text="Testprotokoll" width="100%" >
<t:row id="g_29" >
<t:label id="g_30" text="Geschwindigkeit" width="150" />
<t:field id="g_31" text="#{d.TestUI.valuesSpeed}" width="400" />
</t:row>
<t:row id="g_32" >
<t:label id="g_33" text="Herzfrequenz" width="150" />
<t:field id="g_34" text="#{d.TestUI.valuesHr}" width="400" />
</t:row>
<t:rowdistance id="g_35" />
<t:row id="g_36" >
<t:label id="g_37" text="Laktat" width="150" />
<t:field id="g_38" text="#{d.TestUI.valuesLactat}" width="400" />
</t:row>
<t:rowdistance id="g_39" height="15" />
<t:row id="g_40" >
<t:button id="g_41" actionListener="#{d.TestUI.onRefreshProtocol}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
<t:rowdistance id="g_42" />
<t:row id="g_43" >
<t:foldablepane id="g_44" text="Diagramm" width="100%" >
<t:row id="g_45" >
<t:label id="g_46" text="Formel" width="150" />
<t:field id="g_47" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.formula}" width="200" />
</t:row>
<t:rowdistance id="g_48" />
<t:row id="g_49" >
<t:label id="g_50" text="Korrelationsfaktor" width="150" />
<t:field id="g_51" border="top:0;bottom:0;left:0;right:0" enabled="false" text="#{d.TestUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_52" />
<t:row id="g_53" >
<t:heximage id="g_54" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram}" valign="center" width="400" />
<t:coldistance id="g_55" />
<t:heximage id="g_56" align="center" border="#808080" height="300" hexdata="#{d.TestUI.diagram2}" valign="center" width="400" />
</t:row>
<t:rowdistance id="g_57" height="15" />
<t:row id="g_58" >
<t:pane id="g_59" >
<t:row id="g_60" >
<t:label id="g_61" text="Geschwindigkeit" width="150" />
<t:field id="g_62" text="#{d.TestUI.speed}" width="50" />
<t:coldistance id="g_63" />
</t:row>
<t:rowdistance id="g_64" />
<t:row id="g_65" >
<t:label id="g_66" text="Laktat" width="150" />
<t:field id="g_67" text="#{d.TestUI.lactat}" width="50" />
</t:row>
<t:rowdistance id="g_68" />
<t:row id="g_69" >
<t:label id="g_70" text="Herzfrequenz" width="150" />
<t:field id="g_71" text="#{d.TestUI.hr}" width="50" />
</t:row>
</t:pane>
<t:button id="g_72" actionListener="#{d.TestUI.onCalculate}" text="Berechne Werte" />
</t:row>
<t:rowdistance id="g_73" height="15" />
<t:row id="g_74" >
<t:button id="g_75" actionListener="#{d.TestUI.onRefreshDiagram}" text="Neuladen" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
