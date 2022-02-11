<%@include file="../shared/header.jsp" %>
<div class="card">
    <div class="card-header">
        Create Student
    </div>
    <div class="card-body p-3">
        <form action="/students" method="post">
            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="text" class="form-control" name="name">
            </div>
            <div class="mb-3">
                <label class="form-label">Address</label>
                <input type="text" class="form-control" name="address">
            </div>
            <div class="mb-3">
                <label class="form-label">ContactNo</label>
                <input type="text" class="form-control" name="contactNo">
            </div>
            <div class="mb-3">
                <label class="form-label">DOB</label>
                <input type="date" class="form-control" name="dob">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>
<%@include file="../shared/footer.jsp" %>
