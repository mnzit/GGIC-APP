<%@include file="../shared/header.jsp" %>
<div class="card">
    <div class="card-header">
        ${requestScope.student.name} Detail's
    </div>
    <c:choose>
        <c:when test="${requestScope.success }">
            <div class="card-body">

                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Name</span>
                    <span class="form-control">${requestScope.student.name}</span>
                </div>

                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Date Of Birth</span>
                    <span class="form-control">${requestScope.student.dob}</span>
                </div>

                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Contact No</span>
                    <span class="form-control">${requestScope.student.contactNo}</span>
                </div>

                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Address</span>
                    <span class="form-control">${requestScope.student.address}</span>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info" role="alert">${requestScope.message}</div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../shared/footer.jsp" %>
