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
<t:field id="g_25" align="center" enabled="false" text=".{sessionID}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_26" >
<t:button id="g_27" actionListener="#{d.AdminPanelUI.onInvalidate}" text="Session löschen" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_28" height="15" />
<t:row id="g_29" comment="restart workplace" >
<t:pane id="g_30" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_31" text="Workplace Neustart" />
<t:rowheader id="g_32" />
<t:row id="g_33" >
<t:button id="g_34" actionListener="#{d.AdminPanelUI.onRestart}" text="Workplace neu starten (Hard)" />
<t:coldistance id="g_35" />
<t:button id="g_36" actionListener="#{d.AdminPanelUI.onRestartSoft}" text="Workplace neu starten (Soft)" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_37" height="15" />
<t:row id="g_38" comment="memory handling" >
<t:pane id="g_39" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_40" text="Speicherverwaltung" />
<t:rowheader id="g_41" />
<t:row id="g_42" >
<t:label id="g_43" text="Verbrauchter Speicher" width="150" />
<t:formattedfield id="g_44" enabled="false" format="long" value="#{d.AdminPanelUI.usedMemory}" width="100" />
</t:row>
<t:row id="g_45" >
<t:label id="g_46" text="Freier Speicher" width="150" />
<t:formattedfield id="g_47" enabled="false" format="long" value="#{d.AdminPanelUI.availableMemory}" width="100" />
</t:row>
<t:row id="g_48" >
<t:button id="g_49" actionListener="#{d.AdminPanelUI.onCollectGarbage}" text="Garbage Collection starten" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
