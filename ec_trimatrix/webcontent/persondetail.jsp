<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="persondetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:foldablepane id="g_3" text="#{rr.literals.details}" width="100%" >
<t:row id="g_4" >
<t:pane id="g_5" rowalignmenty="top" rowdistance="5" >
<t:row id="g_6" >
<t:label id="g_7" height="20" text="#{rr.literals.salutation}" width="100" x="15" y="15" />
<t:combobox id="g_8" attributemacro="entityDetailMacro(PersonDetailUI,salutation)" height="20" validvaluesbinding="#{d.PersonDetailUI.salutationsVvb}" valuetextmode="TEXT" width="100" x="115" y="15" />
</t:row>
<t:row id="g_9" >
<t:label id="g_10" text="#{rr.literals.person_first_name}" width="100" x="15" y="45" />
<t:field id="g_11" attributemacro="entityDetailMacro(PersonDetailUI,name_first)" width="200" x="115" y="45" />
</t:row>
<t:row id="g_12" >
<t:label id="g_13" text="#{rr.literals.person_last_name}" width="100" x="15" y="75" />
<t:field id="g_14" attributemacro="entityDetailMacro(PersonDetailUI,name_last)" width="200" x="115" y="75" />
</t:row>
<t:rowdistance id="g_15" height="15" />
<t:row id="g_16" >
<t:label id="g_17" height="15" text="#{rr.literals.person_birthday}" width="100" x="15" y="120" />
<t:calendarfield id="g_18" attributemacro="entityDetailMacro(PersonDetailUI,birthdate)" format="date" height="20" timezone="CET" width="200" x="115" y="120" />
</t:row>
</t:pane>
<t:coldistance id="g_19" />
<t:pane id="g_20" rowdistance="5" >
<t:row id="g_21" >
<t:heximage id="g_22" align="center" border="#{d.PersonDetailUI.border}" dragsend="TEST" height="150" hexdata="#{d.PersonDetailUI.picture}" keepratio="true" valign="center" width="130" x="365" y="15" />
</t:row>
<t:row id="g_23" >
<t:fileuploadbutton id="g_24" actionListener="#{d.PersonDetailUI.onUploadImage}" enabled="#{d.PersonDetailUI.enabled}" fileextensions="jpg;png;gif" height="20" rendered="#{d.PersonDetailUI.enabled}" text="#{rr.literals.edit}" width="60" x="365" y="150" />
<t:coldistance id="g_25" width="5" />
<t:button id="g_26" actionListener="#{d.PersonDetailUI.onRemoveImage}" enabled="#{d.PersonDetailUI.enabled}" height="20" rendered="#{d.PersonDetailUI.enabled}" text="#{rr.literals.delete}" width="65" x="430" y="150" />
</t:row>
</t:pane>
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_27" >
<t:foldablepane id="g_28" rowdistance="5" text="#{rr.literals.address}" width="100%" x="15" y="200" >
<t:row id="g_29" >
<t:label id="g_30" height="20" text="#{rr.literals.street}" width="100" x="15" y="15" />
<t:field id="g_31" attributemacro="entityDetailMacro(PersonDetailUI,street)" height="20" width="200" x="115" y="15" />
<t:coldistance id="g_32" />
<t:label id="g_33" height="20" text="#{rr.literals.housenumber}" width="100" x="365" y="15" />
<t:field id="g_34" attributemacro="entityDetailMacro(PersonDetailUI,housenumber)" height="20" width="200" x="465" y="15" />
</t:row>
<t:row id="g_35" >
<t:label id="g_36" height="20" text="#{rr.literals.postcode}" width="100" x="15" y="45" />
<t:field id="g_37" attributemacro="entityDetailMacro(PersonDetailUI,postcode)" height="20" width="200" x="115" y="45" />
<t:coldistance id="g_38" />
<t:label id="g_39" height="20" text="#{rr.literals.city}" width="100" x="365" y="45" />
<t:field id="g_40" attributemacro="entityDetailMacro(PersonDetailUI,city)" height="20" width="200" x="465" y="45" />
</t:row>
<t:row id="g_41" >
<t:label id="g_42" height="20" text="#{rr.literals.country}" width="100" x="15" y="75" />
<t:combobox id="g_43" attributemacro="entityDetailMacro(PersonDetailUI,country)" height="20" validvaluesbinding="#{d.PersonDetailUI.countriesVvb}" valuetextmode="TEXT" width="200" x="115" y="75" />
<t:coldistance id="g_44" />
<t:label id="g_45" height="20" text="#{rr.literals.state}" width="100" x="365" y="75" />
<t:field id="g_46" attributemacro="entityDetailMacro(PersonDetailUI,state)" height="20" width="200" x="465" y="75" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_47" >
<t:foldablepane id="g_48" rowdistance="5" text="#{rr.literals.communication}" width="100%" >
<t:row id="g_49" >
<t:label id="g_50" text="#{rr.literals.email}" width="100" x="15" y="15" />
<t:field id="g_51" attributemacro="entityDetailMacro(PersonDetailUI,email)" width="200" x="115" y="15" />
<t:coldistance id="g_52" />
<t:label id="g_53" height="20" text="#{rr.literals.homepage}" width="100" x="365" y="15" />
<t:field id="g_54" attributemacro="entityDetailMacro(PersonDetailUI,homepage)" height="20" width="200" x="465" y="15" />
</t:row>
<t:row id="g_55" >
<t:label id="g_56" text="#{rr.literals.telephone}" width="100" x="15" y="45" />
<t:field id="g_57" attributemacro="entityDetailMacro(PersonDetailUI,telephone)" width="200" x="115" y="45" />
<t:coldistance id="g_58" />
<t:label id="g_59" height="20" text="#{rr.literals.mobile}" width="100" x="365" y="45" />
<t:field id="g_60" attributemacro="entityDetailMacro(PersonDetailUI,mobile)" height="20" width="200" x="465" y="45" />
</t:row>
<t:row id="g_61" >
<t:label id="g_62" text="#{rr.literals.fax}" width="100" x="15" y="75" />
<t:field id="g_63" attributemacro="entityDetailMacro(PersonDetailUI,fax)" width="200" x="115" y="75" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_64" rendered="#{d.PersonDetailUI.athlete}" >
<t:foldablepane id="g_65" rowdistance="5" text="#{rr.literals.athlete}" width="100%" >
<t:row id="g_66" >
<t:label id="g_67" text="#{rr.literals.height}" width="100" x="15" y="45" />
<t:formattedfield id="g_68" attributemacro="entityDetailMacro(PersonDetailUI,height)" format="double" width="50" x="115" y="45" />
<t:coldistance id="g_69" />
<t:label id="g_70" text="cm" width="15" />
<t:coldistance id="g_71" />
<t:label id="g_72" height="20" text="#{rr.literals.weight}" width="100" x="365" y="45" />
<t:formattedfield id="g_73" attributemacro="entityDetailMacro(PersonDetailUI,weight)" format="double" height="20" width="50" x="465" y="45" />
<t:coldistance id="g_74" />
<t:label id="g_75" text="kg" />
</t:row>
<t:row id="g_76" >
<t:label id="g_77" text="#{rr.literals.hr_rest}" width="100" x="15" y="75" />
<t:formattedfield id="g_78" attributemacro="entityDetailMacro(PersonDetailUI,resting_hr)" format="int" userhint="[20-299]" width="50" x="115" y="75" />
<t:coldistance id="g_79" width="35" />
<t:label id="g_80" text="#{rr.literals.hr_max}" width="100" />
<t:formattedfield id="g_81" attributemacro="entityDetailMacro(PersonDetailUI,max_hr)" format="int" userhint="[20-299]" width="50" />
</t:row>
<t:row id="g_82" >
<t:label id="g_83" text="#{rr.literals.vo2_max}" width="100" x="15" y="15" />
<t:formattedfield id="g_84" attributemacro="entityDetailMacro(PersonDetailUI,vo2_max)" format="int" userhint="[0-100]" width="50" x="115" y="15" />
<t:coldistance id="g_85" />
<t:label id="g_86" height="20" width="100" x="365" y="15" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_87" rendered="#{d.PersonDetailUI.admin}" >
<t:foldablepane id="g_88" rowdistance="5" text="Admin" width="100%" >
<t:row id="g_89" >
<t:label id="g_90" text="#{rr.literals.profile_athlete}" width="100" x="15" y="15" />
<t:checkbox id="g_91" enabled="#{d.PersonDetailUI.enabled}" flush="true" selected="#{d.PersonDetailUI.athlete}" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
