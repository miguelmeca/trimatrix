<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="persondetailg_sv">
<t:row id="g_1" >
<t:paintarea id="g_2" height="100%" width="100%" >
<t:label id="g_3" text="#{rr.literals.person_first_name}" width="100" x="15" y="30" />
<t:field id="g_4" attributemacro="entityDetailMacro(PersonDetailUI,name_first)" width="100" x="115" y="30" />
<t:heximage id="g_5" height="130" hexdata="#{d.PersonDetailUI.picture}" width="130" x="365" y="30" />
<t:label id="g_6" text="#{rr.literals.person_last_name}" width="100" x="15" y="60" />
<t:field id="g_7" attributemacro="entityDetailMacro(PersonDetailUI,name_last)" width="100" x="115" y="60" />
<t:label id="g_8" text="#{rr.literals.person_email}" width="100" x="15" y="90" />
<t:field id="g_9" attributemacro="entityDetailMacro(PersonDetailUI,email)" width="100" x="115" y="90" />
<t:label id="g_10" height="15" text="#{rr.literals.person_birthday}" width="100" x="15" y="120" />
<t:calendarfield id="g_11" attributemacro="entityDetailMacro(PersonDetailUI,birthdate)" format="date" height="20" timezone="CET" width="100" x="115" y="120" />
<t:fileuploadbutton id="g_12" actionListener="#{d.PersonDetailUI.onUploadImage}" enabled="#{d.PersonDetailUI.enabled}" rendered="#{d.PersonDetailUI.enabled}" fileextensions="jpg;png;gif" height="20" text="#{rr.literals.edit}" width="60" x="370" y="170" />
<t:button id="g_13" actionListener="#{d.PersonDetailUI.onRemoveImage}" enabled="#{d.PersonDetailUI.enabled}" height="20" rendered="#{d.PersonDetailUI.enabled}" text="#{rr.literals.delete}" width="65" x="435" y="170" />
</t:paintarea>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
