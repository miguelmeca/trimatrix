<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="testdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:rowdynamiccontent id="g_2" contentbinding="#{d.TestDetailUI.labelRow}" />
<t:row id="g_3" >
<t:tabbedpane id="g_4" height="100%" value="#{d.TestDetailUI.tabIndex}" width="100%" >
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
<t:field id="g_47" actionListener="#{d.TestDetailUI.onTimeFlush}" attributemacro="entityDetailMacro(TestDetailUI,treadmill_step_time)" clientname="treadmill_step_time" flush="true" maxlength="5" userhint="mm:ss" width="50" />
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
<t:field id="g_64" actionListener="#{d.TestDetailUI.onTimeFlush}" attributemacro="entityDetailMacro(TestDetailUI,ergo_step_time)" clientname="ergo_step_time" flush="true" maxlength="5" userhint="mm:ss" width="50" />
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
<t:fixgrid id="g_110" avoidroundtrips="1" enabled="#{d.TestDetailUI.enabled}" horizontalscrollmode="autowithresize" multiselect="false" objectbinding="#{d.TestDetailUI.gridTreadmill}" sbvisibleamount="20" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_111" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_112" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_113" align="center" sortenabled="false" text="#{rr.literals.speed}" width="90" >
<t:formattedfield id="g_114" enabled="false" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_115" align="center" sortenabled="false" text="#{rr.literals.incline}" width="60" >
<t:formattedfield id="g_116" enabled="false" format="int" value=".{incline}" />
</t:gridcol>
<t:gridcol id="g_117" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_118" actionListener=".{onChangeItem}" clientname="step_time" enabled=".{lastItemEnabled}" flush="true" maxlength="5" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_119" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_120" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_121" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_122" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_123" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_124" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_125" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_126" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_127" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_128" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_129" align="center" sortenabled="false" text="RQ" width="45" >
<t:formattedfield id="g_130" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_131" />
<t:row id="g_132" >
<t:button id="g_133" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_134" />
<t:button id="g_135" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_136" comment="ergo protocol" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_137" rowdistance="2" >
<t:row id="g_138" >
<t:label id="g_139" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_140" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_141" >
<t:label id="g_142" text="#{rr.literals.model_ergo}" width="100" />
<t:field id="g_143" attributemacro="entityDetailMacro(TestDetailUI,model)" width="200" />
</t:row>
<t:row id="g_144" >
<t:label id="g_145" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_146" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:row id="g_147" >
<t:label id="g_148" text="#{rr.literals.model_spiro}" width="100" />
<t:field id="g_149" attributemacro="entityDetailMacro(TestDetailUI,model_spiro)" width="200" />
</t:row>
<t:rowdistance id="g_150" />
<t:row id="g_151" >
<t:coldistance id="g_152" width="100%" />
<t:link id="g_153" actionListener="#{d.TestDetailUI.gridErgo.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_154" >
<t:fixgrid id="g_155" avoidroundtrips="1" enabled="#{d.TestDetailUI.enabled}" horizontalscrollmode="autowithresize" multiselect="false" objectbinding="#{d.TestDetailUI.gridErgo}" sbvisibleamount="20" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_156" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_157" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_158" align="center" sortenabled="false" text="#{rr.literals.power}" width="90" >
<t:formattedfield id="g_159" enabled="false" format="int" value=".{power}" />
</t:gridcol>
<t:gridcol id="g_160" align="center" sortenabled="false" text="#{rr.literals.cadence}" width="60" >
<t:field id="g_161" enabled="false" text=".{cadence}" />
</t:gridcol>
<t:gridcol id="g_162" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_163" actionListener=".{onChangeItem}" clientname="step_time" enabled=".{lastItemEnabled}" flush="true" maxlength="5" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_164" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_165" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_166" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_167" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_168" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_169" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_170" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_171" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_172" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_173" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_174" align="center" sortenabled="false" text="RQ" width="45" >
<t:formattedfield id="g_175" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_176" />
<t:row id="g_177" >
<t:button id="g_178" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_179" />
<t:button id="g_180" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_181" comment="swim protocol" rendered="#{d.TestDetailUI.swim}" >
<t:pane id="g_182" rowdistance="2" >
<t:row id="g_183" >
<t:label id="g_184" rowalignmenty="top" text="#{rr.literals.description}" width="120" />
<t:textarea id="g_185" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_186" >
<t:label id="g_187" text="#{rr.literals.model_lactate}" width="120" />
<t:field id="g_188" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:rowdistance id="g_189" height="15" />
<t:row id="g_190" >
<t:label id="g_191" text="#{rr.literals.max_performance} #{d.TestDetailUI.values.distance} m  " width="120" />
<t:field id="g_192" actionListener="#{d.TestDetailUI.onChangeMaxPerformance}" attributemacro="entityDetailMacro(TestDetailUI,performance_max)" flush="true" maxlength="9" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="65" />
<t:coldistance id="g_193" />
<t:label id="g_194" text="#{rr.literals.speed}" width="90" />
<t:formattedfield id="g_195" enabled="false" format="double" tooltip="m/s" userhint="m/s" value="#{d.TestDetailUI.maxSpeed}" width="50" />
</t:row>
<t:rowdistance id="g_196" />
<t:row id="g_197" >
<t:coldistance id="g_198" width="100%" />
<t:link id="g_199" actionListener="#{d.TestDetailUI.gridSwim.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_200" >
<t:fixgrid id="g_201" avoidroundtrips="1" border="#808080" enabled="#{d.TestDetailUI.enabled}" horizontalscrollmode="autowithresize" multiselect="false" objectbinding="#{d.TestDetailUI.gridSwim}" sbvisibleamount="20" showemptyrows="false" >
<t:gridcol id="g_202" align="center" sortenabled="false" text="#{rr.literals.step}" width="100" >
<t:treenode id="g_203" text=".{step}" />
</t:gridcol>
<t:gridcol id="g_204" align="center" sortenabled="false" width="20" >
<t:pane id="g_205" background="rectangle(0,0,100%,50%,#c0c0c008);rectangle(0,50%,100%,50%,#00000006)" >
<t:row id="g_206" >
<t:button id="g_207" actionListener=".{onAddSubItem}" align="center" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" horizontaltextposition="center" image="/images/icons/add.png" rendered=".{topNode}" rowalignmenty="center" verticaltextposition="middle" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_208" sortenabled="false" width="20" >
<t:pane id="g_209" bgpaint="rectangle(0,0,100%,50%,#c0c0c008);rectangle(0,50%,100%,50%,#00000006)" >
<t:row id="g_210" >
<t:checkbox id="g_211" actionListener=".{onMarkItem}" align="center" enabled="#{d.TestDetailUI.enabled}" flush="true" rendered=".{topNode==false}" selected=".{valid}" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_212" align="center" sortenabled="false" text="#{rr.literals.intensity}" width="60" >
<t:formattedfield id="g_213" bgpaint=".{bgPaintTop}" enabled=".{enabledAndTopNode}" flush="true" format="int" maxlength="3" userhint="max. 100%" value=".{intensity}" />
</t:gridcol>
<t:gridcol id="g_214" align="center" sortenabled="false" text="#{rr.literals.target_time}" width="65" >
<t:field id="g_215" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{targetTime}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_216" align="center" sortenabled="false" text="m/s" width="50" >
<t:formattedfield id="g_217" enabled="false" format="double" value=".{targetSpeed}" />
</t:gridcol>
<t:gridcol id="g_218" align="center" sortenabled="false" text="#{rr.literals.time}" width="65" >
<t:field id="g_219" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" clientname="time" enabled=".{enabled}" flush="true" maxlength="9" text=".{time}" userhint="mm:ss,zzz" />
</t:gridcol>
<t:gridcol id="g_220" align="center" sortenabled="false" text="m/s" width="50" >
<t:formattedfield id="g_221" enabled="false" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_222" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="50" >
<t:field id="g_223" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" enabled=".{enabled}" flush="true" text=".{lactate}" userhint="lactate@minutes ..." />
</t:gridcol>
<t:gridcol id="g_224" align="center" sortenabled="false" text="#{rr.literals.hr}" width="50" >
<t:formattedfield id="g_225" actionListener=".{onChangeItem}" enabled=".{enabled}" flush="true" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_226" attributemacro="swimSplitsHeadMacro(0)" rendered="true" >
<t:pane id="g_227" >
<t:row id="g_228" >
<t:field id="g_229" attributemacro="swimSplitsItemTimeMacro(0)" rendered="true" />
<t:formattedfield id="g_230" attributemacro="swimSplitsItemStrokeMacro(0)" rendered="true" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_231" attributemacro="swimSplitsHeadMacro(1)" >
<t:pane id="g_232" >
<t:row id="g_233" >
<t:field id="g_234" attributemacro="swimSplitsItemTimeMacro(1)" />
<t:formattedfield id="g_235" attributemacro="swimSplitsItemStrokeMacro(1)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_236" attributemacro="swimSplitsHeadMacro(2)" >
<t:pane id="g_237" >
<t:row id="g_238" >
<t:field id="g_239" attributemacro="swimSplitsItemTimeMacro(2)" />
<t:formattedfield id="g_240" attributemacro="swimSplitsItemStrokeMacro(2)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_241" attributemacro="swimSplitsHeadMacro(3)" >
<t:pane id="g_242" >
<t:row id="g_243" >
<t:field id="g_244" attributemacro="swimSplitsItemTimeMacro(3)" />
<t:formattedfield id="g_245" attributemacro="swimSplitsItemStrokeMacro(3)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_246" attributemacro="swimSplitsHeadMacro(4)" >
<t:pane id="g_247" >
<t:row id="g_248" >
<t:field id="g_249" attributemacro="swimSplitsItemTimeMacro(4)" />
<t:formattedfield id="g_250" attributemacro="swimSplitsItemStrokeMacro(4)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_251" attributemacro="swimSplitsHeadMacro(5)" >
<t:pane id="g_252" >
<t:row id="g_253" >
<t:field id="g_254" attributemacro="swimSplitsItemTimeMacro(5)" />
<t:formattedfield id="g_255" attributemacro="swimSplitsItemStrokeMacro(5)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_256" attributemacro="swimSplitsHeadMacro(6)" >
<t:pane id="g_257" >
<t:row id="g_258" >
<t:field id="g_259" attributemacro="swimSplitsItemTimeMacro(6)" />
<t:formattedfield id="g_260" attributemacro="swimSplitsItemStrokeMacro(6)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_261" attributemacro="swimSplitsHeadMacro(7)" >
<t:pane id="g_262" >
<t:row id="g_263" >
<t:field id="g_264" attributemacro="swimSplitsItemTimeMacro(7)" />
<t:formattedfield id="g_265" attributemacro="swimSplitsItemStrokeMacro(7)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_266" align="center" sortenabled="false" text="#{rr.literals.comment}" width="200" >
<t:field id="g_267" enabled=".{enabled}" maxlength="1000" text=".{comment}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_268" />
<t:row id="g_269" >
<t:button id="g_270" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" tooltip="ctrl + a" />
<t:coldistance id="g_271" />
<t:button id="g_272" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_273" height="15" />
<t:row id="g_274" rendered="#{d.TestDetailUI.analysis==false}" >
<t:button id="g_275" actionListener="#{d.TestDetailUI.onAnalysisCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testanalysis}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_276" comment="Analysis" padding="5" rendered="#{d.TestDetailUI.analysis}" rowdistance="2" text="#{rr.literals.analysis}" >
<t:row id="g_277" >
<t:pane id="g_278" rowalignmenty="top" rowdistance="5" width="100%" >
<t:row id="g_279" >
<t:label id="g_280" font="size:12;weight:bold" text="#{rr.literals.width}" />
<t:slider id="g_281" majortickspacing="200" maxvalue="1600" minortickspacing="50" minvalue="0" snaptoticks="true" value="#{d.TestDetailUI.width}" width="250" />
<t:coldistance id="g_282" width="25" />
<t:label id="g_283" font="size:12;weight:bold" text="#{rr.literals.height2}" />
<t:slider id="g_284" majortickspacing="200" maxvalue="1600" minortickspacing="50" minvalue="0" snaptoticks="true" value="#{d.TestDetailUI.height}" width="250" />
<t:coldistance id="g_285" />
<t:coldistance id="g_286" />
</t:row>
<t:row id="g_287" >
<t:checkbox id="g_288" actionListener="#{d.TestDetailUI.resetResult}" selected="#{d.TestDetailUI.inverse}" text="#{rr.literals.lactate_on_x_axis}" />
</t:row>
<t:row id="g_289" rendered="#{d.TestDetailUI.swim==false}" >
<t:checkbox id="g_290" actionListener="#{d.TestDetailUI.resetResult}" enabled="#{d.TestDetailUI.enabled}" selected="#{d.TestDetailUI.values.analysis_lactate_hr}" text="#{rr.literals.lactate_hr}" />
</t:row>
<t:row id="g_291" >
<t:label id="g_292" text="#{rr.literals.function}" />
<t:coldistance id="g_293" />
<t:combobox id="g_294" actionListener="#{d.TestDetailUI.resetResult}" attributemacro="entityDetailMacro(TestDetailUI,analysis_function)" flush="true" validvaluesbinding="#{d.TestDetailUI.functionsVvb}" width="200" />
<t:coldistance id="g_295" />
<t:label id="g_296" rendered="#{d.TestDetailUI.exponential}" text="#{rr.literals.offset}" />
<t:coldistance id="g_297" />
<t:formattedfield id="g_298" actionListener="#{d.TestDetailUI.resetResult}" attributemacro="entityDetailMacro(TestDetailUI,analysis_offset)" flush="true" format="double" rendered="#{d.TestDetailUI.exponential}" width="50" />
<t:label id="g_299" rendered="#{d.TestDetailUI.polynomial}" text="#{rr.literals.degree}" />
<t:coldistance id="g_300" />
<t:spinner id="g_301" actionListener="#{d.TestDetailUI.resetResult}" attributemacro="entityDetailMacro(TestDetailUI,analysis_degree)" flush="true" maxvalue="6" minvalue="0" rendered="#{d.TestDetailUI.polynomial}" width="50" />
</t:row>
<t:rowdistance id="g_302" height="10" />
<t:row id="g_303" >
<t:button id="g_304" actionListener="#{d.TestDetailUI.onRefresh}" text="#{rr.literals.create_dia}" />
<t:coldistance id="g_305" />
<t:button id="g_306" actionListener="#{d.TestDetailUI.onSetZones}" text="#{rr.literals.set_zones}" />
</t:row>
<t:rowdistance id="g_307" height="10" />
<t:rowline id="g_308" />
<t:rowdistance id="g_309" height="10" />
<t:row id="g_310" >
<t:label id="g_311" text="#{rr.literals.formula}" width="100" />
<t:field id="g_312" enabled="false" text="#{d.TestDetailUI.formel}" width="600" />
</t:row>
<t:row id="g_313" >
<t:label id="g_314" text="#{rr.literals.correlation}" width="100" />
<t:field id="g_315" enabled="false" text="#{d.TestDetailUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_316" height="10" />
<t:row id="g_317" >
<t:pane id="g_318" rowalignmenty="top" rowdistance="5" >
<t:row id="g_319" >
<t:label id="g_320" text="#{d.TestDetailUI.descriptionX}" width="100" />
<t:formattedfield id="g_321" actionListener="#{d.TestDetailUI.onChangeXY}" align="center" bgpaint="write(100%-5,100%-5,#{d.TestDetailUI.unitX},9,#a0a0a0,rightmiddle)" clientname="x" flush="true" format="double" value="#{d.TestDetailUI.valueX}" width="50" />
</t:row>
<t:row id="g_322" >
<t:label id="g_323" text="#{d.TestDetailUI.descriptionY}" width="100" />
<t:formattedfield id="g_324" actionListener="#{d.TestDetailUI.onChangeXY}" align="center" bgpaint="write(100%-5,100%-5,#{d.TestDetailUI.unitY},9,#a0a0a0,rightmiddle)" clientname="y" flush="true" format="double" value="#{d.TestDetailUI.valueY}" width="50" />
</t:row>
<t:row id="g_325" rendered="false" >
<t:label id="g_326" font="size:16;weight:bold" text="HR" width="25" />
<t:formattedfield id="g_327" align="center" enabled="false" font="size:16" format="int" value="#{d.TestDetailUI.hr}" width="80" />
</t:row>
</t:pane>
<t:coldistance id="g_328" />
<t:heximage id="g_329" align="center" border="#808080" height="#{d.TestDetailUI.height}" hexdata="#{d.TestDetailUI.diagram}" rowalignmenty="center" valign="center" width="#{d.TestDetailUI.width}" />
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
