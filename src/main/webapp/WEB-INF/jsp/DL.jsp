<%@ page contentType="text/plain;charset=UTF-8" language="java"   %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://www.xc66.cc/functions"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
          <span class="d">${x:formatDateToLong(p.createTime)}</span>
          <span class="plus">${p.up}</span></p>
        <cite>${p.replys}</cite>
      </a>
    </li>
    <c:if test="${loop.last}">
      <p class="loading empty">没有更多啦OwQ</p>
    </c:if>
</c:forEach>
</ul>
<c:if test="${ss==0}"><p class="loading empty">没有新内容噢OwQ</p></c:if>


