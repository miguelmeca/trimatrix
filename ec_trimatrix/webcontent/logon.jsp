<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="logong_sv">
<t:rowbodypane id="g_1" >
<t:rowdistance id="g_2" height="50%" />
<t:row id="g_3" >
<tx:decspinner id="g_4" />
<t:coldistance id="g_5" width="40%" />
<t:image id="g_6" align="center" image="/images/trimatrix.png" />
<t:coldistance id="g_7" />
<t:pane id="g_8" background="#CCCCCC50" border="#000000" >
<t:rowdistance id="g_9" height="10" />
<t:row id="g_10" >
<t:coldistance id="g_11" width="10" />
<t:label id="g_12" font="size:14" text="#{rr.literals.logon_user}" width="100" />
<t:coldistance id="g_13" width="10" />
<t:field id="g_14" bgpaint="mandatory()" font="size:14" requestfocus="creation" text="#{d.LogonUI.user}" width="160" />
<t:coldistance id="g_15" width="10" />
</t:row>
<t:rowdistance id="g_16" height="5" />
<t:row id="g_17" >
<t:coldistance id="g_18" width="10" />
<t:label id="g_19" font="size:14" text="#{rr.literals.logon_pass}" width="100" />
<t:coldistance id="g_20" width="10" />
<t:password id="g_21" bgpaint="mandatory()" font="size:14" text="#{d.LogonUI.password}" width="160" />
<t:coldistance id="g_22" width="10" />
</t:row>
<t:rowdistance id="g_23" height="5" />
<t:row id="g_24" >
<t:coldistance id="g_25" width="10" />
<t:label id="g_26" font="size:14" text="#{rr.literals.language}" width="100" />
<t:coldistance id="g_27" width="10" />
<t:combobox id="g_28" actionListener="#{d.LogonUI.onLanguage}" flush="true" font="size:14" validvaluesbinding="#{d.LogonUI.languageVvb}" value="#{d.LogonUI.language}" valuetextmode="TEXT" width="160" />
<t:coldistance id="g_29" width="10" />
</t:row>
<t:rowdistance id="g_30" height="10" />
<t:row id="g_31" >
<t:coldistance id="g_32" width="50%" />
<t:button id="g_33" actionListener="#{d.LogonUI.onLogon}" font="size:14" hotkey="10" text="#{rr.literals.logon_logon}" width="130" />
<t:coldistance id="g_34" />
<t:button id="g_35" actionListener="#{d.LogonUI.onPasswordChange}" font="size:14" text="#{rr.literals.logon_changepass}" width="130" />
<t:coldistance id="g_36" width="5%" />
</t:row>
<t:rowdistance id="g_37" height="10" />
</t:pane>
<t:coldistance id="g_38" width="60%" />
</t:row>
<t:rowdistance id="g_39" height="50%" />
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
