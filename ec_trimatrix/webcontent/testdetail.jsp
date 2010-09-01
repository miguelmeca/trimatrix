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
<t:beanprocessing id="g_1" >
<t:filedownload id="g_2" fileextensions="png" filename="diagram.png" openimmediately="true" opensupported="true" trigger="#{d.TestDetailUI.downloadTrigger}" url="#{d.TestDetailUI.diagramBufferdContent.URL}" />
</t:beanprocessing>
<t:rowbodypane id="g_3" rowdistance="5" >
<t:rowdynamiccontent id="g_4" contentbinding="#{d.TestDetailUI.labelRow}" />
<t:row id="g_5" >
<t:tabbedpane id="g_6" height="100%" value="#{d.TestDetailUI.tabIndex}" width="100%" >
<t:tabbedpanetab id="g_7" comment="Detail" padding="5" rowdistance="2" text="#{rr.literals.test}" >
<t:row id="g_8" >
<t:pane id="g_9" rowdistance="5" >
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.type}" width="100" />
<t:combobox id="g_12" actionListener="#{d.TestDetailUI.onTypeChange}" attributemacro="entityDetailMacro(TestDetailUI,type)" enabled="#{d.TestDetailUI.typeEnabled}" flush="true" validvaluesbinding="#{d.TestDetailUI.testTypesVvb}" width="200" />
<t:coldistance id="g_13" />
<t:label id="g_14" text="#{rr.literals.test_date}" width="100" />
<t:calendarfield id="g_15" attributemacro="entityDetailMacro(TestDetailUI,date)" timezone="CET" width="200" />
</t:row>
<t:row id="g_16" >
<t:label id="g_17" text="#{rr.literals.athlete}" width="100" />
<t:field id="g_18" attributemacro="entityDetailMacro(TestDetailUI,person)" enabled="false" focusable="false" width="175" />
<t:button id="g_19" actionListener="#{d.TestDetailUI.onAthleteSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
<t:coldistance id="g_20" />
<t:label id="g_21" text="#{rr.literals.doctor}" width="100" />
<t:field id="g_22" attributemacro="entityDetailMacro(TestDetailUI,doctor)" enabled="false" focusable="false" width="175" />
<t:button id="g_23" actionListener="#{d.TestDetailUI.onDoctorSearch}" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" image="/images/icons/magnifier.png" />
</t:row>
<t:row id="g_24" >
<t:label id="g_25" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_26" attributemacro="entityDetailMacro(TestDetailUI,description)" height="100" width="510" />
</t:row>
<t:row id="g_27" />
</t:pane>
</t:row>
<t:rowdistance id="g_28" height="15" />
<t:row id="g_29" comment="treadmill" rendered="#{d.TestDetailUI.treadmill}" >
<t:pane id="g_30" rowdistance="5" width="100%" >
<t:row id="g_31" >
<t:label id="g_32" text="#{rr.literals.speed_init}" width="150" />
<t:formattedfield id="g_33" attributemacro="entityDetailMacro(TestDetailUI,speed_init)" format="double" userhint="km/h" width="50" />
<t:coldistance id="g_34" />
<t:checkbox id="g_35" attributemacro="entityDetailMacro(TestDetailUI,speed_variable)" flush="true" selected="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_36" />
<t:label id="g_37" rendered="#{d.TestDetailUI.values.speed_variable}" text="#{rr.literals.speed_step}" width="150" />
<t:formattedfield id="g_38" attributemacro="entityDetailMacro(TestDetailUI,speed_step)" format="double" rendered="#{d.TestDetailUI.values.speed_variable}" userhint="km/h" width="50" />
</t:row>
<t:row id="g_39" >
<t:label id="g_40" text="#{rr.literals.incline_init}" width="150" />
<t:formattedfield id="g_41" attributemacro="entityDetailMacro(TestDetailUI,incline_init)" format="int" userhint="%" width="50" />
<t:coldistance id="g_42" />
<t:checkbox id="g_43" attributemacro="entityDetailMacro(TestDetailUI,incline_variable)" flush="true" selected="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.variable}" />
<t:coldistance id="g_44" />
<t:label id="g_45" rendered="#{d.TestDetailUI.values.incline_variable}" text="#{rr.literals.incline_step}" width="150" />
<t:formattedfield id="g_46" attributemacro="entityDetailMacro(TestDetailUI,incline_step)" format="int" rendered="#{d.TestDetailUI.values.incline_variable}" userhint="%" width="50" />
</t:row>
<t:row id="g_47" >
<t:label id="g_48" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_49" actionListener="#{d.TestDetailUI.onTimeFlush}" attributemacro="entityDetailMacro(TestDetailUI,treadmill_step_time)" clientname="treadmill_step_time" flush="true" maxlength="5" userhint="mm:ss" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_50" comment="ergo" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_51" rowdistance="5" width="100%" >
<t:row id="g_52" >
<t:label id="g_53" text="#{rr.literals.power_init}" width="150" />
<t:formattedfield id="g_54" attributemacro="entityDetailMacro(TestDetailUI,power_init)" format="int" width="50" />
<t:coldistance id="g_55" />
<t:label id="g_56" text="#{rr.literals.power_step}" width="150" />
<t:formattedfield id="g_57" attributemacro="entityDetailMacro(TestDetailUI,power_step)" format="int" width="50" />
</t:row>
<t:row id="g_58" >
<t:label id="g_59" text="#{rr.literals.cadence_low}" width="150" />
<t:formattedfield id="g_60" attributemacro="entityDetailMacro(TestDetailUI,cadence_low)" format="int" width="50" />
<t:coldistance id="g_61" />
<t:label id="g_62" text="#{rr.literals.cadence_high}" width="150" />
<t:formattedfield id="g_63" attributemacro="entityDetailMacro(TestDetailUI,cadence_high)" format="int" width="50" />
</t:row>
<t:row id="g_64" >
<t:label id="g_65" text="#{rr.literals.time_step}" width="150" />
<t:field id="g_66" actionListener="#{d.TestDetailUI.onTimeFlush}" attributemacro="entityDetailMacro(TestDetailUI,ergo_step_time)" clientname="ergo_step_time" flush="true" maxlength="5" userhint="mm:ss" width="50" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_67" comment="swim" >
<t:pane id="g_68" rendered="#{d.TestDetailUI.swim}" rowdistance="5" width="100%" >
<t:row id="g_69" >
<t:label id="g_70" text="#{rr.literals.test_date2}" width="150" />
<t:calendarfield id="g_71" attributemacro="entityDetailMacro(TestDetailUI,date2)" timezone="CET" width="200" />
</t:row>
<t:rowdistance id="g_72" />
<t:row id="g_73" >
<t:label id="g_74" text="#{rr.literals.test_assistance}" width="150" />
<t:field id="g_75" attributemacro="entityDetailMacro(TestDetailUI,assistant_name)" width="200" />
</t:row>
<t:row id="g_76" >
<t:label id="g_77" text="#{rr.literals.baths}" width="150" />
<t:field id="g_78" attributemacro="entityDetailMacro(TestDetailUI,baths)" width="200" />
</t:row>
<t:row id="g_79" >
<t:label id="g_80" text="#{rr.literals.pools}" width="150" />
<t:field id="g_81" attributemacro="entityDetailMacro(TestDetailUI,pool)" width="200" />
</t:row>
<t:rowdistance id="g_82" />
<t:row id="g_83" >
<t:label id="g_84" text="#{rr.literals.test_distance}" width="150" />
<t:formattedfield id="g_85" attributemacro="entityDetailMacro(TestDetailUI,distance)" format="int" width="50" />
</t:row>
<t:row id="g_86" >
<t:label id="g_87" text="#{rr.literals.test_splits}" width="150" />
<t:formattedfield id="g_88" actionListener="#{d.TestDetailUI.onChangeData}" attributemacro="entityDetailMacro(TestDetailUI,splits)" flush="true" format="int" userhint="max. 8" width="50" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_89" height="15" />
<t:row id="g_90" rendered="#{d.TestDetailUI.protocol==false}" >
<t:button id="g_91" actionListener="#{d.TestDetailUI.onProtocolCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testprotocol}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_92" comment="Protocol" padding="5" rendered="#{d.TestDetailUI.protocol}" rowdistance="2" text="#{rr.literals.test_protocol}" >
<t:row id="g_93" comment="treadmill protocol" rendered="#{d.TestDetailUI.treadmill}" >
<t:pane id="g_94" rowdistance="2" >
<t:row id="g_95" >
<t:label id="g_96" rowalignmenty="top" text="#{rr.literals.description}" width="100" />
<t:textarea id="g_97" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_98" >
<t:label id="g_99" text="#{rr.literals.model_treadmill}" width="100" />
<t:field id="g_100" attributemacro="entityDetailMacro(TestDetailUI,model)" width="200" />
</t:row>
<t:row id="g_101" >
<t:label id="g_102" text="#{rr.literals.model_lactate}" width="100" />
<t:field id="g_103" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:row id="g_104" >
<t:label id="g_105" text="#{rr.literals.model_spiro}" width="100" />
<t:field id="g_106" attributemacro="entityDetailMacro(TestDetailUI,model_spiro)" width="200" />
</t:row>
<t:rowdistance id="g_107" />
<t:row id="g_108" >
<t:coldistance id="g_109" width="100%" />
<t:link id="g_110" actionListener="#{d.TestDetailUI.gridTreadmill.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_111" >
<t:fixgrid id="g_112" avoidroundtrips="1" enabled="#{d.TestDetailUI.enabled}" horizontalscrollmode="autowithresize" multiselect="false" objectbinding="#{d.TestDetailUI.gridTreadmill}" sbvisibleamount="20" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
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
<t:field id="g_120" actionListener=".{onChangeItem}" clientname="step_time" enabled=".{lastItemEnabled}" flush="true" maxlength="5" text=".{step_time}" userhint="mm:ss" />
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
<t:button id="g_135" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_136" />
<t:button id="g_137" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_138" comment="ergo protocol" rendered="#{d.TestDetailUI.ergo}" >
<t:pane id="g_139" rowdistance="2" >
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
<t:fixgrid id="g_157" avoidroundtrips="1" enabled="#{d.TestDetailUI.enabled}" horizontalscrollmode="autowithresize" multiselect="false" objectbinding="#{d.TestDetailUI.gridErgo}" sbvisibleamount="20" selectorcolumn="1" selectorcolumnwidth="20" showemptyrows="false" >
<t:gridcol id="g_158" align="center" sortenabled="false" text="#{rr.literals.step}" width="50" >
<t:formattedfield id="g_159" enabled="false" format="int" value=".{step}" />
</t:gridcol>
<t:gridcol id="g_160" align="center" sortenabled="false" text="#{rr.literals.power}" width="90" >
<t:formattedfield id="g_161" enabled="false" format="int" value=".{power}" />
</t:gridcol>
<t:gridcol id="g_162" align="center" sortenabled="false" text="#{rr.literals.cadence}" width="60" >
<t:field id="g_163" enabled="false" text=".{cadence}" />
</t:gridcol>
<t:gridcol id="g_164" align="center" sortenabled="false" text="#{rr.literals.time_step}" width="75" >
<t:field id="g_165" actionListener=".{onChangeItem}" clientname="step_time" enabled=".{lastItemEnabled}" flush="true" maxlength="5" text=".{step_time}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_166" align="center" sortenabled="false" text="#{rr.literals.total_time}" width="75" >
<t:field id="g_167" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{time_total}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_168" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="45" >
<t:formattedfield id="g_169" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{lactate}" />
</t:gridcol>
<t:gridcol id="g_170" align="center" sortenabled="false" text="#{rr.literals.hr}" width="45" >
<t:formattedfield id="g_171" bgpaint="mandatory()" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_172" align="center" sortenabled="false" text="O2" width="45" >
<t:formattedfield id="g_173" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{o2_absorption}" />
</t:gridcol>
<t:gridcol id="g_174" align="center" sortenabled="false" text="CO2" width="45" >
<t:formattedfield id="g_175" enabled="#{d.TestDetailUI.enabled}" format="int" value=".{co2_emission}" />
</t:gridcol>
<t:gridcol id="g_176" align="center" sortenabled="false" text="RQ" width="45" >
<t:formattedfield id="g_177" enabled="#{d.TestDetailUI.enabled}" format="double" value=".{rq}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_178" />
<t:row id="g_179" >
<t:button id="g_180" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" />
<t:coldistance id="g_181" />
<t:button id="g_182" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_183" comment="swim protocol" rendered="#{d.TestDetailUI.swim}" >
<t:pane id="g_184" rowdistance="2" >
<t:row id="g_185" >
<t:label id="g_186" rowalignmenty="top" text="#{rr.literals.description}" width="120" />
<t:textarea id="g_187" attributemacro="entityDetailMacro(TestDetailUI,protocol_description)" height="100" width="510" />
</t:row>
<t:row id="g_188" >
<t:label id="g_189" text="#{rr.literals.model_lactate}" width="120" />
<t:field id="g_190" attributemacro="entityDetailMacro(TestDetailUI,model_lactate)" width="200" />
</t:row>
<t:rowdistance id="g_191" height="15" />
<t:row id="g_192" >
<t:label id="g_193" text="#{rr.literals.max_performance} #{d.TestDetailUI.values.distance} m  " width="120" />
<t:field id="g_194" actionListener="#{d.TestDetailUI.onChangeMaxPerformance}" attributemacro="entityDetailMacro(TestDetailUI,performance_max)" flush="true" maxlength="9" tooltip="mm:ss,zzz" userhint="mm:ss,zzz" width="65" />
<t:coldistance id="g_195" />
<t:label id="g_196" text="#{rr.literals.speed}" width="90" />
<t:formattedfield id="g_197" enabled="false" format="double" tooltip="m/s" userhint="m/s" value="#{d.TestDetailUI.maxSpeed}" width="50" />
</t:row>
<t:rowdistance id="g_198" />
<t:row id="g_199" >
<t:coldistance id="g_200" width="100%" />
<t:link id="g_201" actionListener="#{d.TestDetailUI.gridSwim.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:row id="g_202" >
<t:fixgrid id="g_203" avoidroundtrips="1" border="#808080" enabled="#{d.TestDetailUI.enabled}" horizontalscrollmode="autowithresize" multiselect="false" objectbinding="#{d.TestDetailUI.gridSwim}" sbvisibleamount="20" showemptyrows="false" >
<t:gridcol id="g_204" align="center" sortenabled="false" text="#{rr.literals.step}" width="100" >
<t:treenode id="g_205" text=".{step}" />
</t:gridcol>
<t:gridcol id="g_206" align="center" sortenabled="false" width="20" >
<t:pane id="g_207" background="rectangle(0,0,100%,50%,#c0c0c008);rectangle(0,50%,100%,50%,#00000006)" >
<t:row id="g_208" >
<t:button id="g_209" actionListener=".{onAddSubItem}" align="center" contentareafilled="false" enabled="#{d.TestDetailUI.enabled}" horizontaltextposition="center" image="/images/icons/add.png" rendered=".{topNode}" rowalignmenty="center" verticaltextposition="middle" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_210" sortenabled="false" width="20" >
<t:pane id="g_211" bgpaint="rectangle(0,0,100%,50%,#c0c0c008);rectangle(0,50%,100%,50%,#00000006)" >
<t:row id="g_212" >
<t:checkbox id="g_213" actionListener=".{onMarkItem}" align="center" enabled="#{d.TestDetailUI.enabled}" flush="true" rendered=".{topNode==false}" selected=".{valid}" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_214" align="center" sortenabled="false" text="#{rr.literals.intensity}" width="60" >
<t:formattedfield id="g_215" bgpaint=".{bgPaintTop}" enabled=".{enabledAndTopNode}" flush="true" format="int" maxlength="3" userhint="max. 100%" value=".{intensity}" />
</t:gridcol>
<t:gridcol id="g_216" align="center" sortenabled="false" text="#{rr.literals.target_time}" width="65" >
<t:field id="g_217" enabled="false" maxlength="5" regex="\d\d:[0-5]\d" regexmode="1" text=".{targetTime}" userhint="mm:ss" />
</t:gridcol>
<t:gridcol id="g_218" align="center" sortenabled="false" text="m/s" width="50" >
<t:formattedfield id="g_219" enabled="false" format="double" value=".{targetSpeed}" />
</t:gridcol>
<t:gridcol id="g_220" align="center" sortenabled="false" text="#{rr.literals.time}" width="65" >
<t:field id="g_221" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" clientname="time" enabled=".{enabled}" flush="true" maxlength="9" text=".{time}" userhint="mm:ss,zzz" />
</t:gridcol>
<t:gridcol id="g_222" align="center" sortenabled="false" text="m/s" width="50" >
<t:formattedfield id="g_223" enabled="false" format="double" value=".{speed}" />
</t:gridcol>
<t:gridcol id="g_224" align="center" sortenabled="false" text="#{rr.literals.lactate}" width="50" >
<t:field id="g_225" actionListener=".{onChangeItem}" bgpaint=".{bgpaint}" enabled=".{enabled}" flush="true" text=".{lactate}" userhint="lactate@minutes ..." />
</t:gridcol>
<t:gridcol id="g_226" align="center" sortenabled="false" text="#{rr.literals.hr}" width="50" >
<t:formattedfield id="g_227" actionListener=".{onChangeItem}" enabled=".{enabled}" flush="true" format="int" value=".{hr}" />
</t:gridcol>
<t:gridcol id="g_228" attributemacro="swimSplitsHeadMacro(0)" rendered="true" >
<t:pane id="g_229" >
<t:row id="g_230" >
<t:field id="g_231" attributemacro="swimSplitsItemTimeMacro(0)" rendered="true" />
<t:formattedfield id="g_232" attributemacro="swimSplitsItemStrokeMacro(0)" rendered="true" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_233" attributemacro="swimSplitsHeadMacro(1)" >
<t:pane id="g_234" >
<t:row id="g_235" >
<t:field id="g_236" attributemacro="swimSplitsItemTimeMacro(1)" />
<t:formattedfield id="g_237" attributemacro="swimSplitsItemStrokeMacro(1)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_238" attributemacro="swimSplitsHeadMacro(2)" >
<t:pane id="g_239" >
<t:row id="g_240" >
<t:field id="g_241" attributemacro="swimSplitsItemTimeMacro(2)" />
<t:formattedfield id="g_242" attributemacro="swimSplitsItemStrokeMacro(2)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_243" attributemacro="swimSplitsHeadMacro(3)" >
<t:pane id="g_244" >
<t:row id="g_245" >
<t:field id="g_246" attributemacro="swimSplitsItemTimeMacro(3)" />
<t:formattedfield id="g_247" attributemacro="swimSplitsItemStrokeMacro(3)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_248" attributemacro="swimSplitsHeadMacro(4)" >
<t:pane id="g_249" >
<t:row id="g_250" >
<t:field id="g_251" attributemacro="swimSplitsItemTimeMacro(4)" />
<t:formattedfield id="g_252" attributemacro="swimSplitsItemStrokeMacro(4)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_253" attributemacro="swimSplitsHeadMacro(5)" >
<t:pane id="g_254" >
<t:row id="g_255" >
<t:field id="g_256" attributemacro="swimSplitsItemTimeMacro(5)" />
<t:formattedfield id="g_257" attributemacro="swimSplitsItemStrokeMacro(5)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_258" attributemacro="swimSplitsHeadMacro(6)" >
<t:pane id="g_259" >
<t:row id="g_260" >
<t:field id="g_261" attributemacro="swimSplitsItemTimeMacro(6)" />
<t:formattedfield id="g_262" attributemacro="swimSplitsItemStrokeMacro(6)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_263" attributemacro="swimSplitsHeadMacro(7)" >
<t:pane id="g_264" >
<t:row id="g_265" >
<t:field id="g_266" attributemacro="swimSplitsItemTimeMacro(7)" />
<t:formattedfield id="g_267" attributemacro="swimSplitsItemStrokeMacro(7)" />
</t:row>
</t:pane>
</t:gridcol>
<t:gridcol id="g_268" align="center" sortenabled="false" text="#{rr.literals.comment}" width="200" >
<t:field id="g_269" enabled=".{enabled}" maxlength="1000" text=".{comment}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_270" />
<t:row id="g_271" >
<t:button id="g_272" actionListener="#{d.TestDetailUI.onAddItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-65" image="/images/icons/add.png" imageheight="15" text="#{rr.literals.add}" tooltip="ctrl + a" />
<t:coldistance id="g_273" />
<t:button id="g_274" actionListener="#{d.TestDetailUI.onRemoveItem}" enabled="#{d.TestDetailUI.enabled}" hotkey="ctrl-68" image="/images/icons/remove.png" imageheight="15" text="#{rr.literals.remove}" />
</t:row>
</t:pane>
</t:row>
<t:rowdistance id="g_275" height="15" />
<t:row id="g_276" rendered="#{d.TestDetailUI.analysis==false}" >
<t:button id="g_277" actionListener="#{d.TestDetailUI.onAnalysisCreate}" enabled="#{d.TestDetailUI.enabled}" text="#{rr.literals.create_testanalysis}" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_278" comment="Analysis" padding="5" rendered="#{d.TestDetailUI.analysis}" rowdistance="2" text="#{rr.literals.analysis}" >
<t:row id="g_279" >
<t:pane id="g_280" rowalignmenty="top" rowdistance="5" width="100%" >
<t:row id="g_281" >
<t:label id="g_282" font="size:12;weight:bold" text="#{rr.literals.width}" />
<t:slider id="g_283" majortickspacing="200" maxvalue="1600" minortickspacing="50" minvalue="0" snaptoticks="true" value="#{d.TestDetailUI.width}" width="250" />
<t:coldistance id="g_284" width="25" />
<t:label id="g_285" font="size:12;weight:bold" text="#{rr.literals.height2}" />
<t:slider id="g_286" majortickspacing="200" maxvalue="1600" minortickspacing="50" minvalue="0" snaptoticks="true" value="#{d.TestDetailUI.height}" width="250" />
<t:coldistance id="g_287" />
<t:coldistance id="g_288" />
</t:row>
<t:row id="g_289" >
<t:checkbox id="g_290" actionListener="#{d.TestDetailUI.resetResult}" selected="#{d.TestDetailUI.inverse}" text="#{rr.literals.lactate_on_x_axis}" />
</t:row>
<t:row id="g_291" rendered="#{d.TestDetailUI.swim==false}" >
<t:checkbox id="g_292" actionListener="#{d.TestDetailUI.resetResult}" enabled="#{d.TestDetailUI.enabled}" selected="#{d.TestDetailUI.values.analysis_lactate_hr}" text="#{rr.literals.lactate_hr}" />
</t:row>
<t:row id="g_293" >
<t:label id="g_294" text="#{rr.literals.function}" />
<t:coldistance id="g_295" />
<t:combobox id="g_296" actionListener="#{d.TestDetailUI.resetResult}" attributemacro="entityDetailMacro(TestDetailUI,analysis_function)" flush="true" validvaluesbinding="#{d.TestDetailUI.functionsVvb}" width="200" />
<t:coldistance id="g_297" />
<t:label id="g_298" rendered="#{d.TestDetailUI.exponential}" text="#{rr.literals.offset}" />
<t:coldistance id="g_299" />
<t:formattedfield id="g_300" actionListener="#{d.TestDetailUI.resetResult}" attributemacro="entityDetailMacro(TestDetailUI,analysis_offset)" flush="true" format="double" rendered="#{d.TestDetailUI.exponential}" width="50" />
<t:label id="g_301" rendered="#{d.TestDetailUI.polynomial}" text="#{rr.literals.degree}" />
<t:coldistance id="g_302" />
<t:spinner id="g_303" actionListener="#{d.TestDetailUI.resetResult}" attributemacro="entityDetailMacro(TestDetailUI,analysis_degree)" flush="true" maxvalue="6" minvalue="0" rendered="#{d.TestDetailUI.polynomial}" width="50" />
</t:row>
<t:rowdistance id="g_304" height="10" />
<t:row id="g_305" >
<t:button id="g_306" actionListener="#{d.TestDetailUI.onRefresh}" text="#{rr.literals.create_dia}" />
<t:coldistance id="g_307" />
<t:button id="g_308" actionListener="#{d.TestDetailUI.onDiagramDownload}" text="#{rr.literals.download_dia}" />
<t:coldistance id="g_309" />
<t:button id="g_310" actionListener="#{d.TestDetailUI.onSetZones}" text="#{rr.literals.set_zones}" />
</t:row>
<t:rowdistance id="g_311" height="10" />
<t:rowline id="g_312" />
<t:rowdistance id="g_313" height="10" />
<t:row id="g_314" >
<t:label id="g_315" text="#{rr.literals.formula}" width="100" />
<t:field id="g_316" enabled="false" text="#{d.TestDetailUI.formel}" width="600" />
</t:row>
<t:row id="g_317" >
<t:label id="g_318" text="#{rr.literals.correlation}" width="100" />
<t:field id="g_319" enabled="false" text="#{d.TestDetailUI.correlation}" width="200" />
</t:row>
<t:rowdistance id="g_320" height="10" />
<t:row id="g_321" >
<t:pane id="g_322" rowalignmenty="top" rowdistance="5" >
<t:row id="g_323" >
<t:label id="g_324" text="#{d.TestDetailUI.descriptionX}" width="100" />
<t:formattedfield id="g_325" actionListener="#{d.TestDetailUI.onChangeXY}" align="center" bgpaint="write(100%-5,100%-5,#{d.TestDetailUI.unitX},9,#a0a0a0,rightmiddle)" clientname="x" flush="true" format="double" value="#{d.TestDetailUI.valueX}" width="50" />
</t:row>
<t:row id="g_326" >
<t:label id="g_327" text="#{d.TestDetailUI.descriptionY}" width="100" />
<t:formattedfield id="g_328" actionListener="#{d.TestDetailUI.onChangeXY}" align="center" bgpaint="write(100%-5,100%-5,#{d.TestDetailUI.unitY},9,#a0a0a0,rightmiddle)" clientname="y" flush="true" format="double" value="#{d.TestDetailUI.valueY}" width="50" />
</t:row>
<t:row id="g_329" rendered="false" >
<t:label id="g_330" font="size:16;weight:bold" text="HR" width="25" />
<t:formattedfield id="g_331" align="center" enabled="false" font="size:16" format="int" value="#{d.TestDetailUI.hr}" width="80" />
</t:row>
</t:pane>
<t:coldistance id="g_332" />
<t:heximage id="g_333" actionListener="#{d.TestDetailUI.onPrintReport}" align="center" border="#808080" height="#{d.TestDetailUI.height}" hexdata="#{d.TestDetailUI.diagram}" invokeevent="rightclick" rowalignmenty="center" valign="center" width="#{d.TestDetailUI.width}" />
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
