<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_resultslistg_sv">
<t:row id="g_1" >
<t:pane id="g_2" height="100%" rowdistance="5" stylevariant="popup" width="100%" >
<t:row id="g_3" >
<t:label id="g_4" text="#{rr.literals.results_list}" width="90" />
<t:field id="g_5" enabled="false" focusable="false" text="#{d.ResultsListPopUp.resultList.fileName}" width="200" />
<t:coldistance id="g_6" rendered="#{d.ResultsListPopUp.resultList!=null}" />
<t:button id="g_7" actionListener="#{d.ResultsListPopUp.onDelete}" image="/images/icons/delete.png" imageheight="16" rendered="#{d.ResultsListPopUp.resultList!=null}" text="#{rr.literals.delete}" />
<t:coldistance id="g_8" rendered="#{d.ResultsListPopUp.resultList==null}" />
<t:fileuploadbutton id="g_9" actionListener="#{d.ResultsListPopUp.onUpload}" fileextensions="xls" image="/images/icons/upload.png" imageheight="16" rendered="#{d.ResultsListPopUp.resultList==null}" text="#{rr.literals.upload}" />
<t:coldistance id="g_10" />
<t:filedownloadbutton id="g_11" filename="#{d.ResultsListPopUp.resultList.fileName}" image="/images/icons/download.png" imageheight="16" rendered="#{d.ResultsListPopUp.resultList!=null}" text="#{rr.literals.download}" url="#{d.ResultsListPopUp.downloadURL}" />
</t:row>
<t:row id="g_12" >
<t:label id="g_13" text="#{rr.literals.template}" width="90" />
<t:combofield id="g_14" actionListener="#{d.ResultsListPopUp.onTemplateF4}" text="#{d.ResultsListPopUp.template}" width="200" />
</t:row>
<t:row id="g_15" >
<t:textpane id="g_16" align="center" background="#00000015" font="size:12;weight:bold" text="To create or test a template go to menu Import =&gt; Results" width="100%" />
</t:row>
<t:row id="g_17" >
<t:button id="g_18" actionListener="#{d.ResultsListPopUp.onOk}" text="#{rr.literals.ok}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
