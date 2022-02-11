<%@include file="../shared/header.jsp" %>
<div class="card">
    <div class="card-header">
        Update Student
    </div>
    <div class="card-body p-3">
        <form action="/students" method="post">
            <input type="number" hidden name="id" value="${requestScope.student.id}">
            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="text" class="form-control" name="name" value="${requestScope.student.name}">
            </div>
            <div class="mb-3">
                <label class="form-label">Address</label>
                <input type="text" class="form-control" name="address" value="${requestScope.student.address}">
            </div>
            <div class="mb-3">
                <label class="form-label">ContactNo</label>
                <input type="text" class="form-control" name="contactNo" value="${requestScope.student.contactNo}">
            </div>
            <div class="mb-3">
                <label class="form-label">DOB</label>
                <input type="date" class="form-control" name="dob" value="${requestScope.student.dob}">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
<%@include file="../shared/footer.jsp" %>
