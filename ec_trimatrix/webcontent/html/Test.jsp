<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <link id="mystyle" rel="stylesheet" type="text/css" href="../eclntjsfserver/htstyle/htstyle.css">
  <script>
  function isIE() { if (navigator.appName == "Netscape") return false; else return true; }
  </script>
</head>
<body class="classbody" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" style="overflow:hidden">
<script>if (isIE() == false) document.write("<div style='height:100%;width:100%;overflow:auto'>");</script>
<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="html_Testg_sv">
<t:htpageaddons1 id="g_pa1"/>
<t:htrowdistance id="g_2" height="10" />
<t:htrow id="g_3" >
<t:htcoldistance id="g_4" width="15" />
<t:htpane id="g_5" height="100%" width="100%" >
<t:htrow id="g_6" width="100%" >
<t:htlabel id="g_7" align="center" text="Testprotokoll" width="100%" />
</t:htrow>
<t:htrowdistance id="g_8" height="25" />
<t:htrow id="g_9" width="100%" >
<t:htlabel id="g_10" text="Geschwindigkeit" width="150" />
<t:htfield id="g_11" text="#{d.HTTestUI.speed}" width="400" />
</t:htrow>
<t:htrow id="g_12" width="100%" >
<t:htlabel id="g_13" text="Herzfrequenz" width="150" />
<t:htfield id="g_14" text="#{d.HTTestUI.hr}" width="400" />
</t:htrow>
<t:htrow id="g_15" width="100%" >
<t:htlabel id="g_16" text="Laktat" width="150" />
<t:htfield id="g_17" text="#{d.HTTestUI.lactat}" width="400" />
</t:htrow>
<t:htrowdistance id="g_18" height="15" />
<t:htrow id="g_19" >
<t:htbutton id="g_20" actionListener="#{d.HTTestUI.onSubmit}" text="Senden" />
</t:htrow>
</t:htpane>
</t:htrow>
<t:htrowstatusbar id="g_21" />
<t:htpageaddons2 id="g_pa2"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
<script>if (isIE() == false) document.write("</div>");</script>
</body>
</html>