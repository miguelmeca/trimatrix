<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="imports_resultsg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:label id="g_3" height="20" text="#{rr.literals.competition}" width="120" />
<t:link id="g_4" actionListener="#{d.ResultDetailUI.onCompetitionClicked}" align="left" attributemacro="entityDetailMacro(ResultDetailUI,competition)" enabled="true" focusable="true" foreground="#000000" height="20" width="200" />
<t:coldistance id="g_5" />
<t:button id="g_6" actionListener="#{d.ResultDetailUI.onCompetitionSearch}" enabled="#{d.ResultDetailUI.enabled}" height="20" text="#{rr.literals.edit}" width="60" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.category}" width="120" />
<t:combofield id="g_9" actionListener="#{d.ResultDetailUI.onCategoryF4}" attributemacro="entityDetailMacro(ResultDetailUI,category_tria)" flush="true" maxlength="10" width="100" />
</t:row>
<t:row id="g_10" >
<t:button id="g_11" image="/images/icons/mapping.png" imageheight="16" text="#{rr.literals.mapping}" />
</t:row>
<t:row id="g_12" >
<t:fileuploadbutton id="g_13" image="/images/icons/upload.png" imageheight="16" text="#{rr.literals.upload_file}" />
<t:coldistance id="g_14" />
<t:label id="g_15" text="Dateiname" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
