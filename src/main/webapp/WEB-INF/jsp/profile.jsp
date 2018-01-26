<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="localization.main" var="main"/>
<fmt:setBundle basename="localization.profile" var="profile"/>
<fmt:setBundle basename="localization.auth" var="auth"/>


<fmt:message key="label.name" bundle="${main}" var="nameSite"/>
<fmt:message key="label.openTopic" bundle="${main}" var="openTopic"/>
<fmt:message key="label.likes" bundle="${main}" var="likes"/>

<fmt:message key="label.name" bundle="${profile}" var="name"/>
<fmt:message key="label.rating" bundle="${profile}" var="rating"/>
<fmt:message key="label.email" bundle="${profile}" var="email"/>
<fmt:message key="label.about" bundle="${profile}" var="about"/>
<fmt:message key="label.topic" bundle="${profile}" var="topic"/>
<fmt:message key="label.message" bundle="${profile}" var="message"/>

<fmt:message key="label.exit" bundle="${auth}" var="exit"/>
<fmt:message key="label.password" bundle="${auth}" var="password"/>
<fmt:message key="label.newPassword" bundle="${auth}" var="newPassword"/>
<fmt:message key="label.confirm" bundle="${auth}" var="confirm"/>
<fmt:message key="label.errorChangePasswordMessage" bundle="${auth}" var="errMessage"/>
<fmt:message key="label.changePassword" bundle="${auth}" var="changePassword"/>

<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="../../css/profile.css"/>
    <title>Title</title>
</head>
<body>
<header class="header_container">
    <div class="header_background"></div>
    <div class="header_menu">
        <a href="/NetworkController?command=go_to_main_page&page_number=1&count=10">
            <div class="header_menu-item header_menu-name">
                ${nameSite}
            </div>
        </a>
        <c:if test="${sessionScope.role == null || sessionScope.role == 1}">
            <a href="/NetworkController?command=go_to_sign_up">
                <div class="header_menu-item">
                        ${signUp}
                </div>
            </a>
            <a href="/NetworkController?command=go_to_sign_in">
                <div class="header_menu-item">
                        ${signIn}
                </div>
            </a>
        </c:if>
        <c:if test="${sessionScope.role >= 2}">
            <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.user.id}">
                <div class="header_menu-item">
                        ${requestScope.user.name}
                </div>
            </a>
        </c:if>
        <c:if test="${sessionScope.role > 2}">
            <a href="/NetworkController?command=go_to_admin_page&action=not_banned&page_number=1&count=10">
                <div class="header_menu-item">
                        ADMIN PAGE
                </div>
            </a>
        </c:if>
    </div>
</header>


<main>

    <div class="card profile_info_block">
        <div class="profile_info-name">
            ${requestScope.profile_user.name}
        </div>
        <c:if test="${requestScope.user != null && requestScope.user.id==requestScope.profile_user.id}">
            <div class="profile_info-settings">
                <a href="/NetworkController?command=go_to_profile&action=get_settings&profile_user_id=${requestScope.user.id}">
                    <img src="../../img/settings.png">
                </a>
            </div>
        </c:if>
        <div class="profile_info-email">
           <sup>${email}</sup></br><c:out value="${requestScope.profile_user.email}"/>
        </div>
        <div class="profile_info-about">
            <sup>${about}</sup></br> <c:out value="${requestScope.profile_user.about}"/>
        </div>

    </div>


    <div class="card profile_info-rating">
        <div class="profile-digit">
            <c:out value="${requestScope.profile_user.rating}"/>
        </div>
        <div class="profile-text">
            ${rating}
        </div>
    </div>

    <div class="card profile_post_info">

        <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.profile_user.id}&action=get_topics">
            <div class="profile_post_info-topics">
                <div class="profile-digit">
                    <c:out value="${requestScope.topics_count}"/>
                </div>
                <div class="profile-text">

                    ${topic}

                </div>
            </div>
        </a>


        <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.profile_user.id}&action=get_messages">
            <div class="profile_post_info-messages">
                <div class="profile-digit">
                    <c:out value="${requestScope.messages_count}"/>
                </div>
                <div class="profile-text">

                    ${message}

                </div>
            </div>
        </a>

    </div>

    <c:if test="${requestScope.topic_list != null}">
        <c:forEach var="topic" items="${requestScope.topic_list}">
            <div class="card topics_block-item">
                <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                    <div class="topics_block-item-header">
                            ${topic.header}
                    </div>
                </a>
                <div class="topics_block-item-date-author">
                    <a href="#">${topic.creatingDate}</a> by <a
                        href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.profile_user.id}">${requestScope.profile_user.name}</a>
                </div>
                <div class="topics_block-item-theme">
                    <a href="/NetworkController?command=go_to_main_page&theme_id=${topic.theme.id}&page_number=1&count=10">${topic.theme.name}</a>
                </div>
                <div class="topics_block-item-context">
                        ${topic.context}
                </div>
                <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                    <div class="topics_block-item-open">
                            ${openTopic}
                    </div>
                </a>
            </div>
        </c:forEach>
    </c:if>


    <c:if test="${requestScope.message_list != null}">
        <div class="topics_block">
            <c:forEach var="topic" items="${requestScope.topic_list_for_messages}">
                <div class="card topics_block-item">
                    <div class="topics_block-item-header">
                        <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                                ${topic.header}
                        </a>
                    </div>
                    <div class="topics_block-item-date-author">
                        <a href="#">${topic.creatingDate}</a> by <a
                            href="/NetworkController?command=go_to_profile&profile_user_id=${topic.user.id}">${topic.user.name}</a>
                    </div>
                    <div class="topics_block-item-theme">
                        <a href="/NetworkController?command=go_to_main_page&theme_id=${topic.theme.id}&page_number=1&count=10">${topic.theme.name}</a>
                    </div>
                    <div class="topics_block-item-context">
                            ${topic.context}
                    </div>
                    <div class="messages_block">
                        <c:forEach var="message" items="${requestScope.message_list}">
                            <c:if test="${topic.id == message.topicId}">
                                <div class="messages_block-item">
                                    <div class="message_block-item-name">
                                        <a href="/NetworkController?command=go_to_profile&profile_user_id=${message.user.id}">${requestScope.profile_user.name}</a>
                                    </div>
                                    <c:if test="${message.user.id == requestScope.user.id}">
                                        <div class="message_block-item-delete">

                                            <a href="/NetworkController?command=delete_message&message_id=${message.id}"><img
                                                    src="img/delete.png"></a>

                                        </div>
                                    </c:if>
                                    <div class="message_block-item-context">${message.context}</div>
                                    <div class="message_block-item-date">${message.creatingDate}</div>
                                    <div class="message_block-item-like">
                                        <c:if test="${requestScope.user!=null}">
                                            <c:if test="${!message.likedUserId.contains(requestScope.user.id)}">
                                                <a href="/NetworkController?command=rate_message&action=like&message_id=${message.id}">${message.likes}<img
                                                        src="img/notlike.png"></a>
                                            </c:if>
                                            <c:if test="${message.likedUserId.contains(requestScope.user.id)}">
                                                <a href="/NetworkController?command=rate_message&action=unlike&message_id=${message.id}">${message.likes}<img
                                                        src="img/like.png"></a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${requestScope.user==null}">
                                            ${message.likes} ${likes}
                                        </c:if>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>

    </c:if>
</main>

<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
<script type="text/javascript" src="../../js/scroll.js"></script>
</body>
</html>
