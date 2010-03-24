<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="tx" uri="/WEB-INF/trimatrixcontrols"%>
<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <link id="mystyle" rel="stylesheet" type="text/css" href="../eclntjsfserver/htstyle/htstyle.css">
  <script>
  function isIE() { if (navigator.appName == "Netscape") return false; else return true; }
  var m_hotkeycallbacks = new Array();
  function ccHotkey(pEvent)
  {
    for (var i=0; i<m_hotkeycallbacks.length; i++)
      m_hotkeycallbacks[i](pEvent);
  }
  function ccDisableEnterKey(e)
  {
    if(e.keyCode == 13) return false;
    else return true;
  }  
  </script>
</head>
<body class="classbody" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" style="overflow:hidden" onkeyup="ccHotkey(event)">
<script>if (isIE() == false) document.write("<div style='height:100%;width:100%;overflow:auto'>");</script>
<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="html_scheduleg_sv">
<t:htpageaddons1 id="g_pa1"/>
<t:htrow id="g_2" >
<t:htpane id="g_3" valign="center" width="100%" >
<t:htrow id="g_4" >
<t:htlabel id="g_5" style="color:#000000" text="Markus Reich" width="100%" />
</t:htrow>
<t:htrow id="g_6" >
<t:htbutton id="g_7" text="zurück" width="80" />
<t:htcoldistance id="g_8" />
<t:htfield id="g_9" />
<t:htcoldistance id="g_10" />
<t:htbutton id="g_11" text="vor" width="80" />
</t:htrow>
<t:htrow id="g_12" >
<t:htlabel id="g_13" text="Laufeinheit" />
</t:htrow>
<t:htrow id="g_14" >
<t:htfixgrid id="g_15" />
</t:htrow>
<t:htrow id="g_16" >
<t:htbutton id="g_17" text="Erledigt" width="80" />
</t:htrow>
</t:htpane>
</t:htrow>
<t:htrowstatusbar id="g_18" />
<t:htpageaddons2 id="g_pa2"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
<script>if (isIE() == false) document.write("</div>");</script>
</body>
</html>