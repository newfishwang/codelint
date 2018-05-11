$(document).ready(function() {
    $.ajax({
        url: "/hello/data"
    }).then(function(data) {
        $('.greeting-id').append(data.name);
        $('.greeting-content').append(data.age);
        if (data.age==12) {
            
        }
    });
});