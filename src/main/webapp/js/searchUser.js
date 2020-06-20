// searchUser

var searchUser = document.querySelector("#searchUser");

searchUser.addEventListener("click", function () {
    var searchUsername = $("#searchUsername").val();
    console.log(searchUsername);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/user/searchUser",
        data: {"searchUsername": searchUsername},
        success: function (e) {
            var searchOutcome = document.querySelector("#searchOutcome");
            console.log(e);
            var html = "";
            for (var i = 0; i < e.length; i++) {
                html += "<div class=\"list-group-item\">"
                    + "<div class=\"row flex-column flex-md-row align-items-center\">"
                    + "<div class=\"col-auto\">"
                    + "<a href=\"javascript:;\" class=\"avatar rounded-circle\">"
                    + "<img alt=\"Image placeholder\" src=\"" + e[i].avatar.realName + "\" class=\"\">"
                    + "</a>"
                    + "</div>"
                    + "<div class=\"col ml-md-n2 text-center text-md-left\">"
                    + "<a href=\"#!\" class=\"h6 text-sm mb-0\">" + e[i].username + "</a>"
                    + "</div>"
                    + "<hr class=\"divider divider-fade my-3 d-md-none\" />"
                    + "<div class=\"col-12 col-md-auto d-flex justify-content-between align-items-center\">"
                    + "<button type=\"button\" class=\"btn btn-sm btn-secondary\">add</button>"
                    + "</div>"
                    + "</div>"
                    + "</div>";
            }
            searchOutcome.innerHTML = html;
        }
    })
})