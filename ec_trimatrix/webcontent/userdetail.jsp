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
<t:label id="g_5" height="20" text="#{rr.literals.language}" width="85" x="20" y="60" />
<t:combobox id="g_6" attributemacro="entityDetailMacro(UserDetailUI,language)" height="20" validvaluesbinding="#{d.UserDetailUI.languagesVvb}" valuetextmode="TEXT" width="100" x="115" y="60" />
<t:label id="g_7" height="20" text="#{rr.literals.user_person}" width="85" x="20" y="90" />
<t:field id="g_8" attributemacro="entityDetailMacro(UserDetailUI,person)" enabled="false" width="100" x="115" y="90" />
<t:button id="g_9" actionListener="#{d.UserDetailUI.onPersonSearch}" attributemacro="entityDetailMacro(DetailUserUI,null)" height="15" rendered="#{d.UserDetailUI.enabledBool}" text="#{rr.literals.personsearch}" width="100" x="225" y="90" />
<t:label id="g_10" height="20" text="#{rr.literals.user_email}" width="85" x="20" y="120" />
<t:field id="g_11" attributemacro="entityDetailMacro(UserDetailUI,email)" width="100" x="115" y="120" />
</t:paintarea>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
