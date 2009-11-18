<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="adminpanelg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" comment="logged on users" >
<t:pane id="g_3" rowdistance="5" width="100%" >
<t:rowheader id="g_4" />
<t:rowtitlebar id="g_5" text="Benutzer" />
<t:row id="g_6" >
<t:label id="g_7" text="Anzahl angemeldeter Benuter" />
<t:coldistance id="g_8" />
<t:formattedfield id="g_9" align="center" enabled="false" format="int" value="#{d.AdminPanelUI.countUsers}" width="50" />
<t:coldistance id="g_10" />
<t:button id="g_11" actionListener="#{d.AdminPanelUI.onRefresh}" text="Aktualisieren" />
</t:row>
<t:row id="g_12" >
<t:fixgrid id="g_13" objectbinding="#{d.AdminPanelUI.gridLoggedInUsers}" sbvisibleamount="10" width="100%" >
<t:gridcol id="g_14" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_15" />
</t:gridcol>
<t:gridcol id="g_16" align="center" text="Benutzer" width="100" >
<t:field id="g_17" enabled="false" text=".{userName}" width="100" />
</t:gridcol>
<t:gridcol id="g_18" align="center" text="Client IP" width="100" >
<t:field id="g_19" align="center" enabled="false" text=".{clientIP}" width="100" />
</t:gridcol>
<t:gridcol id="g_20" align="center" text="Session ID" width="100" >
<t:field id="g_21" align="center" enabled="false" text=".{sessionID}" width="150" />
</t:gridcol>
<t:gridcol id="g_22" align="center" text="Logon Zeitpunkt" width="150" >
<t:formattedfield id="g_23" align="center" enabled="false" format="datetime" formatmask="medium" value=".{creationTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_24" align="center" text="Letzte Aktivität" width="150" >
<t:formattedfield id="g_25" align="center" enabled="false" format="datetime" formatmask="medium" value=".{lastAccessedTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_26" align="center" text="Message" width="50" >
<t:checkbox id="g_27" align="center" enabled="false" selected=".{message}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_28" >
<t:button id="g_29" actionListener="#{d.AdminPanelUI.onInvalidate}" text="Session löschen" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_30" height="15" />
<t:row id="g_31" comment="memory handling" >
<t:pane id="g_32" height="100%" rowdistance="5" width="100%" >
<t:rowheader id="g_33" />
<t:rowtitlebar id="g_34" text="Speicherverwaltung" />
<t:row id="g_35" >
<t:label id="g_36" text="Verbrauchter Speicher" width="150" />
<t:formattedfield id="g_37" align="right" enabled="false" format="long" value="#{d.AdminPanelUI.usedMemory}" width="100" />
<t:coldistance id="g_38" />
<t:label id="g_39" text="Byte" />
</t:row>
<t:row id="g_40" >
<t:label id="g_41" text="Freier Speicher" width="150" />
<t:formattedfield id="g_42" align="right" enabled="false" format="long" value="#{d.AdminPanelUI.availableMemory}" width="100" />
<t:coldistance id="g_43" />
<t:label id="g_44" text="Byte" />
</t:row>
<t:row id="g_45" >
<t:button id="g_46" actionListener="#{d.AdminPanelUI.onCollectGarbage}" text="Garbage Collection starten" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_47" height="15" />
<t:row id="g_48" comment="message handling" >
<t:pane id="g_49" height="100%" rowdistance="5" width="100%" >
<t:rowheader id="g_50" />
<t:rowtitlebar id="g_51" text="Meldungsverwaltung" />
<t:row id="g_52" >
<t:label id="g_53" font="size:12;weight:bold" text="Logon Meldung" />
</t:row>
<t:row id="g_54" >
<t:checkbox id="g_55" flush="true" selected="#{d.AdminPanelUI.showLogonMessage}" text="Logon Meldung aktiv" />
<t:textarea id="g_56" enabled="#{d.AdminPanelUI.showLogonMessage==false}" flush="true" height="100" text="#{d.AdminPanelUI.logonMessage}" width="400" />
</t:row>
<t:rowdistance id="g_57" />
<t:rowline id="g_58" />
<t:rowdistance id="g_59" />
<t:row id="g_60" >
<t:label id="g_61" font="size:12;weight:bold" text="Session  Meldung" />
</t:row>
<t:row id="g_62" >
<t:label id="g_63" text="Session" />
<t:coldistance id="g_64" />
<t:combobox id="g_65" validvaluesbinding="#{d.AdminPanelUI.vvbSessionIds}" value="#{d.AdminPanelUI.sessionId}" valuetextmode="VALUE (TEXT)" />
<t:coldistance id="g_66" />
<t:button id="g_67" actionListener="#{d.AdminPanelUI.onSetSessionMessage}" text="Setzen" />
</t:row>
<t:row id="g_68" >
<t:textarea id="g_69" height="100" text="#{d.AdminPanelUI.sessionMessage}" width="400" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_70" height="15" />
<t:row id="g_71" comment="restart workplace" >
<t:pane id="g_72" height="100%" rowdistance="5" width="100%" >
<t:rowheader id="g_73" />
<t:rowtitlebar id="g_74" text="Workplace Neustart" />
<t:row id="g_75" >
<t:button id="g_76" actionListener="#{d.AdminPanelUI.onRestart}" text="Workplace neu starten (Hard)" />
<t:coldistance id="g_77" />
<t:button id="g_78" actionListener="#{d.AdminPanelUI.onRestartSoft}" text="Workplace neu starten (Soft)" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
