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
<t:combobox id="g_7" actionListener="#{d.TestDetailUI.onTypeChange}" attributemacro="entityDetailMacro(TestDetailUI,type)" enabled="#{d.TestDetailUI.typeEnabled}" flush="true" validvaluesbinding="#{d.TestDetailUI.testTypesVvb}" width="200" />
<t:coldistance id="g_8" />
<t:label id="g_9" text="#{rr.literals.test_date}" width="100" />
<t:calendarfield id="g_10" attributemacro="entityDetailMacro(TestDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_11" />
<t:row id="g_12" >
<t:label id="g_13" text="#{rr.literals.athlete}" width="100" />
<t:field id="g_14" attributemacro="entityDetailMacro(TestDetailUI,person)" enabled="false" focusable="false" width="175" />
<t:button id="g_15" actionListener="#{d.TestDetailUI.onAthleteSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
<t:coldistance id="g_16" />
<t:label id="g_17" text="#{rr.literals.doctor}" width="100" />
<t:field id="g_18" attributemacro="entityDetailMacro(TestDetailUI,doctor)" enabled="false" focusable="false" width="175" />
<t:button id="g_19" actionListener="#{d.TestDetailUI.onDoctorSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
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
<t:foldablepane id="g_26" rendered="#{d.TestDetailUI.ergo}" text="#{rr.literals.ergo}" width="100%" >
<t:row id="g_27" >
<t:label id="g_28" text="#{rr.literals.power_init}" width="150" />
<t:formattedfield id="g_29" attributemacro="entityDetailMacro(TestDetailUI,power_init)" format="int" width="50" />
<t:coldistance id="g_30" />
<t:label id="g_31" text="#{rr.literals.power_step}" width="150" />
<t:formattedfield id="g_32" attributemacro="entityDetailMacro(TestDetailUI,power_step)" format="int" width="50" />
</t:row>
<t:rowdistance id="g_33" />
<t:row id="g_34" >
<t:label id="g_35" text="#{rr.literals.cadence_low}" width="150" />
<t:formattedfield id="g_36" attributemacro="entityDetailMacro(TestDetailUI,cadence_low)" format="int" width="50" />
<t:coldistance id="g_37" />
<t:label id="g_38" text="#{rr.literals.cadence_high}" width="150" />
<t:formattedfield id="g_39" attributemacro="entityDetailMacro(TestDetailUI,cadence_high)" format="int" width="50" />
</t:row>
<t:rowdistance id="g_40" />
<t:row id="g_41" >
<t:label id="g_42" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_43" attributemacro="entityDetailMacro(TestDetailUI,ergo_step_time)" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="50" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_44" >
<t:foldablepane id="g_45" rendered="#{d.TestDetailUI.treadmill}" text="#{rr.literals.treadmill}" width="100%" >
<t:row id="g_46" >
<t:label id="g_47" text="#{rr.literals.speed_init}" width="150" />
<t:formattedfield id="g_48" attributemacro="entityDetailMacro(TestDetailUI,speed_init)" format="double" userhint="km/h" width="50" />
<t:coldistance id="g_49" />
<t:checkbox id="g_50" attributemacro="entityDetailMacro(TestDetailUI,speed_variable)" flush="true" selected="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_51" />
<t:label id="g_52" rendered="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.speed_step}" width="150" />
<t:formattedfield id="g_53" attributemacro="entityDetailMacro(TestDetailUI,speed_step)" format="double" rendered="#{d.TestDetailUI.values.speed_variable}" userhint="km/h" width="50" />
</t:row>
<t:rowdistance id="g_54" />
<t:row id="g_55" >
<t:label id="g_56" text="#{rr.literals.incline_init}" width="150" />
<t:formattedfield id="g_57" attributemacro="entityDetailMacro(TestDetailUI,incline_init)" format="int" userhint="%" width="50" />
<t:coldistance id="g_58" />
<t:checkbox id="g_59" attributemacro="entityDetailMacro(TestDetailUI,incline_variable)" flush="true" selected="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_60" />
<t:label id="g_61" rendered="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.incline_step}" width="150" />
<t:formattedfield id="g_62" attributemacro="entityDetailMacro(TestDetailUI,incline_step)" format="int" rendered="#{d.TestDetailUI.values.incline_variable}" userhint="%" width="50" />
</t:row>
<t:rowdistance id="g_63" />
<t:row id="g_64" >
<t:label id="g_65" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_66" attributemacro="entityDetailMacro(TestDetailUI,treadmill_step_time)" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="50" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_67" >
<t:foldablepane id="g_68" rendered="#{d.TestDetailUI.swim}" text="#{rr.literals.swim}" width="100%" >
<t:row id="g_69" >
<t:label id="g_70" text="#{rr.literals.test_date2}" width="150" />
<t:calendarfield id="g_71" attributemacro="entityDetailMacro(TestDetailUI,date2)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_72" />
<t:rowdistance id="g_73" />
<t:row id="g_74" >
<t:label id="g_75" text="#{rr.literals.test_assistance}" width="150" />
<t:field id="g_76" attributemacro="entityDetailMacro(TestDetailUI,assistant_name)" width="200" />
</t:row>
<t:rowdistance id="g_77" />
<t:row id="g_78" >
<t:label id="g_79" text="#{rr.literals.baths}" width="150" />
<t:field id="g_80" attributemacro="entityDetailMacro(TestDetailUI,baths)" width="200" />
</t:row>
<t:rowdistance id="g_81" />
<t:row id="g_82" >
<t:label id="g_83" text="#{rr.literals.pools}" width="150" />
<t:field id="g_84" attributemacro="entityDetailMacro(TestDetailUI,pool)" width="200" />
</t:row>
<t:rowdistance id="g_85" height="15" />
<t:row id="g_86" >
<t:label id="g_87" text="#{rr.literals.test_distance}" width="150" />
<t:formattedfield id="g_88" attributemacro="entityDetailMacro(TestDetailUI,distance)" format="int" width="50" />
</t:row>
<t:rowdistance id="g_89" />
<t:row id="g_90" >
<t:label id="g_91" text="#{rr.literals.test_splits}" width="150" />
<t:formattedfield id="g_92" attributemacro="entityDetailMacro(TestDetailUI,splits)" format="int" width="50" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_93" >
<t:foldablepane id="g_94" text="#{rr.literals.test_protocol}" width="100%" >
<t:row id="g_95" rendered="#{d.TestDetailUI.protocol==false}" >
<t:button id="g_96" actionListener="#{d.TestDetailUI.onProtocolCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testprotocol}" />
</t:row>
<t:row id="g_97" rendered="#{d.TestDetailUI.protocol}" >
<t:pane id="g_98" >
<t:row id="g_99" >
<t:label id="g_100" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_101" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="510" />
</t:row>
<t:rowdistance id="g_102" />
<t:row id="g_103" >
<t:fixgrid id="g_104" avoidroundtrips="true" border="#808080" multiselect="false" objectbinding="#{d.TestDetailUI.gridTreadmill}" width="100%" >
<t:gridcol id="g_105" align="center" text="Stufe" width="50" />
<t:gridcol id="g_106" align="center" text="Geschwindigkeit" width="90" />
<t:gridcol id="g_107" align="center" text="Steigung" width="60" />
<t:gridcol id="g_108" align="center" text="Zeit (Stufe)" width="75" />
<t:gridcol id="g_109" align="center" text="Zeit(Gesamt)" width="75" />
<t:gridcol id="g_110" align="center" text="Laktat" width="45" />
<t:gridcol id="g_111" align="center" text="HF" width="45" />
<t:gridcol id="g_112" align="center" text="O2" width="45" />
<t:gridcol id="g_113" align="center" text="CO2" width="45" />
<t:gridcol id="g_114" align="center" text="RQ" width="45" />
</t:fixgrid>
</t:row>
<t:rowdistance id="g_115" />
<t:row id="g_116" >
<t:button id="g_117" text="Hinzufügen" />
<t:coldistance id="g_118" />
<t:button id="g_119" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
