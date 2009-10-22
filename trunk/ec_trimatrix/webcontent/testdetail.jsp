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
<t:tabbedpanetab id="g_5" comment="Detail" padding="5" rowdistance="2" text="#{rr.literals.test}" >
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
<t:formattedfield id="g_96" actionListener="#{d.TestDetailUI.onChangeData}" attributemacro="entityDetailMacro(TestDetailUI,splits)" flush="true" format="int" userhint="max. 8" width="50" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_97" height="15" />
<t:row id="g_98" rendered="#{d.TestDetailUI.protocol==false}" >
<t:button id="g_99" actionListener="#{d.TestDetailUI.onProtocolCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testprotocol}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_100" comment="Protocol" padding="5" rendered="#{d.TestDetailUI.protocol}" rowdistance="2" text="#{rr.literals.test_protocol}" >
<t:row id="g_101" comment="treadmill protocol" rendered="#{d.TestDetailUI.treadmill}" >
<t:pane id="g_102" rowdistance="2" >
<t:row id="g_103" >
<t:label id="g_104" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_105" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_106" >
<t:label id="g_107" text="#{rr.literals.model_treadmill}" width="100" />
<t:field id="g_108" attributemacro="entityDetailMacro(TestDetailUI,model)" width="200" />
</t:row>
<t:row id="g_109" >
<t:label id="g_110" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_111" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:row id="g_112" >
<t:label id="g_113" text="#{rr.literals.model_spiro}" width="100" />
<t:field id="g_114" attributemacro="entityDetailMacro(TestDetailUI,model_spiro)" width="200" />
</t:row>
<t:rowdistance id="g_115" />
<t:row id="g_116" >
<t:coldistance id="g_117" width="100%" />
<t:link id="g_118" actionListener="#{d.TestDetailUI.gridTreadmill.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_119" >
<t:fixgrid id="g_120" avoidroundtrips="1" bordercolor="#C0C0C0" borderheight="1" borderwidth="1" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridTreadmill}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_121" columnresizingenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_122" />
</t:gridcol>
<t:gridcol id="g_123" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_124" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_125" align="center" sortenabled="false" text="#{rr.literals.speed}" width="90" >
<t:formattedfield id="g_126" enabled="false" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_127" align="center" sortenabled="false" text="#{rr.literals.incline}" width="60" >
<t:formattedfield id="g_128" enabled="false" format="int" value=".{incline}" />
</t:gridcol>
<t:gridcol id="g_129" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_130" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_131" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_132" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_133" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_134" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_135" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_136" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_137" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_138" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_139" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_140" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_141" align="center" sortenabled="false" text="RQ" width="45" >
<t:formattedfield id="g_142" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_143" />
<t:row id="g_144" >
<t:button id="g_145" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_146" />
<t:button id="g_147" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_148" comment="ergo protocol" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_149" height="100%" rowdistance="2" width="100%" >
<t:row id="g_150" >
<t:label id="g_151" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_152" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_153" >
<t:label id="g_154" text="#{rr.literals.model_ergo}" width="100" />
<t:field id="g_155" attributemacro="entityDetailMacro(TestDetailUI,model)" width="200" />
</t:row>
<t:row id="g_156" >
<t:label id="g_157" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_158" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:row id="g_159" >
<t:label id="g_160" text="#{rr.literals.model_spiro}" width="100" />
<t:field id="g_161" attributemacro="entityDetailMacro(TestDetailUI,model_spiro)" width="200" />
</t:row>
<t:rowdistance id="g_162" />
<t:row id="g_163" >
<t:coldistance id="g_164" width="100%" />
<t:link id="g_165" actionListener="#{d.TestDetailUI.gridErgo.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_166" >
<t:fixgrid id="g_167" avoidroundtrips="1" border="#808080" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridErgo}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_168" columnresizingenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_169" />
</t:gridcol>
<t:gridcol id="g_170" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_171" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_172" align="center" sortenabled="false" text="#{rr.literals.power}" width="90" >
<t:formattedfield id="g_173" enabled="false" format="int" value=".{power}" />
</t:gridcol>
<t:gridcol id="g_174" align="center" sortenabled="false" text="#{rr.literals.cadence}" width="60" >
<t:field id="g_175" enabled="false" text=".{cadence}" />
</t:gridcol>
<t:gridcol id="g_176" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_177" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_178" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_179" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_180" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_181" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_182" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_183" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_184" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_185" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_186" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_187" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_188" align="center" sortenabled="false" text="RQ" width="15" >
<t:formattedfield id="g_189" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_190" />
<t:row id="g_191" >
<t:button id="g_192" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_193" />
<t:button id="g_194" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_195" comment="swim protocol" rendered="#{d.TestDetailUI.swim}" >
<t:pane id="g_196" height="100%" rowdistance="2" width="100%" >
<t:row id="g_197" >
<t:label id="g_198" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_199" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_200" >
<t:label id="g_201" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_202" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:rowdistance id="g_203" height="15" />
<t:row id="g_204" >
<t:label id="g_205" text="#{rr.literals.max_performance} #{d.TestDetailUI.values.distance} m  " width="200" />
<t:field id="g_206" attributemacro="entityDetailMacro(TestDetailUI,performance_max)" flush="true" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="100" />
</t:row>
<t:rowdistance id="g_207" />
<t:row id="g_208" >
<t:coldistance id="g_209" width="100%" />
<t:link id="g_210" actionListener="#{d.TestDetailUI.gridSwim.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_211" >
<t:fixgrid id="g_212" avoidroundtrips="1" border="#808080" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridSwim}" sbvisibleamount="20" width="100%" >
<t:gridcol id="g_213" align="center" sortenabled="false" text="#{rr.literals.step}" width="100" >
<t:treenode id="g_214" bgpaint="rectangle(0,0,100%,100%,#FFFFFF40,#FFFFFF10,vertical)" text=".{step}" />
</t:gridcol>
<t:gridcol id="g_215" sortenabled="false" width="20" >
<t:pane id="g_216" bgpaint="null!" >
<t:row id="g_217" >
<t:button id="g_218" actionListener=".{onAddSubItem}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" horizontaltextposition="center" image="/images/icons/add.png" rendered=".{topNode}" verticaltextposition="middle" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_219" sortenabled="false" width="20" >
<t:pane id="g_220" bgpaint="null!" >
<t:row id="g_221" >
<t:checkbox id="g_222" actionListener=".{onMarkItem}" enabled="#{d.TestDetailUI.enabled}" flush="true" rendered=".{topNode==false}" selected=".{valid}" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_223" align="center" sortenabled="false" text="#{rr.literals.intensity}" width="60" >
<t:formattedfield id="g_224" bgpaint=".{bgPaintTop}" enabled=".{enabledAndTopNode}" flush="true" format="int" maxlength="3" userhint="max. 100%" value=".{intensity}" />
</t:gridcol>
<t:gridcol id="g_225" align="center" sortenabled="false" text="#{rr.literals.target_time}" width="60" >
<t:field id="g_226" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{targetTime}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_227" align="center" sortenabled="false" text="#{rr.literals.time}" width="60" >
<t:field id="g_228" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" enabled=".{enabled}" flush="true" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_229" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="100" >
<t:field id="g_230" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" enabled=".{enabled}" flush="true" text=".{lactate}" userhint="lactate@minutes ..." />
</t:gridcol>
<t:gridcol id="g_231" align="center" sortenabled="false" text="#{rr.literals.hr}" width="100" >
<t:formattedfield id="g_232" actionListener=".{onChangeItem}" enabled=".{enabled}" flush="true" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_233" attributemacro="swimSplitsHeadMacro(0)" rendered="true" >
<t:pane id="g_234" >
<t:row id="g_235" >
<t:field id="g_236" attributemacro="swimSplitsItemTimeMacro(0)" rendered="true" />
<t:formattedfield id="g_237" attributemacro="swimSplitsItemStrokeMacro(0)" rendered="true" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_238" attributemacro="swimSplitsHeadMacro(1)" >
<t:pane id="g_239" >
<t:row id="g_240" >
<t:field id="g_241" attributemacro="swimSplitsItemTimeMacro(1)" />
<t:formattedfield id="g_242" attributemacro="swimSplitsItemStrokeMacro(1)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_243" attributemacro="swimSplitsHeadMacro(2)" >
<t:pane id="g_244" >
<t:row id="g_245" >
<t:field id="g_246" attributemacro="swimSplitsItemTimeMacro(2)" />
<t:formattedfield id="g_247" attributemacro="swimSplitsItemStrokeMacro(2)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_248" attributemacro="swimSplitsHeadMacro(3)" >
<t:pane id="g_249" >
<t:row id="g_250" >
<t:field id="g_251" attributemacro="swimSplitsItemTimeMacro(3)" />
<t:formattedfield id="g_252" attributemacro="swimSplitsItemStrokeMacro(3)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_253" attributemacro="swimSplitsHeadMacro(4)" >
<t:pane id="g_254" >
<t:row id="g_255" >
<t:field id="g_256" attributemacro="swimSplitsItemTimeMacro(4)" />
<t:formattedfield id="g_257" attributemacro="swimSplitsItemStrokeMacro(4)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_258" attributemacro="swimSplitsHeadMacro(5)" >
<t:pane id="g_259" >
<t:row id="g_260" >
<t:field id="g_261" attributemacro="swimSplitsItemTimeMacro(5)" />
<t:formattedfield id="g_262" attributemacro="swimSplitsItemStrokeMacro(5)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_263" attributemacro="swimSplitsHeadMacro(6)" >
<t:pane id="g_264" >
<t:row id="g_265" >
<t:field id="g_266" attributemacro="swimSplitsItemTimeMacro(6)" />
<t:formattedfield id="g_267" attributemacro="swimSplitsItemStrokeMacro(6)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_268" attributemacro="swimSplitsHeadMacro(7)" >
<t:pane id="g_269" >
<t:row id="g_270" >
<t:field id="g_271" attributemacro="swimSplitsItemTimeMacro(7)" />
<t:formattedfield id="g_272" attributemacro="swimSplitsItemStrokeMacro(7)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_273" align="center" sortenabled="false" text="#{rr.literals.comment}" width="200" >
<t:field id="g_274" enabled=".{enabled}" maxlength="1000" text=".{comment}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_275" />
<t:row id="g_276" >
<t:button id="g_277" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_278" />
<t:button id="g_279" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_280" height="15" />
<t:row id="g_281" rendered="#{d.TestDetailUI.analysis==false}" >
<t:button id="g_282" actionListener="#{d.TestDetailUI.onAnalysisCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testanalysis}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_283" comment="Analysis" padding="5" rendered="#{d.TestDetailUI.analysis}" rowdistance="2" text="#{rr.literals.analysis}" >
<t:row id="g_284" >
<t:pane id="g_285" width="100%" >
<t:row id="g_286" >
<t:label id="g_287" text="Größe" />
<t:coldistance id="g_288" />
<t:formattedfield id="g_289" format="int" userhint="Breite" value="#{d.TestDetailUI.width}" width="50" />
<t:coldistance id="g_290" />
<t:label id="g_291" text="X" />
<t:coldistance id="g_292" />
<t:formattedfield id="g_293" format="int" userhint="Höhe" value="#{d.TestDetailUI.height}" width="50" />
<t:coldistance id="g_294" />
<t:button id="g_295" actionListener="#{d.TestDetailUI.onRefresh}" text="#{rr.literals.list_refresh}" />
</t:row>
<t:rowdistance id="g_296" />
<t:row id="g_297" >
<t:label id="g_298" text="#{rr.literals.formula}" width="100" />
<t:field id="g_299" enabled="false" text="#{d.TestDetailUI.formel}" width="400" />
</t:row>
<t:rowdistance id="g_300" />
<t:row id="g_301" >
<t:label id="g_302" text="#{rr.literals.correlation}" width="100" />
<t:field id="g_303" enabled="false" text="#{d.TestDetailUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_304" />
<t:row id="g_305" >
<t:heximage id="g_306" align="center" border="#808080" height="#{d.TestDetailUI.height}" hexdata="#{d.TestDetailUI.diagram}" rowalignmenty="center" valign="center" width="#{d.TestDetailUI.width}" />
</t:row>
<t:rowdistance id="g_307" />
<t:rowdistance id="g_308" />
<t:row id="g_309" >
<t:label id="g_310" text="#{rr.literals.function}" width="100" />
<t:combobox id="g_311" actionListener="#{d.TestDetailUI.resetResult}" flush="true" validvaluesbinding="#{d.TestDetailUI.functionsVvb}" value="#{d.TestDetailUI.function}" width="200" />
</t:row>
<t:rowdistance id="g_312" />
<t:row id="g_313" >
<t:label id="g_314" text="#{rr.literals.offset}" width="100" />
<t:formattedfield id="g_315" actionListener="#{d.TestDetailUI.resetResult}" flush="true" format="double" value="#{d.TestDetailUI.offset}" width="50" />
</t:row>
<t:row id="g_316" >
<t:label id="g_317" text="#{rr.literals.degree}" width="100" />
<t:spinner id="g_318" actionListener="#{d.TestDetailUI.resetResult}" flush="true" maxvalue="6" minvalue="0" value="#{d.TestDetailUI.degree}" width="50" />
</t:row>
<t:rowdistance id="g_319" />
<t:row id="g_320" >
<t:label id="g_321" text="x" width="100" />
<t:formattedfield id="g_322" actionListener="#{d.TestDetailUI.onChangeXY}" clientname="x" flush="true" format="double" value="#{d.TestDetailUI.valueX}" width="50" />
</t:row>
<t:rowdistance id="g_323" />
<t:row id="g_324" >
<t:label id="g_325" text="y" width="100" />
<t:formattedfield id="g_326" actionListener="#{d.TestDetailUI.onChangeXY}" clientname="y" flush="true" format="double" value="#{d.TestDetailUI.valueY}" width="50" />
</t:row>
<t:rowdistance id="g_327" />
<t:row id="g_328" >
<t:field id="g_329" text="#{d.TestDetailUI.maxWidth}" width="100" />
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
