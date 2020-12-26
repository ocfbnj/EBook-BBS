<%--
  Created by IntelliJ IDEA.
  User: tangh
  Date: 2020/12/24
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--下面用来解决在jsp页面中jstl不起作用--%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<head>

    <title>乐源阅读</title>

    <%
        String basePath = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/";
    %>

    <!--写base标签，永远固定相对路径跳转的结果-->
    <base href="<%=basePath%>">

    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/left.css">
    <link rel="stylesheet" href="css/indexTop.css">
    <script src="js/jquery-3.5.1.js"></script>
    <script src="js/include.js"></script>
</head>

<body>
<div class="wrapper layout-v3">
    <!--页面顶部-->
    <div id="header"></div>

    <!--主体内容-->
    <div id="main">
        <div id="react-root">
            <div class="filter-main">
                <!--页面标题-->
                <section class="section-container">
                    <div class="category-breadcrumbs"><span class="breadcrumb">中文出版</span></div>
                </section>

                <!--分类-->
                <div class="sticky-outer-wrapper">
                    <div class="sticky-inner-wrapper "
                         style="position: relative; z-index: 1; transform: translate3d(0px, 0px, 0px);">
                        <section class="section-container filter-section">
                            <div class="filter-toolbar">
                                <div class="inner">
                                    <div class="toolbar-crumbs"></div>
                                    <div class="sort-selector" id="clasi">
                                        <%--                                        <a class="sort-option selected">小说</a>--%>
                                        <%--                                        <a class="sort-option ">文学</a>--%>
                                        <a class="sort-option" href="bookServlet/page" id="default">默认</a>
                                        <a class="sort-option " href="bookServlet/pageByPrice" id="price">价格从低到高</a>
                                        <%--                                        <a class="sort-option">科技科普</a>--%>
                                        <%--                                        <a class="sort-option ">计算机与互联网</a>--%>
                                        <%--                                        <a class="sort-option ">漫画和绘本</a>--%>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>

                <script type="text/javascript">
                    $(function () {
                        //点击之后，先给所有a标签去除被选定的selected类，最终给被点击的a标签添加该类，即想要被选定的能被选定
                        // $("#clasi a").click(function () {
                        //     $("#clasi a").each(function () {
                        //         $(this).removeClass("selected");
                        //     });
                        //     $(this).addClass("selected");
                        // });
                        // uri.substring(uri.lastIndexOf('/') + 1);
                        var s = window.location.href;
                        s = s.substring(s.lastIndexOf('/') + 1);
                        if (s == '') {
                            $("#default").addClass("selected");
                        } else {
                            var ss = s.split('?');
                            s = ss[0];
                            if (s == "pageByPrice") {
                                $("#price").addClass("selected");
                            } else if (s == "page") {
                                $("#default").addClass("selected");
                            }
                        }


                    });

                </script>


                <!--图书列表和页码-->
                <section class="section-container">
                    <!--图书列表-->
                    <div class="section-works">
                        <ul class="works-list">

                            <c:forEach items="${requestScope.page.items}" var="book">
                                <!--图书-->
                                <li class="works-item " data-works-id="">
                                    <div class="inner">
                                        <!--图书封面-->
                                        <div class="cover shadow-cover ">
                                            <a class="pic" href="ebook_detail.html?bid=${book.ebook_id}"
                                               target="_blank">
                                                <span class="cover-label vip-can-read-label"></span>
                                                <img
                                                        src="${book.cover_path}"
                                                        alt="小风暴1.0"/>
                                            </a>
                                        </div>

                                        <!--图书信息-->
                                        <div class="info">
                                            <h1>${book.price}</h1>
                                            <!--图书标题-->
                                            <h4 class="title">
                                                <a href="ebook_detail.html?bid=${book.ebook_id}" target="_blank"
                                                   title="小风暴1.0"
                                                   class="title-container">
                                                    <span class="title-text"><span class="">${book.title}</span></span>
                                                </a>
                                            </h4>
                                            <!--图书作者-->
                                            <div class="author">
                                                <a href="/search?q=肖茉莉" target="_blank" class="author-link"><span><span
                                                        class="">${book.author}</span></span></a>
                                            </div>
                                            <!--图书简介-->
                                            <div class="intro null">
                                                <span class="">${book.intro}</span>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <%--		静态包含分页条--%>
                    <%@ include file="/page_nav.jsp" %>


                </section>

            </div>
        </div>
    </div>
</div>
</body>
</html>
