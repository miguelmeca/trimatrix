<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="popups_labelchangeg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:label id="g_3" text="#{rr.literals.color}" width="70" />
<t:coldistance id="g_4" />
<t:colorfield id="g_5" background="#{d.LabelChangePopUp.color}" flush="true" focusable="false" foreground="#{d.LabelChangePopUp.color}" value="#{d.LabelChangePopUp.color}" width="100" />
</t:row>
<t:rowdistance id="g_6" />
<t:row id="g_7" >
<t:label id="g_8" text="#{rr.literals.description}" width="70" />
<t:coldistance id="g_9" />
<t:field id="g_10" requestfocus="creation" text="#{d.LabelChangePopUp.description}" width="200" />
</t:row>
<t:rowdistance id="g_11" height="10" />
<t:row id="g_12" >
<t:coldistance id="g_13" width="50%" />
<t:button id="g_14" actionListener="#{d.LabelChangePopUp.onChange}" text="#{rr.literals.change}" />
<t:coldistance id="g_15" />
<t:button id="g_16" actionListener="#{d.LabelChangePopUp.onCancel}" text="#{rr.literals.cancel}" />
<t:coldistance id="g_17" width="50%" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
