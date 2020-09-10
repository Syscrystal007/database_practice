$(function () {
    pages = parseInt($("#pages").html());
    cur = parseInt($("#currentPage").html());

    $("#next").click(function () {
        if(cur == pages)	return;
        cur++;
        location.href = "/book?page="+cur;
        alert("dick");
    })

    $("#previous").click(function () {
        if(cur == 1)	return;
        cur--;
        location.href = "/book?page="+cur;
    })

    $("#first").click(function () {
        location.href = "/book?page=1";
    })

    $("#last").click(function () {
        location.href = "/book?page="+pages;
    })
})