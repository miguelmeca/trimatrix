<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="userdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" height="20" text="#{rr.literals.user_user_name}" width="100" />
<t:field id="g_4" attributemacro="entityDetailMacro(UserDetailUI,user_name)" width="200" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.UserDetailUI.onSendGeneratedPassword}" enabled="#{d.UserDetailUI.notEnabled}" height="20" text="#{rr.literals.user_send_gen_pw}" width="155" x="225" y="30" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" height="20" text="#{rr.literals.language}" width="100" x="20" y="60" />
<t:combobox id="g_9" attributemacro="entityDetailMacro(UserDetailUI,language)" height="20" validvaluesbinding="#{d.UserDetailUI.languagesVvb}" valuetextmode="TEXT" width="100" x="115" y="60" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" height="20" text="#{rr.literals.user_person}" width="100" x="20" y="90" />
<t:field id="g_12" actionListener="#{d.UserDetailUI.onPersonClicked}" attributemacro="entityDetailMacro(UserDetailUI,person)" enabled="false" focusable="false" width="200" x="115" y="90" />
<t:coldistance id="g_13" />
<t:button id="g_14" actionListener="#{d.UserDetailUI.onPersonSearch}" enabled="#{d.UserDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" x="225" y="90" />
<t:coldistance id="g_15" />
<t:button id="g_16" actionListener="#{d.UserDetailUI.onPersonRemove}" enabled="#{d.UserDetailUI.enabled}" height="20" text="#{rr.literals.delete}" width="60" x="290" y="90" />
</t:row>
<t:row id="g_17" >
<t:label id="g_18" height="20" text="#{rr.literals.user_email}" width="100" x="20" y="120" />
<t:field id="g_19" attributemacro="entityDetailMacro(UserDetailUI,email)" width="200" x="115" y="120" />
</t:row>
<t:row id="g_20" >
<t:label id="g_21" height="20" text="#{rr.literals.user_active}" width="100" x="20" y="150" />
<t:checkbox id="g_22" align="center" enabled="false" height="20" selected="#{d.UserDetailUI.values.active}" x="115" y="150" />
</t:row>
<t:row id="g_23" >
<t:label id="g_24" height="20" text="#{rr.literals.user_locked}" width="100" x="20" y="180" />
<t:checkbox id="g_25" align="center" enabled="false" height="20" selected="#{d.UserDetailUI.values.locked}" x="115" y="180" />
</t:row>
<t:row id="g_26" >
<t:label id="g_27" height="20" text="#{rr.literals.user_initial}" width="100" />
<t:checkbox id="g_28" align="center" enabled="false" height="20" selected="#{d.UserDetailUI.values.initial}" />
</t:row>
<t:rowdistance id="g_29" height="25" />
<t:row id="g_30" >
<t:foldablepane id="g_31" text="#{rr.literals.roles}" width="100%" >
<t:row id="g_32" >
<t:label id="g_33" height="20" text="#{rr.literals.role_admin}" width="100" />
<t:checkbox id="g_34" align="center" enabled="#{d.UserDetailUI.enabled}" height="20" selected="#{d.UserDetailUI.admin}" />
</t:row>
<t:row id="g_35" >
<t:label id="g_36" height="20" text="#{rr.literals.role_trainer}" width="100" />
<t:checkbox id="g_37" align="center" enabled="#{d.UserDetailUI.enabled}" height="20" selected="#{d.UserDetailUI.coach}" />
</t:row>
<t:row id="g_38" >
<t:label id="g_39" height="20" text="#{rr.literals.role_athlet}" width="100" />
<t:checkbox id="g_40" align="center" enabled="#{d.UserDetailUI.enabled}" height="20" selected="#{d.UserDetailUI.athlete}" />
</t:row>
<t:row id="g_41" >
<t:label id="g_42" height="20" text="#{rr.literals.role_scouter}" width="100" />
<t:checkbox id="g_43" align="center" enabled="#{d.UserDetailUI.enabled}" height="20" selected="#{d.UserDetailUI.scouter}" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
