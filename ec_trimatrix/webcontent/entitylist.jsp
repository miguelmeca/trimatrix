<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="entitylistg_sv">
<t:rowbodypane id="g_1" background="#00000010" rowdistance="5" >
<t:row id="g_2" >
<t:pane id="g_3" rowdistance="5" width="100%" >
<t:row id="g_4" >
<t:pane id="g_5" width="100%" >
<t:row id="g_6" rendered="#{d.EntityListUI.renderButtons}" >
<t:button id="g_7" actionListener="#{d.EntityListUI.onNew}" enabled="#{d.EntityListUI.createAllowed}" image="/images/icons/new.png" imageheight="15" text="#{rr.literals.new}" />
<t:coldistance id="g_8" />
<t:button id="g_9" actionListener="#{d.EntityListUI.onDelete}" enabled="#{d.EntityListUI.deleteAllowed}" image="/images/icons/delete.png" imageheight="15" text="#{rr.literals.delete}" />
<t:coldistance id="g_10" width="100%" />
<t:filedownloadbutton id="g_11" fileextensions="#{d.EntityListUI.printReportExtension}" filename="#{d.EntityListUI.printReportFilename}" image="/images/icons/print.png" imageheight="15" openimmediately="true" opensupported="true" rendered="#{d.EntityListUI.printSupported}" text="#{rr.literals.print}" url="#{d.EntityListUI.printReportUrl}" />
</t:row>
</t:pane>
</t:row>
<t:rowdynamiccontent id="g_12" contentbinding="#{d.EntityListUI.dynSearchRow}" rendered="#{d.EntityListUI.renderSearch}" />
<t:row id="g_13" >
<t:foldablepane id="g_14" rowdistance="5" text="#{rr.literals.search}" width="100%" >
<t:row id="g_15" comment="0" >
<t:combobox id="g_16" actionListener="#{d.EntityListUI.onSearchItemChange}" comment="field" flush="true" valuetextmode="TEXT" width="150" withnullitem="false" >
<t:comboboxitem id="g_17" text="Vorname" value="nameFirst" />
</t:combobox>
<t:coldistance id="g_18" />
<t:combobox id="g_19" actionListener="#{d.EntityListUI.onSearchItemChange}" comment="operator" valuetextmode="TEXT" width="100" withnullitem="false" >
<t:comboboxitem id="g_20" text="ist gleich" value="EQ" />
</t:combobox>
<t:coldistance id="g_21" />
<t:field id="g_22" width="200" />
<t:coldistance id="g_23" />
<t:button id="g_24" actionListener="#{d.EntityListUI.onAddSearchItem}" contentareafilled="false" image="/images/icons/add.png" imageheight="18" />
<t:button id="g_25" actionListener="#{d.EntityListUI.onRemoveSearchItem}" contentareafilled="false" image="/images/icons/remove.png" imageheight="18" />
</t:row>
<t:row id="g_26" >
<t:button id="g_27" actionListener="#{d.EntityListUI.onSearch}" image="/images/icons/magnifier.png" imageheight="15" text="#{rr.literals.search}" />
<t:coldistance id="g_28" />
<t:button id="g_29" actionListener="#{d.EntityListUI.onClear}" image="/images/icons/clear.png" text="#{rr.literals.clear}" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_30" rendered="#{d.EntityListUI.renderButtons}" >
<t:icon id="g_31" actionListener="#{d.EntityListUI.gridList.textSearcher.onPrevious}" image="/eclntjsfserver/images/control_playbackwards.png" />
<t:field id="g_32" bgpaint="image(100%-5,50%,/images/icons/magnifier.png,rightmiddle)" text="#{d.EntityListUI.gridList.textSearcher.text}" width="100" />
<t:icon id="g_33" actionListener="#{d.EntityListUI.gridList.textSearcher.onNext}" image="/eclntjsfserver/images/control_play.png" />
<t:coldistance id="g_34" width="100%" />
<t:link id="g_35" actionListener="#{d.EntityListUI.gridList.onOpenGridFunctions}" text="#{rr.literals.export}" />
</t:row>
<t:rowline id="g_36" />
<t:rowdynamiccontent id="g_37" contentbinding="#{d.EntityListUI.dynRow}" />
<t:row id="g_38" rendered="#{d.EntityListUI.renderButtons}" >
<t:button id="g_39" actionListener="#{d.EntityListUI.onRefresh}" image="/images/icons/refresh.png" imageheight="15" text="#{rr.literals.list_refresh}" />
<t:coldistance id="g_40" width="100%" />
<t:button id="g_41" actionListener="#{d.EntityListUI.gridList.onEditColumnDetails}" image="/images/icons/configure.png" imageheight="15" text="#{rr.literals.list_configure}" />
<t:coldistance id="g_42" />
<t:button id="g_43" actionListener="#{d.EntityListUI.saveGridState}" image="/images/icons/save.png" imageheight="15" text="#{rr.literals.save}" />
</t:row>
</t:pane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
