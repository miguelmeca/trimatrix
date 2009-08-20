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
<t:combobox id="g_7" attributemacro="entityDetailMacro(TestDetailUI,type)" validvaluesbinding="#{d.TestDetailUI.testTypesVvb}" width="200" />
</t:row>
<t:rowdistance id="g_8" />
<t:row id="g_9" >
<t:label id="g_10" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_11" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="200" />
</t:row>
<t:rowdistance id="g_12" />
<t:row id="g_13" >
<t:label id="g_14" text="#{rr.literals.test_date}" width="100" />
<t:calendarfield id="g_15" attributemacro="entityDetailMacro(TestDetailUI,date)" timezone="CET" width="200" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
