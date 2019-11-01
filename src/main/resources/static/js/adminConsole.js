
function toggleUpdateAndText() {
    $(".building-u").toggle();
    $(".floor-u").toggle();
    $(".number-u").toggle();
    $(".capacity-u").toggle();
    $(".update-b").toggle();
    $(".building-t").toggle();
    $(".floor-t").toggle();
    $(".number-t").toggle();
    $(".capacity-t").toggle();
    $(".delete-b").toggle();
    $(".edit-to").toggle();
    $(".delete-to").toggle();
}


$(document).ready(function() {
    console.log("Test");
    $(".building-u").hide();
    $(".floor-u").hide();
    $(".number-u").hide();
    $(".capacity-u").hide();
    $(".update-b").hide();
    $(".delete-to").hide();
});