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
<t:combobox id="g_15" flush="true" value="#{d.DayInfoPopUp.dayInfos.weather}" width="100" withnullitem="false" withvaluecomment="true" >
<t:comboboxitem id="g_16" image="/images/icons/weather_sun.png" text="#{rr.literals.sun}" value="sun" />
<t:comboboxitem id="g_17" image="/images/icons/weather_fewclouds.png" text="#{rr.literals.fewclouds}" value="fewclouds" />
<t:comboboxitem id="g_18" image="/images/icons/weather_overcast.png" text="#{rr.literals.overcast}" value="overcast" />
<t:comboboxitem id="g_19" image="/images/icons/weather_scattered.png" text="#{rr.literals.scattered}" value="scattered" />
<t:comboboxitem id="g_20" image="/images/icons/weather_showers.png" text="#{rr.literals.showers}" value="showers" />
<t:comboboxitem id="g_21" image="/images/icons/weather_snow.png" text="#{rr.literals.snow}" value="snow" />
</t:combobox>
<t:coldistance id="g_22" />
<t:image id="g_23" image="#{d.DayInfoPopUp.weatherImage}" />
<t:coldistance id="g_24" />
<t:label id="g_25" text="#{rr.literals.temperature}" />
<t:coldistance id="g_26" />
<tx:decspinner id="g_27" objectbinding="#{d.DayInfoPopUp.temperatureDS}" step="0.5" userhint="#{rr.literals.degree}" width="40" />
</t:row>
<t:row id="g_28" >
<t:label id="g_29" text="#{rr.literals.city}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_30" text="#{d.DayInfoPopUp.dayInfos.location}" width="100" />
<t:coldistance id="g_31" />
<t:label id="g_32" text="#{rr.literals.altitude}" />
<t:coldistance id="g_33" />
<t:formattedfield id="g_34" align="center" format="int" maxlength="5" value="#{d.DayInfoPopUp.dayInfos.altitude}" width="60" />
</t:row>
<t:row id="g_35" >
<t:label id="g_36" text="#{rr.literals.weight} #{rr.literals.morning}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_37" objectbinding="#{d.DayInfoPopUp.weightMorningDS}" step="0.1" userhint="kg" width="40" />
<t:coldistance id="g_38" width="35" />
<t:label id="g_39" text="#{rr.literals.weight} #{rr.literals.evening}" />
<t:coldistance id="g_40" />
<tx:decspinner id="g_41" objectbinding="#{d.DayInfoPopUp.weightEveningDS}" step="0.1" userhint="kg" width="40" />
</t:row>
<t:row id="g_42" >
<t:label id="g_43" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 12:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_44" height="40" rowalignmenty="top" text="#{d.DayInfoPopUp.dayInfos.dinnersLunch}" width="200" />
<t:coldistance id="g_45" />
<t:label id="g_46" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 22:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_47" height="40" text="#{d.DayInfoPopUp.dayInfos.dinnersEvening}" width="200" />
</t:row>
<t:row id="g_48" >
<t:label id="g_49" text="#{rr.literals.fluids_intake}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_50" objectbinding="#{d.DayInfoPopUp.fluidsIntakeDS}" step="0.1" userhint="l" width="40" />
</t:row>
<t:row id="g_51" >
<t:label id="g_52" text="#{rr.literals.sleeping_quality}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_53" objectbinding="#{d.DayInfoPopUp.sleepingQualityS}" />
<t:coldistance id="g_54" />
<t:label id="g_55" text="#{rr.literals.sleeping_hours}" />
<t:coldistance id="g_56" />
<t:formattedfield id="g_57" format="double" userhint="h" value="#{d.DayInfoPopUp.dayInfos.sleepingHours}" width="50" />
</t:row>
<t:row id="g_58" >
<t:label id="g_59" text="#{rr.literals.tiredness}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_60" objectbinding="#{d.DayInfoPopUp.tirednessS}" />
<t:coldistance id="g_61" width="50" />
<t:label id="g_62" text="#{rr.literals.feeling}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_63" objectbinding="#{d.DayInfoPopUp.feelingS}" />
</t:row>
<t:row id="g_64" >
<t:label id="g_65" text="#{rr.literals.training_intensity}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_66" objectbinding="#{d.DayInfoPopUp.trainingIntensityS}" />
<t:coldistance id="g_67" width="50" />
<t:label id="g_68" text="#{rr.literals.training_valuation}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_69" objectbinding="#{d.DayInfoPopUp.trainingValuationS}" />
</t:row>
<t:row id="g_70" >
<t:pane id="g_71" border="#808080" padding="2" width="100%" >
<t:row id="g_72" >
<t:radiobutton id="g_73" flush="true" group="daytime" isdefault="true" refvalue="0" text="#{rr.literals.morning}" value="#{d.DayInfoPopUp.rbgDayTime}" />
<t:radiobutton id="g_74" flush="true" group="daytime" refvalue="1" text="#{rr.literals.lunchtime}" value="#{d.DayInfoPopUp.rbgDayTime}" />
<t:radiobutton id="g_75" flush="true" group="daytime" refvalue="2" text="#{rr.literals.evening}" value="#{d.DayInfoPopUp.rbgDayTime}" />
</t:row>
<t:row id="g_76" comment="morning" rendered="#{d.DayInfoPopUp.rbgDayTime==0}" >
<t:pane id="g_77" >
<t:row id="g_78" >
<t:label id="g_79" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_80" flush="true" objectbinding="#{d.DayInfoPopUp.ckMorningDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_81" />
<t:formattedfield id="g_82" align="center" format="time" rendered="#{d.DayInfoPopUp.ckMorningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ckMorningTime}" width="100" />
</t:row>
<t:row id="g_83" >
<t:label id="g_84" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_85" flush="true" objectbinding="#{d.DayInfoPopUp.ureaMorningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_86" />
<t:formattedfield id="g_87" align="center" format="time" rendered="#{d.DayInfoPopUp.ureaMorningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ureaMorningTime}" width="100" />
</t:row>
<t:row id="g_88" >
<t:label id="g_89" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_90" flush="true" objectbinding="#{d.DayInfoPopUp.glucoseMorningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_91" />
<t:formattedfield id="g_92" align="center" format="time" rendered="#{d.DayInfoPopUp.glucoseMorningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.glucoseMorningTime}" width="100" />
</t:row>
<t:row id="g_93" >
<t:label id="g_94" text="#{rr.literals.haemo}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_95" flush="true" objectbinding="#{d.DayInfoPopUp.hbDS}" step="1" userhint="g/dl" width="40" />
<t:coldistance id="g_96" />
<t:formattedfield id="g_97" align="center" format="time" rendered="#{d.DayInfoPopUp.hbDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.hbTime}" width="100" />
</t:row>
<t:row id="g_98" >
<t:label id="g_99" text="#{rr.literals.haema}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_100" flush="true" objectbinding="#{d.DayInfoPopUp.hktDS}" step="1" userhint="%" width="40" />
<t:coldistance id="g_101" />
<t:formattedfield id="g_102" align="center" format="time" rendered="#{d.DayInfoPopUp.hktDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.hktTime}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_103" comment="lunch" rendered="#{d.DayInfoPopUp.rbgDayTime==1}" >
<t:pane id="g_104" >
<t:row id="g_105" >
<t:label id="g_106" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_107" flush="true" objectbinding="#{d.DayInfoPopUp.ckLunchDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_108" />
<t:formattedfield id="g_109" align="center" format="time" rendered="#{d.DayInfoPopUp.ckLunchDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ckLunchTime}" width="100" />
</t:row>
<t:row id="g_110" >
<t:label id="g_111" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_112" flush="true" objectbinding="#{d.DayInfoPopUp.ureaLunchDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_113" />
<t:formattedfield id="g_114" align="center" format="time" rendered="#{d.DayInfoPopUp.ureaLunchDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ureaLunchTime}" width="100" />
</t:row>
<t:row id="g_115" >
<t:label id="g_116" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_117" flush="true" objectbinding="#{d.DayInfoPopUp.glucoseLunchDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_118" />
<t:formattedfield id="g_119" align="center" format="time" rendered="#{d.DayInfoPopUp.glucoseLunchDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.glucoseLunchTime}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_120" comment="evening" rendered="#{d.DayInfoPopUp.rbgDayTime==2}" >
<t:pane id="g_121" >
<t:row id="g_122" >
<t:label id="g_123" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_124" flush="true" objectbinding="#{d.DayInfoPopUp.ckEveningDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_125" />
<t:formattedfield id="g_126" align="center" format="time" rendered="#{d.DayInfoPopUp.ckEveningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ckEveningTime}" width="100" />
</t:row>
<t:row id="g_127" >
<t:label id="g_128" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_129" flush="true" objectbinding="#{d.DayInfoPopUp.ureaEveningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_130" />
<t:formattedfield id="g_131" align="center" format="time" rendered="#{d.DayInfoPopUp.ureaEveningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ureaEveningTime}" width="100" />
</t:row>
<t:row id="g_132" >
<t:label id="g_133" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_134" flush="true" objectbinding="#{d.DayInfoPopUp.glucoseEveningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_135" />
<t:formattedfield id="g_136" align="center" format="time" rendered="#{d.DayInfoPopUp.glucoseEveningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.glucoseEveningTime}" width="100" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_137" >
<t:checkbox id="g_138" selected="#{d.DayInfoPopUp.dayInfos.restday}" text="#{rr.literals.restday}" />
<t:coldistance id="g_139" />
<t:checkbox id="g_140" selected="#{d.DayInfoPopUp.dayInfos.travelday}" text="#{rr.literals.travelday}" />
<t:coldistance id="g_141" />
<t:checkbox id="g_142" selected="#{d.DayInfoPopUp.dayInfos.camp}" text="#{rr.literals.camp}" />
</t:row>
<t:row id="g_143" >
<t:checkbox id="g_144" flush="true" selected="#{d.DayInfoPopUp.dayInfos.illness}" text="#{rr.literals.illness}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_145" rendered="#{d.DayInfoPopUp.dayInfos.illness==true}" text="#{d.DayInfoPopUp.dayInfos.illnessText}" width="200" />
</t:row>
<t:row id="g_146" >
<t:checkbox id="g_147" flush="true" selected="#{d.DayInfoPopUp.dayInfos.massage}" text="#{rr.literals.massage}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_148" rendered="#{d.DayInfoPopUp.dayInfos.massage==true}" text="#{d.DayInfoPopUp.dayInfos.massageText}" width="200" />
</t:row>
<t:row id="g_149" >
<t:checkbox id="g_150" flush="true" selected="#{d.DayInfoPopUp.dayInfos.therapy}" text="#{rr.literals.therapie}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_151" rendered="#{d.DayInfoPopUp.dayInfos.therapy==truel}" text="#{d.DayInfoPopUp.dayInfos.therapyText}" width="200" />
</t:row>
<t:row id="g_152" >
<t:label id="g_153" rowalignmenty="top" text="#{rr.literals.comment}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_154" height="60" text="#{d.DayInfoPopUp.dayInfos.comment}" width="200" />
<t:coldistance id="g_155" />
<t:label id="g_156" rowalignmenty="top" text="#{rr.literals.comment} #{rr.literals.coach}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_157" height="60" text="#{d.DayInfoPopUp.dayInfos.commentCoach}" width="200" />
</t:row>
<t:row id="g_158" >
<t:button id="g_159" actionListener="#{d.DayInfoPopUp.onPrefill}" image="/images/icons/prefill.png" imageheight="15" text="#{rr.literals.prefill}" />
<t:coldistance id="g_160" width="100%" />
<t:button id="g_161" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
