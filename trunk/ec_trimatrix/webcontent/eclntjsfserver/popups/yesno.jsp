<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
	<h:form>
		<f:subview id="eclntjsfserver_popups_yesnog_16">
			<t:rowbodypane id="g_1">
				<t:row id="g_2">
                    <t:image id="g_101" rowalignmenty="middle" image="#{eclntdefscr.yesNoPopup.icon}" rendered="#{eclntdefscr.yesNoPopup.iconRendered}"/>
                    <t:coldistance id="g_102" width="20" rendered="#{eclntdefscr.yesNoPopup.iconRendered}"/>
					<t:scrollpane id="g_3" height="100%" width="100%">
						<t:row id="g_4">
							<t:textpane id="g_5" text="#{eclntdefscr.yesNoPopup.text}" align="center"
								width="100%" />
						</t:row>
					</t:scrollpane>
				</t:row>
				<t:rowdistance id="g_6" height="10" />
				<t:row id="g_7">
					<t:coldistance id="g_8" width="50%" />
					<t:button id="g_9" actionListener="#{eclntdefscr.yesNoPopup.onYes}"
						image="../images/yesnopopup_yes.png"
						text="#{eclnti18n.YESNOPopup_yes}" width="80+" />
					<t:coldistance id="g_10" />
					<t:button id="g_11" actionListener="#{eclntdefscr.yesNoPopup.onNo}"
						image="../images/yesnopopup_no.png"
						text="#{eclnti18n.YESNOPopup_no}" width="80+" />
					<t:coldistance id="g_12"
						rendered="#{eclntdefscr.yesNoPopup.showCancel}" width="50" />
					<t:button id="g_13"
						actionListener="#{eclntdefscr.yesNoPopup.onCancel}"
						rendered="#{eclntdefscr.yesNoPopup.showCancel}"
						text="#{eclnti18n.YESNOPopup_cancel}" width="80+" />
					<t:coldistance id="g_14" width="50%" />
				</t:row>
			</t:rowbodypane>
			<t:pageaddons id="g_pa" />
		</f:subview>
	</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
