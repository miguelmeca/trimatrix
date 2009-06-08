<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="attachmentdetailg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:pane id="g_3" rowdistance="5" >
<t:row id="g_4" >
<t:label id="g_5" text="#{rr.literals.description}" width="100" />
<t:field id="g_6" attributemacro="entityDetailMacro(AttachmentDetailUI,description)" width="200" />
</t:row>
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.category}" width="100" />
<t:combobox id="g_9" attributemacro="entityDetailMacro(AttachmentDetailUI,category)" validvaluesbinding="#{d.AttachmentDetailUI.categoriesVvb}" width="200" />
</t:row>
</t:pane>
<t:coldistance id="g_10" width="20" />
<t:fileuploadbutton id="g_11" actionListener="#{d.AttachmentDetailUI.onUploadFile}" enabled="#{d.AttachmentDetailUI.enabled}" text="#{rr.literals.upload_file}" />
<t:coldistance id="g_12" width="20" />
<t:filedownloadbutton id="g_13" enabled="#{d.AttachmentDetailUI.notEnabled}" filename="#{d.AttachmentDetailUI.values.filename}" opensupported="true" text="#{rr.literals.download_file}" url="#{d.AttachmentDetailUI.downloadURL}" />
</t:row>
<t:rowdistance id="g_14" height="25" />
<t:row id="g_15" >
<t:label id="g_16" text="#{rr.literals.file_name}" width="100" />
<t:field id="g_17" attributemacro="entityDetailMacro(AttachmentDetailUI,filename)" enabled="false" width="200" />
</t:row>
<t:row id="g_18" >
<t:label id="g_19" text="#{rr.literals.file_size}" width="100" />
<t:field id="g_20" align="right" attributemacro="entityDetailMacro(AttachmentDetailUI, filesize)" enabled="false" width="100" />
<t:coldistance id="g_21" width="5" />
<t:label id="g_22" text="bytes" />
</t:row>
<t:row id="g_23" >
<t:label id="g_24" text="#{rr.literals.mime_type}" width="100" />
<t:field id="g_25" attributemacro="entityDetailMacro(AttachmentDetailUI,mimetype)" enabled="false" width="200" />
</t:row>
<t:row id="g_26" >
<t:label id="g_27" text="#{rr.literals.owner}" width="100" />
<t:field id="g_28" attributemacro="entityDetailMacro(AttachmentDetailUI,owner)" enabled="false" width="200" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
