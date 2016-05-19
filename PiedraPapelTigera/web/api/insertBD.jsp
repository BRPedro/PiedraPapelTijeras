<jsp:useBean id="conexion" scope="page" class="conexion.CJ_juego"></jsp:useBean>
<%  Object data = null;
    String p1 = (request.getParameter("p1") != null) ? new String(request.getParameter("p1").getBytes("ISO-8859-1"),"UTF-8") : "";
    String p2 = (request.getParameter("p2") != null) ? new String(request.getParameter("p2").getBytes("ISO-8859-1"),"UTF-8") : "";
    if (!p1.isEmpty()&&!p2.isEmpty()) { 
            data= conexion.jugada(p1);
    }
%>

<%=data%>
