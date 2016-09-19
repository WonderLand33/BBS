<%@ page contentType="text/plain;charset=UTF-8" language="java"   %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" uri="http://spring-abc.xyz/mytaglib" %>
<c:set value="${fn:length(r.items)}" scope="page" var="ss"></c:set>

<ul class="F a-fadein">
<c:forEach items="${r.items}" var="p" varStatus="loop">
    <li id="P-${p.id}">
      <a href="#p/${p.id}">
        <i class="i ps">${p.sname}</i>
        <img src="" r="${p.headImg}">
        <h2><c:out value="${p.title}" /></h2>
        <p class="ctrl">
          <span>${p.nikename}</span>
          <span class="d" t="${my:formatDateToLong(p.createTime)}"></span>
          <span class="plus">${p.up}</span></p>
        <cite>${p.replys}</cite>
      </a>
    </li>
</c:forEach>
</ul>
<c:if test="${ss==0}"><p class="loading empty">没有新内容噢OwQ</p></c:if>



