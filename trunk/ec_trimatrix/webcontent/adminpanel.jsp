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
<t:rowtitlebar id="g_4" text="Benutzer" />
<t:rowheader id="g_5" />
<t:row id="g_6" >
<t:label id="g_7" text="Anzahl angemeldeter Benuter" />
<t:coldistance id="g_8" />
<t:formattedfield id="g_9" format="int" value="#{d.AdminPanelUI.countUsers}" width="100" />
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
<t:gridcol id="g_18" align="center" text="Logon Zeitpunkt" width="150" >
<t:formattedfield id="g_19" align="center" enabled="false" format="datetime" formatmask="medium" value=".{creationTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_20" align="center" text="Letzte Aktivität" width="150" >
<t:formattedfield id="g_21" align="center" enabled="false" format="datetime" formatmask="medium" value=".{lastAccessedTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_22" align="center" text="Client IP" width="100" >
<t:field id="g_23" align="center" enabled="false" text=".{clientIP}" width="100" />
</t:gridcol>
<t:gridcol id="g_24" align="center" text="Session ID" width="100" >
<t:field id="g_25" align="center" enabled="false" text=".{sessionID}" width="150" />
</t:gridcol>
<t:gridcol id="g_26" align="center" text="Message" width="20" >
<t:checkbox id="g_27" align="center" enabled="false" selected=".{message}" width="150" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_28" >
<t:button id="g_29" actionListener="#{d.AdminPanelUI.onInvalidate}" text="Session löschen" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_30" height="15" />
<t:row id="g_31" comment="restart workplace" >
<t:pane id="g_32" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_33" text="Workplace Neustart" />
<t:rowheader id="g_34" />
<t:row id="g_35" >
<t:button id="g_36" actionListener="#{d.AdminPanelUI.onRestart}" text="Workplace neu starten (Hard)" />
<t:coldistance id="g_37" />
<t:button id="g_38" actionListener="#{d.AdminPanelUI.onRestartSoft}" text="Workplace neu starten (Soft)" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_39" height="15" />
<t:row id="g_40" comment="memory handling" >
<t:pane id="g_41" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_42" text="Speicherverwaltung" />
<t:rowheader id="g_43" />
<t:row id="g_44" >
<t:label id="g_45" text="Verbrauchter Speicher" width="150" />
<t:formattedfield id="g_46" enabled="false" format="long" value="#{d.AdminPanelUI.usedMemory}" width="100" />
</t:row>
<t:row id="g_47" >
<t:label id="g_48" text="Freier Speicher" width="150" />
<t:formattedfield id="g_49" enabled="false" format="long" value="#{d.AdminPanelUI.availableMemory}" width="100" />
</t:row>
<t:row id="g_50" >
<t:button id="g_51" actionListener="#{d.AdminPanelUI.onCollectGarbage}" text="Garbage Collection starten" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_52" height="15" />
<t:row id="g_53" comment="message handling" >
<t:pane id="g_54" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_55" text="Meldungsverwaltung" />
<t:rowheader id="g_56" />
<t:row id="g_57" >
<t:checkbox id="g_58" flush="true" selected="#{d.AdminPanelUI.showLogonMessage}" text="Logon Meldung aktiv" width="150" />
<t:textarea id="g_59" enabled="#{d.AdminPanelUI.showLogonMessage==false}" flush="true" height="100" text="#{d.AdminPanelUI.logonMessage}" width="400" />
</t:row>
<t:row id="g_60" >
<t:label id="g_61" text="Session" />
<t:combobox id="g_62" validvaluesbinding="#{d.AdminPanelUI.vvbSessionIds}" value="#{d.AdminPanelUI.sessionId}" width="114" />
<t:textarea id="g_63" height="100" text="#{d.AdminPanelUI.sessionMessage}" width="400" />
<t:coldistance id="g_64" />
<t:button id="g_65" actionListener="#{d.AdminPanelUI.onSetSessionMessage}" text="Setzen" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
