<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="attachmentdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:rowdynamiccontent id="g_2" contentbinding="#{d.AttachmentDetailUI.labelRow}" />
<t:row id="g_3" >
<t:pane id="g_4" rowdistance="5" >
<t:row id="g_5" >
<t:label id="g_6" text="#{rr.literals.description}" width="100" />
<t:field id="g_7" attributemacro="entityDetailMacro(AttachmentDetailUI,description)" width="200" />
</t:row>
<t:row id="g_8" >
<t:label id="g_9" text="#{rr.literals.category}" width="100" />
<t:combobox id="g_10" attributemacro="entityDetailMacro(AttachmentDetailUI,category)" validvaluesbinding="#{d.AttachmentDetailUI.categoriesVvb}" width="200" />
</t:row>
</t:pane>
<t:coldistance id="g_11" width="20" />
<t:fileuploadbutton id="g_12" actionListener="#{d.AttachmentDetailUI.onUploadFile}" enabled="#{d.AttachmentDetailUI.enabled}" image="/images/icons/upload.png" imageheight="16" text="#{rr.literals.upload_file}" />
<t:coldistance id="g_13" width="20" />
<t:filedownloadbutton id="g_14" enabled="#{d.AttachmentDetailUI.notEnabled}" filename="#{d.AttachmentDetailUI.values.filename}" image="/images/icons/download.png" imageheight="16" opensupported="true" text="#{rr.literals.download_file}" url="#{d.AttachmentDetailUI.downloadURL}" />
</t:row>
<t:rowdistance id="g_15" height="25" />
<t:row id="g_16" >
<t:label id="g_17" text="#{rr.literals.file_name}" width="100" />
<t:field id="g_18" attributemacro="entityDetailMacro(AttachmentDetailUI,filename)" enabled="false" width="200" />
</t:row>
<t:row id="g_19" >
<t:label id="g_20" text="#{rr.literals.file_size}" width="100" />
<t:field id="g_21" align="right" attributemacro="entityDetailMacro(AttachmentDetailUI, filesize)" enabled="false" width="100" />
<t:coldistance id="g_22" width="5" />
<t:label id="g_23" text="bytes" />
</t:row>
<t:row id="g_24" >
<t:label id="g_25" text="#{rr.literals.mime_type}" width="100" />
<t:field id="g_26" attributemacro="entityDetailMacro(AttachmentDetailUI,mimetype)" enabled="false" width="200" />
</t:row>
<t:row id="g_27" >
<t:label id="g_28" text="#{rr.literals.owner}" width="100" />
<t:field id="g_29" attributemacro="entityDetailMacro(AttachmentDetailUI,owner)" enabled="false" width="200" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
