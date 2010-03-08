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
<tx:decspinner id="g_19" objectbinding="#{d.DayInfoPopUp.temperatureDS}" step="0.5" userhint="#{rr.literals.degree}" width="40" />
</t:row>
<t:row id="g_20" >
<t:label id="g_21" text="#{rr.literals.city}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_22" text="#{d.DayInfoPopUp.dayInfos.location}" width="100" />
<t:coldistance id="g_23" />
<t:label id="g_24" text="#{rr.literals.altitude}" />
<t:coldistance id="g_25" />
<t:formattedfield id="g_26" align="center" format="int" maxlength="5" value="#{d.DayInfoPopUp.dayInfos.altitude}" width="60" />
</t:row>
<t:row id="g_27" >
<t:label id="g_28" text="#{rr.literals.weight} #{rr.literals.morning}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_29" objectbinding="#{d.DayInfoPopUp.weightMorningDS}" step="0.1" userhint="kg" width="40" />
<t:coldistance id="g_30" />
<t:label id="g_31" text="#{rr.literals.weight} #{rr.literals.evening}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_32" objectbinding="#{d.DayInfoPopUp.weightEveningDS}" step="0.1" userhint="kg" width="40" />
</t:row>
<t:row id="g_33" >
<t:label id="g_34" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 12:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_35" height="40" rowalignmenty="top" text="#{d.DayInfoPopUp.dayInfos.dinnersLunch}" width="200" />
<t:coldistance id="g_36" />
<t:label id="g_37" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 22:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_38" height="40" text="#{d.DayInfoPopUp.dayInfos.dinnersEvening}" width="200" />
</t:row>
<t:row id="g_39" >
<t:label id="g_40" text="#{rr.literals.fluids_intake}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_41" objectbinding="#{d.DayInfoPopUp.fluidsIntakeDS}" step="0.1" userhint="l" width="40" />
</t:row>
<t:row id="g_42" >
<t:label id="g_43" text="#{rr.literals.sleeping_quality}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_44" objectbinding="#{d.DayInfoPopUp.sleepingQualityS}" />
<t:coldistance id="g_45" />
<t:label id="g_46" text="#{rr.literals.sleeping_hours}" />
<t:coldistance id="g_47" />
<t:formattedfield id="g_48" format="double" userhint="h" value="#{d.DayInfoPopUp.dayInfos.sleepingHours}" width="50" />
</t:row>
<t:row id="g_49" >
<t:label id="g_50" text="#{rr.literals.tiredness}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_51" objectbinding="#{d.DayInfoPopUp.tirednessS}" />
</t:row>
<t:row id="g_52" >
<t:label id="g_53" text="#{rr.literals.feeling}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_54" objectbinding="#{d.DayInfoPopUp.feelingS}" />
</t:row>
<t:row id="g_55" >
<t:label id="g_56" text="#{rr.literals.training_intensity}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_57" objectbinding="#{d.DayInfoPopUp.trainingIntensityS}" />
</t:row>
<t:row id="g_58" >
<t:label id="g_59" text="#{rr.literals.training_valuation}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_60" objectbinding="#{d.DayInfoPopUp.trainingValuationS}" />
</t:row>
<t:row id="g_61" >
<t:pane id="g_62" >
<t:row id="g_63" >
<t:coldistance id="g_64" width="100%" />
<t:radiobutton id="g_65" group="daytime" text="#{rr.literals.morning}" />
<t:radiobutton id="g_66" group="daytime" text="#{rr.literals.lunchtime}" />
<t:radiobutton id="g_67" group="daytime" text="#{rr.literals.evening}" />
<t:coldistance id="g_68" width="100%" />
</t:row>
<t:row id="g_69" comment="morning" >
<t:pane id="g_70" >
<t:row id="g_71" >
<t:label id="g_72" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_73" objectbinding="#{d.DayInfoPopUp.ckMorningDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_74" />
<t:formattedfield id="g_75" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.ckMorningTime}" width="100" />
</t:row>
<t:row id="g_76" >
<t:label id="g_77" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_78" objectbinding="#{d.DayInfoPopUp.ureaMorningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_79" />
<t:formattedfield id="g_80" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.ureaMorningTime}" width="100" />
</t:row>
<t:row id="g_81" >
<t:label id="g_82" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_83" objectbinding="#{d.DayInfoPopUp.glucoseMorningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_84" />
<t:formattedfield id="g_85" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.glucoseMorningTime}" width="100" />
</t:row>
<t:row id="g_86" >
<t:label id="g_87" text="#{rr.literals.haemo}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_88" objectbinding="#{d.DayInfoPopUp.hbDS}" step="1" userhint="g/dl" width="40" />
<t:coldistance id="g_89" />
<t:formattedfield id="g_90" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.hbTime}" width="100" />
</t:row>
<t:row id="g_91" >
<t:label id="g_92" text="#{rr.literals.haema}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_93" objectbinding="#{d.DayInfoPopUp.hktDS}" step="1" userhint="%" width="40" />
<t:coldistance id="g_94" />
<t:formattedfield id="g_95" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.hktTime}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_96" comment="lunch" >
<t:pane id="g_97" >
<t:row id="g_98" >
<t:label id="g_99" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_100" objectbinding="#{d.DayInfoPopUp.ckLunchDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_101" />
<t:formattedfield id="g_102" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.ckLunchTime}" width="100" />
</t:row>
<t:row id="g_103" >
<t:label id="g_104" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_105" objectbinding="#{d.DayInfoPopUp.ureaLunchDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_106" />
<t:formattedfield id="g_107" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.ureaLunchTime}" width="100" />
</t:row>
<t:row id="g_108" >
<t:label id="g_109" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_110" objectbinding="#{d.DayInfoPopUp.glucoseLunchDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_111" />
<t:formattedfield id="g_112" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.glucoseLunchTime}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_113" comment="evening" >
<t:pane id="g_114" >
<t:row id="g_115" >
<t:label id="g_116" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_117" objectbinding="#{d.DayInfoPopUp.ckEveningDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_118" />
<t:formattedfield id="g_119" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.ckEveningTime}" width="100" />
</t:row>
<t:row id="g_120" >
<t:label id="g_121" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_122" objectbinding="#{d.DayInfoPopUp.ureaEveningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_123" />
<t:formattedfield id="g_124" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.ureaEveningTime}" width="100" />
</t:row>
<t:row id="g_125" >
<t:label id="g_126" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_127" objectbinding="#{d.DayInfoPopUp.glucoseEveningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_128" />
<t:formattedfield id="g_129" align="center" format="time" value="#{d.DayInfoPopUp.dayInfos.glucoseEveningTime}" width="100" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_130" >
<t:checkbox id="g_131" selected="#{d.DayInfoPopUp.dayInfos.restday}" text="#{rr.literals.restday}" />
<t:coldistance id="g_132" />
<t:checkbox id="g_133" selected="#{d.DayInfoPopUp.dayInfos.travelday}" text="#{rr.literals.travelday}" />
<t:coldistance id="g_134" />
<t:checkbox id="g_135" selected="#{d.DayInfoPopUp.dayInfos.camp}" text="#{rr.literals.camp}" />
</t:row>
<t:row id="g_136" >
<t:checkbox id="g_137" selected="#{d.DayInfoPopUp.dayInfos.illness}" text="#{rr.literals.illness}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_138" text="#{d.DayInfoPopUp.dayInfos.illnessText}" width="200" />
</t:row>
<t:row id="g_139" >
<t:checkbox id="g_140" selected="#{d.DayInfoPopUp.dayInfos.massage}" text="#{rr.literals.massage}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_141" text="#{d.DayInfoPopUp.dayInfos.massageText}" width="200" />
</t:row>
<t:row id="g_142" >
<t:checkbox id="g_143" selected="#{d.DayInfoPopUp.dayInfos.therapy}" text="#{rr.literals.therapie}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_144" text="#{d.DayInfoPopUp.dayInfos.therapyText}" width="200" />
</t:row>
<t:row id="g_145" >
<t:label id="g_146" rowalignmenty="top" text="#{rr.literals.comment}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_147" height="60" text="#{d.DayInfoPopUp.dayInfos.comment}" width="200" />
<t:coldistance id="g_148" />
<t:label id="g_149" rowalignmenty="top" text="#{rr.literals.comment} #{rr.literals.coach}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_150" height="60" text="#{d.DayInfoPopUp.dayInfos.commentCoach}" width="200" />
</t:row>
<t:row id="g_151" >
<t:button id="g_152" actionListener="#{d.DayInfoPopUp.onPrefill}" image="/images/icons/prefill.png" imageheight="15" text="#{rr.literals.prefill}" />
<t:coldistance id="g_153" width="100%" />
<t:button id="g_154" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
