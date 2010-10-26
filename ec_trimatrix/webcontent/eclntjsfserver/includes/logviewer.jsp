<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="eclntjsfserver_includes_logviewerg_sv">
<t:rowheader id="g_1" >
<t:button id="g_2" actionListener="#{eclntdefscr.logViewer.onShowLogFile}" rowalignmenty="top" text="(Re-) Read Logfile" contentareafilled="false"/>
<t:coldistance id="g_3" width="100%" />
<t:checkbox id="g_4" flush="true" selected="#{eclntdefscr.logViewer.flagShowTimestampThread}" text="Show Detail Columns" />
</t:rowheader>
<t:rowbodypane id="g_5" padding="0" rowdistance="0" >
<t:row id="g_6" >
<t:fixgrid id="g_7" background="#FFFFFF" border="top:0" bordercolor="#00000030" borderheight="1" borderwidth="1" height="100%" objectbinding="#{eclntdefscr.logViewer.all}" rowbgpaint="rectangle(0,50%,100%,50%,#00000010)" rowheight="16" sbvisibleamount="30" width="100%" >
<t:gridcol id="g_8" rendered="#{eclntdefscr.logViewer.flagShowTimestampThread}" text="Timestamp" width="120" >
<t:label id="g_9" background=".{categoryColor}" font="size:10" text=".{timestamp}" />
</t:gridcol>
<t:gridcol id="g_10" rendered="#{eclntdefscr.logViewer.flagShowTimestampThread}" text="Thr." width="30" >
<t:label id="g_11" background=".{categoryColor}" font="size:10" text=".{threadInfo}" />
</t:gridcol>
<t:gridcol id="g_12" text="Level" width="40" >
<t:label id="g_13" background=".{categoryColor}" font="size:10" text=".{category}" />
</t:gridcol>
<t:gridcol id="g_14" text="Text" width="100%" >
<t:label id="g_15" background=".{textColor}" text=".{text}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_16" >
<t:foldablepane id="g_17" bgpaint="rectan" foreground="#FFFFFF" headerbgpaint="rectangle(0,0,100%,30,#606070,#202030,vertical)" innerpadding="0" padding="0" text="Severe &amp; Warning Messages only" width="100%" >
<t:row id="g_18" >
<t:fixgrid id="g_19" background="#FFFFFF" border="top:0" bordercolor="#00000030" borderheight="1" borderwidth="1" objectbinding="#{eclntdefscr.logViewer.grid}" rowbgpaint="rectangle(0,50%,100%,50%,#00000010)" rowheight="16" sbvisibleamount="8" suppressheadline="true" width="100%" >
<t:gridcol id="g_20" rendered="#{eclntdefscr.logViewer.flagShowTimestampThread}" text="Timestamp" width="120" >
<t:label id="g_21" background=".{categoryColor}" font="size:10" text=".{timestamp}" />
</t:gridcol>
<t:gridcol id="g_22" rendered="#{eclntdefscr.logViewer.flagShowTimestampThread}" text="Thr." width="30" >
<t:label id="g_23" background=".{categoryColor}" font="size:10" text=".{threadInfo}" />
</t:gridcol>
<t:gridcol id="g_24" text="Level" width="40" >
<t:label id="g_25" background=".{categoryColor}" font="size:10" text=".{category}" />
</t:gridcol>
<t:gridcol id="g_26" text="Text" width="100%" >
<t:label id="g_27" text=".{text}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
