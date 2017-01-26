jQuery(document).ready(function ($) {

    $("#search-form").submit(function (event) {

        // Disble the search button
        enableSearchButton(false);
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        searchViaAjax();
    });
});
function searchViaAjax() {

    var search = {};
    search["accountNumber"] = $("#accountNumber").val();
    var accountNumber = $("#accountNumber").val();

    var url = document.location.protocol + "//" + document.location.hostname + ":" + document.location.port
            + "/BillingDirectory-1.0/search/api/getSearchResult/" + accountNumber;

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        timeout: 100000,
        success: function (data) {

            var json = JSON.parse(data.result);
            
            display(data);

            var accountContact = "";

            $.each(json.contacts, function (i, item) {
                accountContact += '<tr><td>' + item.contactId + '</td>'
                        + '<td>' + accountNumber + '</td>'
                        + '<td>' + item.firstName + '</td>'
                        + '<td>' + item.lastName + '</td>'
                        + '<td>' + item.email + '</td>'
                        + '</tr>';
            });

            $("#accountContact tbody").html(accountContact);

            var accountInvoiceDetails = "";
            var accountItems = "";

            $.each(json.invoices, function (i, item) {
                accountInvoiceDetails += '<tr><td>' + item.invoiceId + '</td>'
                        + '<td>' + accountNumber + '</td>'
                        + '<td>' + item.openingBalance + '</td>'
                        + '<td>' + item.closingBalance + '</td>'
                        + '<td>' + item.invoiceDueDate + '</td>'
                        + '</tr>';

                $.each(item.items, function (i, item) {
                    accountItems += '<tr><td>' + item.itemisedId + '</td>'
                            + '<td>' + item.invoiceId + '</td>'
                            + '<td>' + item.callDate + '</td>'
                            + '<td>' + item.callTime + '</td>'
                            + '<td>' + item.callType + '</td>'
                            + '<td>' + item.callCost + '</td>'
                            + '</tr>';
                });

            });

            $("#accountInvoiceDetails tbody").html(accountInvoiceDetails);





            $("#accountItems tbody").html(accountItems);

        },
        error: function (e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE");
            enableSearchButton(true);
        }
    });

}

function enableSearchButton(flag) {
    $("#btn-search").prop("disabled", flag);
}

function display(data) {
    var json = "<h4>Ajax Response</h4><pre>"
            + data.msg + "</pre>";
    $('#feedback').html(json);
}