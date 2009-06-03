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
<t:field id="g_4" attributemacro="entityDetailMacro(DoctorDetailUI,name)" height="20" width="510" />
</t:row>
<t:row id="g_5" >
<t:label id="g_6" height="20" text="#{rr.literals.street}" width="100" x="15" y="45" />
<t:field id="g_7" attributemacro="entityDetailMacro(DoctorDetailUI,street)" height="20" width="200" />
<t:coldistance id="g_8" />
<t:label id="g_9" height="20" text="#{rr.literals.housenumber}" width="100" x="365" y="45" />
<t:field id="g_10" attributemacro="entityDetailMacro(DoctorDetailUI,housenumber)" height="20" width="200" x="465" y="45" />
</t:row>
<t:row id="g_11" >
<t:label id="g_12" height="20" text="#{rr.literals.postcode}" width="100" x="15" y="75" />
<t:field id="g_13" attributemacro="entityDetailMacro(DoctorDetailUI,postcode)" height="20" width="200" x="115" y="75" />
<t:coldistance id="g_14" />
<t:label id="g_15" height="20" text="#{rr.literals.city}" width="100" x="365" y="75" />
<t:field id="g_16" attributemacro="entityDetailMacro(DoctorDetailUI,city)" height="20" width="200" x="465" y="75" />
</t:row>
<t:row id="g_17" >
<t:label id="g_18" height="20" text="#{rr.literals.country}" width="100" x="15" y="105" />
<t:combobox id="g_19" attributemacro="entityDetailMacro(DoctorDetailUI,country)" height="20" validvaluesbinding="#{d.DoctorDetailUI.countriesVvb}" valuetextmode="TEXT" width="200" x="115" y="105" />
<t:coldistance id="g_20" />
<t:label id="g_21" height="20" text="#{rr.literals.state}" width="100" x="365" y="105" />
<t:field id="g_22" attributemacro="entityDetailMacro(DoctorDetailUI,state)" height="20" width="200" x="465" y="105" />
</t:row>
<t:row id="g_23" >
<t:label id="g_24" text="#{rr.literals.email}" width="100" x="15" y="135" />
<t:field id="g_25" attributemacro="entityDetailMacro(DoctorDetailUI,email)" width="200" x="115" y="135" />
<t:coldistance id="g_26" />
<t:label id="g_27" height="20" text="#{rr.literals.homepage}" width="100" x="365" y="135" />
<t:field id="g_28" attributemacro="entityDetailMacro(DoctorDetailUI,homepage)" height="20" width="200" x="465" y="135" />
</t:row>
<t:row id="g_29" >
<t:label id="g_30" text="#{rr.literals.telephone}" width="100" x="15" y="165" />
<t:field id="g_31" attributemacro="entityDetailMacro(DoctorDetailUI,telephone)" width="200" x="115" y="165" />
<t:coldistance id="g_32" />
<t:label id="g_33" height="20" text="#{rr.literals.mobile}" width="100" x="365" y="165" />
<t:field id="g_34" attributemacro="entityDetailMacro(DoctorDetailUI,mobile)" height="20" width="200" x="465" y="165" />
</t:row>
<t:row id="g_35" >
<t:label id="g_36" text="#{rr.literals.fax}" width="100" x="15" y="195" />
<t:field id="g_37" attributemacro="entityDetailMacro(DoctorDetailUI,fax)" width="200" x="115" y="195" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
