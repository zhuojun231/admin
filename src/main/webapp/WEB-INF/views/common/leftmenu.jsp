<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <!-- <li class="active treeview menu-open">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                    <li class="active"><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                </ul>
            </li> -->
            <c:if test="${!empty currentUser.menuList}">
                <c:forEach items="${currentUser.menuList}" var="authMenu">
                    <li class="treeview">
                        <a href="#">
                            <i class="fa fa-user-o"></i>
                            <span>${authMenu.name}</span>
                            <span class="pull-right-container">
                               <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <c:if test="${!empty authMenu.subPermissions}">
                            <ul class="treeview-menu">
                                <c:forEach items="${authMenu.subPermissions}" var="subPerm">
                                    <li><a href="${ctx}${subPerm.url}"><i class="fa fa-circle-o"></i> ${subPerm.name}</a></li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>