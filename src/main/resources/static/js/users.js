function retrieveUsers() {
    var url = '/get';

    if ($('#searchUser').val() !== '') {
        url = url + '?name=' + $('#searchUser').val();
    }

    $("#table").load(url);
}

function newUser() {
    var url = '/new';

    if ($('#newUser').val() !== '') {
        url = url + '?name=' + $('#newUser').val();
    }

    $("#table").load(url);
}