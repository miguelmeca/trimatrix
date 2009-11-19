<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="passwordg_sv">
<t:rowbodypane id="g_1" >
<t:rowdistance id="g_2" height="50%" />
<t:row id="g_3" >
<t:coldistance id="g_4" width="50%" />
<t:pane id="g_5" background="#CCCCCC" border="#808080" >
<t:rowdistance id="g_6" height="10" />
<t:rowdistance id="g_7" height="5" />
<t:row id="g_8" >
<t:coldistance id="g_9" width="10" />
<t:label id="g_10" text="#{rr.literals.logon_newpass}" width="80" />
<t:coldistance id="g_11" width="10" />
<t:password id="g_12" bgpaint="mandatory()" text="#{d.PasswordUI.newPassword}" width="130" />
<t:coldistance id="g_13" width="10" />
</t:row>
<t:rowdistance id="g_14" height="5" />
<t:row id="g_15" >
<t:coldistance id="g_16" width="10" />
<t:label id="g_17" text="#{rr.literals.logon_newpass}" width="80" />
<t:coldistance id="g_18" width="10" />
<t:password id="g_19" bgpaint="mandatory()" text="#{d.PasswordUI.newPassword2}" width="130" />
<t:coldistance id="g_20" width="10" />
</t:row>
<t:rowdistance id="g_21" height="10" />
<t:row id="g_22" >
<t:coldistance id="g_23" width="20" />
<t:button id="g_24" actionListener="#{d.PasswordUI.onChange}" text="#{rr.literals.logon_changepass}" />
<t:coldistance id="g_25" width="20" />
<t:coldistance id="g_26" width="20" />
</t:row>
<t:rowdistance id="g_27" height="10" />
</t:pane>
<t:coldistance id="g_28" width="50%" />
</t:row>
<t:rowdistance id="g_29" height="50%" />
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
