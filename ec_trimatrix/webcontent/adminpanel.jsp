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
<t:row id="g_2" >
<t:tabbedpane id="g_3" width="100%" >
<t:tabbedpanetab id="g_4" text="Session" >
<t:row id="g_5" comment="logged on users" >
<t:pane id="g_6" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_7" text="Benutzer" />
<t:rowheader id="g_8" />
<t:row id="g_9" >
<t:label id="g_10" text="Anzahl angemeldeter Benuter" />
<t:coldistance id="g_11" />
<t:formattedfield id="g_12" align="center" enabled="false" format="int" value="#{d.AdminPanelUI.countUsers}" width="50" />
<t:coldistance id="g_13" />
<t:button id="g_14" actionListener="#{d.AdminPanelUI.onRefresh}" text="Aktualisieren" />
</t:row>
<t:row id="g_15" >
<t:fixgrid id="g_16" objectbinding="#{d.AdminPanelUI.gridLoggedInUsers}" sbvisibleamount="10" width="100%" >
<t:gridcol id="g_17" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_18" />
</t:gridcol>
<t:gridcol id="g_19" align="center" text="Benutzer" width="100" >
<t:field id="g_20" enabled="false" text=".{userName}" width="100" />
</t:gridcol>
<t:gridcol id="g_21" align="center" text="Client IP" width="100" >
<t:field id="g_22" align="center" enabled="false" text=".{clientIP}" width="100" />
</t:gridcol>
<t:gridcol id="g_23" align="center" text="Session ID" width="220" >
<t:field id="g_24" align="center" enabled="false" text=".{sessionID}" />
</t:gridcol>
<t:gridcol id="g_25" align="center" text="Logon Zeitpunkt" width="150" >
<t:formattedfield id="g_26" align="center" enabled="false" format="datetime" formatmask="medium" value=".{creationTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_27" align="center" text="Letzte Aktivität" width="150" >
<t:formattedfield id="g_28" align="center" enabled="false" format="datetime" formatmask="medium" value=".{lastAccessedTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_29" align="center" text="Message" width="50" >
<t:checkbox id="g_30" align="center" enabled="false" selected=".{message}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_31" >
<t:button id="g_32" actionListener="#{d.AdminPanelUI.onInvalidate}" text="Session löschen" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_33" height="15" />
<t:row id="g_34" comment="memory handling" >
<t:pane id="g_35" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_36" text="Speicherverwaltung" />
<t:rowheader id="g_37" />
<t:row id="g_38" >
<t:label id="g_39" text="Verbrauchter Speicher" width="150" />
<t:formattedfield id="g_40" align="right" enabled="false" format="long" value="#{d.AdminPanelUI.usedMemory}" width="100" />
<t:coldistance id="g_41" />
<t:label id="g_42" text="Byte" />
</t:row>
<t:row id="g_43" >
<t:label id="g_44" text="Freier Speicher" width="150" />
<t:formattedfield id="g_45" align="right" enabled="false" format="long" value="#{d.AdminPanelUI.availableMemory}" width="100" />
<t:coldistance id="g_46" />
<t:label id="g_47" text="Byte" />
</t:row>
<t:row id="g_48" >
<t:button id="g_49" actionListener="#{d.AdminPanelUI.onCollectGarbage}" text="Garbage Collection starten" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_50" height="15" />
<t:row id="g_51" comment="message handling" >
<t:pane id="g_52" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_53" text="Meldungsverwaltung" />
<t:rowheader id="g_54" />
<t:row id="g_55" >
<t:label id="g_56" font="size:12;weight:bold" text="Logon Meldung" />
</t:row>
<t:row id="g_57" >
<t:checkbox id="g_58" flush="true" selected="#{d.AdminPanelUI.showLogonMessage}" text="Logon Meldung aktiv" />
<t:textarea id="g_59" enabled="#{d.AdminPanelUI.showLogonMessage==false}" flush="true" height="100" text="#{d.AdminPanelUI.logonMessage}" width="400" />
</t:row>
<t:rowdistance id="g_60" />
<t:rowline id="g_61" />
<t:rowdistance id="g_62" />
<t:row id="g_63" >
<t:label id="g_64" font="size:12;weight:bold" text="Session  Meldung" />
</t:row>
<t:row id="g_65" >
<t:label id="g_66" text="Session" />
<t:coldistance id="g_67" />
<t:combobox id="g_68" validvaluesbinding="#{d.AdminPanelUI.vvbSessionIds}" value="#{d.AdminPanelUI.sessionId}" valuetextmode="VALUE (TEXT)" />
<t:coldistance id="g_69" />
<t:button id="g_70" actionListener="#{d.AdminPanelUI.onSetSessionMessage}" text="Setzen" />
</t:row>
<t:row id="g_71" >
<t:textarea id="g_72" height="100" text="#{d.AdminPanelUI.sessionMessage}" width="400" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_73" height="15" />
<t:row id="g_74" comment="restart workplace" >
<t:pane id="g_75" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_76" text="Workplace Neustart" />
<t:rowheader id="g_77" />
<t:row id="g_78" >
<t:button id="g_79" actionListener="#{d.AdminPanelUI.onRestart}" text="Workplace neu starten (Hard)" />
<t:coldistance id="g_80" />
<t:button id="g_81" actionListener="#{d.AdminPanelUI.onRestartSoft}" text="Workplace neu starten (Soft)" />
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_82" text="Entities" >
<t:row id="g_83" comment="entity locks" >
<t:pane id="g_84" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_85" text="Objektsperren" />
<t:rowheader id="g_86" />
<t:row id="g_87" >
<t:fixgrid id="g_88" objectbinding="#{d.AdminPanelUI.gridLockedEntities}" sbvisibleamount="10" width="100%" >
<t:gridcol id="g_89" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_90" />
</t:gridcol>
<t:gridcol id="g_91" align="center" text="Entity" width="100" >
<t:field id="g_92" align="center" enabled="false" text=".{entity}" />
</t:gridcol>
<t:gridcol id="g_93" align="center" text="ID" width="220" >
<t:field id="g_94" align="center" enabled="false" text=".{id}" width="150" />
</t:gridcol>
<t:gridcol id="g_95" align="center" text="Session ID" width="220" >
<t:field id="g_96" align="center" enabled="false" text=".{sessionId}" />
</t:gridcol>
<t:gridcol id="g_97" align="center" text="Benutzer" width="100" >
<t:field id="g_98" align="center" enabled="false" text=".{user}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_99" >
<t:button id="g_100" actionListener="#{d.AdminPanelUI.onRefreshLocks}" text="Aktualisieren" />
<t:coldistance id="g_101" />
<t:button id="g_102" actionListener="#{d.AdminPanelUI.onUnlockEntity}" text="Sperre löschen" />
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
</t:tabbedpane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
