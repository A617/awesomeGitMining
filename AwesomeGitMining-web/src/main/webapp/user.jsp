<%--
  Created by IntelliJ IDEA.
  User: fwtgm
  Date: 2016/5/3
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
    <link href="<img alt="image" src="<c:url value="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="file:///D|/模板/cpts_311_nc/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="My Skills Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <script src="file:///D|/模板/cpts_311_nc/js/jquery-1.11.1.min.js"></script>
    <!---- start-smoth-scrolling---->
    <script type="text/javascript" src="file:///D|/模板/cpts_311_nc/js/move-top.js"></script>
    <script type="text/javascript" src="file:///D|/模板/cpts_311_nc/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
            });
        });
    </script>
    <!---End-smoth-scrolling---->

</head>
<body>
<!--start-header-section-->
<div class="header-section">
    <div class="continer">
        <img name="imageTuxiang" src="" width="197" height="158" alt="">
        <h1>Username<span>!</span></h1>
        <p>profile</p>
        <a href="#contact" class="scroll top"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
    </div>
</div>
<!--end header-section-->
<!--start-study-section-->
<div class="study-section">
    <div class="container">
        <div class="study-grids">
            <div class="col-md-6 study-grid">
                <h3>Infomation..<span>!</span></h3>
                <div class="study1">
                    <p>Name:
                        <label></label></p>
                    <p>Address:</p>
                    <p>Company:</p>
                    <p>Email:
                        <label></label></p>
                    <p>Blog:</p>
                    <p>url:</p>
                </div>
            </div>
            <div class="col-md-6 study-grid">
                <h3>skills..<span>!</span></h3>
                <div class="study2">
                    <h4>hot</h4>
                    <div class="progress">
                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                            <span class="sr-only">40% Complete (success)</span>
                        </div>
                    </div>
                    <h4>xxx</h4>
                    <div class="progress">
                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                            <span class="sr-only">20% Complete</span>
                        </div>
                    </div>
                    <h4>hot</h4>
                    <div class="progress">
                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width: 30%">
                            <span class="sr-only">30% Complete (success)</span>
                        </div>
                    </div>
                    <h4>xxx</h4>
                    <div class="progress">
                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                            <span class="sr-only">60% Complete (warning)</span>
                        </div>
                    </div>
                    <h4>yyy</h4>
                    <div class="progress">
                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                            <span class="sr-only">80% Complete (danger)</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--end study-section--><!--start-services-section-->
<div class="service-section" id="service">
    <div class="container">

        <div class="service-grids">

        </div>
    </div>
    <!--end services-section-->
    <!--start-social-section-->
    <div class="social-icons">
        <h3>Social Network</h3>
        <div class="container">
            <div class="col-md-3 face">
                <p><i class="facebook"> </i> 733k</p>
                <h4>followings</h4>
            </div>
            <div class="col-md-3 face">
                <p><i class="twitter"> </i> 620k</p>
                <h4>Followers</h4>
            </div>
            <div class="col-md-3 face">
                <p><i class="google"> </i> 412k</p>
                <h4>subscributors</h4>
            </div>
            <div class="col-md-3 face">
                <p><i class="beh"> </i> 322k</p>
                <h4>contributors</h4>
            </div>
            <div class="col-md-3 face">
                <p><i class="beh"> </i> 322k</p>
                <h4>collabrators</h4>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
    <!--end-social-section-->
    <!--start-contact-section-->
    <div class="contact-section" id="contact">
        <div class="container">
            <h3>projects</h3>
            <div class="contact-details">
                <form>
                    <div class="col-md-6 contact-left">

                    </div>

                    <table width="471" height="106"> <tr>

                        <td>创建过的项目</td>

                        <td>贡献过的项目</td>

                    </tr>

                        <tr>

                            <td>XXX</td>

                            <td>XXX</td>

                        </tr>
                    </table>

                    <div class="clearfix">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d114829.39166857985!2d-80.19154352520549!3d25.92148032545394!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x88d9b2eec0a4b145%3A0x6fb7ea318103f481!2sCollins+Ave%2C+Sunny+Isles+Beach%2C+FL+33160%2C+USA!5e0!3m2!1sen!2sin!4v1436081255308"></iframe>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--end-contact-section-->
    <!--start-map-section-->
    <div class="google-map"></div>
    <!--end-map-section-->
    <!--start-footer-section-->
    <div class="footer-section">
        <div class="container">
            <div class="footer-top">
                <p>Copyright &copy; 2016.Company name All rights reserved 2A617<a href="http://www.cssmoban.com/" title="网页模板" target="_blank"></a></p>
            </div>
            <script type="text/javascript">
                $(document).ready(function() {
                    /*
                     var defaults = {
                     containerID: 'toTop', // fading element id
                     containerHoverID: 'toTopHover', // fading element hover id
                     scrollSpeed: 1200,
                     easingType: 'linear'
                     };
                     */

                    $().UItoTop({ easingType: 'easeOutQuart' });

                });
            </script>
            <a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
        </div>
    </div>
    <!--end-footer-section-->

</div>
</body>
</html>