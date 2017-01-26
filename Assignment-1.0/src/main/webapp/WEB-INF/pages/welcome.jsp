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
                <a class="navbar-brand" href="#">Tangent Projects Directory</a>
            </div>
        </div>
    </nav>

    <div class="container" style="min-height: 500px">

        <div class="starter-template"> 

            <div id="tablesDiv">
                <h1>Projects List</h1> 
                <table class="table table-bordered table-striped table-condensed cf" id="accountContact">
                    <thead class="cf" >
                        <tr>                   
                            <th>PK</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Start Date</th>
                            <th>End Date</th> 
                            <th>Is Billable</th> 
                            <th>Is Active</th> 
                        </tr>
                    </thead>
                    <tbody>
                       ${projectsList}
                    </tbody>
                </table>              
                
            </div>
        </div>
    </div>

</body>
</html>