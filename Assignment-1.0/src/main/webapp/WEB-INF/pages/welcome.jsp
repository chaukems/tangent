<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <c:url var="home" value="/" scope="request" />

        <spring:url value="/static/css/bootstrap.min.css"
                    var="bootstrapCss" />
        <link href="${bootstrapCss}" rel="stylesheet" />
        <link href="${coreCss}" rel="stylesheet" />

        <spring:url value="/static/js/jquery-2.1.3.js"
                    var="jqueryJs" />
        <script src="${jqueryJs}"></script>

        <spring:url value="/static/js/homescript.js"
                    var="jqueryJs" />
        <script src="${jqueryJs}"></script>

    </head>

    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Billing Directory Home</a>
            </div>
        </div>
    </nav>

    <div class="container" style="min-height: 500px">

        <div class="starter-template">
            <h1>Search Account</h1>
            <br>

            <div id="feedback"></div>

            <form class="form-horizontal" id="search-form">
                <div class="form-group form-group-lg">
                    <label class="col-sm-2 control-label">Account Number</label>
                    <div class="col-sm-10">
                        <input type=text class="form-control" id="accountNumber">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="bth-search"
                                class="btn btn-primary btn-lg">Search</button>
                    </div>
                </div>
            </form>
            <br>

            <div id="tablesDiv">
                <h1 id="accountHeader"></h1> 
                <br>

                <h1>Account holder Contact inform</h1> 
                <table class="table table-bordered table-striped table-condensed cf" id="accountContact">
                    <thead class="cf" >
                        <tr>                   
                            <th>Contact Id</th>
                            <th>Account Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>                    
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <br>
                <h1>Invoice details</h1>               

                <table class="table table-bordered table-striped table-condensed cf" id="accountInvoiceDetails">
                    <thead class="cf" >
                        <tr>                   
                            <th>Invoice Id</th>
                            <th>Account Id</th>
                            <th>Opening Balance</th>
                            <th>Closing Balance</th>
                            <th>Invoice Due Date</th>                    
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <br>
                <h1>Itemised Calls</h1>
                <table class="table table-bordered table-striped table-condensed cf" id="accountItems">
                    <thead class="cf" >
                        <tr>                   
                            <th>Item Id</th>
                            <th>invoice Id</th>
                            <th>Call Date</th>
                            <th>Call Time</th>
                            <th>Call Type</th> 
                            <th>Call Cost</th> 
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>