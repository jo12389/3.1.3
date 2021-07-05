$(document).ready(refreshUsersTable());

function createUser() {
    let user = {
        "firstName": $('#firstNameCreate').val(),
        "lastName": $('#lastNameCreate').val(),
        "age": $('#ageCreate').val(),
        "email": $('#emailCreate').val(),
        "password": $('#passwordCreate').val(),
        "roles": $('#rolesCreate').val()
    };
    $.ajax({
        url: 'http://localhost:8080/api/users/',
        type: 'post',
        data: JSON.stringify(user),
        headers: {
            'x-auth-token': localStorage.accessToken,
            "Content-Type": "application/json"
        },
        dataType: 'json',
        success: refreshUsersTable,
        error: (data) => showError(data)
    })
}

function openDeleteUserModal(userId) {
    $.get(`http://localhost:8080/api/users/${userId}`)
        .done((user) => {
            $('#idD').val(user.id);
            $('#firstNameD').val(user.firstName);
            $('#lastNameD').val(user.lastName);
            $('#ageD').val(user.age);
            $('#emailD').val(user.email);
            $('#rolesD').val(user.rolesDisplayed);

            $('#modalDelete').modal('show');


            $('#delete_button').off("click");
            $('#delete_button').click(function () {
                deleteUser(userId);
            })
        });
}

function openEditUserModal(userId) {
    console.log("openEditUserModal " + userId);

    $.ajax({
        url: `http://localhost:8080/api/users/${userId}`,
        type: 'get',
        headers: {
            'x-auth-token': localStorage.accessToken,
        },
        success: (user) => {
            sendEditRequest(user);
        },
        error: (data) => showError(data)
    })
}

function sendEditRequest(user) {
    const userId = user.id;

    console.log("sendEditRequest " + userId);

    $('#firstNameE').val(user.firstName);
    $('#lastNameE').val(user.lastName);
    $('#ageE').val(user.age);
    $('#emailE').val(user.email);

    $('#modalEdit').modal('show');

    $('#edit_button').off("click");

    $('#edit_button').click(() => {
        console.log("edit_button click " + userId);
        let user = {
            "firstName": $('#firstNameE').val(),
            "lastName": $('#lastNameE').val(),
            "age": $('#ageE').val(),
            "email": $('#emailE').val(),
            "password": $('#passwordE').val(),
            "roles": $('#rolesSelect').val()
        };
        $.ajax({
            url: `http://localhost:8080/api/users/${userId}`,
            type: 'put',
            data: JSON.stringify(user),
            headers: {
                'x-auth-token': localStorage.accessToken,
                "Content-Type": "application/json"
            },
            dataType: 'json',
            success: refreshUsersTable,
            error: (data) => showError(data)
        })
    });

}

function refreshUsersTable() {
    $.get(`http://localhost:8080/api/users/me`)
        .done((currentUser) => {
            $('#current_user_roles').empty().text(currentUser.rolesDisplayed);
            fillInUsersTable(currentUser.admin);
        })
}

function refreshCurrentUserTable() {
    $.get(`http://localhost:8080/api/users/me`)
        .done((currentUser) => {

            $('#modalEdit').modal('hide');
            $('#modalDelete').modal('hide');
            $("#currentUserTableBody")
                .empty()
                .append($('<tr>')
                    .append($('<td>').text(currentUser.id))
                    .append($('<td>').text(currentUser.firstName))
                    .append($('<td>').text(currentUser.lastName))
                    .append($('<td>').text(currentUser.age))
                    .append($('<td>').text(currentUser.email))
                    .append($('<td>').text(currentUser.rolesDisplayed))
                );
        })
}

function fillInUsersTable(isAdmin) {
    $.get(`http://localhost:8080/api/users`)
        .done((usersList) => {
            $('#modalEdit').modal('hide');
            $('#modalDelete').modal('hide');
            $(document).ready(() => $("#userTablePage").click());
            $(document).ready(() => $("#usersTableBody").empty());
            $(document).ready(() => {
                for (let i = 0; i < usersList.length; i++) {
                    const user = usersList[i];
                    if (isAdmin === true) {
                        $("#usersTableBody")
                            .append($('<tr>')
                                .append($('<td>').text(user.id))
                                .append($('<td>').text(user.firstName))
                                .append($('<td>').text(user.lastName))
                                .append($('<td>').text(user.age))
                                .append($('<td>').text(user.email))
                                .append($('<td>').text(user.rolesDisplayed))
                                .append($('<td>').html("<input onclick=\"openEditUserModal(" + user.id + ")\" type=\"button\" class=\"btn btn-primary\"\n" +
                                    "                                           value=\"Edit\">"))
                                .append($('<td>').html("<input onclick=\"openDeleteUserModal(" + user.id + ")\" type=\"button\" class=\"btn btn-danger\"\n" +
                                    "                                           value=\"Delete\">"))
                            );
                    } else {
                        $("#usersTableBody")
                            .append($('<tr>')
                                .append($('<td>').text(user.id))
                                .append($('<td>').text(user.firstName))
                                .append($('<td>').text(user.lastName))
                                .append($('<td>').text(user.age))
                                .append($('<td>').text(user.email))
                                .append($('<td>').text(user.rolesDisplayed))
                            );
                    }
                }
            });
        });
}

function deleteUser(userId) {
    $.ajax({
        url: 'http://localhost:8080/api/users/' + userId,
        type: 'delete',
        headers: {                                  //заголовки
            'x-auth-token': localStorage.accessToken
        },
        success: refreshUsersTable,
        error: (data) => showError(data)
    })
}

function showError(message) {
    alert(message.responseText);
}