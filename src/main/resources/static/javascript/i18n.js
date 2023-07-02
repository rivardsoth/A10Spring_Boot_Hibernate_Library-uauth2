

var htmlFile = document.currentScript.getAttribute('data-html-file');

$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption !== ''){
            window.location.replace(htmlFile + '?language=' + selectedOption);

        }
    });
});