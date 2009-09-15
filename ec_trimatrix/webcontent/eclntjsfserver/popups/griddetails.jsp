<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
	<h:form>
		<f:subview id="eclntjsfserver_popups_griddetailsg_4">
			<t:rowbodypane id="g_1" padding="1">

				<t:row id="g_2">

					<t:pane id="g_3" actionListener="#{eclntdefscr.gridDetails.onLeft}"
						dropreceive="rights" height="100%" padding="10" width="100%">

						<t:row id="g_4">

							<t:fixgrid id="g_5" background="#E0E0E0" bordercolor="#c0c0c0"
								borderheight="1" borderwidth="1" height="100%"
								multiselect="true"
								objectbinding="#{eclntdefscr.gridDetails.lefts}"
								sbvisibleamount="25" width="40%">

								<t:gridcol id="g_6" text="#{eclnti18n.GRID_availableColumns}"
									width="100%">

									<t:label id="g_7" dragsend="lefts:lefts" text=".{text}" />
								</t:gridcol>

							</t:fixgrid>

							<t:coldistance id="g_8" width="10" />
							<t:pane id="g_9">

								<t:rowdistance id="g_10" height="50%" />
								<t:row id="g_11">

									<t:icon id="g_12"
										actionListener="#{eclntdefscr.gridDetails.onRight}"
										image="/eclntjsfserver/images/control_play.png" />
								</t:row>

								<t:row id="g_13">

									<t:icon id="g_14"
										actionListener="#{eclntdefscr.gridDetails.onLeft}"
										image="/eclntjsfserver/images/control_playbackwards.png" />
								</t:row>

								<t:rowdistance id="g_15" height="50%" />
							</t:pane>

							<t:coldistance id="g_16" width="10" />
							<t:pane id="g_17"
								actionListener="#{eclntdefscr.gridDetails.onRight}"
								dropreceive="lefts" height="100%" width="60%">

								<t:row id="g_18">

									<t:fixgrid id="g_19" background="#FFFFFF" bordercolor="#c0c0c0"
										borderheight="1" borderwidth="1" height="100%"
										multiselect="true"
										objectbinding="#{eclntdefscr.gridDetails.rights}"
										sbvisibleamount="25" width="100%">

										<t:gridcol id="g_20" text="#{eclnti18n.GRID_selectedColumns}"
											width="100%">

											<t:label id="g_21" actionListener=".{onDropRights}"
												dragsend="rights:rights" dropreceive="rights;lefts"
												text=".{text}" width="100%" />
										</t:gridcol>

									</t:fixgrid>

								</t:row>

								<t:row id="g_22">

									<t:coldistance id="g_23" width="50%" />
									<t:icon id="g_24"
										actionListener="#{eclntdefscr.gridDetails.onDown}"
										image="/eclntjsfserver/images/arrow_upgray.png" />
									<t:icon id="g_25"
										actionListener="#{eclntdefscr.gridDetails.onUp}"
										image="/eclntjsfserver/images/arrow_downgray.png" />
									<t:coldistance id="g_26" width="50%" />
								</t:row>

							</t:pane>

						</t:row>

					</t:pane>

				</t:row>

				<t:rowdistance id="g_27" height="10" />
				<t:row id="g_28">

					<t:pane id="g_29" background="#00000010"
						border="top:1;color:#c0c0c0"
						padding="top:3;bottom:3;left:10;right:10" width="100%">

						<t:row id="g_30">

							<t:link id="g_1000" text="#{eclnti18n.GRID_revert}"
								actionListener="#{eclntdefscr.gridDetails.onRevertChanges}" />

							<t:coldistance id="g_31" width="100%" />
							<t:button id="g_32"
								actionListener="#{eclntdefscr.gridDetails.onApply}"
								text="#{eclnti18n.GRID_apply}" />
						</t:row>

					</t:pane>

				</t:row>

			</t:rowbodypane>

			<t:pageaddons id="g_pa" />
		</f:subview>
	</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
