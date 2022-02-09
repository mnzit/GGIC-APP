<%@include file="../shared/header.jsp" %>
<div class="card">
    <div class="card-header">
        Students
    </div>
    <c:choose>
        <c:when test="${requestScope.success }">
            <div class="card-body">
                <h3>Name: ${requestScope.student.name}</h3>
                <h3>DOB: ${requestScope.student.dob}</h3>
                <h3>ContactNo: ${requestScope.student.contactNo}</h3>
                <h3>Address: ${requestScope.student.address}</h3>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info" role="alert">${requestScope.message}</div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../shared/footer.jsp" %>
