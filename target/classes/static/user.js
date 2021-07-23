$(document).ready(() => {
        getUser();
    }
);


function getUser() {
    fetch('http://localhost:8080/api/admin/users/authorizedUser')
        .then((response) => {
            return response.json()
        })
        .then((authorizedUser) => {
            let tableRows = '';
            let rolesAuthorizedUser = userRolesForPrint(authorizedUser.roles);
            tableRows += '<tr>' +
                '<td>' + authorizedUser.id + '</td>' +
                '<td>' + authorizedUser.name + '</td>' +
                '<td>' + rolesAuthorizedUser + '</td>' +
                '</tr>';
            $('#tableUser tbody').empty().append(tableRows);
            $('#userRolesForHeader').text(' with roles: ' + rolesAuthorizedUser);
        });
}

function userRolesForPrint(roles) {
    let rolesForPrint = '';
    $.each(roles, function (key, value) {
        rolesForPrint += (value.role + ' ');
    })
    return rolesForPrint;
}
