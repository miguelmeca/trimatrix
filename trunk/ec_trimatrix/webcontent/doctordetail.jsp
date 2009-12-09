<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="doctordetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" height="20" text="#{rr.literals.doctor_name}" width="100" />
<t:field id="g_4" attributemacro="entityDetailMacro(DoctorDetailUI,name)" height="20" width="540" />
</t:row>
<t:row id="g_5" >
<t:label id="g_6" height="20" text="#{rr.literals.street}" width="100" x="15" y="45" />
<t:field id="g_7" attributemacro="entityDetailMacro(DoctorDetailUI,street)" height="20" width="200" />
<t:coldistance id="g_8" width="40" />
<t:label id="g_9" height="20" text="#{rr.literals.housenumber}" width="100" x="365" y="45" />
<t:field id="g_10" attributemacro="entityDetailMacro(DoctorDetailUI,housenumber)" height="20" width="200" x="465" y="45" />
</t:row>
<t:row id="g_11" >
<t:label id="g_12" height="20" text="#{rr.literals.postcode}" width="100" x="15" y="75" />
<t:field id="g_13" attributemacro="entityDetailMacro(DoctorDetailUI,postcode)" height="20" width="200" x="115" y="75" />
<t:coldistance id="g_14" width="40" />
<t:label id="g_15" height="20" text="#{rr.literals.city}" width="100" x="365" y="75" />
<t:field id="g_16" attributemacro="entityDetailMacro(DoctorDetailUI,city)" height="20" width="200" x="465" y="75" />
</t:row>
<t:row id="g_17" >
<t:label id="g_18" height="20" text="#{rr.literals.country}" width="100" x="15" y="105" />
<t:combobox id="g_19" attributemacro="entityDetailMacro(DoctorDetailUI,country)" height="20" validvaluesbinding="#{helper.vvb.country}" valuetextmode="TEXT" width="200" x="115" y="105" />
<t:coldistance id="g_20" width="40" />
<t:label id="g_21" height="20" text="#{rr.literals.state}" width="100" x="365" y="105" />
<t:field id="g_22" attributemacro="entityDetailMacro(DoctorDetailUI,state)" height="20" width="200" x="465" y="105" />
</t:row>
<t:row id="g_23" >
<t:label id="g_24" text="#{rr.literals.email}" width="100" x="15" y="135" />
<t:field id="g_25" attributemacro="entityDetailMacro(DoctorDetailUI,email)" width="200" x="115" y="135" />
<t:button id="g_26" actionListener="#{d.DoctorDetailUI.onMailSend}" contentareafilled="false" image="/images/icons/send_mail.png" imageheight="20" />
<t:coldistance id="g_27" />
<t:label id="g_28" height="20" text="#{rr.literals.homepage}" width="100" x="365" y="135" />
<t:field id="g_29" attributemacro="entityDetailMacro(DoctorDetailUI,homepage)" height="20" regex="http://.*" regexmode="1" userhint="http:// ..." width="200" x="465" y="135" />
<t:button id="g_30" actionListener="#{d.DoctorDetailUI.onShowUrl}" contentareafilled="false" image="/images/icons/internet.png" imageheight="20" />
</t:row>
<t:row id="g_31" >
<t:label id="g_32" text="#{rr.literals.telephone}" width="100" x="15" y="165" />
<t:field id="g_33" attributemacro="entityDetailMacro(DoctorDetailUI,telephone)" width="200" x="115" y="165" />
<t:coldistance id="g_34" width="40" />
<t:label id="g_35" height="20" text="#{rr.literals.mobile}" width="100" x="365" y="165" />
<t:field id="g_36" attributemacro="entityDetailMacro(DoctorDetailUI,mobile)" height="20" width="200" x="465" y="165" />
</t:row>
<t:row id="g_37" >
<t:label id="g_38" text="#{rr.literals.fax}" width="100" x="15" y="195" />
<t:field id="g_39" attributemacro="entityDetailMacro(DoctorDetailUI,fax)" width="200" x="115" y="195" />
</t:row>
<t:rowdistance id="g_40" height="20" />
<t:row id="g_41" >
<t:button id="g_42" actionListener="#{d.DoctorDetailUI.onMapSearch}" text="Suche Adresse" />
<t:coldistance id="g_43" />
<t:field id="g_44" enabled="false" text="#{d.DoctorDetailUI.latitude}" width="100" />
<t:field id="g_45" enabled="false" text="#{d.DoctorDetailUI.longitude}" width="100" />
</t:row>
<t:row id="g_46" >
<t:browser id="g_47" height="300" url="#{d.DoctorDetailUI.url}" width="400" />
</t:row>
</t:rowbodypane>
<t:beanprocessing id="g_48" >
<t:mailto id="g_49" receiver="#{d.DoctorDetailUI.values.email}" trigger="#{d.DoctorDetailUI.sendTrigger}" />
<t:jshowurl id="g_50" target="_blank" trigger="#{d.PersonDetailUI.browserTrigger}" url="#{d.PersonDetailUI.values.homepage}" usedesktop="true" />
<t:clienthttpreceiver id="g_51" actionListener="#{d.DoctorDetailUI.onClientReceive}" port="50055" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
