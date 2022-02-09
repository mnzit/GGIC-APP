<%@include file="../shared/header.jsp" %>
<div class="card">
    <div class="card-header">
        <b>Students</b>
        <a href="/students/save/" class="btn btn-info btn-lg float-end">Save</a>
    </div>
    <div class="card-body">
        <c:choose>
            <c:when test="${requestScope.success }">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">DOB</th>
                        <th scope="col">Contact No</th>
                        <th scope="col">Address</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${requestScope.students}">
                        <tr>
                            <th scope="row">${student.id}</th>
                            <td>${student.name}</td>
                            <td>${student.dob}</td>
                            <td>${student.contactNo}</td>
                            <td>${student.address}</td>
                            <td>
                                <a href="/students/detail/${student.id}" class="btn btn-info btn-lg">Detail</a>
                                <a href="/students/edit/${student.id}" class="btn btn-primary btn-lg">Edit</a>
                                <a href="/students/delete/${student.id}" class="btn btn-danger btn-lg">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
            <div class="alert alert-info" role="alert">${requestScope.message}</div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="../shared/footer.jsp" %>