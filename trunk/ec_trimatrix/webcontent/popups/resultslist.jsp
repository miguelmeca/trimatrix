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
<t:pane id="g_2" rowdistance="5" stylevariant="popup" >
<t:row id="g_3" >
<t:label id="g_4" text="#{rr.literals.results_list}" width="90" />
<t:field id="g_5" enabled="false" focusable="false" width="200" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.ResultsListPopUp.onDelete}" image="/images/icons/delete.png" imageheight="16" text="#{rr.literals.delete}" />
<t:coldistance id="g_8" />
<t:fileuploadbutton id="g_9" actionListener="#{d.ResultsListPopUp.onUpload}" fileextensions="xls" image="/images/icons/upload.png" imageheight="16" text="#{rr.literals.upload}" />
<t:coldistance id="g_10" />
<t:filedownloadbutton id="g_11" filename="#{d.ResultsListPopUp.resultList.fileName}" image="/images/icons/download.png" imageheight="16" text="#{rr.literals.download}" url="#{d.ResultsListPopUp.downloadURL}" />
</t:row>
<t:row id="g_12" >
<t:label id="g_13" text="#{rr.literals.template}" width="90" />
<t:field id="g_14" enabled="false" focusable="false" width="200" />
<t:coldistance id="g_15" />
<t:button id="g_16" actionListener="#{d.ResultsListPopUp.onCreateTemplate}" image="/images/icons/new.png" imageheight="16" text="#{rr.literals.new}" />
<t:coldistance id="g_17" />
<t:button id="g_18" actionListener="#{d.ResultsListPopUp.onPreview}" image="/images/icons/preview.png" imageheight="16" text="#{rr.literals.download}" />
</t:row>
<t:row id="g_19" comment="preview" >
<t:arraygrid id="g_20" horizontalscrollmode="autowithresize" objectbinding="#{d.ResultsListPopUp.gridPreview}" sbvisibleamount="15" showemptyrows="false" />
</t:row>
<t:row id="g_21" >
<t:button id="g_22" actionListener="#{d.ResultsListPopUp.onOk}" text="#{rr.literals.ok}" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
