<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="wpft_8">
<t:row id="g_2" >
  <t:fixgrid id="g_3" avoidroundtrips="true" 
                      background="BACKGROUND"
                      bgpaint="BGPAINT"
                      border="top:0;left:0;right:0;bottom:0" 
                      borderheight="0" 
                      borderwidth="0" 
                      emptycolor="null!" height="100%" objectbinding="#{OBJECTBINDING.ftree}" 
                      rowheight="TREENODEROWHEIGHT" 
                      sbvisibleamount="40" 
                      suppressheadline="true" width="100%" 
                      scrollanimationtype="SCROLLANIMATIONTYPE"
                      scrollbartype="SCROLLBARTYPE"
                      singleclickexecute="SINGLECLICKEXECUTE">
    <t:gridcol id="g_4" text="Function" width="100%" >
      <t:treenode id="g_5" 
                  foreground="FOREGROUND" 
                  bgpaint="TREENODEBGPAINT"
                  dragsend=".{dragSend}"
                  dropreceive=".{dropReceive}"
                  font="TREENODEFONT"
                  levelwidth="TREENODELEVELWIDTH"/>
    </t:gridcol>
  </t:fixgrid>
</t:row>
<t:pageaddons/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
