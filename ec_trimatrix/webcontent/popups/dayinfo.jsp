<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_dayinfog_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:coldistance id="g_3" width="100%" />
<t:button id="g_4" actionListener="#{d.DayInfoPopUp.onPreviousDay}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:coldistance id="g_5" />
<t:label id="g_6" text="#{d.DayInfoPopUp.selectedDate}" />
<t:coldistance id="g_7" />
<t:button id="g_8" actionListener="#{d.DayInfoPopUp.onNextDay}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_9" width="100%" />
</t:row>
<t:row id="g_10" >
<t:label id="g_11" text="#{rr.literals.hr_rest}" width="#{d.DayInfoPopUp.width}" />
<t:formattedfield id="g_12" align="center" format="int" maxlength="3" value="#{d.DayInfoPopUp.dayInfos.restingHr}" width="100" />
</t:row>
<t:row id="g_13" >
<t:label id="g_14" text="#{rr.literals.weather}" width="#{d.DayInfoPopUp.width}" />
<t:combofield id="g_15" width="100" />
<t:coldistance id="g_16" />
<t:label id="g_17" text="#{rr.literals.temperature}" />
<t:coldistance id="g_18" />
<tx:decspinner id="g_19" step="0.5" userhint="#{rr.literals.degree}" width="30" />
</t:row>
<t:row id="g_20" >
<t:label id="g_21" text="#{rr.literals.city}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_22" width="100" />
<t:coldistance id="g_23" />
<t:label id="g_24" text="#{rr.literals.altitude}" />
<t:formattedfield id="g_25" align="center" format="int" maxlength="4" width="100" />
</t:row>
<t:row id="g_26" >
<t:label id="g_27" text="#{rr.literals.weight} #{rr.literals.morning}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_28" step="0.1" userhint="kg" width="30" />
</t:row>
<t:row id="g_29" >
<t:label id="g_30" text="#{rr.literals.weight} #{rr.literals.evening}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_31" step="0.1" userhint="kg" width="30" />
</t:row>
<t:row id="g_32" >
<t:label id="g_33" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 12:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_34" height="40" rowalignmenty="top" width="200" />
<t:coldistance id="g_35" />
<t:label id="g_36" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 22:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_37" height="40" width="200" />
</t:row>
<t:row id="g_38" >
<t:label id="g_39" text="#{rr.literals.fluids_intake}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_40" step="0.1" userhint="kg" width="30" />
</t:row>
<t:row id="g_41" >
<t:label id="g_42" text="#{rr.literals.sleeping_quality}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_43" />
<t:coldistance id="g_44" />
<t:label id="g_45" text="#{rr.literals.sleeping_hours}" />
<t:coldistance id="g_46" />
<t:field id="g_47" width="50" />
</t:row>
<t:row id="g_48" >
<t:label id="g_49" text="#{rr.literals.tiredness}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_50" />
</t:row>
<t:row id="g_51" >
<t:label id="g_52" text="#{rr.literals.feeling}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_53" />
</t:row>
<t:row id="g_54" >
<t:label id="g_55" text="#{rr.literals.training_intensitsy}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_56" />
</t:row>
<t:row id="g_57" >
<t:label id="g_58" text="#{rr.literals.training_valuation}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_59" />
</t:row>
<t:row id="g_60" >
<t:pane id="g_61" >
<t:row id="g_62" >
<t:coldistance id="g_63" width="100%" />
<t:radiobutton id="g_64" group="daytime" text="#{rr.literals.morning}" />
<t:radiobutton id="g_65" group="daytime" text="#{rr.literals.lunchtime}" />
<t:radiobutton id="g_66" group="daytime" text="#{rr.literals.evening}" />
<t:coldistance id="g_67" width="100%" />
</t:row>
<t:row id="g_68" comment="morning" >
<t:pane id="g_69" >
<t:row id="g_70" >
<t:label id="g_71" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_72" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_73" />
<t:formattedfield id="g_74" align="center" format="time" width="100" />
</t:row>
<t:row id="g_75" >
<t:label id="g_76" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_77" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_78" />
<t:formattedfield id="g_79" align="center" format="time" width="100" />
</t:row>
<t:row id="g_80" >
<t:label id="g_81" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_82" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_83" />
<t:formattedfield id="g_84" align="center" format="time" width="100" />
</t:row>
<t:row id="g_85" >
<t:label id="g_86" text="#{rr.literals.haemo}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_87" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_88" />
<t:formattedfield id="g_89" align="center" format="time" width="100" />
</t:row>
<t:row id="g_90" >
<t:label id="g_91" text="#{rr.literals.haema}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_92" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_93" />
<t:formattedfield id="g_94" align="center" format="time" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_95" comment="lunch" >
<t:pane id="g_96" >
<t:row id="g_97" >
<t:label id="g_98" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_99" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_100" />
<t:formattedfield id="g_101" align="center" format="time" width="100" />
</t:row>
<t:row id="g_102" >
<t:label id="g_103" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_104" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_105" />
<t:formattedfield id="g_106" align="center" format="time" width="100" />
</t:row>
<t:row id="g_107" >
<t:label id="g_108" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_109" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_110" />
<t:formattedfield id="g_111" align="center" format="time" width="100" />
</t:row>
<t:row id="g_112" >
<t:label id="g_113" text="#{rr.literals.haemo}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_114" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_115" />
<t:formattedfield id="g_116" align="center" format="time" width="100" />
</t:row>
<t:row id="g_117" >
<t:label id="g_118" text="#{rr.literals.haema}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_119" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_120" />
<t:formattedfield id="g_121" align="center" format="time" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_122" comment="evening" >
<t:pane id="g_123" >
<t:row id="g_124" >
<t:label id="g_125" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_126" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_127" />
<t:formattedfield id="g_128" align="center" format="time" width="100" />
</t:row>
<t:row id="g_129" >
<t:label id="g_130" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_131" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_132" />
<t:formattedfield id="g_133" align="center" format="time" width="100" />
</t:row>
<t:row id="g_134" >
<t:label id="g_135" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_136" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_137" />
<t:formattedfield id="g_138" align="center" format="time" width="100" />
</t:row>
<t:row id="g_139" >
<t:label id="g_140" text="#{rr.literals.haemo}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_141" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_142" />
<t:formattedfield id="g_143" align="center" format="time" width="100" />
</t:row>
<t:row id="g_144" >
<t:label id="g_145" text="#{rr.literals.haema}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_146" step="0.1" userhint="kg" width="30" />
<t:coldistance id="g_147" />
<t:formattedfield id="g_148" align="center" format="time" width="100" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_149" >
<t:checkbox id="g_150" text="#{rr.literals.restday}" />
<t:coldistance id="g_151" />
<t:checkbox id="g_152" text="#{rr.literals.travelday}" />
<t:coldistance id="g_153" />
<t:checkbox id="g_154" text="#{rr.literals.camp}" />
</t:row>
<t:row id="g_155" >
<t:checkbox id="g_156" text="#{rr.literals.illness}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_157" width="100" />
</t:row>
<t:row id="g_158" >
<t:checkbox id="g_159" text="#{rr.literals.massage}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_160" width="100" />
</t:row>
<t:row id="g_161" >
<t:checkbox id="g_162" text="#{rr.literals.therapie}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_163" width="100" />
</t:row>
<t:row id="g_164" >
<t:label id="g_165" rowalignmenty="top" text="#{rr.literals.comment}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_166" height="60" width="200" />
<t:coldistance id="g_167" />
<t:label id="g_168" rowalignmenty="top" text="#{rr.literals.comment} #{rr.literals.coach}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_169" height="60" width="200" />
</t:row>
<t:row id="g_170" >
<t:button id="g_171" actionListener="#{d.DayInfoPopUp.onPrefill}" image="/images/icons/prefill.png" imageheight="15" text="#{rr.literals.prefill}" />
<t:coldistance id="g_172" width="100%" />
<t:button id="g_173" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
