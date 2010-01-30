<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="scheduleseriesg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:coldistance id="g_3" width="100%" />
<t:button id="g_4" align="center" bgpaint="roundedrectangle(0,0,100%,100%,5,5,#0000FF80)" contentareafilled="false" dragsend="schedule:series_a" font="size:10;weight:bold" foreground="#000000" stylevariant="WP_ISOLATEDWORKPAGE" text="Plan A" width="140" />
<t:coldistance id="g_5" width="100%" />
</t:row>
<t:row id="g_6" >
<t:coldistance id="g_7" width="100%" />
<t:button id="g_8" align="center" bgpaint="roundedrectangle(0,0,100%,100%,5,5,#00FF0080)" contentareafilled="false" dragsend="schedule:series_b" font="size:10;weight:bold" foreground="#000000" stylevariant="WP_ISOLATEDWORKPAGE" text="Plan B" width="140" />
<t:coldistance id="g_9" width="100%" />
</t:row>
<t:row id="g_10" >
<t:coldistance id="g_11" width="100%" />
<t:button id="g_12" align="center" bgpaint="roundedrectangle(0,0,100%,100%,5,5,#FF000080)" contentareafilled="false" dragsend="schedule:series_b" font="size:10;weight:bold" foreground="#000000" stylevariant="WP_ISOLATEDWORKPAGE" text="Plan C" width="140" />
<t:coldistance id="g_13" width="100%" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
