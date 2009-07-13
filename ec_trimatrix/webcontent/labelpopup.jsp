<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="labelpopupg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:pane id="g_3" border="#00000030" height="100%" padding="20" width="100%" >
<t:row id="g_4" >
<t:field id="g_5" actionListener="#{d.LabelPopUpUI.onSearch}" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" flush="true" flushtimer="1000" text="#{d.LabelPopUpUI.searchText}" width="100%" />
</t:row>
<t:rowdistance id="g_6" height="20" />
<t:row id="g_7" >
<t:fixgrid id="g_8" avoidroundtrips="true" background="#ffffff40" drawoddevenrows="false" height="100%" objectbinding="#{d.LabelPopUpUI.grid}" rowheight="18" sbvisibleamount="30" suppressheadline="true" width="100%" >
<t:gridcol id="g_9" text="Column" width="100%" >
<t:label id="g_10" text=".{description}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:rowdistance id="g_11" />
<t:row id="g_12" >
<t:button id="g_13" actionListener="#{d.LabelPopUpUI.onCreate}" rendered="#{d.LabelPopUpUI.renderCreate}" text="#{d.LabelPopUpUI.txtCreate}" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
