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
<t:row id="g_1" >
<t:pane id="g_2" rowdistance="5" stylevariant="popup" >
<t:row id="g_3" >
<t:coldistance id="g_4" width="100%" />
<t:button id="g_5" actionListener="#{d.DayInfoPopUp.onPreviousDay}" align="center" contentareafilled="false" image="/images/icons/black_prev.png" />
<t:coldistance id="g_6" />
<t:label id="g_7" text="#{d.DayInfoPopUp.selectedDate}" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.DayInfoPopUp.onNextDay}" align="center" contentareafilled="false" image="/images/icons/black_next.png" />
<t:coldistance id="g_10" width="100%" />
</t:row>
<t:rowline id="g_11" />
<t:row id="g_12" >
<t:label id="g_13" text="#{rr.literals.hr_rest}" width="#{d.DayInfoPopUp.width}" />
<t:formattedfield id="g_14" align="center" format="int" maxlength="3" value="#{d.DayInfoPopUp.dayInfos.restingHr}" width="100" />
</t:row>
<t:row id="g_15" >
<t:label id="g_16" text="#{rr.literals.weather}" width="#{d.DayInfoPopUp.width}" />
<t:combobox id="g_17" flush="true" value="#{d.DayInfoPopUp.dayInfos.weather}" width="100" withnullitem="false" withvaluecomment="true" >
<t:comboboxitem id="g_18" image="/images/icons/weather_sun.png" text="#{rr.literals.sun}" value="sun" />
<t:comboboxitem id="g_19" image="/images/icons/weather_fewclouds.png" text="#{rr.literals.fewclouds}" value="fewclouds" />
<t:comboboxitem id="g_20" image="/images/icons/weather_overcast.png" text="#{rr.literals.overcast}" value="overcast" />
<t:comboboxitem id="g_21" image="/images/icons/weather_scattered.png" text="#{rr.literals.scattered}" value="scattered" />
<t:comboboxitem id="g_22" image="/images/icons/weather_showers.png" text="#{rr.literals.showers}" value="showers" />
<t:comboboxitem id="g_23" image="/images/icons/weather_snow.png" text="#{rr.literals.snow}" value="snow" />
</t:combobox>
<t:coldistance id="g_24" />
<t:image id="g_25" image="#{d.DayInfoPopUp.weatherImage}" />
<t:coldistance id="g_26" />
<t:label id="g_27" text="#{rr.literals.temperature}" />
<t:coldistance id="g_28" />
<tx:decspinner id="g_29" objectbinding="#{d.DayInfoPopUp.temperatureDS}" step="0.5" userhint="#{rr.literals.degree}" width="40" />
</t:row>
<t:row id="g_30" >
<t:label id="g_31" text="#{rr.literals.city}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_32" text="#{d.DayInfoPopUp.dayInfos.location}" width="100" />
<t:coldistance id="g_33" />
<t:label id="g_34" text="#{rr.literals.altitude}" />
<t:coldistance id="g_35" />
<t:formattedfield id="g_36" align="center" format="int" maxlength="5" value="#{d.DayInfoPopUp.dayInfos.altitude}" width="60" />
</t:row>
<t:row id="g_37" >
<t:label id="g_38" text="#{rr.literals.weight} #{rr.literals.morning}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_39" objectbinding="#{d.DayInfoPopUp.weightMorningDS}" step="0.1" userhint="kg" width="40" />
<t:coldistance id="g_40" width="35" />
<t:label id="g_41" text="#{rr.literals.weight} #{rr.literals.evening}" />
<t:coldistance id="g_42" />
<tx:decspinner id="g_43" objectbinding="#{d.DayInfoPopUp.weightEveningDS}" step="0.1" userhint="kg" width="40" />
</t:row>
<t:row id="g_44" >
<t:label id="g_45" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 12:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_46" height="40" rowalignmenty="top" text="#{d.DayInfoPopUp.dayInfos.dinnersLunch}" width="200" />
<t:coldistance id="g_47" />
<t:label id="g_48" rowalignmenty="top" text="#{rr.literals.dinners} #{rr.literals.to} 22:00" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_49" height="40" text="#{d.DayInfoPopUp.dayInfos.dinnersEvening}" width="200" />
</t:row>
<t:row id="g_50" >
<t:label id="g_51" text="#{rr.literals.fluids_intake}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_52" objectbinding="#{d.DayInfoPopUp.fluidsIntakeDS}" step="0.1" userhint="l" width="40" />
</t:row>
<t:row id="g_53" >
<t:label id="g_54" text="#{rr.literals.sleeping_quality}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_55" objectbinding="#{d.DayInfoPopUp.sleepingQualityS}" />
<t:coldistance id="g_56" />
<t:label id="g_57" text="#{rr.literals.sleeping_hours}" />
<t:coldistance id="g_58" />
<t:formattedfield id="g_59" format="double" userhint="h" value="#{d.DayInfoPopUp.dayInfos.sleepingHours}" width="50" />
</t:row>
<t:row id="g_60" >
<t:label id="g_61" text="#{rr.literals.tiredness}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_62" objectbinding="#{d.DayInfoPopUp.tirednessS}" />
<t:coldistance id="g_63" width="50" />
<t:label id="g_64" text="#{rr.literals.feeling}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_65" objectbinding="#{d.DayInfoPopUp.feelingS}" />
</t:row>
<t:row id="g_66" >
<t:label id="g_67" text="#{rr.literals.training_intensity}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_68" objectbinding="#{d.DayInfoPopUp.trainingIntensityS}" />
<t:coldistance id="g_69" width="50" />
<t:label id="g_70" text="#{rr.literals.training_valuation}" width="#{d.DayInfoPopUp.width}" />
<tx:star id="g_71" objectbinding="#{d.DayInfoPopUp.trainingValuationS}" />
</t:row>
<t:row id="g_72" >
<t:pane id="g_73" border="#808080" padding="2" >
<t:row id="g_74" >
<t:radiobutton id="g_75" flush="true" group="daytime" isdefault="true" refvalue="0" text="#{rr.literals.morning}" value="#{d.DayInfoPopUp.rbgDayTime}" />
<t:radiobutton id="g_76" flush="true" group="daytime" refvalue="1" text="#{rr.literals.lunchtime}" value="#{d.DayInfoPopUp.rbgDayTime}" />
<t:radiobutton id="g_77" flush="true" group="daytime" refvalue="2" text="#{rr.literals.evening}" value="#{d.DayInfoPopUp.rbgDayTime}" />
</t:row>
<t:row id="g_78" comment="morning" rendered="#{d.DayInfoPopUp.rbgDayTime==0}" >
<t:pane id="g_79" >
<t:row id="g_80" >
<t:label id="g_81" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_82" flush="true" objectbinding="#{d.DayInfoPopUp.ckMorningDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_83" />
<t:formattedfield id="g_84" align="center" format="time" rendered="#{d.DayInfoPopUp.ckMorningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ckMorningTime}" width="100" />
</t:row>
<t:row id="g_85" >
<t:label id="g_86" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_87" flush="true" objectbinding="#{d.DayInfoPopUp.ureaMorningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_88" />
<t:formattedfield id="g_89" align="center" format="time" rendered="#{d.DayInfoPopUp.ureaMorningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ureaMorningTime}" width="100" />
</t:row>
<t:row id="g_90" >
<t:label id="g_91" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_92" flush="true" objectbinding="#{d.DayInfoPopUp.glucoseMorningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_93" />
<t:formattedfield id="g_94" align="center" format="time" rendered="#{d.DayInfoPopUp.glucoseMorningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.glucoseMorningTime}" width="100" />
</t:row>
<t:row id="g_95" >
<t:label id="g_96" text="#{rr.literals.haemo}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_97" flush="true" objectbinding="#{d.DayInfoPopUp.hbDS}" step="1" userhint="g/dl" width="40" />
<t:coldistance id="g_98" />
<t:formattedfield id="g_99" align="center" format="time" rendered="#{d.DayInfoPopUp.hbDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.hbTime}" width="100" />
</t:row>
<t:row id="g_100" >
<t:label id="g_101" text="#{rr.literals.haema}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_102" flush="true" objectbinding="#{d.DayInfoPopUp.hktDS}" step="1" userhint="%" width="40" />
<t:coldistance id="g_103" />
<t:formattedfield id="g_104" align="center" format="time" rendered="#{d.DayInfoPopUp.hktDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.hktTime}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_105" comment="lunch" rendered="#{d.DayInfoPopUp.rbgDayTime==1}" >
<t:pane id="g_106" >
<t:row id="g_107" >
<t:label id="g_108" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_109" flush="true" objectbinding="#{d.DayInfoPopUp.ckLunchDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_110" />
<t:formattedfield id="g_111" align="center" format="time" rendered="#{d.DayInfoPopUp.ckLunchDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ckLunchTime}" width="100" />
</t:row>
<t:row id="g_112" >
<t:label id="g_113" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_114" flush="true" objectbinding="#{d.DayInfoPopUp.ureaLunchDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_115" />
<t:formattedfield id="g_116" align="center" format="time" rendered="#{d.DayInfoPopUp.ureaLunchDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ureaLunchTime}" width="100" />
</t:row>
<t:row id="g_117" >
<t:label id="g_118" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_119" flush="true" objectbinding="#{d.DayInfoPopUp.glucoseLunchDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_120" />
<t:formattedfield id="g_121" align="center" format="time" rendered="#{d.DayInfoPopUp.glucoseLunchDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.glucoseLunchTime}" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_122" comment="evening" rendered="#{d.DayInfoPopUp.rbgDayTime==2}" >
<t:pane id="g_123" >
<t:row id="g_124" >
<t:label id="g_125" text="#{rr.literals.ck}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_126" flush="true" objectbinding="#{d.DayInfoPopUp.ckEveningDS}" step="1" userhint="U/l" width="40" />
<t:coldistance id="g_127" />
<t:formattedfield id="g_128" align="center" format="time" rendered="#{d.DayInfoPopUp.ckEveningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ckEveningTime}" width="100" />
</t:row>
<t:row id="g_129" >
<t:label id="g_130" text="#{rr.literals.urea}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_131" flush="true" objectbinding="#{d.DayInfoPopUp.ureaEveningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_132" />
<t:formattedfield id="g_133" align="center" format="time" rendered="#{d.DayInfoPopUp.ureaEveningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.ureaEveningTime}" width="100" />
</t:row>
<t:row id="g_134" >
<t:label id="g_135" text="#{rr.literals.glucose}" width="#{d.DayInfoPopUp.width}" />
<tx:decspinner id="g_136" flush="true" objectbinding="#{d.DayInfoPopUp.glucoseEveningDS}" step="1" userhint="mg/dl" width="40" />
<t:coldistance id="g_137" />
<t:formattedfield id="g_138" align="center" format="time" rendered="#{d.DayInfoPopUp.glucoseEveningDS.value!=null}" value="#{d.DayInfoPopUp.dayInfos.glucoseEveningTime}" width="100" />
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_139" >
<t:checkbox id="g_140" selected="#{d.DayInfoPopUp.dayInfos.restday}" text="#{rr.literals.restday}" />
<t:coldistance id="g_141" />
<t:checkbox id="g_142" selected="#{d.DayInfoPopUp.dayInfos.travelday}" text="#{rr.literals.travelday}" />
<t:coldistance id="g_143" />
<t:checkbox id="g_144" selected="#{d.DayInfoPopUp.dayInfos.camp}" text="#{rr.literals.camp}" />
</t:row>
<t:row id="g_145" >
<t:checkbox id="g_146" flush="true" selected="#{d.DayInfoPopUp.dayInfos.illness}" text="#{rr.literals.illness}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_147" rendered="#{d.DayInfoPopUp.dayInfos.illness==true}" text="#{d.DayInfoPopUp.dayInfos.illnessText}" width="200" />
</t:row>
<t:row id="g_148" >
<t:checkbox id="g_149" flush="true" selected="#{d.DayInfoPopUp.dayInfos.massage}" text="#{rr.literals.massage}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_150" rendered="#{d.DayInfoPopUp.dayInfos.massage==true}" text="#{d.DayInfoPopUp.dayInfos.massageText}" width="200" />
</t:row>
<t:row id="g_151" >
<t:checkbox id="g_152" flush="true" selected="#{d.DayInfoPopUp.dayInfos.therapy}" text="#{rr.literals.therapie}" width="#{d.DayInfoPopUp.width}" />
<t:field id="g_153" rendered="#{d.DayInfoPopUp.dayInfos.therapy==true}" text="#{d.DayInfoPopUp.dayInfos.therapyText}" width="200" />
</t:row>
<t:row id="g_154" >
<t:label id="g_155" rowalignmenty="top" text="#{rr.literals.comment}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_156" height="60" text="#{d.DayInfoPopUp.dayInfos.comment}" width="200" />
<t:coldistance id="g_157" />
<t:label id="g_158" rowalignmenty="top" text="#{rr.literals.comment} #{rr.literals.coach}" width="#{d.DayInfoPopUp.width}" />
<t:textarea id="g_159" height="60" text="#{d.DayInfoPopUp.dayInfos.commentCoach}" width="200" />
</t:row>
<t:row id="g_160" >
<t:button id="g_161" actionListener="#{d.DayInfoPopUp.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
<t:coldistance id="g_162" width="100%" />
<t:button id="g_163" actionListener="#{d.DayInfoPopUp.onPrefill}" image="/images/icons/prefill.png" imageheight="15" text="#{rr.literals.prefill}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
