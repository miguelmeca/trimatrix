<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="adminpanelg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:tabbedpane id="g_3" width="100%" >
<t:tabbedpanetab id="g_4" text="#{rr.literals.session}" >
<t:row id="g_5" comment="logged on users" >
<t:pane id="g_6" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_7" text="#{rr.literals.logon_user}" />
<t:rowheader id="g_8" />
<t:row id="g_9" >
<t:label id="g_10" text="#{rr.literals.logged_in_users}" />
<t:coldistance id="g_11" />
<t:formattedfield id="g_12" align="center" enabled="false" format="int" value="#{d.AdminPanelUI.countUsers}" width="50" />
<t:coldistance id="g_13" />
<t:button id="g_14" actionListener="#{d.AdminPanelUI.onRefresh}" text="#{rr.literals.list_refresh}" />
</t:row>
<t:row id="g_15" >
<t:fixgrid id="g_16" horizontalscrollmode="hidden" objectbinding="#{d.AdminPanelUI.gridLoggedInUsers}" sbvisibleamount="10" showemptyrows="false" width="100%" >
<t:gridcol id="g_17" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_18" />
</t:gridcol>
<t:gridcol id="g_19" align="center" text="#{rr.literals.logon_user}" width="100" >
<t:field id="g_20" enabled="false" text=".{userName}" width="100" />
</t:gridcol>
<t:gridcol id="g_21" align="center" text="#{rr.literals.client_ip}" width="100" >
<t:field id="g_22" align="center" enabled="false" text=".{clientIP}" width="100" />
</t:gridcol>
<t:gridcol id="g_23" align="center" text="#{rr.literals.session_id}" width="220" >
<t:field id="g_24" align="center" enabled="false" text=".{sessionID}" />
</t:gridcol>
<t:gridcol id="g_25" align="center" text="#{rr.literals.logon_timestamp}" width="150" >
<t:formattedfield id="g_26" align="center" enabled="false" format="datetime" formatmask="medium" value=".{creationTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_27" align="center" text="#{rr.literals.last_activity}" width="150" >
<t:formattedfield id="g_28" align="center" enabled="false" format="datetime" formatmask="medium" value=".{lastAccessedTime}" width="100" />
</t:gridcol>
<t:gridcol id="g_29" align="center" text="#{rr.literals.message}" width="50" >
<t:checkbox id="g_30" align="center" enabled="false" selected=".{message}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_31" >
<t:button id="g_32" actionListener="#{d.AdminPanelUI.onInvalidate}" text="#{rr.literals.delete_session}" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_33" height="15" />
<t:row id="g_34" comment="memory handling" >
<t:pane id="g_35" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_36" text="#{rr.literals.memory_mgt}" />
<t:rowheader id="g_37" />
<t:row id="g_38" >
<t:label id="g_39" text="#{rr.literals.used_memory}" width="150" />
<t:formattedfield id="g_40" align="right" enabled="false" format="long" value="#{d.AdminPanelUI.usedMemory}" width="100" />
<t:coldistance id="g_41" />
<t:label id="g_42" text="Byte" />
</t:row>
<t:row id="g_43" >
<t:label id="g_44" text="#{rr.literals.free_memory}" width="150" />
<t:formattedfield id="g_45" align="right" enabled="false" format="long" value="#{d.AdminPanelUI.availableMemory}" width="100" />
<t:coldistance id="g_46" />
<t:label id="g_47" text="Byte" />
</t:row>
<t:row id="g_48" >
<t:button id="g_49" actionListener="#{d.AdminPanelUI.onCollectGarbage}" text="#{rr.literals.start_gc}" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_50" height="15" />
<t:row id="g_51" comment="message handling" >
<t:pane id="g_52" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_53" text="#{rr.literals.message_mgt}" />
<t:rowheader id="g_54" />
<t:row id="g_55" >
<t:pane id="g_56" rowalignmenty="top" rowdistance="5" >
<t:row id="g_57" >
<t:label id="g_58" font="size:12;weight:bold" text="#{rr.literals.logon_message}" />
</t:row>
<t:row id="g_59" >
<t:checkbox id="g_60" flush="true" selected="#{d.AdminPanelUI.showLogonMessage}" text="#{rr.literals.logon_message_active}" />
</t:row>
<t:row id="g_61" >
<t:textarea id="g_62" enabled="#{d.AdminPanelUI.showLogonMessage==false}" flush="true" height="100" text="#{d.AdminPanelUI.logonMessage}" width="400" />
</t:row>
</t:pane>
<t:coldistance id="g_63" width="20" />
<t:pane id="g_64" rowalignmenty="top" rowdistance="5" >
<t:row id="g_65" >
<t:label id="g_66" font="size:12;weight:bold" text="#{rr.literals.session_message}" />
</t:row>
<t:row id="g_67" >
<t:label id="g_68" text="#{rr.literals.session}" />
<t:coldistance id="g_69" />
<t:combobox id="g_70" validvaluesbinding="#{d.AdminPanelUI.vvbSessionIds}" value="#{d.AdminPanelUI.sessionId}" valuetextmode="VALUE (TEXT)" />
<t:coldistance id="g_71" />
<t:button id="g_72" actionListener="#{d.AdminPanelUI.onSetSessionMessage}" text="#{rr.literals.set}" />
</t:row>
<t:row id="g_73" >
<t:textarea id="g_74" height="100" text="#{d.AdminPanelUI.sessionMessage}" width="400" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_75" height="15" />
<t:row id="g_76" comment="restart workplace" >
<t:pane id="g_77" height="100%" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_78" text="#{rr.literals.workplace_restart}" />
<t:rowheader id="g_79" />
<t:row id="g_80" >
<t:button id="g_81" actionListener="#{d.AdminPanelUI.onRestart}" text="#{rr.literals.workplace_restart} (Hard)" />
<t:coldistance id="g_82" />
<t:button id="g_83" actionListener="#{d.AdminPanelUI.onRestartSoft}" text="#{rr.literals.workplace_restart} (Soft)" />
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_84" text="#{rr.literals.entities}" >
<t:row id="g_85" comment="entity locks" >
<t:pane id="g_86" rowdistance="5" width="100%" >
<t:rowtitlebar id="g_87" text="#{rr.literals.object_locks}" />
<t:rowheader id="g_88" />
<t:row id="g_89" >
<t:fixgrid id="g_90" horizontalscrollmode="hidden" objectbinding="#{d.AdminPanelUI.gridLockedEntities}" sbvisibleamount="10" showemptyrows="false" width="100%" >
<t:gridcol id="g_91" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_92" />
</t:gridcol>
<t:gridcol id="g_93" align="center" text="#{rr.literals.entity}" width="100" >
<t:field id="g_94" align="center" enabled="false" text=".{entity}" />
</t:gridcol>
<t:gridcol id="g_95" align="center" text="ID" width="220" >
<t:field id="g_96" align="center" enabled="false" text=".{id}" width="150" />
</t:gridcol>
<t:gridcol id="g_97" align="center" text="#{rr.literals.session}" width="220" >
<t:field id="g_98" align="center" enabled="false" text=".{sessionId}" />
</t:gridcol>
<t:gridcol id="g_99" align="center" text="#{rr.literals.logon_user}" width="100" >
<t:field id="g_100" align="center" enabled="false" text=".{user}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_101" >
<t:button id="g_102" actionListener="#{d.AdminPanelUI.onRefreshLocks}" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_103" />
<t:button id="g_104" actionListener="#{d.AdminPanelUI.onUnlockEntity}" text="#{rr.literals.delete_lock}" />
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
