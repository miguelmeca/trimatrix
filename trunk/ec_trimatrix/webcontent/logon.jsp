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
<t:coldistance id="g_4" width="40%" />
<t:image id="g_5" align="center" image="/images/trimatrix.png" />
<t:coldistance id="g_6" />
<t:pane id="g_7" background="#CCCCCC50" border="#000000" >
<t:rowdistance id="g_8" height="10" />
<t:row id="g_9" >
<t:coldistance id="g_10" width="10" />
<t:label id="g_11" font="size:14" text="#{rr.literals.logon_user}" width="100" />
<t:coldistance id="g_12" width="10" />
<t:field id="g_13" bgpaint="mandatory()" font="size:14" requestfocus="creation" text="#{d.LogonUI.user}" width="160" />
<t:coldistance id="g_14" width="10" />
</t:row>
<t:rowdistance id="g_15" height="5" />
<t:row id="g_16" >
<t:coldistance id="g_17" width="10" />
<t:label id="g_18" font="size:14" text="#{rr.literals.logon_pass}" width="100" />
<t:coldistance id="g_19" width="10" />
<t:password id="g_20" bgpaint="mandatory()" font="size:14" text="#{d.LogonUI.password}" width="160" />
<t:coldistance id="g_21" width="10" />
</t:row>
<t:rowdistance id="g_22" height="5" />
<t:row id="g_23" >
<t:coldistance id="g_24" width="10" />
<t:label id="g_25" font="size:14" text="#{rr.literals.language}" width="100" />
<t:coldistance id="g_26" width="10" />
<t:combobox id="g_27" actionListener="#{d.LogonUI.onLanguage}" flush="true" font="size:14" validvaluesbinding="#{d.LogonUI.languageVvb}" value="#{d.LogonUI.language}" valuetextmode="TEXT" width="160" />
<t:coldistance id="g_28" width="10" />
</t:row>
<t:rowdistance id="g_29" height="10" />
<t:row id="g_30" >
<t:coldistance id="g_31" width="50%" />
<t:button id="g_32" actionListener="#{d.LogonUI.onLogon}" font="size:14" hotkey="10" text="#{rr.literals.logon_logon}" width="130" />
<t:coldistance id="g_33" />
<t:button id="g_34" actionListener="#{d.LogonUI.onPasswordChange}" font="size:14" text="#{rr.literals.logon_changepass}" width="130" />
<t:coldistance id="g_35" width="5%" />
</t:row>
<t:rowdistance id="g_36" height="10" />
</t:pane>
<t:coldistance id="g_37" width="60%" />
</t:row>
<t:rowdistance id="g_38" height="50%" />
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
