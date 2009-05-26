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
<t:foldablepane id="g_2" height="250" text="#{rr.literals.details}" width="100%" >
<t:row id="g_3" >
<t:paintarea id="g_4" height="100%" width="100%" >
<t:label id="g_5" height="20" text="#{rr.literals.salutation}" width="100" x="15" y="15" />
<t:combobox id="g_6" attributemacro="entityDetailMacro(PersonDetailUI,salutation)" height="20" validvaluesbinding="#{d.PersonDetailUI.salutationsVvb}" valuetextmode="TEXT" width="100" x="115" y="15" />
<t:heximage id="g_7" border="#808080" height="130" hexdata="#{d.PersonDetailUI.picture}" width="130" x="365" y="15" />
<t:label id="g_8" text="#{rr.literals.person_first_name}" width="100" x="15" y="45" />
<t:field id="g_9" attributemacro="entityDetailMacro(PersonDetailUI,name_first)" width="200" x="115" y="45" />
<t:label id="g_10" text="#{rr.literals.person_last_name}" width="100" x="15" y="75" />
<t:field id="g_11" attributemacro="entityDetailMacro(PersonDetailUI,name_last)" width="200" x="115" y="75" />
<t:label id="g_12" height="15" text="#{rr.literals.person_birthday}" width="100" x="15" y="120" />
<t:calendarfield id="g_13" attributemacro="entityDetailMacro(PersonDetailUI,birthdate)" format="date" height="20" timezone="CET" width="200" x="115" y="120" />
<t:fileuploadbutton id="g_14" actionListener="#{d.PersonDetailUI.onUploadImage}" enabled="#{d.PersonDetailUI.enabled}" fileextensions="jpg;png;gif" height="20" rendered="#{d.PersonDetailUI.enabled}" text="#{rr.literals.edit}" width="60" x="365" y="150" />
<t:button id="g_15" actionListener="#{d.PersonDetailUI.onRemoveImage}" enabled="#{d.PersonDetailUI.enabled}" height="20" rendered="#{d.PersonDetailUI.enabled}" text="#{rr.literals.delete}" width="65" x="430" y="150" />
</t:paintarea>
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_16" >
<t:foldablepane id="g_17" height="180" text="#{rr.literals.address}" width="100%" x="15" y="200" >
<t:row id="g_18" >
<t:paintarea id="g_19" height="100%" width="100%" >
<t:label id="g_20" height="20" text="#{rr.literals.street}" width="100" x="15" y="15" />
<t:field id="g_21" attributemacro="entityDetailMacro(PersonDetailUI,street)" height="20" width="200" x="115" y="15" />
<t:label id="g_22" height="20" text="#{rr.literals.country}" width="100" x="15" y="45" />
<t:combobox id="g_23" attributemacro="entityDetailMacro(PersonDetailUI,country)" height="20" validvaluesbinding="#{d.PersonDetailUI.countriesVvb}" valuetextmode="TEXT" width="200" x="115" y="45" />
</t:paintarea>
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_24" >
<t:foldablepane id="g_25" height="200" text="#{rr.literals.communication}" width="100%" >
<t:row id="g_26" >
<t:paintarea id="g_27" height="100%" width="100%" >
<t:label id="g_28" text="#{rr.literals.person_email}" width="100" x="15" y="15" />
<t:field id="g_29" attributemacro="entityDetailMacro(PersonDetailUI,email)" width="200" x="115" y="15" />
<t:label id="g_30" height="20" text="#{rr.literals.homepage}" width="100" x="15" y="45" />
<t:field id="g_31" attributemacro="entityDetailMacro(PersonDetailUI,homepage)" height="20" width="200" x="115" y="45" />
</t:paintarea>
</t:row>
</t:foldablepane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
