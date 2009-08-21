<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="testdetailg_sv">
<t:rowbodypane id="g_1" >
<t:rowdynamiccontent id="g_2" contentbinding="#{d.TestDetailUI.labelRow}" />
<t:row id="g_3" >
<t:foldablepane id="g_4" text="#{rr.literals.details}" width="100%" >
<t:row id="g_5" >
<t:label id="g_6" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_7" actionListener="#{d.TestDetailUI.onTypeChange}" attributemacro="entityDetailMacro(TestDetailUI,type)" flush="true" validvaluesbinding="#{d.TestDetailUI.testTypesVvb}" width="200" />
<t:coldistance id="g_8" />
<t:label id="g_9" text="#{rr.literals.test_date}" width="100" />
<t:calendarfield id="g_10" attributemacro="entityDetailMacro(TestDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_11" />
<t:row id="g_12" >
<t:label id="g_13" text="#{rr.literals.athlete}" width="100" />
<t:field id="g_14" attributemacro="entityDetailMacro(TestDetailUI,person)" enabled="false" focusable="false" width="175" />
<t:button id="g_15" actionListener="#{d.TestDetailUI.onAthleteSearch}" contentareafilled="false" image="/images/icons/magnifier.png" />
<t:coldistance id="g_16" />
<t:label id="g_17" text="#{rr.literals.doctor}" width="100" />
<t:field id="g_18" attributemacro="entityDetailMacro(TestDetailUI,doctor)" enabled="false" focusable="false" width="175" />
<t:button id="g_19" actionListener="#{d.TestDetailUI.onDoctorSearch}" contentareafilled="false" image="/images/icons/magnifier.png" />
</t:row>
<t:rowdistance id="g_20" />
<t:row id="g_21" >
<t:label id="g_22" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_23" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="510" />
</t:row>
<t:row id="g_24" />
</t:foldablepane>
</t:row>
<t:row id="g_25" >
<t:foldablepane id="g_26" rendered="#{d.TestDetailUI.treadmill}" text="#{rr.literals.treadmill}" width="100%" />
</t:row>
<t:row id="g_27" >
<t:foldablepane id="g_28" rendered="#{d.TestDetailUI.ergo}" text="#{rr.literals.ergo}" width="100%" />
</t:row>
<t:row id="g_29" >
<t:foldablepane id="g_30" text="#{rr.literals.test_protocol}" width="100%" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
