<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <c:url var="home" value="/" scope="request" />

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <style>
            /* Absolute Center Spinner */
            .loading {
                position: fixed;
                z-index: 99999;
                height: 2em;
                width: 2em;
                overflow: show;
                margin: auto;
                top: 0;
                left: 0;
                bottom: 0;
                right: 0;
                display: none;
            }

            /* Transparent Overlay */
            .loading:before {
                content: '';
                display: block;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0,0,0,0.3);
            }

            /* :not(:required) hides these rules from IE9 and below */
            .loading:not(:required) {
                /* hide "loading..." text */
                font: 0/0 a;
                color: transparent;
                text-shadow: none;
                background-color: transparent;
                border: 0;
            }

            .loading:not(:required):after {
                content: '';
                display: block;
                font-size: 10px;
                width: 1em;
                height: 1em;
                margin-top: -0.5em;
                -webkit-animation: spinner 1500ms infinite linear;
                -moz-animation: spinner 1500ms infinite linear;
                -ms-animation: spinner 1500ms infinite linear;
                -o-animation: spinner 1500ms infinite linear;
                animation: spinner 1500ms infinite linear;
                border-radius: 0.5em;
                -webkit-box-shadow: rgba(0, 0, 0, 0.75) 1.5em 0 0 0, rgba(0, 0, 0, 0.75) 1.1em 1.1em 0 0, rgba(0, 0, 0, 0.75) 0 1.5em 0 0, rgba(0, 0, 0, 0.75) -1.1em 1.1em 0 0, rgba(0, 0, 0, 0.5) -1.5em 0 0 0, rgba(0, 0, 0, 0.5) -1.1em -1.1em 0 0, rgba(0, 0, 0, 0.75) 0 -1.5em 0 0, rgba(0, 0, 0, 0.75) 1.1em -1.1em 0 0;
                box-shadow: rgba(0, 0, 0, 0.75) 1.5em 0 0 0, rgba(0, 0, 0, 0.75) 1.1em 1.1em 0 0, rgba(0, 0, 0, 0.75) 0 1.5em 0 0, rgba(0, 0, 0, 0.75) -1.1em 1.1em 0 0, rgba(0, 0, 0, 0.75) -1.5em 0 0 0, rgba(0, 0, 0, 0.75) -1.1em -1.1em 0 0, rgba(0, 0, 0, 0.75) 0 -1.5em 0 0, rgba(0, 0, 0, 0.75) 1.1em -1.1em 0 0;
            }

            /* Animation */

            @-webkit-keyframes spinner {
                0% {
                    -webkit-transform: rotate(0deg);
                    -moz-transform: rotate(0deg);
                    -ms-transform: rotate(0deg);
                    -o-transform: rotate(0deg);
                    transform: rotate(0deg);
                }
                100% {
                    -webkit-transform: rotate(360deg);
                    -moz-transform: rotate(360deg);
                    -ms-transform: rotate(360deg);
                    -o-transform: rotate(360deg);
                    transform: rotate(360deg);
                }
            }
            @-moz-keyframes spinner {
                0% {
                    -webkit-transform: rotate(0deg);
                    -moz-transform: rotate(0deg);
                    -ms-transform: rotate(0deg);
                    -o-transform: rotate(0deg);
                    transform: rotate(0deg);
                }
                100% {
                    -webkit-transform: rotate(360deg);
                    -moz-transform: rotate(360deg);
                    -ms-transform: rotate(360deg);
                    -o-transform: rotate(360deg);
                    transform: rotate(360deg);
                }
            }
            @-o-keyframes spinner {
                0% {
                    -webkit-transform: rotate(0deg);
                    -moz-transform: rotate(0deg);
                    -ms-transform: rotate(0deg);
                    -o-transform: rotate(0deg);
                    transform: rotate(0deg);
                }
                100% {
                    -webkit-transform: rotate(360deg);
                    -moz-transform: rotate(360deg);
                    -ms-transform: rotate(360deg);
                    -o-transform: rotate(360deg);
                    transform: rotate(360deg);
                }
            }
            @keyframes spinner {
                0% {
                    -webkit-transform: rotate(0deg);
                    -moz-transform: rotate(0deg);
                    -ms-transform: rotate(0deg);
                    -o-transform: rotate(0deg);
                    transform: rotate(0deg);
                }
                100% {
                    -webkit-transform: rotate(360deg);
                    -moz-transform: rotate(360deg);
                    -ms-transform: rotate(360deg);
                    -o-transform: rotate(360deg);
                    transform: rotate(360deg);
                }
            }
        </style>

        <script>
            function showOverlay(message) {
                $("#loadingMessage").html(message);

                $(".loading").css("display", "block");
            }

            function closeOverlay() {
                $(".loading").css("display", "none");
            }
        </script>

        <script>
            $(document).ready(function () {
                $('.btnViewTasks').on('click', function (e) {
                    showOverlay("Please wait");
                    var taskId = $(this).attr("id");
                    var token = $("#token").html();

                    $.ajax({
                        url: "http://projectservice.staging.tangentmicroservices.com:80/api/v1/tasks/" + taskId + "/",
                        type: 'GET',
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader('Authorization', 'Token ' + token);
                        },
                        success: function (result) {
                            $("#id").html(result.id);
                            $("#due_date").html(result.due_date);
                            $("#estimated_hours").html(result.estimated_hours);
                            $("#title").html(result.title);
                            closeOverlay();
                        },
                        error: function (jqXHR, exception) {
                            closeOverlay();
                            var msg = '';
                            if (jqXHR.status === 0) {
                                msg = 'Not connect.\n Verify Network.';
                            } else if (jqXHR.status == 404) {
                                msg = 'Requested page not found. [404]';
                            } else if (jqXHR.status == 500) {
                                msg = 'Internal Server Error [500].';
                            } else if (exception === 'parsererror') {
                                msg = 'Requested JSON parse failed.';
                            } else if (exception === 'timeout') {
                                msg = 'Time out error.';
                            } else if (exception === 'abort') {
                                msg = 'Ajax request aborted.';
                            } else {
                                msg = 'Uncaught Error.\n' + jqXHR.responseText;
                            }

                            alert(msg);
                        }
                    });
                });
            });
        </script>
    </head>

    <div class="loading">Loading&#8230;
        <br/>
        <span id="loadingMessage"></span>
    </div>

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
                <div id="token" style="display:none">${token}</div>
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
                            <th></th> 
                        </tr>
                    </thead>
                    <tbody>
                        ${projectsList}
                    </tbody>
                </table>              

            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="tasksModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Project Tasks</h4>
                </div>
                <div class="modal-body">

                    <table class="table table-bordered table-striped table-condensed cf">
                        <tr>
                            <td>
                                Id
                            </td>
                            <td>
                                <span id="id"></span>
                            </td>				  
                        </tr>
                        <tr>
                            <td>
                                Title
                            </td>
                            <td>
                                <span id="title"></span>
                            </td>				  
                        </tr>
                        <tr>
                            <td>
                                Due date
                            </td>
                            <td>
                                <span id="due_date"></span>
                            </td>				  
                        </tr>

                        <tr>
                            <td>
                                Estimated hours
                            </td>
                            <td>
                                <span id="estimated_hours"></span>
                            </td>				  
                        </tr>				   
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

</body>
</html>