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
<t:combobox id="g_8" attributemacro="entityDetailMacro(PersonDetailUI,salutation)" height="20" validvaluesbinding="#{helper.vvb.salutation}" valuetextmode="TEXT" width="100" x="115" y="15" />
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
<t:coldistance id="g_32" width="40" />
<t:label id="g_33" height="20" text="#{rr.literals.housenumber}" width="100" x="365" y="15" />
<t:field id="g_34" attributemacro="entityDetailMacro(PersonDetailUI,housenumber)" height="20" width="200" x="465" y="15" />
</t:row>
<t:row id="g_35" >
<t:label id="g_36" height="20" text="#{rr.literals.postcode}" width="100" x="15" y="45" />
<t:field id="g_37" attributemacro="entityDetailMacro(PersonDetailUI,postcode)" height="20" width="200" x="115" y="45" />
<t:coldistance id="g_38" width="40" />
<t:label id="g_39" height="20" text="#{rr.literals.city}" width="100" x="365" y="45" />
<t:field id="g_40" attributemacro="entityDetailMacro(PersonDetailUI,city)" height="20" width="200" x="465" y="45" />
</t:row>
<t:row id="g_41" >
<t:label id="g_42" height="20" text="#{rr.literals.country}" width="100" x="15" y="75" />
<t:combobox id="g_43" attributemacro="entityDetailMacro(PersonDetailUI,country)" height="20" validvaluesbinding="#{helper.vvb.country}" valuetextmode="TEXT" width="200" x="115" y="75" />
<t:coldistance id="g_44" width="40" />
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
<t:button id="g_52" actionListener="#{d.PersonDetailUI.onMailSend}" contentareafilled="false" image="/images/icons/send_mail.png" imageheight="20" />
<t:coldistance id="g_53" />
<t:label id="g_54" height="20" text="#{rr.literals.homepage}" width="100" x="365" y="15" />
<t:field id="g_55" attributemacro="entityDetailMacro(PersonDetailUI,homepage)" height="20" regex="http://.*" regexmode="1" userhint="http://..." width="200" x="465" y="15" />
<t:button id="g_56" actionListener="#{d.PersonDetailUI.onShowUrl}" contentareafilled="false" image="/images/icons/internet.png" imageheight="20" />
</t:row>
<t:row id="g_57" >
<t:label id="g_58" text="#{rr.literals.telephone}" width="100" x="15" y="45" />
<t:field id="g_59" attributemacro="entityDetailMacro(PersonDetailUI,telephone)" width="200" x="115" y="45" />
<t:coldistance id="g_60" width="40" />
<t:label id="g_61" height="20" text="#{rr.literals.mobile}" width="100" x="365" y="45" />
<t:field id="g_62" attributemacro="entityDetailMacro(PersonDetailUI,mobile)" height="20" width="200" x="465" y="45" />
</t:row>
<t:row id="g_63" >
<t:label id="g_64" text="#{rr.literals.fax}" width="100" x="15" y="75" />
<t:field id="g_65" attributemacro="entityDetailMacro(PersonDetailUI,fax)" width="200" x="115" y="75" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_66" rendered="#{d.PersonDetailUI.athlete}" >
<t:foldablepane id="g_67" rowdistance="5" text="#{rr.literals.athlete}" width="100%" >
<t:row id="g_68" >
<t:label id="g_69" text="#{rr.literals.height}" width="100" x="15" y="45" />
<t:formattedfield id="g_70" attributemacro="entityDetailMacro(PersonDetailUI,height)" format="double" width="50" x="115" y="45" />
<t:coldistance id="g_71" />
<t:label id="g_72" text="cm" width="15" />
<t:coldistance id="g_73" width="40" />
<t:label id="g_74" height="20" text="#{rr.literals.weight}" width="100" x="365" y="45" />
<t:formattedfield id="g_75" attributemacro="entityDetailMacro(PersonDetailUI,weight)" format="double" height="20" width="50" x="465" y="45" />
<t:coldistance id="g_76" />
<t:label id="g_77" text="kg" />
</t:row>
<t:row id="g_78" >
<t:label id="g_79" text="#{rr.literals.hr_rest}" width="100" x="15" y="75" />
<t:formattedfield id="g_80" attributemacro="entityDetailMacro(PersonDetailUI,resting_hr)" format="int" userhint="[20-299]" width="50" x="115" y="75" />
<t:coldistance id="g_81" width="65" />
<t:label id="g_82" text="#{rr.literals.hr_max}" width="100" />
<t:formattedfield id="g_83" attributemacro="entityDetailMacro(PersonDetailUI,max_hr)" format="int" userhint="[20-299]" width="50" />
</t:row>
<t:row id="g_84" >
<t:label id="g_85" text="#{rr.literals.vo2_max}" width="100" x="15" y="15" />
<t:formattedfield id="g_86" attributemacro="entityDetailMacro(PersonDetailUI,vo2_max)" format="int" userhint="[0-100]" width="50" x="115" y="15" />
<t:coldistance id="g_87" />
<t:label id="g_88" height="20" width="100" x="365" y="15" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_89" rendered="#{d.PersonDetailUI.admin}" >
<t:foldablepane id="g_90" rowdistance="5" text="Admin" width="100%" >
<t:row id="g_91" >
<t:label id="g_92" text="#{rr.literals.profile_athlete}" width="100" x="15" y="15" />
<t:checkbox id="g_93" enabled="#{d.PersonDetailUI.enabled}" flush="true" selected="#{d.PersonDetailUI.athlete}" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:beanprocessing id="g_94" >
<t:mailto id="g_95" receiver="#{d.PersonDetailUI.values.email}" trigger="#{d.PersonDetailUI.sendTrigger}" />
<t:jshowurl id="g_96" target="_blank" trigger="#{d.PersonDetailUI.browserTrigger}" url="#{d.PersonDetailUI.values.homepage}" usedesktop="true" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
