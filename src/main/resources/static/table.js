var response = [{
    "rank":"9",
    "content":"Alon",
    "UID":"544343"
},
    {
        "rank":"6",
        "content":"NATA",
        "UID":"6"
    }];

$(function() {
    $.each(response, function(i, item) {
        $('<tr>').append(
            $('<td>').text(item.rank),
            $('<td>').text(item.content),
            $('<td>').text(item.UID)
        ).appendTo('#records_table');

    });
});