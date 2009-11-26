<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="logong_sv">
<t:rowbodypane id="g_1" >
<t:rowdistance id="g_2" height="50%" />
<t:row id="g_3" >
<t:coldistance id="g_4" width="50%" />
<t:pane id="g_5" background="#CCCCCC" border="#000000" >
<t:rowdistance id="g_6" height="10" />
<t:row id="g_7" >
<t:coldistance id="g_8" width="10" />
<t:label id="g_9" font="size:14" text="#{rr.literals.logon_user}" width="80" />
<t:coldistance id="g_10" width="10" />
<t:field id="g_11" bgpaint="mandatory()" font="size:14" requestfocus="creation" text="#{d.LogonUI.user}" width="160" />
<t:coldistance id="g_12" width="10" />
</t:row>
<t:rowdistance id="g_13" height="5" />
<t:row id="g_14" >
<t:coldistance id="g_15" width="10" />
<t:label id="g_16" font="size:14" text="#{rr.literals.logon_pass}" width="80" />
<t:coldistance id="g_17" width="10" />
<t:password id="g_18" bgpaint="mandatory()" font="size:14" text="#{d.LogonUI.password}" width="160" />
<t:coldistance id="g_19" width="10" />
</t:row>
<t:rowdistance id="g_20" height="5" />
<t:row id="g_21" >
<t:coldistance id="g_22" width="10" />
<t:label id="g_23" font="size:14" text="#{rr.literals.language}" width="80" />
<t:coldistance id="g_24" width="10" />
<t:combobox id="g_25" actionListener="#{d.LogonUI.onLanguage}" flush="true" font="size:14" validvaluesbinding="#{d.LogonUI.languageVvb}" value="#{d.LogonUI.language}" valuetextmode="TEXT" width="160" />
<t:coldistance id="g_26" width="10" />
</t:row>
<t:rowdistance id="g_27" height="10" />
<t:row id="g_28" >
<t:coldistance id="g_29" width="20" />
<t:button id="g_30" actionListener="#{d.LogonUI.onLogon}" font="size:14" hotkey="10" text="#{rr.literals.logon_logon}" />
<t:coldistance id="g_31" width="20" />
<t:button id="g_32" actionListener="#{d.LogonUI.onPasswordChange}" font="size:14" text="#{rr.literals.logon_changepass}" />
<t:coldistance id="g_33" width="20" />
</t:row>
<t:rowdistance id="g_34" height="10" />
</t:pane>
<t:coldistance id="g_35" width="50%" />
</t:row>
<t:rowdistance id="g_36" height="50%" />
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
