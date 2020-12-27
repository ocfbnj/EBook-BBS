<!--页码-->
<c:choose>
    <%--                情况一，如果页码小于等于5的情况，页码范围是1-总页码--%>
    <c:when test="${requestScope.page.pageTotal <= 5}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${requestScope.page.pageTotal}"/>
    </c:when>
    <%--                情况二，总页码大于5的情况--%>
    <c:when test="${requestScope.page.pageTotal > 5}">
        <c:choose>
            <%--						   小情况1：当前页码为前面3个，1,2,3的情况,直接显示1页到5页即可--%>
            <c:when test="${requestScope.page.pageNo <= 3}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="5"/>
            </c:when>
            <%--						   小情况2：当前页码为最后3个：eg:8, 9, 10，即当前页大于等于最后中间的页面,页码范围是：总页码减4 - 总页码--%>
            <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%--						   小情况3：当前页码前面和后面均有3个及以上的页面--%>
            <c:otherwise>
                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
            </c:otherwise>
        </c:choose>
    </c:when>
</c:choose>
<div class="paginator-full">
    <ul>
        <%--上一页--%>
        <%--如果已经是第一页，则上一页显示为灰色--%>
        <c:if test="${requestScope.page.pageNo <= 1}">
            <li>
                <!--上一页箭头-->
                <a class="page-prev disabled" href="javascript:void(0);" onclick="return false;"
                   style="background-color: gainsboro">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 6 11" width="7" height="14"
                         class="icon-direction">
                        <path fill="#A6A6A6"
                              d="M4.894 10.986l1.1-.92-4.096-4.573L5.993.92 4.893 0 0 5.473l4.894 5.513z"></path>
                    </svg>
                </a>
            </li>
        </c:if>
        <%-- 如果不是第一页，则正常显示--%>
        <c:if test="${requestScope.page.pageNo > 1}">
            <li>
                <!--上一页箭头-->
                <a id="cbg" class="page-prev disabled"
                   href="${requestScope.page.url}?pageNo=${requestScope.page.pageNo-1}">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 6 11" width="7" height="14"
                         class="icon-direction">
                        <path fill="#A6A6A6"
                              d="M4.894 10.986l1.1-.92-4.096-4.573L5.993.92 4.893 0 0 5.473l4.894 5.513z"></path>
                    </svg>
                </a>
            </li>
        </c:if>


        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${i == requestScope.page.pageNo}">
                <li><a class="page active">${i}</a></li>
            </c:if>
            <c:if test="${i != requestScope.page.pageNo}">
                <li><a class="page" href="${requestScope.page.url}?pageNo=${i}">${i}</a></li>
            </c:if>
        </c:forEach>

        <%--下一页--%>
        <%-- 如果不是最后一页，则正常显示--%>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
            <li>
                <!--下一页箭头-->
                <a class="page-next" href="${requestScope.page.url}?pageNo=${requestScope.page.pageNo+1}">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 6 11" width="7" height="14"
                         class="icon-direction">
                        <path fill="#a6a6a6" d="M1.1 0L0 .92 4.1 5.5 0 10.08l1.1.92L6 5.52z"></path>
                    </svg>
                </a>
            </li>
        </c:if>
        <%-- 如果是最后一页，则最后一页背景颜色为灰色--%>
        <c:if test="${requestScope.page.pageNo >= requestScope.page.pageTotal}">
            <li>
                <!--下一页箭头-->
                <a class="page-next" href="javascript:void(0);" onclick="return false;"
                   style="background-color: gainsboro">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 6 11" width="7" height="14"
                         class="icon-direction">
                        <path fill="#a6a6a6" d="M1.1 0L0 .92 4.1 5.5 0 10.08l1.1.92L6 5.52z"></path>
                    </svg>
                </a>
            </li>
        </c:if>
    </ul>
</div>
