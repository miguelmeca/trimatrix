<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="testdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:rowdynamiccontent id="g_2" contentbinding="#{d.TestDetailUI.labelRow}" />
<t:row id="g_3" >
<t:tabbedpane id="g_4" height="100%" width="100%" >
<t:tabbedpanetab id="g_5" comment="Detail" padding="5" rowdistance="2" text="#{rr.literals.test}" >
<t:row id="g_6" >
<t:pane id="g_7" rowdistance="5" >
<t:row id="g_8" >
<t:label id="g_9" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_10" actionListener="#{d.TestDetailUI.onTypeChange}" attributemacro="entityDetailMacro(TestDetailUI,type)" enabled="#{d.TestDetailUI.typeEnabled}" flush="true" validvaluesbinding="#{d.TestDetailUI.testTypesVvb}" width="200" />
<t:coldistance id="g_11" />
<t:label id="g_12" text="#{rr.literals.test_date}" width="100" />
<t:calendarfield id="g_13" attributemacro="entityDetailMacro(TestDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:row id="g_14" >
<t:label id="g_15" text="#{rr.literals.athlete}" width="100" />
<t:field id="g_16" attributemacro="entityDetailMacro(TestDetailUI,person)" enabled="false" focusable="false" width="175" />
<t:button id="g_17" actionListener="#{d.TestDetailUI.onAthleteSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
<t:coldistance id="g_18" />
<t:label id="g_19" text="#{rr.literals.doctor}" width="100" />
<t:field id="g_20" attributemacro="entityDetailMacro(TestDetailUI,doctor)" enabled="false" focusable="false" width="175" />
<t:button id="g_21" actionListener="#{d.TestDetailUI.onDoctorSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
</t:row>
<t:row id="g_22" >
<t:label id="g_23" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_24" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="510" />
</t:row>
<t:row id="g_25" />
</t:pane>
</t:row>
<t:rowdistance id="g_26" height="15" />
<t:row id="g_27" comment="treadmill" rendered="#{d.TestDetailUI.treadmill}" >
<t:pane id="g_28" rowdistance="5" width="100%" >
<t:row id="g_29" >
<t:label id="g_30" text="#{rr.literals.speed_init}" width="150" />
<t:formattedfield id="g_31" attributemacro="entityDetailMacro(TestDetailUI,speed_init)" format="double" userhint="km/h" width="50" />
<t:coldistance id="g_32" />
<t:checkbox id="g_33" attributemacro="entityDetailMacro(TestDetailUI,speed_variable)" flush="true" selected="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_34" />
<t:label id="g_35" rendered="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.speed_step}" width="150" />
<t:formattedfield id="g_36" attributemacro="entityDetailMacro(TestDetailUI,speed_step)" format="double" rendered="#{d.TestDetailUI.values.speed_variable}" userhint="km/h" width="50" />
</t:row>
<t:row id="g_37" >
<t:label id="g_38" text="#{rr.literals.incline_init}" width="150" />
<t:formattedfield id="g_39" attributemacro="entityDetailMacro(TestDetailUI,incline_init)" format="int" userhint="%" width="50" />
<t:coldistance id="g_40" />
<t:checkbox id="g_41" attributemacro="entityDetailMacro(TestDetailUI,incline_variable)" flush="true" selected="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_42" />
<t:label id="g_43" rendered="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.incline_step}" width="150" />
<t:formattedfield id="g_44" attributemacro="entityDetailMacro(TestDetailUI,incline_step)" format="int" rendered="#{d.TestDetailUI.values.incline_variable}" userhint="%" width="50" />
</t:row>
<t:row id="g_45" >
<t:label id="g_46" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_47" attributemacro="entityDetailMacro(TestDetailUI,treadmill_step_time)" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_48" comment="ergo" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_49" rowdistance="5" width="100%" >
<t:row id="g_50" >
<t:label id="g_51" text="#{rr.literals.power_init}" width="150" />
<t:formattedfield id="g_52" attributemacro="entityDetailMacro(TestDetailUI,power_init)" format="int" width="50" />
<t:coldistance id="g_53" />
<t:label id="g_54" text="#{rr.literals.power_step}" width="150" />
<t:formattedfield id="g_55" attributemacro="entityDetailMacro(TestDetailUI,power_step)" format="int" width="50" />
</t:row>
<t:row id="g_56" >
<t:label id="g_57" text="#{rr.literals.cadence_low}" width="150" />
<t:formattedfield id="g_58" attributemacro="entityDetailMacro(TestDetailUI,cadence_low)" format="int" width="50" />
<t:coldistance id="g_59" />
<t:label id="g_60" text="#{rr.literals.cadence_high}" width="150" />
<t:formattedfield id="g_61" attributemacro="entityDetailMacro(TestDetailUI,cadence_high)" format="int" width="50" />
</t:row>
<t:row id="g_62" >
<t:label id="g_63" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_64" attributemacro="entityDetailMacro(TestDetailUI,ergo_step_time)" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" userhint="mm:ss" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_65" comment="swim" >
<t:pane id="g_66" rendered="#{d.TestDetailUI.swim}" rowdistance="5" width="100%" >
<t:row id="g_67" >
<t:label id="g_68" text="#{rr.literals.test_date2}" width="150" />
<t:calendarfield id="g_69" attributemacro="entityDetailMacro(TestDetailUI,date2)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_70" />
<t:row id="g_71" >
<t:label id="g_72" text="#{rr.literals.test_assistance}" width="150" />
<t:field id="g_73" attributemacro="entityDetailMacro(TestDetailUI,assistant_name)" width="200" />
</t:row>
<t:row id="g_74" >
<t:label id="g_75" text="#{rr.literals.baths}" width="150" />
<t:field id="g_76" attributemacro="entityDetailMacro(TestDetailUI,baths)" width="200" />
</t:row>
<t:row id="g_77" >
<t:label id="g_78" text="#{rr.literals.pools}" width="150" />
<t:field id="g_79" attributemacro="entityDetailMacro(TestDetailUI,pool)" width="200" />
</t:row>
<t:rowdistance id="g_80" />
<t:row id="g_81" >
<t:label id="g_82" text="#{rr.literals.test_distance}" width="150" />
<t:formattedfield id="g_83" attributemacro="entityDetailMacro(TestDetailUI,distance)" format="int" width="50" />
</t:row>
<t:row id="g_84" >
<t:label id="g_85" text="#{rr.literals.test_splits}" width="150" />
<t:formattedfield id="g_86" actionListener="#{d.TestDetailUI.onChangeData}" attributemacro="entityDetailMacro(TestDetailUI,splits)" flush="true" format="int" userhint="max. 8" width="50" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_87" height="15" />
<t:row id="g_88" rendered="#{d.TestDetailUI.protocol==false}" >
<t:button id="g_89" actionListener="#{d.TestDetailUI.onProtocolCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testprotocol}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_90" comment="Protocol" padding="5" rendered="#{d.TestDetailUI.protocol}" rowdistance="2" text="#{rr.literals.test_protocol}" >
<t:row id="g_91" comment="treadmill protocol" rendered="#{d.TestDetailUI.treadmill}" >
<t:pane id="g_92" rowdistance="2" >
<t:row id="g_93" >
<t:label id="g_94" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_95" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_96" >
<t:label id="g_97" text="#{rr.literals.model_treadmill}" width="100" />
<t:field id="g_98" attributemacro="entityDetailMacro(TestDetailUI,model)" width="200" />
</t:row>
<t:row id="g_99" >
<t:label id="g_100" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_101" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:row id="g_102" >
<t:label id="g_103" text="#{rr.literals.model_spiro}" width="100" />
<t:field id="g_104" attributemacro="entityDetailMacro(TestDetailUI,model_spiro)" width="200" />
</t:row>
<t:rowdistance id="g_105" />
<t:row id="g_106" >
<t:coldistance id="g_107" width="100%" />
<t:link id="g_108" actionListener="#{d.TestDetailUI.gridTreadmill.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_109" >
<t:fixgrid id="g_110" avoidroundtrips="1" bordercolor="#C0C0C0" borderheight="1" borderwidth="1" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridTreadmill}" sbvisibleamount="20" showemptyrows="false" width="100%" >
<t:gridcol id="g_111" columnresizingenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_112" />
</t:gridcol>
<t:gridcol id="g_113" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_114" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_115" align="center" sortenabled="false" text="#{rr.literals.speed}" width="90" >
<t:formattedfield id="g_116" enabled="false" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_117" align="center" sortenabled="false" text="#{rr.literals.incline}" width="60" >
<t:formattedfield id="g_118" enabled="false" format="int" value=".{incline}" />
</t:gridcol>
<t:gridcol id="g_119" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_120" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_121" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_122" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_123" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_124" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_125" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_126" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_127" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_128" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_129" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_130" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_131" align="center" sortenabled="false" text="RQ" width="45" >
<t:formattedfield id="g_132" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_133" />
<t:row id="g_134" >
<t:button id="g_135" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_136" />
<t:button id="g_137" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_138" comment="ergo protocol" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_139" height="100%" rowdistance="2" width="100%" >
<t:row id="g_140" >
<t:label id="g_141" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_142" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_143" >
<t:label id="g_144" text="#{rr.literals.model_ergo}" width="100" />
<t:field id="g_145" attributemacro="entityDetailMacro(TestDetailUI,model)" width="200" />
</t:row>
<t:row id="g_146" >
<t:label id="g_147" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_148" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:row id="g_149" >
<t:label id="g_150" text="#{rr.literals.model_spiro}" width="100" />
<t:field id="g_151" attributemacro="entityDetailMacro(TestDetailUI,model_spiro)" width="200" />
</t:row>
<t:rowdistance id="g_152" />
<t:row id="g_153" >
<t:coldistance id="g_154" width="100%" />
<t:link id="g_155" actionListener="#{d.TestDetailUI.gridErgo.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_156" >
<t:fixgrid id="g_157" avoidroundtrips="1" border="#808080" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridErgo}" sbvisibleamount="20" showemptyrows="false" width="100%" >
<t:gridcol id="g_158" columnresizingenabled="false" sortenabled="false" width="20" >
<t:gridrowselector id="g_159" />
</t:gridcol>
<t:gridcol id="g_160" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_161" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_162" align="center" sortenabled="false" text="#{rr.literals.power}" width="90" >
<t:formattedfield id="g_163" enabled="false" format="int" value=".{power}" />
</t:gridcol>
<t:gridcol id="g_164" align="center" sortenabled="false" text="#{rr.literals.cadence}" width="60" >
<t:field id="g_165" enabled="false" text=".{cadence}" />
</t:gridcol>
<t:gridcol id="g_166" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_167" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_168" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_169" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_170" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_171" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_172" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_173" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_174" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_175" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_176" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_177" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_178" align="center" sortenabled="false" text="RQ" width="45" >
<t:formattedfield id="g_179" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_180" />
<t:row id="g_181" >
<t:button id="g_182" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_183" />
<t:button id="g_184" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_185" comment="swim protocol" rendered="#{d.TestDetailUI.swim}" >
<t:pane id="g_186" height="100%" rowdistance="2" width="100%" >
<t:row id="g_187" >
<t:label id="g_188" rowalignmenty="top" text="#{rr.literals.description}" width="120" />
<t:textarea id="g_189" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_190" >
<t:label id="g_191" text="#{rr.literals.model_lactate}" width="120" />
<t:field id="g_192" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:rowdistance id="g_193" height="15" />
<t:row id="g_194" >
<t:label id="g_195" text="#{rr.literals.max_performance} #{d.TestDetailUI.values.distance} m  " width="120" />
<t:field id="g_196" attributemacro="entityDetailMacro(TestDetailUI,performance_max)" flush="true" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" tooltip="mm:ss" userhint="mm:ss" width="50" />
<t:coldistance id="g_197" />
<t:label id="g_198" text="#{rr.literals.speed}" width="90" />
<t:formattedfield id="g_199" enabled="false" format="double" tooltip="m/s" userhint="m/s" value="#{d.TestDetailUI.maxSpeed}" width="50" />
</t:row>
<t:rowdistance id="g_200" />
<t:row id="g_201" >
<t:coldistance id="g_202" width="100%" />
<t:link id="g_203" actionListener="#{d.TestDetailUI.gridSwim.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_204" >
<t:fixgrid id="g_205" avoidroundtrips="1" border="#808080" enabled="#{d.TestDetailUI.enabled}" multiselect="false" objectbinding="#{d.TestDetailUI.gridSwim}" sbvisibleamount="20" showemptyrows="false" width="100%" >
<t:gridcol id="g_206" align="center" sortenabled="false" text="#{rr.literals.step}" width="100" >
<t:treenode id="g_207" bgpaint="rectangle(0,0,100%,100%,#FFFFFF40,#FFFFFF10,vertical)" text=".{step}" />
</t:gridcol>
<t:gridcol id="g_208" sortenabled="false" width="20" >
<t:pane id="g_209" bgpaint="null!" >
<t:row id="g_210" >
<t:button id="g_211" actionListener=".{onAddSubItem}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" horizontaltextposition="center" image="/images/icons/add.png" rendered=".{topNode}" verticaltextposition="middle" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_212" sortenabled="false" width="20" >
<t:pane id="g_213" bgpaint="null!" >
<t:row id="g_214" >
<t:checkbox id="g_215" actionListener=".{onMarkItem}" enabled="#{d.TestDetailUI.enabled}" flush="true" rendered=".{topNode==false}" selected=".{valid}" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_216" align="center" sortenabled="false" text="#{rr.literals.intensity}" width="60" >
<t:formattedfield id="g_217" bgpaint=".{bgPaintTop}" enabled=".{enabledAndTopNode}" flush="true" format="int" maxlength="3" userhint="max. 100%" value=".{intensity}" />
</t:gridcol>
<t:gridcol id="g_218" align="center" sortenabled="false" text="#{rr.literals.target_time}" width="60" >
<t:field id="g_219" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{targetTime}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_220" align="center" sortenabled="false" text="m/s" width="50" >
<t:formattedfield id="g_221" enabled="false" format="double" value=".{targetSpeed}" />
</t:gridcol>
<t:gridcol id="g_222" align="center" sortenabled="false" text="#{rr.literals.time}" width="60" >
<t:field id="g_223" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" enabled=".{enabled}" flush="true" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_224" align="center" sortenabled="false" text="m/s" width="50" >
<t:formattedfield id="g_225" enabled="false" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_226" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="50" >
<t:field id="g_227" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" enabled=".{enabled}" flush="true" text=".{lactate}" userhint="lactate@minutes ..." />
</t:gridcol>
<t:gridcol id="g_228" align="center" sortenabled="false" text="#{rr.literals.hr}" width="50" >
<t:formattedfield id="g_229" actionListener=".{onChangeItem}" enabled=".{enabled}" flush="true" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_230" attributemacro="swimSplitsHeadMacro(0)" rendered="true" >
<t:pane id="g_231" >
<t:row id="g_232" >
<t:field id="g_233" attributemacro="swimSplitsItemTimeMacro(0)" rendered="true" />
<t:formattedfield id="g_234" attributemacro="swimSplitsItemStrokeMacro(0)" rendered="true" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_235" attributemacro="swimSplitsHeadMacro(1)" >
<t:pane id="g_236" >
<t:row id="g_237" >
<t:field id="g_238" attributemacro="swimSplitsItemTimeMacro(1)" />
<t:formattedfield id="g_239" attributemacro="swimSplitsItemStrokeMacro(1)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_240" attributemacro="swimSplitsHeadMacro(2)" >
<t:pane id="g_241" >
<t:row id="g_242" >
<t:field id="g_243" attributemacro="swimSplitsItemTimeMacro(2)" />
<t:formattedfield id="g_244" attributemacro="swimSplitsItemStrokeMacro(2)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_245" attributemacro="swimSplitsHeadMacro(3)" >
<t:pane id="g_246" >
<t:row id="g_247" >
<t:field id="g_248" attributemacro="swimSplitsItemTimeMacro(3)" />
<t:formattedfield id="g_249" attributemacro="swimSplitsItemStrokeMacro(3)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_250" attributemacro="swimSplitsHeadMacro(4)" >
<t:pane id="g_251" >
<t:row id="g_252" >
<t:field id="g_253" attributemacro="swimSplitsItemTimeMacro(4)" />
<t:formattedfield id="g_254" attributemacro="swimSplitsItemStrokeMacro(4)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_255" attributemacro="swimSplitsHeadMacro(5)" >
<t:pane id="g_256" >
<t:row id="g_257" >
<t:field id="g_258" attributemacro="swimSplitsItemTimeMacro(5)" />
<t:formattedfield id="g_259" attributemacro="swimSplitsItemStrokeMacro(5)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_260" attributemacro="swimSplitsHeadMacro(6)" >
<t:pane id="g_261" >
<t:row id="g_262" >
<t:field id="g_263" attributemacro="swimSplitsItemTimeMacro(6)" />
<t:formattedfield id="g_264" attributemacro="swimSplitsItemStrokeMacro(6)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_265" attributemacro="swimSplitsHeadMacro(7)" >
<t:pane id="g_266" >
<t:row id="g_267" >
<t:field id="g_268" attributemacro="swimSplitsItemTimeMacro(7)" />
<t:formattedfield id="g_269" attributemacro="swimSplitsItemStrokeMacro(7)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_270" align="center" sortenabled="false" text="#{rr.literals.comment}" width="200" >
<t:field id="g_271" enabled=".{enabled}" maxlength="1000" text=".{comment}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_272" />
<t:row id="g_273" >
<t:button id="g_274" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" text="Hinzufügen" />
<t:coldistance id="g_275" />
<t:button id="g_276" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" text="Entfernen" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_277" height="15" />
<t:row id="g_278" rendered="#{d.TestDetailUI.analysis==false}" >
<t:button id="g_279" actionListener="#{d.TestDetailUI.onAnalysisCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testanalysis}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_280" comment="Analysis" padding="5" rendered="#{d.TestDetailUI.analysis}" rowdistance="2" text="#{rr.literals.analysis}" >
<t:row id="g_281" >
<t:pane id="g_282" width="100%" >
<t:row id="g_283" >
<t:label id="g_284" text="Größe" />
<t:coldistance id="g_285" />
<t:formattedfield id="g_286" format="int" userhint="Breite" value="#{d.TestDetailUI.width}" width="50" />
<t:coldistance id="g_287" />
<t:label id="g_288" text="X" />
<t:coldistance id="g_289" />
<t:formattedfield id="g_290" format="int" userhint="Höhe" value="#{d.TestDetailUI.height}" width="50" />
<t:coldistance id="g_291" />
<t:button id="g_292" actionListener="#{d.TestDetailUI.onRefresh}" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_293" />
<t:button id="g_294" actionListener="#{d.TestDetailUI.onSetZones}" text="#{rr.literals.set_zones}" />
</t:row>
<t:rowdistance id="g_295" />
<t:row id="g_296" >
<t:label id="g_297" text="#{rr.literals.formula}" width="100" />
<t:field id="g_298" enabled="false" text="#{d.TestDetailUI.formel}" width="400" />
</t:row>
<t:rowdistance id="g_299" />
<t:row id="g_300" >
<t:label id="g_301" text="#{rr.literals.correlation}" width="100" />
<t:field id="g_302" enabled="false" text="#{d.TestDetailUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_303" />
<t:row id="g_304" >
<t:heximage id="g_305" align="center" border="#808080" height="#{d.TestDetailUI.height}" hexdata="#{d.TestDetailUI.diagram}" rowalignmenty="center" valign="center" width="#{d.TestDetailUI.width}" />
</t:row>
<t:rowdistance id="g_306" />
<t:rowdistance id="g_307" />
<t:row id="g_308" >
<t:label id="g_309" text="#{rr.literals.function}" width="100" />
<t:combobox id="g_310" actionListener="#{d.TestDetailUI.resetResult}" flush="true" validvaluesbinding="#{d.TestDetailUI.functionsVvb}" value="#{d.TestDetailUI.function}" width="200" />
</t:row>
<t:rowdistance id="g_311" />
<t:row id="g_312" >
<t:label id="g_313" text="#{rr.literals.offset}" width="100" />
<t:formattedfield id="g_314" actionListener="#{d.TestDetailUI.resetResult}" flush="true" format="double" value="#{d.TestDetailUI.offset}" width="50" />
</t:row>
<t:row id="g_315" >
<t:label id="g_316" text="#{rr.literals.degree}" width="100" />
<t:spinner id="g_317" actionListener="#{d.TestDetailUI.resetResult}" flush="true" maxvalue="6" minvalue="0" value="#{d.TestDetailUI.degree}" width="50" />
</t:row>
<t:rowdistance id="g_318" />
<t:row id="g_319" >
<t:label id="g_320" text="x" width="100" />
<t:formattedfield id="g_321" actionListener="#{d.TestDetailUI.onChangeXY}" clientname="x" flush="true" format="double" value="#{d.TestDetailUI.valueX}" width="50" />
</t:row>
<t:rowdistance id="g_322" />
<t:row id="g_323" >
<t:label id="g_324" text="y" width="100" />
<t:formattedfield id="g_325" actionListener="#{d.TestDetailUI.onChangeXY}" clientname="y" flush="true" format="double" value="#{d.TestDetailUI.valueY}" width="50" />
</t:row>
<t:rowdistance id="g_326" />
<t:row id="g_327" >
<t:label id="g_328" text="hr" width="100" />
<t:formattedfield id="g_329" enabled="false" format="int" value="#{d.TestDetailUI.hr}" width="50" />
</t:row>
<t:rowdistance id="g_330" />
<t:row id="g_331" >
<t:field id="g_332" text="#{d.TestDetailUI.maxWidth}" width="100" />
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
