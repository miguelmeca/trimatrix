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
<t:tabbedpane id="g_4" height="100%" width="100%" >
<t:tabbedpanetab id="g_5" padding="5" rowdistance="2" text="#{rr.literals.test}" >
<t:row id="g_6" >
<t:pane id="g_7" >
<t:row id="g_8" >
<t:label id="g_9" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_10" actionListener="#{d.TestDetailUI.onTypeChange}" attributemacro="entityDetailMacro(TestDetailUI,type)" enabled="#{d.TestDetailUI.typeEnabled}" flush="true" validvaluesbinding="#{d.TestDetailUI.testTypesVvb}" width="200" />
<t:coldistance id="g_11" />
<t:label id="g_12" text="#{rr.literals.test_date}" width="100" />
<t:calendarfield id="g_13" attributemacro="entityDetailMacro(TestDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_14" />
<t:row id="g_15" >
<t:label id="g_16" text="#{rr.literals.athlete}" width="100" />
<t:field id="g_17" attributemacro="entityDetailMacro(TestDetailUI,person)" enabled="false" focusable="false" width="175" />
<t:button id="g_18" actionListener="#{d.TestDetailUI.onAthleteSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
<t:coldistance id="g_19" />
<t:label id="g_20" text="#{rr.literals.doctor}" width="100" />
<t:field id="g_21" attributemacro="entityDetailMacro(TestDetailUI,doctor)" enabled="false" focusable="false" width="175" />
<t:button id="g_22" actionListener="#{d.TestDetailUI.onDoctorSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
</t:row>
<t:rowdistance id="g_23" />
<t:row id="g_24" >
<t:label id="g_25" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_26" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="510" />
</t:row>
<t:row id="g_27" />
</t:pane>
</t:row>
<t:rowdistance id="g_28" height="15" />
<t:row id="g_29" rendered="#{d.TestDetailUI.treadmill}" >
<t:pane id="g_30" width="100%" >
<t:row id="g_31" >
<t:label id="g_32" text="#{rr.literals.speed_init}" width="150" />
<t:formattedfield id="g_33" attributemacro="entityDetailMacro(TestDetailUI,speed_init)" format="double" userhint="km/h" width="50" />
<t:coldistance id="g_34" />
<t:checkbox id="g_35" attributemacro="entityDetailMacro(TestDetailUI,speed_variable)" flush="true" selected="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_36" />
<t:label id="g_37" rendered="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.speed_step}" width="150" />
<t:formattedfield id="g_38" attributemacro="entityDetailMacro(TestDetailUI,speed_step)" format="double" rendered="#{d.TestDetailUI.values.speed_variable}" userhint="km/h" width="50" />
</t:row>
<t:rowdistance id="g_39" />
<t:row id="g_40" >
<t:label id="g_41" text="#{rr.literals.incline_init}" width="150" />
<t:formattedfield id="g_42" attributemacro="entityDetailMacro(TestDetailUI,incline_init)" format="int" userhint="%" width="50" />
<t:coldistance id="g_43" />
<t:checkbox id="g_44" attributemacro="entityDetailMacro(TestDetailUI,incline_variable)" flush="true" selected="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_45" />
<t:label id="g_46" rendered="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.incline_step}" width="150" />
<t:formattedfield id="g_47" attributemacro="entityDetailMacro(TestDetailUI,incline_step)" format="int" rendered="#{d.TestDetailUI.values.incline_variable}" userhint="%" width="50" />
</t:row>
<t:rowdistance id="g_48" />
<t:row id="g_49" >
<t:label id="g_50" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_51" attributemacro="entityDetailMacro(TestDetailUI,treadmill_step_time)" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_52" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_53" width="100%" >
<t:row id="g_54" >
<t:label id="g_55" text="#{rr.literals.power_init}" width="150" />
<t:formattedfield id="g_56" attributemacro="entityDetailMacro(TestDetailUI,power_init)" format="int" width="50" />
<t:coldistance id="g_57" />
<t:label id="g_58" text="#{rr.literals.power_step}" width="150" />
<t:formattedfield id="g_59" attributemacro="entityDetailMacro(TestDetailUI,power_step)" format="int" width="50" />
</t:row>
<t:rowdistance id="g_60" />
<t:row id="g_61" >
<t:label id="g_62" text="#{rr.literals.cadence_low}" width="150" />
<t:formattedfield id="g_63" attributemacro="entityDetailMacro(TestDetailUI,cadence_low)" format="int" width="50" />
<t:coldistance id="g_64" />
<t:label id="g_65" text="#{rr.literals.cadence_high}" width="150" />
<t:formattedfield id="g_66" attributemacro="entityDetailMacro(TestDetailUI,cadence_high)" format="int" width="50" />
</t:row>
<t:rowdistance id="g_67" />
<t:row id="g_68" >
<t:label id="g_69" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_70" attributemacro="entityDetailMacro(TestDetailUI,ergo_step_time)" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_71" >
<t:pane id="g_72" rendered="#{d.TestDetailUI.swim}" width="100%" >
<t:row id="g_73" >
<t:label id="g_74" text="#{rr.literals.test_date2}" width="150" />
<t:calendarfield id="g_75" attributemacro="entityDetailMacro(TestDetailUI,date2)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_76" />
<t:rowdistance id="g_77" />
<t:row id="g_78" >
<t:label id="g_79" text="#{rr.literals.test_assistance}" width="150" />
<t:field id="g_80" attributemacro="entityDetailMacro(TestDetailUI,assistant_name)" width="200" />
</t:row>
<t:rowdistance id="g_81" />
<t:row id="g_82" >
<t:label id="g_83" text="#{rr.literals.baths}" width="150" />
<t:field id="g_84" attributemacro="entityDetailMacro(TestDetailUI,baths)" width="200" />
</t:row>
<t:rowdistance id="g_85" />
<t:row id="g_86" >
<t:label id="g_87" text="#{rr.literals.pools}" width="150" />
<t:field id="g_88" attributemacro="entityDetailMacro(TestDetailUI,pool)" width="200" />
</t:row>
<t:rowdistance id="g_89" height="15" />
<t:row id="g_90" >
<t:label id="g_91" text="#{rr.literals.test_distance}" width="150" />
<t:formattedfield id="g_92" attributemacro="entityDetailMacro(TestDetailUI,distance)" format="int" width="50" />
</t:row>
<t:rowdistance id="g_93" />
<t:row id="g_94" >
<t:label id="g_95" text="#{rr.literals.test_splits}" width="150" />
<t:formattedfield id="g_96" attributemacro="entityDetailMacro(TestDetailUI,splits)" format="int" width="50" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_97" height="15" />
<t:row id="g_98" rendered="#{d.TestDetailUI.protocol==false}" >
<t:button id="g_99" actionListener="#{d.TestDetailUI.onProtocolCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testprotocol}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_100" padding="5" rendered="#{d.TestDetailUI.protocol}" rowdistance="2" text="#{rr.literals.test_protocol}" >
<t:row id="g_101" >
<t:pane id="g_102" >
<t:row id="g_103" >
<t:label id="g_104" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_105" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="510" />
</t:row>
<t:rowdistance id="g_106" />
<t:row id="g_107" >
<t:fixgrid id="g_108" avoidroundtrips="true" border="#808080" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridTreadmill}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_109" columnresizingenabled="false" width="20" >
<t:gridrowselector id="g_110" />
</t:gridcol>
<t:gridcol id="g_111" align="center" text="Stufe" width="50" >
<t:formattedfield id="g_112" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_113" align="center" text="Geschwindigkeit" width="90" >
<t:formattedfield id="g_114" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_115" align="center" text="Steigung" width="60" >
<t:formattedfield id="g_116" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{incline}" />
</t:gridcol>
<t:gridcol id="g_117" align="center" text="Zeit (Stufe)" width="75" >
<t:field id="g_118" enabled="#{d.TestDetailUI.enabled}" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_119" align="center" text="Zeit(Gesamt)" width="75" >
<t:field id="g_120" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_121" align="center" text="Laktat" width="45" >
<t:formattedfield id="g_122" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_123" align="center" text="HF" width="45" >
<t:formattedfield id="g_124" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_125" align="center" text="O2" width="45" >
<t:formattedfield id="g_126" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_127" align="center" text="CO2" width="45" >
<t:formattedfield id="g_128" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_129" align="center" text="RQ" width="45" >
<t:formattedfield id="g_130" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_131" />
<t:row id="g_132" >
<t:button id="g_133" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_134" />
<t:button id="g_135" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
</t:tabbedpanetab>
</t:tabbedpane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
