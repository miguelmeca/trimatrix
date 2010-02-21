<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="preferencespanelg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" text="#{rr.literals.sbvisibleamount}" width="200" />
<t:coldistance id="g_4" />
<t:formattedfield id="g_5" format="int" value="#{d.PreferencesPanelUI.sbvisibleamount}" width="40" />
</t:row>
<t:row id="g_6" >
<t:label id="g_7" rowalignmenty="top" text="#{rr.literals.competition_categories}" width="200" />
<t:coldistance id="g_8" />
<t:textarea id="g_9" height="40" text="#{d.PreferencesPanelUI.competitionCategories}" userhint="#{rr.literals.separate_semicolon}" width="350" />
</t:row>
<t:row id="g_10" >
<t:coldistance id="g_11" width="100%" />
<t:button id="g_12" actionListener="#{d.PreferencesPanelUI.onSave}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
