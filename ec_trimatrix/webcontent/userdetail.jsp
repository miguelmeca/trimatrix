<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="userdetailg_sv">
<t:row id="g_1" >
<t:paintarea id="g_2" height="100%" width="100%" >
<t:label id="g_3" height="20" text="#{rr.literals.user_user_name}" width="85" x="20" y="30" />
<t:field id="g_4" attributemacro="entityDetailMacro(UserDetailUI,user_name)" width="100" x="115" y="30" />
<t:button id="g_5" actionListener="#{d.UserDetailUI.onSendGeneratedPassword}" enabled="#{d.UserDetailUI.notEnabled}" height="20" text="#{rr.literals.user_send_gen_pw}" width="155" x="225" y="30" />
<t:label id="g_6" height="20" text="#{rr.literals.language}" width="85" x="20" y="60" />
<t:combobox id="g_7" attributemacro="entityDetailMacro(UserDetailUI,language)" height="20" validvaluesbinding="#{d.UserDetailUI.languagesVvb}" valuetextmode="TEXT" width="100" x="115" y="60" />
<t:label id="g_8" height="20" text="#{rr.literals.user_person}" width="85" x="20" y="90" />
<t:field id="g_9" actionListener="#{d.UserDetailUI.onPersonClicked}" attributemacro="entityDetailMacro(UserDetailUI,person)" enabled="false" focusable="false" width="100" x="115" y="90" />
<t:button id="g_10" actionListener="#{d.UserDetailUI.onPersonSearch}" enabled="#{d.UserDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" x="225" y="90" />
<t:button id="g_11" actionListener="#{d.UserDetailUI.onPersonRemove}" enabled="#{d.UserDetailUI.enabled}" height="20" text="#{rr.literals.delete}" width="60" x="290" y="90" />
<t:label id="g_12" height="20" text="#{rr.literals.user_email}" width="85" x="20" y="120" />
<t:field id="g_13" attributemacro="entityDetailMacro(UserDetailUI,email)" width="100" x="115" y="120" />
<t:label id="g_14" height="20" text="#{rr.literals.user_active}" width="85" x="20" y="150" />
<t:checkbox id="g_15" align="center" enabled="false" height="20" selected="#{d.UserDetailUI.values.active}" width="100" x="115" y="150" />
<t:label id="g_16" height="20" text="#{rr.literals.user_locked}" width="85" x="20" y="180" />
<t:checkbox id="g_17" align="center" enabled="false" height="20" selected="#{d.UserDetailUI.values.locked}" width="100" x="115" y="180" />
<t:label id="g_18" height="20" text="#{rr.literals.user_initial}" width="85" x="20" y="210" />
<t:checkbox id="g_19" align="center" enabled="false" height="20" selected="#{d.UserDetailUI.values.initial}" width="100" x="115" y="210" />
</t:paintarea>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
